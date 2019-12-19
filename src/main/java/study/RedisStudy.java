package study;

import com.example.springboot.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName RedisStudy
 * @Author zhaofu
 * @Date 2019/12/17
 * @Version V1.0
 **/
public class RedisStudy {

/**
Redis缓存原理

Redis其实就是说把表中经常访问的记录放在了Redis中，然后用户查询时先去查询Redis再去查询MySQL，确实实现了读写分离，也就是Redis只做读操作。
由于缓存在内存中，所以查询会很快。

如何确定在redis查询还是Mysql查询：对于一个sql语句格式的数据请求，首先计算该语句的MD5并据此得到结果集标识符，然后利用该标识符在Redis中查找该结果集。
 注意，结果集中的每一行都有一个相应的键，这些键都存储在一个Redis集合结构中。
 如果Redis中不存在这样一个集合，说明要找的结果集不在Redis中，所以需要执行相应的sql语句，
 在Mysql中查询到相应的结果集，然后按照上面所说的办法把结果集中的每一行以字符串或哈希的形式存入Redis。

4 其他缓存策略
Redis+MySQL实现了读写分离，但是MySQL本身也可以读写分离，也就是分布式。
 另外，实现缓存的方式也有很多：MYSQL自带的缓存；DAO框架的缓存等。

方法一：直接用Mysql
这种事情首先Mysql里面就有缓存，实现了类似的功能，如果需要缓存的东西很多，你可以把缓存的内存设置大一点。
 这样的好处就是你不用自己去控制缓存的失效，确保数据一致性。
MySQL查询会造成内存碎片等问题，请参考：http://blog.csdn.net/songjianyue12345/article/details/77340168

方法二：启用DAO框架的缓存
比如Mybatis、Hibernate都是可以直接开启二级缓存，一般是用ehcache作为实现，你只要配置一下就行，无需额外操作。

方法三：自己实现
用AOP去在Dao层做一个切面，把调用的“类名+方法名+参数”作为key，查询结果作为value，每次调用去看一下是否已经缓存了，如果没有再去调用Dao的实现类。

5 选择Memcatched还是Redis
1.性能上：
性能上都很出色，具体到细节，由于Redis只使用单核，而Memcached可以使用多核，所以平均每一个核上Redis在存储小数据时比
Memcached性能更高。而在100k以上的数据中，Memcached性能要高于Redis，虽然Redis最近也在存储大数据的性能上进行优化，但是比起 Memcached，还是稍有逊色。

2.内存空间和数据量大小：
MemCached可以修改最大内存，采用LRU算法。Redis增加了VM的特性，突破了物理内存的限制。

3.操作便利上：
MemCached数据结构单一，仅用来缓存数据，而Redis支持更加丰富的数据类型，也可以在服务器端直接对数据进行丰富的操作,这样可以减少网络IO次数和数据体积。

4.可靠性上：
MemCached不支持数据持久化，断电或重启后数据消失，但其稳定性是有保证的。Redis支持数据持久化和数据恢复，允许单点故障，但是同时也会付出性能的代价。

5.应用场景：
Memcached：动态系统中减轻数据库负载，提升性能；做缓存，适合多读少写，大数据量的情况（如人人网大量查询用户信息、好友信息、文章信息等）。
Redis：适用于对读写效率要求都很高，数据处理业务复杂和对安全性要求较高的系统（如新浪微博的计数和微博发布部分系统，对数据安全性、读写要求都很高）。
请参考：https://www.cnblogs.com/work115/p/5584646.html

6 如何使用Redis做缓存
用Redis作Mysql数据库缓存，必须解决2个问题。
 首先，应该确定用何种数据结构存储来自Mysql的数据；在确定数据结构之后，还要考虑用什么标识作为该数据结构的键。
 直观上看，Mysql中的数据都是按表存储的；更微观地看，这些表都是按行存储的。
 每执行一次select查询，Mysql都会返回一个结果集，这个结果集由若干行组成。
 所以，一个自然而然的想法就是在Redis中找到一种对应于Mysql行的数据结构。
 Redis中提供了五种基本数据结构，即字符串（string）、列表（list）、哈希（hash）、集合（set）和有序集合（sorted set）。
 经过调研，发现适合存储行的数据结构有两种，即string和hash。

要把Mysql的行数据存入string，首先需要对行数据进行格式化。
 事实上，结果集的每一行都可以看做若干由字段名和其对应值组成的键值对集合。
 这种键值对结构很容易让我们想起Json格式。因此，这里选用Json格式作为结果集每一行的格式化模板。
 根据这一想法，我们可以实现将结果集格式化为若干Json对象，并将Json对象转化为字符串存入Redis的代码。
请参考：https://www.cnblogs.com/jinjiangongzuoshi/p/5240300.html


         */
//   Redis 命令

//1. Redis 键(key)

//   private RedisTemplate<String, Object> redisTemplate;

