package study.数组集合.Map;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @ClassName LinkedHashMapTest
 * @Author zhaofu
 * @Date 2021/2/20
 * @Version V1.0
 **/
public class LinkedHashMapTest {


    public static void main(String[] args) {
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("1","--->");
        linkedHashMap.get("1");
        linkedHashMap.remove("1");
    }

/**
 *
 * -----》迭代有序
 * -----》双向链表
 * -----》一个键重新插入，顺序不会受到影响。
 * <p>Hash table and linked list implementation of the <tt>Map</tt> interface,
 * with predictable iteration order.  This implementation differs from
 * <tt>HashMap</tt> in that it maintains a doubly-linked list running through
 * all of its entries.  This linked list defines the iteration ordering,
 * which is normally the order in which keys were inserted into the map
 * (<i>insertion-order</i>).  Note that insertion order is not affected
 * if a key is <i>re-inserted</i> into the map.  (A key <tt>k</tt> is
 * reinserted into a map <tt>m</tt> if <tt>m.put(k, v)</tt> is invoked when
 * <tt>m.containsKey(k)</tt> would return <tt>true</tt> immediately prior to
 * the invocation.)
 *
 * -----》用在需要插入有序的情况下
 * <p>This implementation spares its clients from the unspecified, generally
 * chaotic ordering provided by {@link HashMap} (and {@link Hashtable}),
 * without incurring the increased cost associated with {@link TreeMap}.  It
 * can be used to produce a copy of a map that has the same order as the
 * original, regardless of the original map's implementation:
 * <pre>
 *     void foo(Map m) {
 *         Map copy = new LinkedHashMap(m);
 *         ...
 *     }
 * </pre>
 * This technique is particularly useful if a module takes a map on input,
 * copies it, and later returns results whose order is determined by that of
 * the copy.  (Clients generally appreciate having things returned in the same
 * order they were presented.)
 *
 * -----》提供这个构造方法可以让我们实现最近LRU算法(Least recently used,最近最少使用)，是页面置换算法常用的一种
 * <p>A special {@link #LinkedHashMap(int,float,boolean) constructor} is
 * provided to create a linked hash map whose order of iteration is the order
 * in which its entries were last accessed, from least-recently accessed to
 * most-recently (<i>access-order</i>).  This kind of map is well-suited to
 * building LRU caches.  Invoking the {@code put}, {@code putIfAbsent},
 * {@code get}, {@code getOrDefault}, {@code compute}, {@code computeIfAbsent},
 * {@code computeIfPresent}, or {@code merge} methods results
 * in an access to the corresponding entry (assuming it exists after the
 * invocation completes). The {@code replace} methods only result in an access
 * of the entry if the value is replaced.  The {@code putAll} method generates one
 * entry access for each mapping in the specified map, in the order that
 * key-value mappings are provided by the specified map's entry set iterator.
 * <i>No other methods generate entry accesses.</i>  In particular, operations
 * on collection-views do <i>not</i> affect the order of iteration of the
 * backing map.
 *
 * <p>The {@link #removeEldestEntry(Map.Entry)} method may be overridden to
 * impose a policy for removing stale mappings automatically when new mappings
 * are added to the map.
 *
 * -----》提供了Map应有的所有算法，可以为null
 * <p>This class provides all of the optional <tt>Map</tt> operations, and
 * permits null elements.  Like <tt>HashMap</tt>, it provides constant-time
 * performance for the basic operations (<tt>add</tt>, <tt>contains</tt> and
 * <tt>remove</tt>), assuming the hash function disperses elements
 * properly among the buckets.  Performance is likely to be just slightly
 * below that of <tt>HashMap</tt>, due to the added expense of maintaining the
 * linked list, with one exception: Iteration over the collection-views
 * of a <tt>LinkedHashMap</tt> requires time proportional to the <i>size</i>
 * of the map, regardless of its capacity.  Iteration over a <tt>HashMap</tt>
 * is likely to be more expensive, requiring time proportional to its
 * <i>capacity</i>.
 *
 * -----》与 HashMap 一样，初始容量和装载因子对LinkedHashMap影响是很大的，但它遍历时初始容量是不受影响的（为啥不受影响？？）
 * <p>A linked hash map has two parameters that affect its performance:
 * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
 * as for <tt>HashMap</tt>.  Note, however, that the penalty for choosing an
 * excessively high value for initial capacity is less severe for this class
 * than for <tt>HashMap</tt>, as iteration times for this class are unaffected
 * by capacity.
 *

 * <p><strong>Note that this implementation is not synchronized.</strong>
 * If multiple threads access a linked hash map concurrently, and at least
 * one of the threads modifies the map structurally, it <em>must</em> be
 * synchronized externally.  This is typically accomplished by
 * synchronizing on some object that naturally encapsulates the map.
 *
 * -----》非同步，想要同步可以使用Map m = Collections.synchronizedMap(new LinkedHashMap(...));
 *
 * If no such object exists, the map should be "wrapped" using the
 * {@link Collections#synchronizedMap Collections.synchronizedMap}
 * method.  This is best done at creation time, to prevent accidental
 * unsynchronized access to the map:<pre>
 *   Map m = Collections.synchronizedMap(new LinkedHashMap(...));</pre>
 *
 *
 * -----》access-ordered结构性的修改是会影响遍历的顺序的
 * -----》在insertion-ordered这种条件下，修改已有key的value，不是结构性的修改，
 * -----》但在access-ordered这种条件下，使用get方法就已经是结构性修改了
 * A structural modification is any operation that adds or deletes one or more
 * mappings or, in the case of access-ordered linked hash maps, affects
 * iteration order.  In insertion-ordered linked hash maps, merely changing
 * the value associated with a key that is already contained in the map is not
 * a structural modification.  <strong>In access-ordered linked hash maps,
 * merely querying the map with <tt>get</tt> is a structural modification.
 * </strong>)
 *
 * -----》迭代器的内容
 * <p>The iterators returned by the <tt>iterator</tt> method of the collections
 * returned by all of this class's collection view methods are
 * <em>fail-fast</em>: if the map is structurally modified at any time after
 * the iterator is created, in any way except through the iterator's own
 * <tt>remove</tt> method, the iterator will throw a {@link
 * ConcurrentModificationException}.  Thus, in the face of concurrent
 * modification, the iterator fails quickly and cleanly, rather than risking
 * arbitrary, non-deterministic behavior at an undetermined time in the future.
 *
 * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
 * as it is, generally speaking, impossible to make any hard guarantees in the
 * presence of unsynchronized concurrent modification.  Fail-fast iterators
 * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
 * Therefore, it would be wrong to write a program that depended on this
 * exception for its correctness:   <i>the fail-fast behavior of iterators
 * should be used only to detect bugs.</i>
 *
 * <p>The spliterators returned by the spliterator method of the collections
 * returned by all of this class's collection view methods are
 * <em><a href="Spliterator.html#binding">late-binding</a></em>,
 * <em>fail-fast</em>, and additionally report {@link Spliterator#ORDERED}.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @implNote
 * The spliterators returned by the spliterator method of the collections
 * returned by all of this class's collection view methods are created from
 * the iterators of the corresponding collections.
 *
 *
 * 从顶部翻译我们就可以归纳总结出HashMap几点：
 *
 * - 底层是散列表和双向链表
 *
 * - 允许为null，不同步
 *
 * - 插入的顺序是有序的(底层链表致使有序)
 *
 * - 装载因子和初始容量对LinkedHashMap影响是很大的~
 *
 * 同时也给我带了几个疑问：
 *
 * - access-ordered和insertion-ordered具体的使用和意思
 *
 * - 为什么说初始容量对遍历没有影响？
 *
 */

/**1.1LinkedHashMap的域*/

