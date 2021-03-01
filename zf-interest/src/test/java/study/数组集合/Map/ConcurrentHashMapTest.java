package study.数组集合.Map;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

/**
 * @ClassName ConcurrentHashMapTest
 * @Author zhaofu
 * @Date 2021/1/25
 * @Version V1.0
 * <p>
 * https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484161&idx=1&sn=6f52fb1f714f3ffd2f96a5ee4ebab146&chksm=ebd74200dca0cb16288db11f566cb53cafc580e08fe1c570e0200058e78676f527c014ffef41&scene=21#wechat_redirect
 **/
public class ConcurrentHashMapTest {

    @Test
    public void Test() {
        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1","---->");
        concurrentHashMap.get("1");
        concurrentHashMap.remove("1");
    }
/**
 *
 * ConCurrentHashMap的底层是：散列表+红黑树，与HashMap是一样的。
 *
 * -----》支持高并发的检索和更新
 * -----》线程是安全的，并且检索操作是不用加锁的
 * A hash table supporting full concurrency of retrievals and
 * high expected concurrency for updates. This class obeys the
 * same functional specification as {@link java.util.Hashtable}, and
 * includes versions of methods corresponding to each method of
 * {@code Hashtable}. However, even though all operations are
 * thread-safe, retrieval operations do <em>not</em> entail locking,
 * and there is <em>not</em> any support for locking the entire table
 * in a way that prevents all access.  This class is fully
 * interoperable with {@code Hashtable} in programs that rely on its
 * thread safety but not on its synchronization details.
 *
 * -----》get方法非阻塞
 * -----》检索出来的结果是最新设置的值
 * <p>Retrieval operations (including {@code get}) generally do not
 * block, so may overlap with update operations (including {@code put}
 * and {@code remove}). Retrievals reflect the results of the most
 * recently <em>completed</em> update operations holding upon their
 * onset. (More formally, an update operation for a given key bears a
 * <em>happens-before</em> relation with any (non-null) retrieval for
 * that key reporting the updated value.)  For aggregate operations
 * such as {@code putAll} and {@code clear}, concurrent retrievals may
 * reflect insertion or removal of only some entries.  Similarly,
 * Iterators, Spliterators and Enumerations return elements reflecting the
 * state of the hash table at some point at or since the creation of the
 * iterator/enumeration.  They do <em>not</em> throw {@link
 * java.util.ConcurrentModificationException ConcurrentModificationException}.
 * However, iterators are designed to be used by only one thread at a time.
 * -----》一些关于统计的方法，最好在单线程环境下使用，不然它只满足监控或估算的目的，在项目中（多环境下）使用它是无法准确返回的
 * Bear in mind that the results of aggregate status methods including
 * {@code size}, {@code isEmpty}, and {@code containsValue} are typically
 * useful only when a map is not undergoing concurrent updates in other threads.
 * Otherwise the results of these methods reflect transient states
 * that may be adequate for monitoring or estimation purposes, but not
 * for program control.
 *
 * -----》当有太多散列碰撞时，该表会动态增长
 * -----》再散列（扩容）是一件非常消耗资源的操作，最好是提前计算放入容器中有多少元素来手动初始化装载因子和初始容量，会好很多
 * -----》当很多key的HashCode相等时会非常影响性能的（散列冲突）key实现Comparable接口（自定义比较key，会好一点）
 * <p>The table is dynamically expanded when there are too many
 * collisions (i.e., keys that have distinct hash codes but fall into
 * the same slot modulo the table size), with the expected average
 * effect of maintaining roughly two bins per mapping (corresponding
 * to a 0.75 load factor threshold for resizing). There may be much
 * variance around this average as mappings are added and removed, but
 * overall, this maintains a commonly accepted time/space tradeoff for
 * hash tables.  However, resizing this or any other kind of hash
 * table may be a relatively slow operation. When possible, it is a
 * good idea to provide a size estimate as an optional {@code
 * initialCapacity} constructor argument. An additional optional
 * {@code loadFactor} constructor argument provides a further means of
 * customizing initial table capacity by specifying the table density
 * to be used in calculating the amount of space to allocate for the
 * given number of elements.  Also, for compatibility with previous
 * versions of this class, constructors may optionally specify an
 * expected {@code concurrencyLevel} as an additional hint for
 * internal sizing.  Note that using many keys with exactly the same
 * {@code hashCode()} is a sure way to slow down performance of any
 * hash table. To ameliorate impact, when keys are {@link Comparable},
 * this class may use comparison order among keys to help break ties.
 *
 * <p>A {@link Set} projection of a ConcurrentHashMap may be created
 * (using {@link #newKeySet()} or {@link #newKeySet(int)}), or viewed
 * (using {@link #keySet(Object)} when only keys are of interest, and the
 * mapped values are (perhaps transiently) not used or all take the
 * same mapping value.
 *
 * -----》能被用来频繁改变的Map，通过LongAdder
 * <p>A ConcurrentHashMap can be used as scalable frequency map (a
 * form of histogram or multiset) by using {@link
 * java.util.concurrent.atomic.LongAdder} values and initializing via
 * {@link #computeIfAbsent computeIfAbsent}. For example, to add a count
 * to a {@code ConcurrentHashMap<String,LongAdder> freqs}, you can use
 * {@code freqs.computeIfAbsent(k -> new LongAdder()).increment();}
 *
 * -----》实现了Map和Iterator的所有方法
 * <p>This class and its views and iterators implement all of the
 * <em>optional</em> methods of the {@link Map} and {@link Iterator}
 * interfaces.
 *
 * -----》ConcurrentHashMap不允许key和value为null
 * <p>Like {@link Hashtable} but unlike {@link HashMap}, this class
 * does <em>not</em> allow {@code null} to be used as a key or value.
 *
 * -----》ConcurrentHashMap提供方法支持批量操作
 * <p>ConcurrentHashMaps support a set of sequential and parallel bulk
 * operations that, unlike most {@link Stream} methods, are designed
 * to be safely, and often sensibly, applied even with maps that are
 * being concurrently updated by other threads; for example, when
 * computing a snapshot summary of the values in a shared registry.
 * There are three kinds of operation, each with four forms, accepting
 * functions with Keys, Values, Entries, and (Key, Value) arguments
 * and/or return values. Because the elements of a ConcurrentHashMap
 * are not ordered in any particular way, and may be processed in
 * different orders in different parallel executions, the correctness
 * of supplied functions should not depend on any ordering, or on any
 * other objects or values that may transiently change while
 * computation is in progress; and except for forEach actions, should
 * ideally be side-effect-free. Bulk operations on {@link java.util.Map.Entry}
 * objects do not support method {@code setValue}.
 *
 * 根据上面注释我们可以简单总结：
 *
 * - JDK1.8底层是散列表+红黑树
 *
 * - ConCurrentHashMap支持高并发的访问和更新，它是线程安全的
 *
 * - 检索操作不用加锁，get方法是非阻塞的
 *
 * - key和value都不允许为nu
 *
 */


/**1.2JDK1.7底层实现*/

