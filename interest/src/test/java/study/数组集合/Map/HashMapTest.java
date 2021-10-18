package study.数组集合.Map;

import java.util.*;

/**
 * @ClassName MapTest
 * @Author zhaofu
 * @Date 2021/2/18
 * @Version V1.0
 * @https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484139&idx=1&sn=bb73ac07081edabeaa199d973c3cc2b0&chksm=ebd743eadca0cafc532f298b6ab98b08205e87e37af6a6a2d33f5f2acaae245057fa01bd93f4&scene=21#wechat_redirect
 **/
public class HashMapTest {

//        HashMap hashMap = new HashMap();
/**一、HashMap剖析*/

    /**
     *首先看看HashMap的顶部注释说了些什么
     *
     *
     * -----》允许为null
     * -----》除了允许为null和同步之外Hashtable没什么区别
     * -----》不保证有序
     * Hash table based implementation of the <tt>Map</tt> interface.  This
     * implementation provides all of the optional map operations, and permits
     * <tt>null</tt> values and the <tt>null</tt> key.  (The <tt>HashMap</tt>
     * class is roughly equivalent to <tt>Hashtable</tt>, except that it is
     * unsynchronized and permits nulls.)  This class makes no guarantees as to
     * the order of the map; in particular, it does not guarantee that the order
     * will remain constant over time.
     *
     * -----》初始容量太高和装载因子太低对遍历不好
     * <p>This implementation provides constant-time performance for the basic
     * operations (<tt>get</tt> and <tt>put</tt>), assuming the hash function
     * disperses the elements properly among the buckets.  Iteration over
     * collection views requires time proportional to the "capacity" of the
     * <tt>HashMap</tt> instance (the number of buckets) plus its size (the number
     * of key-value mappings).  Thus, it's very important not to set the initial
     * capacity too high (or the load factor too low) if iteration performance is
     * important.
     *
     * -----》当初始容量*装载因子小于哈希表的容量时，哈希表会再进行散列，桶数会*2
     * <p>An instance of <tt>HashMap</tt> has two parameters that affect its
     * performance: <i>initial capacity</i> and <i>load factor</i>.  The
     * <i>capacity</i> is the number of buckets in the hash table, and the initial
     * capacity is simply the capacity at the time the hash table is created.  The
     * <i>load factor</i> is a measure of how full the hash table is allowed to
     * get before its capacity is automatically increased.  When the number of
     * entries in the hash table exceeds the product of the load factor and the
     * current capacity, the hash table is <i>rehashed</i> (that is, internal data
     * structures are rebuilt) so that the hash table has approximately twice the
     * number of buckets.
     *
     * -----》装载因子默认是0.75，设置高虽然会减少空间，但遍历时开销会增加，
     * -----》在设置初始容量时，应该考虑好装载因子的大小和集合的大小，如果设置的好，那么就不用再散列了。
     * <p>As a general rule, the default load factor (.75) offers a good
     * tradeoff between time and space costs.  Higher values decrease the
     * space overhead but increase the lookup cost (reflected in most of
     * the operations of the <tt>HashMap</tt> class, including
     * <tt>get</tt> and <tt>put</tt>).  The expected number of entries in
     * the map and its load factor should be taken into account when
     * setting its initial capacity, so as to minimize the number of
     * rehash operations.  If the initial capacity is greater than the
     * maximum number of entries divided by the load factor, no rehash
     * operations will ever occur.
     *
     * -----》如果知道有足够多的数据存到HashMap，最好就设置初始容量，比自动散列要好得多-
     * <p>If many mappings are to be stored in a <tt>HashMap</tt>
     * instance, creating it with a sufficiently large capacity will allow
     * the mappings to be stored more efficiently than letting it perform
     * automatic rehashing as needed to grow the table.  Note that using
     * many keys with the same {@code hashCode()} is a sure way to slow
     * down performance of any hash table. To ameliorate impact, when keys
     * are {@link Comparable}, this class may use comparison order among
     * keys to help break ties.
     *
     * -----》不同步
     * <p><strong>Note that this implementation is not synchronized.</strong>
     * If multiple threads access a hash map concurrently, and at least one of
     * the threads modifies the map structurally, it <i>must</i> be
     * synchronized externally.  (A structural modification is any operation
     * that adds or deletes one or more mappings; merely changing the value
     * associated with a key that an instance already contains is not a
     * structural modification.)  This is typically accomplished by
     * synchronizing on some object that naturally encapsulates the map.
     *
     * -----》想要同步可以这样实现Map m = Collections.synchronizedMap(new HashMap(...));
     * If no such object exists, the map should be "wrapped" using the
     * {@link Collections#synchronizedMap Collections.synchronizedMap}
     * method.  This is best done at creation time, to prevent accidental
     * unsynchronized access to the map:<pre>
     *   Map m = Collections.synchronizedMap(new HashMap(...));</pre>
     *
     * -----》迭代器相关
     * <p>The iterators returned by all of this class's "collection view methods"
     * are <i>fail-fast</i>: if the map is structurally modified at any time after
     * the iterator is created, in any way except through the iterator's own
     * <tt>remove</tt> method, the iterator will throw a
     * {@link ConcurrentModificationException}.  Thus, in the face of concurrent
     * modification, the iterator fails quickly and cleanly, rather than risking
     * arbitrary, non-deterministic behavior at an undetermined time in the
     * future.
     *
     * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
     * as it is, generally speaking, impossible to make any hard guarantees in the
     * presence of unsynchronized concurrent modification.  Fail-fast iterators
     * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
     * Therefore, it would be wrong to write a program that depended on this
     * exception for its correctness: <i>the fail-fast behavior of iterators
     * should be used only to detect bugs.</i>
     *
     * <p>This class is a member of the
     * <a href="{@docRoot}/../technotes/guides/collections/index.html">
     * Java Collections Framework</a>.
     */


/**下面我们来看一下HashMap的属性：*/