     private Jedis jedis = new Jedis("localhost");
     private List<JedisShardInfo> shards = new ArrayList<>();
     private ShardedJedis shardedJedis = new ShardedJedis(shards);
        @Autowired
        private RedisUtil redisUtil;


        public static void main(String[] args) {
                KeyOperate();
        }
private static void KeyOperate(){
        Jedis jedis = new Jedis("localhost");
        List<JedisShardInfo> shards = new ArrayList<>();
        ShardedJedis shardedJedis = new ShardedJedis(shards);
        System.out.println("======================key==========================");
       // 清空数据
       System.out.println("清空库中所有数据："+jedis.flushDB());
       // 判断key否存在
       System.out.println("判断key999键是否存在："+shardedJedis.exists("key999"));
       System.out.println("新增key001,value001键值对："+shardedJedis.set("key001", "value001"));
       System.out.println("判断key001是否存在："+shardedJedis.exists("key001"));
       // 输出系统中所有的key
       System.out.println("新增key002,value002键值对："+shardedJedis.set("key002", "value002"));
       System.out.println("系统中所有键如下：");
       Set<String> keys = jedis.keys("*");
       Iterator<String> it=keys.iterator() ;
       while(it.hasNext()){
           String key = it.next();
           System.out.println(key);
       }
       // 删除某个key,若key不存在，则忽略该命令。
       System.out.println("系统中删除key002: "+jedis.del("key002"));
       System.out.println("判断key002是否存在："+shardedJedis.exists("key002"));
       // 设置 key001的过期时间
       System.out.println("设置 key001的过期时间为5秒:"+jedis.expire("key001", 5));
       try{
           Thread.sleep(2000);
       }
       catch (InterruptedException e){
       }
       // 查看某个key的剩余生存时间,单位【秒】.永久生存或者不存在的都返回-1
       System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
       // 移除某个key的生存时间
       System.out.println("移除key001的生存时间："+jedis.persist("key001"));
       System.out.println("查看key001的剩余生存时间："+jedis.ttl("key001"));
       // 查看key所储存的值的类型
       System.out.println("查看key所储存的值的类型："+jedis.type("key001"));

//         一些其他方法：1、修改键名：jedis.rename("key6", "key0");
//                     2、将当前db的key移动到给定的db当中：jedis.move("foo", 1)

}
/*
运行结果：
        ======================key==========================
        清空库中所有数据：OK
        判断key999键是否存在：false
        新增key001,value001键值对：OK
        判断key001是否存在：true
        新增key002,value002键值对：OK
        系统中所有键如下：
        key002
        key001
        系统中删除key002: 1
        判断key002是否存在：false
        设置 key001的过期时间为5秒:1
        查看key001的剩余生存时间：3
        移除key001的生存时间：1
        查看key001的剩余生存时间：-1
        查看key所储存的值的类型：string
*/

//        2. Redis 字符串(String)

private void StringOperate()
        {
        System.out.println("======================String_1==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());

        System.out.println("=============增=============");
        jedis.set("key001","value001");
        jedis.set("key002","value002");
        jedis.set("key003","value003");
        System.out.println("已新增的3个键值对如下：");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));

        System.out.println("=============删=============");
        System.out.println("删除key003键值对："+jedis.del("key003"));
        System.out.println("获取key003键对应的值："+jedis.get("key003"));