    /**
     * 上面指明的是JDK1.8底层是：散列表+红黑树，也就意味着，JDK1.7的底层跟JDK1.8是不同的~
     *
     * JDK1.7的底层是：segments+HashEntry数组：
     *
     * 图片
     * 图来源：https://blog.csdn.net/panweiwei1994/article/details/78897275
     *
     * Segment继承了ReentrantLock,每个片段都有了一个锁，叫做“锁分段”
     *
     * 大概了解一下即可~
     *
     *
     * 1.3有了Hashtable为啥需要ConCurrentHashMap
     *
     * 。Hashtable是在每个方法上都加上了Synchronized完成同步，效率低下。
     *
     * 。ConcurrentHashMap通过在部分加锁和利用CAS算法来实现同步。
     *
     * 1.4CAS算法和volatile简单介绍
     * 在看ConCurrentHashMap源码之前，我们来简单讲讲CAS算法和volatile关键字
     *
     * CAS（比较与交换，Compare and swap） 是一种有名的无锁算法
     *
     * CAS有3个操作数
     *
     * 内存值V
     *
     * 旧的预期值A
     *
     * 要修改的新值B
     *
     * 当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做
     *
     * 当多个线程尝试使用CAS同时更新同一个变量时，只有其中一个线程能更新变量的值(A和内存值V相同时，将内存值V修改为B)，而其它线程都失败，失败的线程并不会被挂起，而是被告知这次竞争中失败，并可以再次尝试(否则什么都不做)
     *
     * 看了上面的描述应该就很容易理解了，先比较是否相等，如果相等则替换(CAS算法)
     *
     * ---------------------------------------------------
     *
     * 接下来我们看看volatile关键字，在初学的时候也很少使用到volatile这个关键字。反正我没用到，而又经常在看Java相关面试题的时候看到它，觉得是一个挺神秘又很难的一个关键字。其实不然，还是挺容易理解的~
     *
     * volatile经典总结：volatile仅仅用来保证该变量对所有线程的可见性，但不保证原子性
     *
     * 我们将其拆开来解释一下：
     *
     * 保证该变量对所有线程的可见性
     *
     * 在多线程的环境下：当这个变量修改时，所有的线程都会知道该变量被修改了，也就是所谓的“可见性”
     *
     * 不保证原子性
     *
     * 修改变量(赋值)实质上是在JVM中分了好几步，而在这几步内(从装载变量到修改)，它是不安全的。
     *
     * 如果没看懂或者想要深入了解其原理和可参考下列博文：
     *
     * http://www.cnblogs.com/Mainz/p/3556430.html
     *
     * https://www.cnblogs.com/Mainz/p/3546347.html
     *
     * http://www.dataguru.cn/java-865024-1-1.html
     * */

/**1.5 ConCurrentHashMap域*/