    /**
     * The default initial capacity - MUST be a power of two.
     * 默认初始容量为16-必须为2的幂
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     * 最大容量2的31次方
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * 默认装载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     * 当添加一个元素被添加到有至少TREEIFY_THRESHOLD个节点的桶中，桶中链表将被转化为树形结构
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     * 同上，不过是将树形结构转化为链表
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     * 桶可能被转化为树形结构的最小容量
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * hashMap的一个内部类Node：链表结构，储存下一个元素
     *       final int hash;
     *       final K key;
     *       V value;
     *       Node<K,V> next;
     *
     *
     *
     *
     * 我们知道Hash的底层是散列表，而在Java中散列表的实现是通过数组+链表的~
     *
     * 再来简单看看put方法就可以印证我们的说法了：数组+链表-->散列表
     * final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
     *                    boolean evict) {
     *         Node<K,V>[] tab; Node<K,V> p; int n, i;
     *
     *
     *
     *
     * 我们可以简单总结出HashMap：
     *
     * 1.无序，允许为null，非同步
     *
     * 2.底层由散列表(哈希表)实现
     *
     * 3.初始容量和装载因子对HashMap影响挺大的，设置小了不好，设置大了也不好
     * */


    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     * (We also tolerate length zero in some operations to allow
     * bootstrapping mechanics that are currently not needed.)
     */
//    transient Node<K, V>[] table;

    /**
     * Holds cached entrySet(). Note that AbstractMap fields are used
     * for keySet() and values().
     */
//    transient Set<Map.Entry<K, V>> entrySet;

    /**
     * The number of key-value mappings contained in this map.
     */
    transient int size;

    /**
     * The number of times this HashMap has been structurally modified
     * Structural modifications are those that change the number of mappings in
     * the HashMap or otherwise modify its internal structure (e.g.,
     * rehash).  This field is used to make iterators on Collection-views of
     * the HashMap fail-fast.  (See ConcurrentModificationException).
     */
    transient int modCount;

    /**
     * The next size value at which to resize (capacity * load factor).
     */
    // (The javadoc description is true upon serialization.
    // Additionally, if the table array has not been allocated, this
    // field holds the initial array capacity, or zero signifying
    // DEFAULT_INITIAL_CAPACITY.)
    int threshold;