        System.out.println("=============改=============");
        //1、直接覆盖原来的数据
        System.out.println("直接覆盖key001原来的数据："+jedis.set("key001","value001-update"));
        System.out.println("获取key001对应的新值："+jedis.get("key001"));
        //2、直接覆盖原来的数据
        System.out.println("在key002原来值后面追加："+jedis.append("key002","+appendString"));
        System.out.println("获取key002对应的新值"+jedis.get("key002"));

        System.out.println("=============增，删，查（多个）=============");
//
//          mset,mget同时新增，修改，查询多个键值对
//          等价于：
//          jedis.set("name","ssss");
//          jedis.set("jarorwar","xxxx");
//
        System.out.println("一次性新增key201,key202,key203,key204及其对应值："+jedis.mset("key201","value201",
        "key202","value202","key203","value203","key204","value204"));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
        jedis.mget("key201","key202","key203","key204"));
        System.out.println("一次性删除key201,key202："+jedis.del(new String[]{"key201", "key202"}));
        System.out.println("一次性获取key201,key202,key203,key204各自对应的值："+
        jedis.mget("key201","key202","key203","key204"));
        System.out.println();


        //jedis具备的功能shardedJedis中也可直接使用，下面测试一些前面没用过的方法
        System.out.println("======================String_2==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());

        System.out.println("=============新增键值对时防止覆盖原先值=============");
        System.out.println("原先key301不存在时，新增key301："+shardedJedis.setnx("key301", "value301"));
        System.out.println("原先key302不存在时，新增key302："+shardedJedis.setnx("key302", "value302"));
        System.out.println("当key302存在时，尝试新增key302："+shardedJedis.setnx("key302", "value302_new"));
        System.out.println("获取key301对应的值："+shardedJedis.get("key301"));
        System.out.println("获取key302对应的值："+shardedJedis.get("key302"));

        System.out.println("=============超过有效期键值对被删除=============");
        // 设置key的有效期，并存储数据
        System.out.println("新增key303，并指定过期时间为2秒"+shardedJedis.setex("key303", 2, "key303-2second"));
        System.out.println("获取key303对应的值："+shardedJedis.get("key303"));
        try{
        Thread.sleep(3000);
        }
        catch (InterruptedException e){
        }
        System.out.println("3秒之后，获取key303对应的值："+shardedJedis.get("key303"));

        System.out.println("=============获取原值，更新为新值一步完成=============");
        System.out.println("key302原值："+shardedJedis.getSet("key302", "value302-after-getset"));
        System.out.println("key302新值："+shardedJedis.get("key302"));

        System.out.println("=============获取子串=============");
        System.out.println("获取key302对应值中的子串："+shardedJedis.getrange("key302", 5, 7));
        }

/*
        运行结果：
        ======================String_1==========================
        清空库中所有数据：OK
        =============增=============
        已新增的3个键值对如下：
        value001
        value002
        value003
        =============删=============
        删除key003键值对：1
        获取key003键对应的值：null
        =============改=============
        直接覆盖key001原来的数据：OK
        获取key001对应的新值：value001-update
        在key002原来值后面追加：21
        获取key002对应的新值value002+appendString
        =============增，删，查（多个）=============
        一次性新增key201,key202,key203,key204及其对应值：OK
        一次性获取key201,key202,key203,key204各自对应的值：[value201, value202, value203, value204]
        一次性删除key201,key202：2
        一次性获取key201,key202,key203,key204各自对应的值：[null, null, value203, value204]
        ======================String_2==========================
        清空库中所有数据：OK
        =============新增键值对时防止覆盖原先值=============
        原先key301不存在时，新增key301：1
        原先key302不存在时，新增key302：1
        当key302存在时，尝试新增key302：0
        获取key301对应的值：value301
        获取key302对应的值：value302
        =============超过有效期键值对被删除=============
        新增key303，并指定过期时间为2秒OK
        获取key303对应的值：key303-2second
        3秒之后，获取key303对应的值：null
        =============获取原值，更新为新值一步完成=============
        key302原值：value302
        key302新值：value302-after-getset
        =============获取子串=============
        获取key302对应值中的子串：302
*/

//        3. Redis 列表(List)