    /* ---------------- Fields -------------- */

    /**
     * The array of bins. Lazily initialized upon first insertion.
     * Size is always a power of two. Accessed directly by iterators.
     * 散列表，迭代器就是迭代它
     */
//    transient volatile Node<K,V>[] table;

    /**
     * The next table to use; non-null only while resizing.
     * 下一张表？除了在扩容的时候，其他时间都为null
     */
//    private transient volatile Node<K,V>[] nextTable;

    /**
     * Base counter value, used mainly when there is no contention,
     * but also as a fallback during table initialization
     * races. Updated via CAS.
     * 基础计数器通过CAS来 更新
     */
//    private transient volatile long baseCount;

    /**
     * Table initialization and resizing control.  When negative, the
     * table is being initialized or resized: -1 for initialization,
     * else -(1 + the number of active resizing threads).  Otherwise,
     * when table is null, holds the initial table size to use upon
     * creation, or 0 for default. After initialization, holds the
     * next element count value upon which to resize the table.
     * 散列表初始化和扩容都是由这个变量来控制！
     * 当为负数时，它正在初始化或者扩容
     *  - -1表示正在初始化
     *  - N表示N-1个线程在扩容
     * 默认是0
     * 初始化之后，保存着下一次扩容的大小
     */
//    private transient volatile int sizeCtl;

    /**
     * The next table index (plus one) to split while resizing.
     * 分割表时用的索引值
     */
//    private transient volatile int transferIndex;

    /**
     * Spinlock (locked via CAS) used when resizing and/or creating CounterCells.
     * 与计算size有关
     */
//    private transient volatile int cellsBusy;

    /**
     * Table of counter cells. When non-null, size is a power of 2.
     * 与计算size有关
     */
//    private transient volatile CounterCell[] counterCells;

    // views视图
//    private transient KeySetView<K,V> keySet;
//    private transient ValuesView<K,V> values;
//    private transient EntrySetView<K,V> entrySet;


/**1.6 ConCurrentHashMap构造方法*/

    /**
     * ConcurrentHashMap的构造方法有5个：
     *
     * Creates a new, empty map with the default initial table size (16).
     * 默认初始容量为16
     */
//    public ConcurrentHashMap() {
//    }

    /**
     * Creates a new, empty map with an initial table size
     * accommodating the specified number of elements without the need
     * to dynamically resize.
     *给出指定的容量来初始化，这样就不用过度依赖动态扩容了
     *
     */
//    public ConcurrentHashMap(int initialCapacity) {
//        if (initialCapacity < 0)
//            throw new IllegalArgumentException();
//        int cap = ((initialCapacity >= (MAXIMUM_CAPACITY >>> 1)) ?
//                MAXIMUM_CAPACITY :
//                tableSizeFor(initialCapacity + (initialCapacity >>> 1) + 1));
//        this.sizeCtl = cap;
//    }