    /**
     * HashMap.Node subclass for normal LinkedHashMap entries.
     * 继承HashMap的Node节点，它是双向链表，包含前置指针和后置指针
     */
//    static class Entry<K,V> extends HashMap.Node<K,V> {
//        Entry<K,V> before, after;
//        Entry(int hash, K key, V value, Node<K,V> next) {
//            super(hash, key, value, next);
//        }
//    }

    private static final long serialVersionUID = 3801124242820219131L;

    /**
     * The head (eldest) of the doubly linked list.
     */
//    transient LinkedHashMap.Entry<K,V> head;

    /**
     * The tail (youngest) of the doubly linked list.
     */
//    transient LinkedHashMap.Entry<K,V> tail;

    /**
     * The iteration ordering method for this linked hash map: <tt>true</tt>
     * for access-order, <tt>false</tt> for insertion-order.
     *
     *此链接的哈希映射的迭代排序方法：
     * true *表示访问顺序， false 表示插入顺序。
     */
//    final boolean accessOrder;


/**1.2LinkedHashMap重写的方法*/

// overrides of HashMap hook methods

//    void reinitialize() {//初始化散列表和双向列表
//        super.reinitialize();
//        head = tail = null;
//    }
//
//    Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
//        LinkedHashMap.Entry<K,V> p =
//                new LinkedHashMap.Entry<K,V>(hash, key, value, e);
//        linkNodeLast(p);//创建一个普通的entry，将entry插入到双向链表的末尾，最后返回entry
//        return p;
//    }
        /**
         *
         * 这就印证了我们的LinkedHashMap底层确确实实是散列表和双向链表~
         * 在构建新节点时，构建的是LinkedHashMap.Entry 不再是Node.
         */


/**1.3构造方法*/