private void ListOperate()
        {
        System.out.println("======================list==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());

        System.out.println("=============增=============");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "ArrayList");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "LinkedList");
        shardedJedis.lpush("stringlists", "MapList");
        shardedJedis.lpush("stringlists", "SerialList");
        shardedJedis.lpush("stringlists", "HashList");
        shardedJedis.lpush("numberlists", "3");
        shardedJedis.lpush("numberlists", "1");
        shardedJedis.lpush("numberlists", "5");
        shardedJedis.lpush("numberlists", "2");
        System.out.println("所有元素-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        System.out.println("所有元素-numberlists："+shardedJedis.lrange("numberlists", 0, -1));

        System.out.println("=============删=============");
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        System.out.println("成功删除指定元素个数-stringlists："+shardedJedis.lrem("stringlists", 2, "vector"));
        System.out.println("删除指定元素之后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        // 删除区间以外的数据
        System.out.println("删除下标0-3区间之外的元素："+shardedJedis.ltrim("stringlists", 0, 3));
        System.out.println("删除指定区间之外元素后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        // 列表元素出栈
        System.out.println("出栈元素："+shardedJedis.lpop("stringlists"));
        System.out.println("元素出栈后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));

        System.out.println("=============改=============");
        // 修改列表中指定下标的值
        shardedJedis.lset("stringlists", 0, "hello list!");
        System.out.println("下标为0的值修改后-stringlists："+shardedJedis.lrange("stringlists", 0, -1));
        System.out.println("=============查=============");
        // 数组长度
        System.out.println("长度-stringlists："+shardedJedis.llen("stringlists"));
        System.out.println("长度-numberlists："+shardedJedis.llen("numberlists"));
        // 排序

//          list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
//          会出现"ERR One or more scores can't be converted into double"

        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(0, 3);
        System.out.println("返回排序后的结果-stringlists："+shardedJedis.sort("stringlists",sortingParameters));
        System.out.println("返回排序后的结果-numberlists："+shardedJedis.sort("numberlists"));
        // 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
        System.out.println("子串-第二个开始到结束："+shardedJedis.lrange("stringlists", 1, -1));
        // 获取列表指定下标的值
        System.out.println("获取下标为2的元素："+shardedJedis.lindex("stringlists", 2)+"\n");
        }

/*
        运行结果：
        ======================list==========================
        清空库中所有数据：OK
        =============增=============
        所有元素-stringlists：[HashList, SerialList, MapList, LinkedList, vector, vector, ArrayList, vector]
        所有元素-numberlists：[2, 5, 1, 3]
        =============删=============
        成功删除指定元素个数-stringlists：2
        删除指定元素之后-stringlists：[HashList, SerialList, MapList, LinkedList, ArrayList, vector]
        删除下标0-3区间之外的元素：OK
        删除指定区间之外元素后-stringlists：[HashList, SerialList, MapList, LinkedList]
        出栈元素：HashList
        元素出栈后-stringlists：[SerialList, MapList, LinkedList]
        =============改=============
        下标为0的值修改后-stringlists：[hello list!, MapList, LinkedList]
        =============查=============
        长度-stringlists：3
        长度-numberlists：4
        返回排序后的结果-stringlists：[LinkedList, MapList, hello list!]
        返回排序后的结果-numberlists：[1, 2, 3, 5]
        子串-第二个开始到结束：[MapList, LinkedList]
        获取下标为2的元素：LinkedList

*/

//        4. Redis 集合(Set)

private void SetOperate()
        {

        System.out.println("======================set==========================");
        // 清空数据
        System.out.println("清空库中所有数据："+jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("向sets集合中加入元素element001："+jedis.sadd("sets", "element001"));
        System.out.println("向sets集合中加入元素element002："+jedis.sadd("sets", "element002"));
        System.out.println("向sets集合中加入元素element003："+jedis.sadd("sets", "element003"));
        System.out.println("向sets集合中加入元素element004："+jedis.sadd("sets", "element004"));
        System.out.println("查看sets集合中的所有元素:"+jedis.smembers("sets"));
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("集合sets中删除元素element003："+jedis.srem("sets", "element003"));
        System.out.println("查看sets集合中的所有元素:"+jedis.smembers("sets"));
//       System.out.println("sets集合中任意位置的元素出栈："+jedis.spop("sets"));//注：出栈元素位置居然不定？--无实际意义
//       System.out.println("查看sets集合中的所有元素:"+jedis.smembers("sets"));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("判断element001是否在集合sets中："+jedis.sismember("sets", "element001"));
        System.out.println("循环查询获取sets中的每个元素：");
        Set<String> set = jedis.smembers("sets");
        Iterator<String> it=set.iterator() ;
        while(it.hasNext()){
        Object obj=it.next();
        System.out.println(obj);
        }
        System.out.println();

        System.out.println("=============集合运算=============");
        System.out.println("sets1中添加元素element001："+jedis.sadd("sets1", "element001"));
        System.out.println("sets1中添加元素element002："+jedis.sadd("sets1", "element002"));
        System.out.println("sets1中添加元素element003："+jedis.sadd("sets1", "element003"));
        System.out.println("sets1中添加元素element002："+jedis.sadd("sets2", "element002"));
        System.out.println("sets1中添加元素element003："+jedis.sadd("sets2", "element003"));
        System.out.println("sets1中添加元素element004："+jedis.sadd("sets2", "element004"));
        System.out.println("查看sets1集合中的所有元素:"+jedis.smembers("sets1"));
        System.out.println("查看sets2集合中的所有元素:"+jedis.smembers("sets2"));
        System.out.println("sets1和sets2交集："+jedis.sinter("sets1", "sets2"));
        System.out.println("sets1和sets2并集："+jedis.sunion("sets1", "sets2"));
        System.out.println("sets1和sets2差集："+jedis.sdiff("sets1", "sets2"));//差集：set1中有，set2中没有的元素
        }

/*
        运行结果：
        ======================set==========================
        清空库中所有数据：OK
        =============增=============
        向sets集合中加入元素element001：1
        向sets集合中加入元素element002：1
        向sets集合中加入元素element003：1
        向sets集合中加入元素element004：1
        查看sets集合中的所有元素:[element001, element002, element003, element004]
        =============删=============
        集合sets中删除元素element003：1
        查看sets集合中的所有元素:[element001, element002, element004]
        =============改=============
        =============查=============
        判断element001是否在集合sets中：true
        循环查询获取sets中的每个元素：
        element001
        element002
        element004
        =============集合运算=============
        sets1中添加元素element001：1
        sets1中添加元素element002：1
        sets1中添加元素element003：1
        sets1中添加元素element002：1
        sets1中添加元素element003：1
        sets1中添加元素element004：1
        查看sets1集合中的所有元素:[element001, element002, element003]
        查看sets2集合中的所有元素:[element002, element003, element004]
        sets1和sets2交集：[element002, element003]
        sets1和sets2并集：[element001, element002, element003, element004]
        sets1和sets2差集：[element001]
**/

//        5. Redis 有序集合(sorted set)

private void SortedSetOperate()
        {
        System.out.println("======================zset==========================");
        // 清空数据
        System.out.println(jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("zset中添加元素element001："+shardedJedis.zadd("zset", 7.0, "element001"));
        System.out.println("zset中添加元素element002："+shardedJedis.zadd("zset", 8.0, "element002"));
        System.out.println("zset中添加元素element003："+shardedJedis.zadd("zset", 2.0, "element003"));
        System.out.println("zset中添加元素element004："+shardedJedis.zadd("zset", 3.0, "element004"));
        System.out.println("zset集合中的所有元素："+shardedJedis.zrange("zset", 0, -1));//按照权重值排序
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("zset中删除元素element002："+shardedJedis.zrem("zset", "element002"));
        System.out.println("zset集合中的所有元素："+shardedJedis.zrange("zset", 0, -1));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("统计zset集合中的元素中个数："+shardedJedis.zcard("zset"));
        System.out.println("统计zset集合中权重某个范围内（1.0——5.0），元素的个数："+shardedJedis.zcount("zset", 1.0, 5.0));
        System.out.println("查看zset集合中element004的权重："+shardedJedis.zscore("zset", "element004"));
        System.out.println("查看下标1到2范围内的元素值："+shardedJedis.zrange("zset", 1, 2));

        }
/*
        运行结果：

        ======================zset==========================
        OK
        =============增=============
        zset中添加元素element001：1
        zset中添加元素element002：1
        zset中添加元素element003：1
        zset中添加元素element004：1
        zset集合中的所有元素：[element003, element004, element001, element002]
        =============删=============
        zset中删除元素element002：1
        zset集合中的所有元素：[element003, element004, element001]
        =============改=============
        =============查=============
        统计zset集合中的元素中个数：3
        统计zset集合中权重某个范围内（1.0——5.0），元素的个数：2
        查看zset集合中element004的权重：3.0
        查看下标1到2范围内的元素值：[element004, element001]

*/

//        6. Redis 哈希(Hash)

private void HashOperate()
        {
        System.out.println("======================hash==========================");
        //清空数据
        System.out.println(jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("hashs中添加key001和value001键值对："+shardedJedis.hset("hashs", "key001", "value001"));
        System.out.println("hashs中添加key002和value002键值对："+shardedJedis.hset("hashs", "key002", "value002"));
        System.out.println("hashs中添加key003和value003键值对："+shardedJedis.hset("hashs", "key003", "value003"));
        System.out.println("新增key004和4的整型键值对："+shardedJedis.hincrBy("hashs", "key004", 4l));
        System.out.println("hashs中的所有值："+shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("hashs中删除key002键值对："+shardedJedis.hdel("hashs", "key002"));
        System.out.println("hashs中的所有值："+shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println("key004整型键值的值增加100："+shardedJedis.hincrBy("hashs", "key004", 100l));
        System.out.println("hashs中的所有值："+shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("判断key003是否存在："+shardedJedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值："+shardedJedis.hget("hashs", "key004"));
        System.out.println("批量获取key001和key003对应的值："+shardedJedis.hmget("hashs", "key001", "key003"));
        System.out.println("获取hashs中所有的key："+shardedJedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value："+shardedJedis.hvals("hashs"));
        System.out.println();

        }
/*
        运行结果：
        ======================hash==========================
        OK
        =============增=============
        hashs中添加key001和value001键值对：1
        hashs中添加key002和value002键值对：1
        hashs中添加key003和value003键值对：1
        新增key004和4的整型键值对：4
        hashs中的所有值：[value001, value002, value003, 4]
        =============删=============
        hashs中删除key002键值对：1
        hashs中的所有值：[value001, value003, 4]
        =============改=============
        key004整型键值的值增加100：104
        hashs中的所有值：[value001, value003, 104]
        =============查=============
        判断key003是否存在：true
        获取key004对应的值：104
        批量获取key001和key003对应的值：[value001, value003]
        获取hashs中所有的key：[key004, key003, key001]
        获取hashs中所有的value：[value001, value003, 104]
    */

/**三、Redis 常用命令集

        1）连接操作命令

        quit：关闭连接（connection）

        auth：简单密码认证

        help cmd： 查看cmd帮助，例如：help quit

        2）持久化

        save：将数据同步保存到磁盘

        bgsave：将数据异步保存到磁盘

        lastsave：返回上次成功将数据保存到磁盘的Unix时戳

        shundown：将数据同步保存到磁盘，然后关闭服务


        3）远程服务控制

        info：提供服务器的信息和统计

        monitor：实时转储收到的请求

        slaveof：改变复制策略设置

        config：在运行时配置Redis服务器


        4）对value操作的命令

        exists(key)：确认一个key是否存在

        del(key)：删除一个key

        type(key)：返回值的类型

        keys(pattern)：返回满足给定pattern的所有key

        randomkey：随机返回key空间的一个

        keyrename(oldname, newname)：重命名key

        dbsize：返回当前数据库中key的数目

        expire：设定一个key的活动时间（s）

        ttl：获得一个key的活动时间

        select(index)：按索引查询

        move(key, dbindex)：移动当前数据库中的key到dbindex数据库

        flushdb：删除当前选择数据库中的所有key

        flushall：删除所有数据库中的所有key

        5）String

        set(key, value)：给数据库中名称为key的string赋予值value

        get(key)：返回数据库中名称为key的string的value

        getset(key, value)：给名称为key的string赋予上一次的value

        mget(key1, key2,…, key N)：返回库中多个string的value

        setnx(key, value)：添加string，名称为key，值为value

        setex(key, time, value)：向库中添加string，设定过期时间time

        mset(key N, value N)：批量设置多个string的值

        msetnx(key N, value N)：如果所有名称为key i的string都不存在

        incr(key)：名称为key的string增1操作

        incrby(key, integer)：名称为key的string增加integer

        decr(key)：名称为key的string减1操作

        decrby(key, integer)：名称为key的string减少integer

        append(key, value)：名称为key的string的值附加value

        substr(key, start, end)：返回名称为key的string的value的子串

        6）List

        rpush(key, value)：在名称为key的list尾添加一个值为value的元素

        lpush(key, value)：在名称为key的list头添加一个值为value的 元素

        llen(key)：返回名称为key的list的长度

        lrange(key, start, end)：返回名称为key的list中start至end之间的元素

        ltrim(key, start, end)：截取名称为key的list

        lindex(key, index)：返回名称为key的list中index位置的元素

        lset(key, index, value)：给名称为key的list中index位置的元素赋值

        lrem(key, count, value)：删除count个key的list中值为value的元素

        lpop(key)：返回并删除名称为key的list中的首元素

        rpop(key)：返回并删除名称为key的list中的尾元素

        blpop(key1, key2,… key N, timeout)：lpop命令的block版本。

        brpop(key1, key2,… key N, timeout)：rpop的block版本。

        rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部

        7）Set

        sadd(key, member)：向名称为key的set中添加元素member

        srem(key, member) ：删除名称为key的set中的元素member

        spop(key) ：随机返回并删除名称为key的set中一个元素

        smove(srckey, dstkey, member) ：移到集合元素

        scard(key) ：返回名称为key的set的基数

        sismember(key, member) ：member是否是名称为key的set的元素

        sinter(key1, key2,…key N) ：求交集

        sinterstore(dstkey, (keys)) ：求交集并将交集保存到dstkey的集合

        sunion(key1, (keys)) ：求并集

        sunionstore(dstkey, (keys)) ：求并集并将并集保存到dstkey的集合

        sdiff(key1, (keys)) ：求差集

        sdiffstore(dstkey, (keys)) ：求差集并将差集保存到dstkey的集合

        smembers(key) ：返回名称为key的set的所有元素

        srandmember(key) ：随机返回名称为key的set的一个元素

        8）Hash

        hset(key, field, value)：向名称为key的hash中添加元素field

        hget(key, field)：返回名称为key的hash中field对应的value

        hmget(key, (fields))：返回名称为key的hash中field i对应的value

        hmset(key, (fields))：向名称为key的hash中添加元素field

        hincrby(key, field, integer)：将名称为key的hash中field的value增加integer

        hexists(key, field)：名称为key的hash中是否存在键为field的域

        hdel(key, field)：删除名称为key的hash中键为field的域

        hlen(key)：返回名称为key的hash中元素个数

        hkeys(key)：返回名称为key的hash中所有键

        hvals(key)：返回名称为key的hash中所有键对应的value

        hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value

        9） Redis 发布订阅命令

        PSUBSCRIBE pattern [pattern ...] ：订阅一个或多个符合给定模式的频道。

        PUBSUB subcommand [argument [argument ...]] ：查看订阅与发布系统状态。

        PUBLISH channel message ：将信息发送到指定的频道。

        PUNSUBSCRIBE [pattern [pattern ...]] ：退订所有给定模式的频道。

        SUBSCRIBE channel [channel ...] ：订阅给定的一个或多个频道的信息。

        UNSUBSCRIBE [channel [channel ...]] ：指退订给定的频道。

        10） Redis 事务命令

        DISCARD ：取消事务，放弃执行事务块内的所有命令。

        EXEC ：执行所有事务块内的命令。

        MULTI ：标记一个事务块的开始。

        UNWATCH ：取消 WATCH 命令对所有 key 的监视。

        WATCH key [key ...] ：监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。

        11） 查看keys个数

        keys *      // 查看所有keys

        keys prefix_*     // 查看前缀为"prefix_"的所有keys

        12） 清空数据库

        flushdb   // 清除当前数据库的所有keys

        flushall    // 清除所有数据库的所有keys
    *
    * */
}