    /**
     * Creates a new map with the same mappings as the given map.
     *
     */
//    public ConcurrentHashMap(Map<? extends K, ? extends V> m) {
//        this.sizeCtl = DEFAULT_CAPACITY;
//        putAll(m);
//    }

    /**
     * Creates a new, empty map with an initial table size based on
     * the given number of elements ({@code initialCapacity}) and
     */
//    public ConcurrentHashMap(int initialCapacity, float loadFactor) {
//        this(initialCapacity, loadFactor, 1);
//    }

    /**
     * Creates a new, empty map with an initial table size based on
     * the given number of elements ({@code initialCapacity}), table
     * density ({@code loadFactor}), and number of concurrently
     * updating threads ({@code concurrencyLevel}).
     * 估计并发更新的线程数量---》int concurrencyLevel
     */
//    public ConcurrentHashMap(int initialCapacity,
//                             float loadFactor, int concurrencyLevel) {
//        if (!(loadFactor > 0.0f) || initialCapacity < 0 || concurrencyLevel <= 0)
//            throw new IllegalArgumentException();
//        if (initialCapacity < concurrencyLevel)   // Use at least as many bins
//            initialCapacity = concurrencyLevel;   // as estimated threads
//        long size = (long)(1.0 + (long)initialCapacity / loadFactor);
//        int cap = (size >= (long)MAXIMUM_CAPACITY) ?
//                MAXIMUM_CAPACITY : tableSizeFor((int)size);
//        this.sizeCtl = cap;
//    }

    /**
     * 可以发现，在构造方法中有几处都调用了tableSizeFor()，我们来看一下他是干什么的：
     * 点进去之后发现，啊，原来我看过这个方法，在HashMap的时候…..
     *
     * Returns a power of two table size for the given desired capacity.
     * See Hackers Delight, sec 3.2
     *
     * 它就是用来获取大于参数且最接近2的整次幂的数…
     * 赋值给sizeCtl属性也就说明了：这是下次扩容的大小~
     */
//    private static final int tableSizeFor(int c) {
//        int n = c - 1;
//        n |= n >>> 1;
//        n |= n >>> 2;
//        n |= n >>> 4;
//        n |= n >>> 8;
//        n |= n >>> 16;
//        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
//    }


/**1.7 put方法*/

//    public V put(K key, V value) {
//        return putVal(key, value, false);
//    }
    /** Implementation for put and putIfAbsent */
//    final V putVal(K key, V value, boolean onlyIfAbsent) {
//        if (key == null || value == null) throw new NullPointerException();
//        int hash = spread(key.hashCode());//对key进行散列获取hash值
//        int binCount = 0;
//        for (Node<K,V>[] tab = table;;) {
//            Node<K,V> f; int n, i, fh;
//            if (tab == null || (n = tab.length) == 0)//当表为null时，进行初始化
//                tab = initTable();
//            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
                //若果这个哈希值直接可以存进数组，就直接插入进去（不用枷加锁）
//                if (casTabAt(tab, i, null,
//                        new Node<K,V>(hash, key, value, null)))
//                    break;                   // no lock when adding to empty bin
//            }
//            else if ((fh = f.hash) == MOVED)
                //插入的位置是表的链接点时，那就表明在扩容，帮助当前线程扩容
//                tab = helpTransfer(tab, f);
//            else {//否则处理散列冲突
//                V oldVal = null;
//                synchronized (f) {//加锁
//                    if (tabAt(tab, i) == f) {
                          //节点的方式处理
//                        if (fh >= 0) {
//                            binCount = 1;
//                            for (Node<K,V> e = f;; ++binCount) {
//                                K ek;
//                                if (e.hash == hash &&
//                                        ((ek = e.key) == key ||
//                                                (ek != null && key.equals(ek)))) {
//                                    oldVal = e.val;
//                                    if (!onlyIfAbsent)
//                                        e.val = value;
//                                    break;
//                                }
//                                Node<K,V> pred = e;
//                                if ((e = e.next) == null) {
//                                    pred.next = new Node<K,V>(hash, key,
//                                            value, null);
//                                    break;
//                                }
//                            }
//                        }
//                        else if (f instanceof TreeBin) {//按照树的方式插入
//                            Node<K,V> p;
//                            binCount = 2;
//                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
//                                    value)) != null) {
//                                oldVal = p.val;
//                                if (!onlyIfAbsent)
//                                    p.val = value;
//                            }
//                        }
//                    }
//                }
//                if (binCount != 0) {//链表长度大于8，把链表结构转为树形结构
//                    if (binCount >= TREEIFY_THRESHOLD)
//                        treeifyBin(tab, i);
//                    if (oldVal != null)
//                        return oldVal;
//                    break;
//                }
//            }
//        }
//        addCount(1L, binCount);
//        return null;
//    }