    /**
     *
     * 有五个构造方法
     * 调用的是HashMap的构造方法
     *从构造方法上我们可以知道的是：LinkedHashMap默认使用的是插入顺序
     *
     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
     * with the specified initial capacity and load factor.
     */
//    public LinkedHashMap(int initialCapacity, float loadFactor) {
//        super(initialCapacity, loadFactor);//调用的是HashMap的构造方法
//        accessOrder = false;//默认是插入顺序
//    }

    /**
     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
     * with the specified initial capacity and a default load factor (0.75).
     */
//    public LinkedHashMap(int initialCapacity) {
//        super(initialCapacity);
//        accessOrder = false;
//    }

    /**
     * Constructs an empty insertion-ordered <tt>LinkedHashMap</tt> instance
     * with the default initial capacity (16) and load factor (0.75).
     */
//    public LinkedHashMap() {
//        super();
//        accessOrder = false;
//    }

    /**
     * Constructs an insertion-ordered <tt>LinkedHashMap</tt> instance with
     * the same mappings as the specified map.  The <tt>LinkedHashMap</tt>
     * instance is created with a default load factor (0.75) and an initial
     * capacity sufficient to hold the mappings in the specified map.
     */
//    public LinkedHashMap(Map<? extends K, ? extends V> m) {
//        super();
//        accessOrder = false;
//        putMapEntries(m, false);
//    }

    /**
     * Constructs an empty <tt>LinkedHashMap</tt> instance with the
     * specified initial capacity, load factor and ordering mode.
     */
//    public LinkedHashMap(int initialCapacity,
//                         float loadFactor,
//                         boolean accessOrder) {
//        super(initialCapacity, loadFactor);
//        this.accessOrder = accessOrder;
//    }


/**1.4put方法*/

    /**
     *
     * LinkedHashMap和HashMap的put方法是一样的！LinkedHashMap继承着HashMap，LinkedHashMap没有重写HashMap的put方法
     *
     * 所以，LinkedHashMap的put方法和HashMap是一样的。
     *
     *
     * 当然了，在创建节点的时候，调用的是LinkedHashMap重写的方法~
     *
     * Implements Map.put and related methods
     */
//    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
//                   boolean evict) {
//        Node<K,V>[] tab; Node<K,V> p; int n, i;
//        if ((tab = table) == null || (n = tab.length) == 0)
//            n = (tab = resize()).length;
//        if ((p = tab[i = (n - 1) & hash]) == null)
//            tab[i] = newNode(hash, key, value, null);//调用的是LinkedHashMap重写的方法
//        else {
//            Node<K,V> e; K k;
//            if (p.hash == hash &&
//                    ((k = p.key) == key || (key != null && key.equals(k))))
//                e = p;
//            else if (p instanceof TreeNode)
//                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
//            else {
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


