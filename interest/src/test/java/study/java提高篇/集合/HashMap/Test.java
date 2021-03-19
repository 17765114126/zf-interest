package study.java提高篇.集合.HashMap;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Test {
//HashMap 也是我们使用非常多的 Collection，它是基于哈希表的 Map 接口的实现，以 key-value 的形式存在。
// 在 HashMap 中，key-value 总是会当做一个整体来处理，系统会根据 hash 算法来来计算 key-value 的存储位置，我们总是可以通过 key 快速地存、取 value。
// 下面就来分析 HashMap 的存取。

//一、定义
//HashMap 实现了 Map 接口，继承 AbstractMap。
// 其中 Map 接口定义了键映射到值的规则，而 AbstractMap 类提供 Map 接口的骨干实现，以最大限度地减少实现此接口所需的工作，
// 其实 AbstractMap 类已经实现了Map，这里标注 Map LZ 觉得应该是更加清晰吧！


//    public class HashMap<K,V>
//        extends AbstractMap<K,V>
//        implements Map<K,V>, Cloneable, Serializable
//二、构造函数
//HashMap 提供了三个构造函数：

//HashMap()：构造一个具有默认初始容量 (16) 和默认加载因子 (0.75) 的空 HashMap。

//HashMap(int initialCapacity)：构造一个带指定初始容量和默认加载因子 (0.75) 的空 HashMap。

//HashMap(int initialCapacity, float loadFactor)：构造一个带指定初始容量和加载因子的空 HashMap。

//在这里提到了两个参数：初始容量，加载因子。
// 这两个参数是影响 HashMap 性能的重要参数，
// 其中容量表示哈希表中桶的数量，初始容量是创建哈希表时的容量，
// 加载因子是哈希表在其容量自动增加之前可以达到多满的一种尺度，它衡量的是一个散列表的空间的使用程度，负载因子越大表示散列表的装填程度越高，反之愈小。
// 对于使用链表法的散列表来说，查找一个元素的平均时间是 O(1+a)，因此如果负载因子越大，对空间的利用更充分，然而后果是查找效率的降低；
// 如果负载因子太小，那么散列表的数据将过于稀疏，对空间造成严重浪费。
// 系统默认负载因子为 0.75，一般情况下我们是无需修改的。

//HashMap 是一种支持快速存取的数据结构，要了解它的性能必须要了解它的数据结构。

//三、数据结构
//我们知道在 Java 中最常用的两种结构是数组和模拟指针(引用)，几乎所有的数据结构都可以利用这两种来组合实现，HashMap 也是如此。
// 实际上 HashMap 是一个“链表散列”，如下是它数据结构：

//fig.1

//从上图我们可以看出 HashMap 底层实现还是数组，只是数组的每一项都是一条链。
// 其中参数 initialCapacity 就代表了该数组的长度。下面为 HashMap 构造函数的源码：


//    public HashMap(int initialCapacity, float loadFactor) {
//            //初始容量不能<0
//            if (initialCapacity < 0)
//                throw new IllegalArgumentException("Illegal initial capacity: "
//                        + initialCapacity);
//            //初始容量不能 > 最大容量值，HashMap的最大容量值为2^30
//            if (initialCapacity > MAXIMUM_CAPACITY)
//                initialCapacity = MAXIMUM_CAPACITY;
//            //负载因子不能 < 0
//            if (loadFactor <= 0 || Float.isNaN(loadFactor))
//                throw new IllegalArgumentException("Illegal load factor: "
//                        + loadFactor);
//
//            // 计算出大于 initialCapacity 的最小的 2 的 n 次方值。
//            int capacity = 1;
//            while (capacity < initialCapacity)
//                capacity <<= 1;

//            this.loadFactor = loadFactor;
//            //设置HashMap的容量极限，当HashMap的容量达到该极限时就会进行扩容操作
//            threshold = (int) (capacity * loadFactor);
//            //初始化table数组
//            table = new Entry[capacity];
//            init();
//        }
//从源码中可以看出，每次新建一个 HashMap 时，都会初始化一个 table 数组。
// table 数组的元素为 Entry 节点。

//    static class Entry<K,V> implements Map.Entry<K,V> {
//            final K key;
//            V value;
//            Entry<K,V> next;
//            final int hash;
//
//            /**
//             * Creates new entry.
//             */
//            Entry(int h, K k, V v, Entry<K,V> n) {
//                value = v;
//                next = n;
//                key = k;
//                hash = h;
//            }
//            .......
//        }
//其中 Entry 为 HashMap 的内部类，它包含了键 key、值 value、下一个节点 next，以及 hash 值，这是非常重要的，正是由于 Entry 才构成了 table 数组的项为链表。

//上面简单分析了 HashMap 的数据结构，下面将探讨 HashMap 是如何实现快速存取的。

//四、存储实现：put(key,vlaue)
//首先我们先看源码


//    public V put(K key, V value) {
//            //当key为null，调用putForNullKey方法，保存null与table第一个位置中，这是HashMap允许为null的原因
//            if (key == null)
//                return putForNullKey(value);
//            //计算key的hash值
//            int hash = hash(key.hashCode());                   ------(1)
//            //计算key hash 值在 table 数组中的位置
//            int i = indexFor(hash, table.length);             ------(2)
//            //从i出开始迭代 e,找到 key 保存的位置
//            for (Entry<K, V> e = table[i]; e != null; e = e.next) {
//                Object k;
//                //判断该条链上是否有hash值相同的(key相同)
//                //若存在相同，则直接覆盖value，返回旧value
//                if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
//                    V oldValue = e.value;    //旧值 = 新值
//                    e.value = value;
//                    e.recordAccess(this);
//                    return oldValue;     //返回旧值
//                }
//            }
//            //修改次数增加1
//            modCount++;
//            //将key、value添加至i位置处
//            addEntry(hash, key, value, i);
//            return null;
//        }
//通过源码我们可以清晰看到 HashMap 保存数据的过程为：
// 首先判断 key 是否为 null，若为 null，则直接调用 putForNullKey 方法。
// 若不为空则先计算 key 的 hash 值，然后根据 hash 值搜索在 table 数组中的索引位置，
// 如果 table 数组在该位置处有元素，则通过比较是否存在相同的 key，若存在则覆盖原来 key 的 value，否则将该元素保存在链头（最先保存的元素放在链尾）。
// 若 table 在该处没有元素，则直接保存。这个过程看似比较简单，其实深有内幕。有如下几点：

//1、 先看迭代处。
// 此处迭代原因就是为了防止存在相同的 key 值，若发现两个 hash 值（key）相同时，HashMap 的处理方式是用新 value 替换旧 value，
// 这里并没有处理 key，这就解释了 HashMap 中没有两个相同的 key。

//2、 在看（1）、（2）处。这里是 HashMap 的精华所在。首先是 hash 方法，该方法为一个纯粹的数学计算，就是计算 h 的 hash 值。


//    static int hash(int h) {
//            h ^= (h >>> 20) ^ (h >>> 12);
//            return h ^ (h >>> 7) ^ (h >>> 4);
//        }
//我们知道对于 HashMap 的 table 而言，数据分布需要均匀（最好每项都只有一个元素，这样就可以直接找到），不能太紧也不能太松，太紧会导致查询速度慢，太松则浪费空间。
// 计算 hash 值后，怎么才能保证 table 元素分布均与呢？
// 我们会想到取模，但是由于取模的消耗较大，HashMap 是这样处理的：调用 indexFor 方法。


//    static int indexFor(int h, int length) {
//            return h & (length-1);
//        }
//HashMap 的底层数组长度总是 2 的 n 次方，在构造函数中存在：capacity <<= 1;
// 这样做总是能够保证 HashMap 的底层数组长度为 2 的 n 次方。
// 当 length 为 2 的 n 次方时，h&(length – 1) 就相当于对 length 取模，而且速度比直接取模快得多，这是 HashMap 在速度上的一个优化。
// 至于为什么是 2 的 n 次方下面解释。

//我们回到 indexFor 方法，该方法仅有一条语句：h&(length – 1)，这句话除了上面的取模运算外还有一个非常重要的责任：均匀分布 table 数据和充分利用空间。

//这里我们假设 length 为 16(2^n) 和 15，h 为 5、6、7。

//fig.2

//当 n=15 时，6 和 7 的结果一样，这样表示他们在 table 存储的位置是相同的，也就是产生了碰撞，6、7 就会在一个位置形成链表，这样就会导致查询速度降低。
// 诚然这里只分析三个数字不是很多，那么我们就看 0-15。

//fig.3

//从上面的图表中我们看到总共发生了 8 此碰撞，同时发现浪费的空间非常大，有 1、3、5、7、9、11、13、15 处没有记录，也就是没有存放数据。
// 这是因为他们在与 14 进行 & 运算时，得到的结果最后一位永远都是 0，
// 即 0001、0011、0101、0111、1001、1011、1101、1111 位置处是不可能存储数据的，空间减少，进一步增加碰撞几率，这样就会导致查询速度慢。
// 而当 length = 16 时，length – 1 = 15 即 1111，那么进行低位 & 运算时，值总是与原来 hash 值相同，而进行高位运算时，其值等于其低位值。
// 所以说当 length = 2^n 时，不同的 hash 值发生碰撞的概率比较小，这样就会使得数据在 table 数组中分布较均匀，查询速度也较快。

//这里我们再来复习 put 的流程：当我们想一个 HashMap 中添加一对 key-value 时，系统首先会计算 key 的 hash 值，然后根据 hash 值确认在 table 中存储的位置。
// 若该位置没有元素，则直接插入。否则迭代该处元素链表并依此比较其 key 的 hash 值。
// 如果两个 hash 值相等且 key 值相等 (e.hash == hash && ((k = e.key) == key || key.equals(k))),则用新的 Entry 的 value 覆盖原来节点的 value。
// 如果两个 hash 值相等但 key 值不等 ，则将该节点插入该链表的链头。具体的实现过程见 addEntry 方法，如下：


//    void addEntry(int hash, K key, V value, int bucketIndex) {
//            //获取bucketIndex处的Entry
//            Entry<K, V> e = table[bucketIndex];
//            //将新创建的 Entry 放入 bucketIndex 索引处，并让新的 Entry 指向原来的 Entry
//            table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
//            //若HashMap中元素的个数超过极限了，则容量扩大两倍
//            if (size++ >= threshold)
//                resize(2 * table.length);
//        }
//这个方法中有两点需要注意：

//一、链的产生
//这是一个非常优雅的设计。
// 系统总是将新的 Entry 对象添加到 bucketIndex 处。
// 如果 bucketIndex 处已经有了对象，那么新添加的 Entry 对象将指向原有的 Entry 对象，形成一条 Entry 链，
// 但是若 bucketIndex 处没有 Entry 对象，也就是 e==null,那么新添加的 Entry 对象指向 null，也就不会产生 Entry 链了。

//二、扩容问题。
//随着 HashMap 中元素的数量越来越多，发生碰撞的概率就越来越大，所产生的链表长度就会越来越长，
// 这样势必会影响 HashMap 的速度，为了保证 HashMap 的效率，系统必须要在某个临界点进行扩容处理。
// 该临界点在当 HashMap 中元素的数量等于 table 数组长度 * 加载因子。
// 但是扩容是一个非常耗时的过程，因为它需要重新计算这些数据在新 table 数组中的位置并进行复制处理。
// 所以如果我们已经预知 HashMap 中元素的个数，那么预设元素的个数能够有效的提高 HashMap 的性能。
public static void main(String[] args) {
    Map map = new HashMap<>();
    Map map2 = new HashMap<>(16,1);
    map.put("1",null);
    map.get("1");
}
//五、读取实现：get(key)
//相对于 HashMap 的存而言，取就显得比较简单了。
// 通过 key 的 hash 值找到在 table 数组中的索引处的 Entry，然后返回该 key 对应的 value 即可。


//    public V get(Object key) {
//            // 若为null，调用getForNullKey方法返回相对应的value
//            if (key == null)
//                return getForNullKey();
//            // 根据该 key 的 hashCode 值计算它的 hash 码
//            int hash = hash(key.hashCode());
//            // 取出 table 数组中指定索引处的值
//            for (Entry<K, V> e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
//                Object k;
//                //若搜索的key与查找的key相同，则返回相对应的value
//                if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
//                    return e.value;
//            }
//            return null;
//        }
//在这里能够根据 key 快速的取到 value 除了和 HashMap 的数据结构密不可分外，还和 Entry 有莫大的关系，
// 在前面就提到过，HashMap 在存储过程中并没有将 key，value 分开来存储，而是当做一个整体 key-value 来处理的，这个整体就是 Entry 对象。
// 同时 value 也只相当于 key 的附属而已。
// 在存储的过程中，系统根据 key 的 hashcode 来决定 Entry 在 table 数组中的存储位置，在取的过程中同样根据 key 的 hashcode 取出相对应的 Entry 对象。

}
