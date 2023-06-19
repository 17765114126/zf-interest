package com.example.springboot.utils.redis;

import org.springframework.data.geo.Metric;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    private RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private static StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate redisTemp) {
        stringRedisTemplate = redisTemp;
    }
    //=============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, timeUnit);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * lua 脚本
     */
    public static final String SETNX_SCRIPT = "return redis.call('setnx',KEYS[1], ARGV[1])";

    /**
     * redis实现分布式锁
     *
     * @param key
     * @return
     */
    public boolean setNx(String key, long time) {
        //自定义脚本
        DefaultRedisScript<List> script = new DefaultRedisScript<>(SETNX_SCRIPT, List.class);
        //执行脚本,传入参数,由于value没啥用,这里随便写死的"1"
        List<Long> rst = redisTemplate.execute(script, Collections.singletonList(key), "1");
        //返回1,表示设置成功,拿到锁
        if (rst.get(0) == 1) {
            System.out.println(key + "成功拿到锁");
            //设置过期时间
            expire(key, time);
            System.out.println(key + "已成功设置过期时间:" + time + " 秒");
            return true;
        } else {
            long expire = getExpire(key);
            System.out.println(key + "未拿到到锁,还有" + expire + "释放");
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    //============================String=============================

    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key 键
     * @param by  要增加几(大于0)
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param by  要减少几(小于0)
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    //================================Map=================================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    //============================set=============================

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sGet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values) {
        try {
            return redisTemplate.opsForSet().add(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public long sGetSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public long setRemove(String key, Object... values) {
        try {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    //===============================list=================================

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public long lGetListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lGetIndex(String key, long index) {
        try {
            return redisTemplate.opsForList().index(key, index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, Object value, long time) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) expire(key, time);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public boolean zAdd(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<Object> zRangeByScore(String key, double start, double end) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean zDecrScore(String key, Object value) {
        try {
            redisTemplate.opsForZSet().incrementScore(key, value, -1d);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean zIncrScore(String key, Object value) {
        try {
            redisTemplate.opsForZSet().incrementScore(key, value, 1d);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    //===============================Geo=================================

    /**
     * @param key    key
     * @param point  经纬度
     * @param member 成员
     * @return Long 返回影响的行
     * @Title: geoAdd
     * @Description: TODO(添加geo)
     */
    public static Long geoAdd(String key, Point point, String member) {
        return stringRedisTemplate.opsForGeo().add(key, point, member);
    }

    /**
     * @param key 
     * @param members 成员
     * @return Long 返回影响的行
     * @Title: geoRemove
     * @Description: TODO(删除成员)
     */
    public static Long geoRemove(String key, String... members) {
        return stringRedisTemplate.opsForGeo().geoRemove(key, members);
    }

    /**
     * @param key     key
     * @param members 成员
     * @return List<Point>
     * @Title: geoPos
     * @Description: TODO(查询地址的经纬度)
     */
    public static List<Point> geoPos(String key, String... members) {
        return stringRedisTemplate.opsForGeo().geoPos(key, members);
    }

    /**
     * @param key
     * @param members
     * @return List<String>
     * @Title: geoHash
     * @Description: TODO(查询位置的geohash)
     */
    public static List<String> geoHash(String key, String... members) {
        return stringRedisTemplate.opsForGeo().geoHash(key, members);
    }

    /**
     * @param key     key
     * @param member1 成员1
     * @param member2 成员2
     * @param metric  单位
     * @return Double 距离
     * @Title: geoDist
     * @Description: TODO(查询2位置距离)
     */
    public static Double geoDist(String key, String member1, String member2, Metric metric) {
        return stringRedisTemplate.opsForGeo().geoDist(key, member1, member2, metric).getValue();
    }

    /**
     * @Title: geoRadius
     * @Description: TODO(查询附近坐标地址)
     * @param key key
     * @param center 中心坐标
     * @param radius 半径
     * @param metric 半径单位
     * @param direction 排序
     * @return List<String>
     * @throws
     */
//    public static List<GeoRadiusDto> geoRadius(String key, Point center, Double radius, Metric metric, Direction direction) {
//        List<GeoRadiusDto> radiusDtos = new ArrayList<GeoRadiusDto>();
//        Distance distance = new Distance(radius, metric);
//        Circle within = new Circle(center, distance);
//        GeoRadiusCommandArgs args = null;
//        if(direction.isAscending()){
//            args = GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().includeCoordinates();
//        }
//        if(direction.isDescending()){
//            args = GeoRadiusCommandArgs.newGeoRadiusArgs().sortDescending().includeCoordinates();
//        }
//        GeoResults<GeoLocation<String>> geoResults = stringRedisTemplate.opsForGeo().geoRadius(key, within, args);
//        List<GeoResult<GeoLocation<String>>> geoResultList = geoResults.getContent();
//        for(GeoResult<GeoLocation<String>> geoResult:geoResultList){
//            String name = geoResult.getContent().getName();
//            Point point = geoResult.getContent().getPoint();
//            GeoRadiusDto radiusDto = new GeoRadiusDto();
//            radiusDto.setKey(key);
//            radiusDto.setMember(name);
//            radiusDto.setX(point.getX());
//            radiusDto.setY(point.getY());
//            radiusDtos.add(radiusDto);
//        }
//        return radiusDtos;
//    }

    /**
     * @Title: geoRadiusByMember
     * @Description: TODO(根据成员查询附近点)
     * @param key
     * @param member 成员
     * @param radius 半径
     * @param metric 半径单位
     * @param direction 排序
     * @return List<GeoRadiusDto>
     */
//    public static List<GeoRadiusDto> geoRadiusByMember(String key, String member, Double radius, Metric metric, Direction direction) {
//        List<GeoRadiusDto> radiusDtos = new ArrayList<GeoRadiusDto>();
//        Distance distance = new Distance(radius, metric);
//        GeoRadiusCommandArgs args = null;
//        if(direction.isAscending()){
//            args = GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending().includeCoordinates();;
//        }
//        if(direction.isDescending()){
//            args = GeoRadiusCommandArgs.newGeoRadiusArgs().sortDescending().includeCoordinates();;
//        }
//        GeoResults<GeoLocation<String>> geoResults = stringRedisTemplate.opsForGeo().geoRadiusByMember(key, member, distance, args);
//        List<GeoResult<GeoLocation<String>>> geoResultList = geoResults.getContent();
//        for(GeoResult<GeoLocation<String>> geoResult:geoResultList){
//            String name = geoResult.getContent().getName();
//            Point point = geoResult.getContent().getPoint();
//            GeoRadiusDto radiusDto = new GeoRadiusDto();
//            radiusDto.setKey(key);
//            radiusDto.setMember(name);
//            radiusDto.setX(point.getX());
//            radiusDto.setY(point.getY());
//            radiusDtos.add(radiusDto);
//        }
//        return radiusDtos;
//    }


    /**
     * @return List<GeoRadiusDto>
     * @Title: geoIntersect
     * @Description: TODO(交集)
     */
//    public static List<GeoRadiusDto> geoIntersect(List<GeoRadiusDto> list1,List<GeoRadiusDto> list2){
//        return list1.retainAll(list2) == true ? list1 : null;
//    }
    public boolean test1() {
        redisTemplate.opsForValue().set("0", 0);
        redisTemplate.opsForList();
        redisTemplate.opsForHash();
        redisTemplate.opsForSet();
        redisTemplate.opsForZSet();
        redisTemplate.opsForCluster();
        redisTemplate.opsForHyperLogLog();
        redisTemplate.opsForGeo();

        return true;
    }
    /**
     * @Date: 2019/11/1
     * @Author: zhaofu
     * @Description: 使用Redis流水线测试10万次读写功能
     **/
/*    @RequestMapping("/..")
    @ResponseBody
    public Map<String,Object> test(){
        long start = System.currentTimeMillis();
        List list = (List) redisTemplate.executePipelined((RedisOperations operations) -> {
            for (int i = 0; i <= 100000; i++) {
            operations.opsForValue().set("key" + i,"value"+i);
                String value = (String)operations.opsForValue().get("key" + i);
                if (i == 100000){
                    System.out.println("命令只是进入队列，所以值为空《"+ value +"》");
                }
            }
            return null;
        });
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+" 毫秒");
        Map<String, Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }*/
}