    /**1.5 get方法*/


    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <i>necessarily</i>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     */
//    public V get(Object key) {
//        Node<K,V> e;
//        if ((e = getNode(hash(key), key)) == null)//调用HashMap定义的方法获取对应的节点
//            return null;
//        if (accessOrder)//如果是访问顺序的话，把该节点放在链表的最后面
//            afterNodeAccess(e);
//        return e.value;
//    }

//    将节点移到最后
//    void afterNodeAccess(Node<K,V> e) { // move node to last
//        LinkedHashMap.Entry<K,V> last;
//        if (accessOrder && (last = tail) != e) {
//            LinkedHashMap.Entry<K,V> p =
//                    (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
//            p.after = null;
//            if (b == null)
//                head = a;
//            else
//                b.after = a;
//            if (a != null)
//                a.before = b;
//            else
//                last = b;
//            if (last == null)
//                head = p;
//            else {
//                p.before = last;
//                last.after = p;
//            }
//            tail = p;
//            ++modCount;
//        }
//    }

    /**
     * get方法也是多了：判断是否为访问顺序~~~
     *
     * 首先我们来看看已插入顺序来进行插入和遍历：
     *
     * 插入的顺序和遍历的顺序是一致的
     * */
    @Test
    public void insertOrder() {
        // 默认是插入顺序
        LinkedHashMap<Integer,String>  insertOrder = new LinkedHashMap();

        String value = "zhaofu";
        int i = 0;

        insertOrder.put(i++, value);
        insertOrder.put(i++, value);
        insertOrder.put(i++, value);
        insertOrder.put(i++, value);
        insertOrder.put(i++, value);

        //遍历
        Set<Integer> set = insertOrder.keySet();
        for (Integer s : set) {
            String mapValue = insertOrder.get(s);
            System.out.println(s + "---" + mapValue);
        }
    }

    /**
     * 接着，我们来测试一下以访问顺序来进行插入和遍历：
     * */
    @Test
    public void accessOrder() {
        // 设置为访问顺序的方式
//        LinkedHashMap<Integer,String> accessOrder = new LinkedHashMap(16, 0.75f, true);
//        String value = "zhaofu";
//        int i = 0;
//        accessOrder.put(i++, value);
//        accessOrder.put(i++, value);
//        accessOrder.put(i++, value);
//        accessOrder.put(i++, value);
//        accessOrder.put(i++, value);
//        // 遍历
//        Set<Integer> sets = accessOrder.keySet();
//        for (Integer key : sets) {
//            String mapValue = accessOrder.get(key);
//            System.out.println(key + "---" + mapValue);
//        }
    /**
     *
     * 上面代码看似是没有问题，但是运行会出错的！
     *
     * 前面在看源码注释的时候我们就发现了：在AccessOrder的情况下，使用get方法也是结构性的修改！
     *
     * 为了简单看出他俩的区别，下面我就直接用key来进行看了~
     *
     * 以下是访问顺序的测试：
     *
     * */
        // 设置为访问顺序的方式
        LinkedHashMap<Integer,String> accessOrder = new LinkedHashMap(16, 0.75f, true);

        String value = "zhaofu";
        int i = 0;
        accessOrder.put(i++, value);
        accessOrder.put(i++, value);
        accessOrder.put(i++, value);
        accessOrder.put(i++, value);
        accessOrder.put(i++, value);

        // 访问一下key为3的元素再进行遍历
        accessOrder.get(3);

        // 遍历
        Set<Integer> sets = accessOrder.keySet();
        for (Integer key : sets) {
            System.out.println(key );
            //可以发现我们的3就在遍历结果的最后了
        }

    }
    /**
     * 我们可以这样理解：最常用的将其放在链表的最后，不常用的放在链表的最前~
     *
     * 这个知识点以我的理解而言，它这个访问顺序在LinkedHashMap如果不重写用处并不大~它是用来给别的实现进行扩展的
     *
     * 因为最常被使用的元素再遍历的时候却放在了最后边，在LinkedHashMap中我也没找到对应的方法来进行调用~
     *
     * 一个removeEldestEntry(Map.Entry<K,V> eldest)方法，重写它可以删除最久未被使用的元素！！
     *
     * 还有一个是afterNodeInsertion(boolean evict)方法，新增时判断是否需要删除最久未被使用的元素！！
     *
     *
     *
     * 去网上搜了几篇资料，都是讲LRUMap的实现的(也就是对LinkedHashMap进行扩展)，有兴趣的同学可参考下列链接：
     *
     * https://blog.csdn.net/exceptional_derek/article/details/11713255
     *
     * http://www.php.cn/java-article-362041.html
     *
     * https://www.jianshu.com/p/1a66529e1a2e
     *
     * https://mp.weixin.qq.com/s?__biz=MzI4Njc5NjM1NQ%3D%3D&chksm=ebd639d5dca1b0c3ba5a26bd46d265544f4fdd468df6465e54d93da230c3457d4947e79eaf0c&idx=1&mid=2247485177&sn=93cfa2c2e6f3e5092e5850bdb5ea4cc3
     *
     * */

/**
 *
 * 1.6 remove方法
 *
 * 对于remove方法，在LinkedHashMap中也没有重写，它调用的还是父类的HashMap的remove()方法，
 *
 * 在LinkedHashMap中重写的是：afterNodeRemoval(Node<K,V> e)这个方法
 * */

//    void afterNodeRemoval(Node<K,V> e) { // unlink
//        LinkedHashMap.Entry<K,V> p =
//                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
//        p.before = p.after = null;
//        if (b == null)
//            head = a;
//        else
//            b.after = a;
//        if (a == null)
//            tail = b;
//        else
//            a.before = b;
//    }


/**1.7 遍历的方法*/