    /**
     * The load factor for the hash table.
     */
    final float loadFactor;

/**1.1HashMap构造方法  有4个*/

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     */
    public HashMapTest(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)//判断初始大小是否合理
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)//如果超过，赋值最大值
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))//判断初始大小是否合理
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;//初始化装载因子
        this.threshold = tableSizeFor(initialCapacity);//返回一个大于输入参数且最近的2的整数次幂的数
//                这是位运算算法，具体流程可参考：
//                https://www.cnblogs.com/loading4/p/6239441.html
//                https://blog.csdn.net/fan2012huan/article/details/51097331
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     */
    public HashMapTest(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    public HashMapTest() {
        this.loadFactor = DEFAULT_LOAD_FACTOR; // all cache fields defaulted
    }

    /**
     * Constructs a new <tt>HashMap</tt> with the same mappings as the
     * specified <tt>Map</tt>.  The <tt>HashMap</tt> is created with
     * default load factor (0.75) and an initial capacity sufficient to
     * hold the mappings in the specified <tt>Map</tt>.
     */
//    public HashMapTest(Map<? extends K, ? extends V> m) {
//        this.loadFactor = DEFAULT_LOAD_FACTOR;
//        putMapEntries(m, false);
//    }


/**1.2 put方法*/

//        hashMap.put("1", "------>");

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * 调用了putVal(hash(key)，以key计算哈希值，传入key和value，还有两个参数
     */
//    public V put(K key, V value) {
//        return putVal(hash(key), key, value, false, true);
//    }

    /**
     * 我们来看看它是怎么计算哈希值的：
     * <p>
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//        为什么要这样干呢？？我们一般来说直接将key作为哈希值不就好了吗，做异或运算是干嘛用的？？
    }

    /**
     * 我们是根据key的哈希值来保存在散列表中的，我们表默认的初始容量是16，要放到散列表中，就是0-15的位置上。
     * 也就是tab[i = (n - 1) & hash]。可以发现的是：在做&运算的时候，仅仅是后4位有效~那如果我们key的哈希值高位变化很大，低位变化很小。
     * 直接拿过去做&运算，这就会导致计算出来的Hash值相同的很多。
     * <p>
     * 而设计者将key的哈希值的高位也做了运算(与高16位做异或运算，使得在做&运算时，此时的低位实际上是高位与低位的结合)，
     * 这就增加了随机性，减少了碰撞冲突的可能性！
     * <p>
     * 下面我们再来看看流程是怎么样的：
     * <p>
     * <p>
     * Implements Map.put and related methods
     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;//当散列表为null时，调用resize()初始化散列表
//        if ((p = tab[i = (n - 1) & hash]) == null)//没有发生碰撞时，直接添加元素到散列表中
//            tab[i] = newNode(hash, key, value, null);
//        else {
//            Node<K, V> e;
//            K k;
//            if (p.hash == hash &&//要插入的元素，桶的hash和key相等，记录下来
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof TreeNode)//如果是红黑树结构，就调用树的插入方法
//                e = ((TreeNode<K, V>) p).putTreeVal(this, tab, hash, key, value);
//            else {
////        链表结构，找到了key映射的节点，就记录这个节点，退出循环，如果没有找到，在链表尾部插入节点
////        插入后，如果发现临界值大于TREEIFY_THRESHOLD，转成红黑树
//                for (int binCount = 0; ; ++binCount) {
//                    if ((e = p.next) == null) {
//                        p.next = newNode(hash, key, value, null);
//                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
//                            treeifyBin(tab, hash);
//                        break;
//                    }
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        break;
//                    p = e;
//                }
//            }
////            新值覆盖旧值，返回旧值
//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
//        }
//        ++modCount;
//        if (++size > threshold)
//            resize();
//        afterNodeInsertion(evict);
//        return null;
//    }

    /**
     * 接下来我们看看resize()方法，在初始化的时候要调用这个方法，
     * 当散列表元素大于capacity * load factor的时候也是调用resize()
     * <p>
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     */
//    final Node<K, V>[] resize() {
//        Node<K, V>[] oldTab = table;
//        int oldCap = (oldTab == null) ? 0 : oldTab.length;
//        int oldThr = threshold;
//        int newCap, newThr = 0;
//        if (oldCap > 0) {
//            if (oldCap >= MAXIMUM_CAPACITY) {//如果旧的容量比最大容量还要大，那就不能散列了，返回旧的散列表
//                threshold = Integer.MAX_VALUE;
//                return oldTab;
//            } else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
//                    oldCap >= DEFAULT_INITIAL_CAPACITY)//新的阈值是旧的两倍
//                newThr = oldThr << 1; // double threshold
//        } else if (oldThr > 0) // initial capacity was placed in threshold
//            newCap = oldThr; //如果旧容量<=0,而且旧阈值>0,数组的新容量设置为老数组扩容的阈值
//        else {               // zero initial threshold signifies using defaults
//            newCap = DEFAULT_INITIAL_CAPACITY;
//            newThr = (int) (DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
//        }
////    第一次初始化散列表的操作
//        if (newThr == 0) {
//            float ft = (float) newCap * loadFactor;
//            newThr = (newCap < MAXIMUM_CAPACITY && ft < (float) MAXIMUM_CAPACITY ?
//                    (int) ft : Integer.MAX_VALUE);
//        }
//        threshold = newThr;
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
//        table = newTab;
////    将旧散列表复制到新散列表中
//        if (oldTab != null) {
//            for (int j = 0; j < oldCap; ++j) {
//                Node<K, V> e;
//                if ((e = oldTab[j]) != null) {
//                    oldTab[j] = null;
//                    if (e.next == null)
//                        newTab[e.hash & (newCap - 1)] = e;
//                    else if (e instanceof TreeNode)//红黑树
//                        ((TreeNode<K, V>) e).split(this, newTab, j, oldCap);
//                    else { // preserve order
//                        Node<K, V> loHead = null, loTail = null;//链表
//                        Node<K, V> hiHead = null, hiTail = null;
//                        Node<K, V> next;
//                        do {
//                            next = e.next;
//                            if ((e.hash & oldCap) == 0) {
//                                if (loTail == null)
//                                    loHead = e;
//                                else
//                                    loTail.next = e;
//                                loTail = e;
//                            } else {
//                                if (hiTail == null)
//                                    hiHead = e;
//                                else
//                                    hiTail.next = e;
//                                hiTail = e;
//                            }
//                        } while ((e = next) != null);
//                        if (loTail != null) {
//                            loTail.next = null;
//                            newTab[j] = loHead;
//                        }
//                        if (hiTail != null) {
//                            hiTail.next = null;
//                            newTab[j + oldCap] = hiHead;
//                        }
//                    }
//                }
//            }
//        }
//        return newTab;
//    }

    /**
     * 1.3 get方法
     */

//    public V get(Object key) {
//        Node<K, V> e;
//        //计算key的哈希值，调用getNode()获取对应的value
//        return (e = getNode(hash(key), key)) == null ? null : e.value;
//    }


    /**
     * Implements Map.get and related methods
     *
     * @param hash hash for key
     * @param key  the key
     * @return the node, or null if none
     */
//    final Node<K, V> getNode(int hash, Object key) {
//        Node<K, V>[] tab;
//        Node<K, V> first, e;
//        int n;
//        K k;
//        if ((tab = table) != null && (n = tab.length) > 0 &&//计算出来的哈希值实在哈希表上的
//                (first = tab[(n - 1) & hash]) != null) {
//            if (first.hash == hash && // always check first node//如果在桶的首位上就可以找到，那就直接返回
//                    ((k = first.key) == key || (key != null && key.equals(k))))
//                return first;
//            //否则在红黑树中找或者遍历链表来寻找
//            if ((e = first.next) != null) {
//                if (first instanceof TreeNode)
//                    return ((TreeNode<K, V>) first).getTreeNode(hash, key);
//                do {
//                    if (e.hash == hash &&
//                            ((k = e.key) == key || (key != null && key.equals(k))))
//                        return e;
//                } while ((e = e.next) != null);
//            }
//        }
//        return null;
//    }


/**1.4 remove方法*/


    /**
     * Removes the mapping for the specified key from this map if present.
     */
//    public V remove(Object key) {
//        Node<K, V> e;
//        //计算key的哈希值来删除value
//        return (e = removeNode(hash(key), key, null, false, true)) == null ?
//                null : e.value;
//    }

    /**
     * Implements Map.remove and related methods
     */
//    final Node<K, V> removeNode(int hash, Object key, Object value,
//                                boolean matchValue, boolean movable) {
//        Node<K, V>[] tab;
//        Node<K, V> p;
//        int n, index;
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (p = tab[index = (n - 1) & hash]) != null) {//桶不为空，映射的哈希也存在
//            Node<K, V> node = null, e;
//            K k;
//            V v;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))//在桶的首位上找到，记录下来
//                node = p;
//            else if ((e = p.next) != null) {
//                //不是在首位，就在红黑树中找或者遍历链表来寻找
//                if (p instanceof TreeNode)
//                    node = ((TreeNode<K, V>) p).getTreeNode(hash, key);
//                else {
//                    do {
//                        if (e.hash == hash &&
//                                ((k = e.key) == key ||
//                                        (key != null && key.equals(k)))) {
//                            node = e;
//                            break;
//                        }
//                        p = e;
//                    } while ((e = e.next) != null);
//                }
//            }
//            if (node != null && (!matchValue || (v = node.value) == value ||
//                    (value != null && value.equals(v)))) {
//                //找到了对应的节点，并且value值对应的上，那么就分三种情况去删除了
//                //1.链表 2.红黑树 3.在桶的首位
//                if (node instanceof TreeNode)
//                    ((TreeNode<K, V>) node).removeTreeNode(this, tab, movable);
//                else if (node == p)
//                    tab[index] = node.next;
//                else
//                    p.next = node.next;
//                ++modCount;
//                --size;
//                afterNodeRemoval(node);
//                return node;
//            }
//        }
//        return null;
//    }

/**
 *
 * # 二、HashMap与Hashtable对比
 *
 * > 从存储结构和实现来讲基本上都是相同的。它和HashMap的最大的不同是它是线程安全的，另外它不允许key和value为null。Hashtable是个过时的集合类，不建议在新代码中使用，不需要线程安全的场合可以用HashMap替换，需要线程安全的场合可以用ConcurrentHashMap替换
 *
 * ![图片](https://mmbiz.qpic.cn/mmbiz_png/2BGWl1qPxib1bEDAPqSM5SyMR2iatxmO0NXYXNDaNbcFvOB3FdtWW6uLWH57ajsgIgueN65rmicZxBYlpm98JEe3w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)
 *
 * Hashtable具体阅读源码可参考：
 *
 * - https://blog.csdn.net/panweiwei1994/article/details/77427010
 * - https://blog.csdn.net/panweiwei1994/article/details/77428710
 *
 * # 四、总结
 *
 * 在JDK8中HashMap的底层是：**数组+链表(散列表)+红黑树**
 *
 * 在散列表中有装载因子这么一个属性，当装载因子*初始容量小于散列表元素时，该散列表会再散列，扩容2倍！
 *
 * 装载因子的**默认值是0.75**，无论是初始大了还是初始小了对我们HashMap的性能都不好
 *
 * - 装载因子初始值大了，可以减少散列表再散列(扩容的次数)，但同时会导致散列冲突的可能性变大(**散列冲突也是耗性能的一个操作，要得操作链表(红黑树)**！
 * - 装载因子初始值小了，可以减小散列冲突的可能性，但同时扩容的次数可能就会变多！
 *
 * 初始容量的**默认值是16**，它也一样，无论初始大了还是小了，对我们的HashMap都是有影响的：
 *
 * - 初始容量过大，那么遍历时我们的速度就会受影响~
 * - 初始容量过小，散列表再散列(扩容的次数)可能就变得多，扩容也是一件非常耗费性能的一件事~
 *
 * 从源码上我们可以发现：HashMap并不是直接拿key的哈希值来用的，它会将key的哈希值的高16位进行异或操作，使得我们将元素放入哈希表的时候**增加了一定的随机性**。
 *
 * 还要值得注意的是：**并不是桶子上有8位元素的时候它就能变成红黑树，它得同时满足我们的散列表容量大于64才行的**~
 *
 * */


    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }


}