    /**
     * Initializes table, using the size recorded in sizeCtl.
     * 接下来，我们来看看初始化散列表的时候干了什么事：initTable()
     *
     * 只让一个线程对散列表进行初始化！
     */
//    private final Node<K,V>[] initTable() {
//        Node<K,V>[] tab; int sc;
//        while ((tab = table) == null || tab.length == 0) {
//            if ((sc = sizeCtl) < 0)//有线程正在初始化，告诉其他线程不要进来了
//                Thread.yield(); // lost initialization race; just spin
//            else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {//设置为-1，说明本线程正在初始化
//                try {
//                    if ((tab = table) == null || tab.length == 0) {
//                        int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
//                        @SuppressWarnings("unchecked")
//                        Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
//                        table = tab = nt;
//                        sc = n - (n >>> 2);//相当于0.75*n，设置一个扩容的阈值
//                    }
//                } finally {
//                    sizeCtl = sc;
//                }
//                break;
//            }
//        }
//        return tab;
//    }

/**1.8 get方法*/

    /**
     *
     * 从顶部注释我们可以读到，get方法是不用加锁的，是非阻塞的。
     *
     * 我们可以发现，Node节点是重写的，设置了volatile关键字修饰，致使它每次获取的都是最新设置的值
     *
     *
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     */
//    public V get(Object key) {
//        Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
//        int h = spread(key.hashCode());
//        if ((tab = table) != null && (n = tab.length) > 0 &&
//                (e = tabAt(tab, (n - 1) & h)) != null) {
//            if ((eh = e.hash) == h) {//在桶子上，就直接获取
//                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
//                    return e.val;
//            }
//            else if (eh < 0)//在树形结构上
//                return (p = e.find(h, key)) != null ? p.val : null;
//            while ((e = e.next) != null) {
//                if (e.hash == h &&//在链表上
//                        ((ek = e.key) == key || (ek != null && key.equals(ek))))
//                    return e.val;
//            }
//        }
//        return null;
//    }

    /**
     *
     * 二、总结
     * 上面简单介绍了ConcurrentHashMap的核心知识，还有很多知识点都没有提及到，作者的水平也不能将其弄懂~~有兴趣进入的同学可到下面的链接继续学习。
     *
     * 下面我来简单总结一下ConcurrentHashMap的核心要点：
     *
     * - 底层结构是散列表(数组+链表)+红黑树，这一点和HashMap是一样的。
     *
     * - Hashtable是将所有的方法进行同步，效率低下。而ConcurrentHashMap作为一个高并发的容器，它是通过部分锁定+CAS算法来进行实现线程安全的。CAS算法也可以认为是乐观锁的一种~
     *
     * - 在高并发环境下，统计数据(计算size…等等)其实是无意义的，因为在下一时刻size值就变化了。
     *
     * - get方法是非阻塞，无锁的。重写Node类，通过volatile修饰next来实现每次获取都是最新设置的值
     *
     * - ConcurrentHashMap的key和Value都不能为null
     *
     * 参考资料：
     *
     * https://blog.csdn.net/u010723709/article/details/48007881
     *
     * https://blog.csdn.net/melod_bc/article/details/54150679
     *
     * https://blog.csdn.net/panweiwei1994/article/details/78897275
     *
     * https://www.jianshu.com/p/e694f1e868ec
     *
     * */
}