    /**
     * Set<Map.Entry<K,V>> entrySet()是被重写的了
     *
     * 重写为LinkedEntrySet
     * */
//    public Set<Map.Entry<K,V>> entrySet() {
//        Set<Map.Entry<K,V>> es;
//        return (es = entrySet) == null ? (entrySet = new LinkedEntrySet()) : es;
//    }

    // Iterators
//    abstract class LinkedHashIterator {
//        LinkedHashMap.Entry<K,V> next;
//        LinkedHashMap.Entry<K,V> current;
//        int expectedModCount;
//
//        LinkedHashIterator() {
//            next = head;
//            expectedModCount = modCount;
//            current = null;
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//        final LinkedHashMap.Entry<K,V> nextNode() {//从内部维护的双链表头开始循环输出
//            LinkedHashMap.Entry<K,V> e = next;
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            if (e == null)
//                throw new NoSuchElementException();
//            current = e;
//            next = e.after;
//            return e;
//        }
//        public final void remove() {
//            Node<K,V> p = current;
//            if (p == null)
//                throw new IllegalStateException();
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            current = null;
//            K key = p.key;
//            removeNode(hash(key), key, null, false, false);
//            expectedModCount = modCount;
//        }
//    }


    /**
     * 看到了这里，我们就知道为啥注释说：初始容量对遍历没有影响
     *
     * 因为它遍历的是LinkedHashMap内部维护的一个双向链表，而不是散列表(当然了，链表双向链表的元素都来源于散列表)
     * */

    /**
     *
     *
     * 二、总结
     * LinkedHashMap比HashMap多了一个双向链表的维护，在数据结构而言它要复杂一些，阅读源码起来比较轻松一些，因为大多都由HashMap实现了..
     *
     * 阅读源码的时候我们会发现多态是无处不在的~子类用父类的方法，子类重写了父类的部分方法即可达到不一样的效果！
     *
     * 比如：LinkedHashMap并没有重写put方法，而put方法内部的newNode()方法重写了。
     *      LinkedHashMap调用父类的put方法，里面回调的是重写后的newNode()，从而达到目的！
     *
     * LinkedHashMap可以设置两种遍历顺序：
     *
     * - 访问顺序（access-ordered）
     *
     * - 插入顺序（insertion-ordered）
     *
     * - 默认是插入顺序的
     *
     * 对于访问顺序，它是LRU(最近最少使用)算法的实现，要使用它要么重写LinkedListMap的几个方法
     * (removeEldestEntry(Map.Entry<K,V> eldest)和afterNodeInsertion(boolean evict))，
     * 要么是扩展成LRUMap来使用，不然设置为访问顺序（access-ordered）的用处不大~
     *
     * LinkedHashMap遍历的是内部维护的双向链表，所以说初始容量对LinkedHashMap遍历是不受影响的
     *
     * 参考资料：
     *
     * 《Core Java》
     *
     * https://blog.csdn.net/zxt0601/article/details/77429150
     *
     * https://blog.csdn.net/panweiwei1994/article/details/76555359
     *
     * https://zhuanlan.zhihu.com/p/28216267
     *
     * https://blog.csdn.net/fan2012huan/article/details/51097331
     *
     * https://www.cnblogs.com/chinajava/p/5808416.html
     * */
}
