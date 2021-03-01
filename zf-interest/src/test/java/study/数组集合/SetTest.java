package study.数组集合;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

/**
 * @ClassName SetTest
 * @Author zhaofu
 * @Date 2021/2/18
 * @Version V1.0
 * <p>
 * HashSet集合
 * <p>
 * A:底层数据结构是哈希表(是一个元素为链表的数组) + 红黑树
 * --------------------
 * TreeSet集合
 * <p>
 * A:底层数据结构是红黑树(是一个自平衡的二叉树)
 * <p>
 * B:保证元素的排序方式
 * --------------------
 * LinkedHashSet集合
 * <p>
 * A:：底层数据结构由哈希表(是一个元素为链表的数组)和双向链表组成。
 * <p>
 * 这篇主要来看看它们比较重要的方法是如何实现的，需要注意些什么，最后比较一下哪个时候用哪个～
 * <p>
 * 强调：在学习本文之前，最好是看过Map系列的文章
 **/
public class SetTest {

    @Test
    public void HashSetTest() {
        HashSet<Object> hashSet = new HashSet<>();
        /**
         * ----->实现Set接口
         * -----》实际就是HashMap
         * -----》不保证迭代顺序
         * -----》允许为null
         * This class implements the <tt>Set</tt> interface, backed by a hash table
         * (actually a <tt>HashMap</tt> instance).  It makes no guarantees as to the
         * iteration order of the set; in particular, it does not guarantee that the
         * order will remain constant over time.  This class permits the <tt>null</tt>
         * element.
         *
         * -----》如果很看重遍历性能，不要将初始容量设置的太高
         * <p>This class offers constant time performance for the basic operations
         * (<tt>add</tt>, <tt>remove</tt>, <tt>contains</tt> and <tt>size</tt>),
         * assuming the hash function disperses the elements properly among the
         * buckets.  Iterating over this set requires time proportional to the sum of
         * the <tt>HashSet</tt> instance's size (the number of elements) plus the
         * "capacity" of the backing <tt>HashMap</tt> instance (the number of
         * buckets).  Thus, it's very important not to set the initial capacity too
         * high (or the load factor too low) if iteration performance is important.
         *
         * -----》非同步，若要可用Set s = Collections.synchronizedSet(new HashSet(...));
         * <p><strong>Note that this implementation is not synchronized.</strong>
         * If multiple threads access a hash set concurrently, and at least one of
         * the threads modifies the set, it <i>must</i> be synchronized externally.
         * This is typically accomplished by synchronizing on some object that
         * naturally encapsulates the set.
         *
         * If no such object exists, the set should be "wrapped" using the
         * {@link Collections#synchronizedSet Collections.synchronizedSet}
         * method.  This is best done at creation time, to prevent accidental
         * unsynchronized access to the set:<pre>
         *   Set s = Collections.synchronizedSet(new HashSet(...));</pre>
         *
         * -----》迭代器相关
         * <p>The iterators returned by this class's <tt>iterator</tt> method are
         * <i>fail-fast</i>: if the set is modified at any time after the iterator is
         * created, in any way except through the iterator's own <tt>remove</tt>
         * method, the Iterator throws a {@link ConcurrentModificationException}.
         * Thus, in the face of concurrent modification, the iterator fails quickly
         * and cleanly, rather than risking arbitrary, non-deterministic behavior at
         * an undetermined time in the future.
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
         *
         *
         * 从顶部注释来看，我们就可以归纳HashSet的要点了：
         *
         * - 实现Set接口
         *
         * - 不保证迭代顺序
         *
         * - 允许元素为null
         *
         * - 底层实际上是一个HashMap实例
         *
         * - 非同步
         *
         * - 初始容量非常影响迭代性能
         *
         * 我本来也是想在写完List集合就转到Set集合的了，可是：看到底层实际上是一个HashMap实例时，我就去学习Map集合先了~
         *
         *
         * 顶部注释说底层实际上是一个HashMap实例，那证据呢？
         */
        /**
         * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
         * default initial capacity (16) and load factor (0.75).
         */
//        public HashSet() {
//            map = new HashMap<>();
//        }
        /**
         * 对于学习过HashMap的人来说，简直简单得让人开心，哈哈哈~
         *
         * 我们知道Map是一个映射，有key有value，既然HashSet底层用的是HashMap，那么value在哪里呢？？？
         * */
        // Dummy value to associate with an Object in the backing Map
//        private static final Object PRESENT = new Object();
        /**
         *
         * value是一个Object，所有的value都是它
         *
         * 所以可以直接总结出：
         * HashSet实际上就是封装了HashMap，操作HashSet元素实际上就是操作HashMap。
         * 这也是面向对象的一种体现，重用性贼高！
         *
         * */
    }


    @Test
    public void TreeSetTest() {
        TreeSet<Object> treeSet = new TreeSet<>();

        /**
         * -----》底层是TreeMap，实现了NavigableSet接口
         * -----》根据Comparable在实现自然排序，或者在常见对象时，使用构造方法传递Comparator对象实现排序
         * A {@link NavigableSet} implementation based on a {@link TreeMap}.
         * The elements are ordered using their {@linkplain Comparable natural
         * ordering}, or by a {@link Comparator} provided at set creation
         * time, depending on which constructor is used.
         *
         * -----》时间复杂度保证log(n)
         * <p>This implementation provides guaranteed log(n) time cost for the basic
         * operations ({@code add}, {@code remove} and {@code contains}).
         *
         * -----》通过compareTo或者compare方法认证元素是否相等
         * <p>Note that the ordering maintained by a set (whether or not an explicit
         * comparator is provided) must be <i>consistent with equals</i> if it is to
         * correctly implement the {@code Set} interface.  (See {@code Comparable}
         * or {@code Comparator} for a precise definition of <i>consistent with
         * equals</i>.)  This is so because the {@code Set} interface is defined in
         * terms of the {@code equals} operation, but a {@code TreeSet} instance
         * performs all element comparisons using its {@code compareTo} (or
         * {@code compare}) method, so two elements that are deemed equal by this method
         * are, from the standpoint of the set, equal.  The behavior of a set
         * <i>is</i> well-defined even if its ordering is inconsistent with equals; it
         * just fails to obey the general contract of the {@code Set} interface.
         *
         * -----》非同步
         * <p><strong>Note that this implementation is not synchronized.</strong>
         * If multiple threads access a tree set concurrently, and at least one
         * of the threads modifies the set, it <i>must</i> be synchronized
         * externally.  This is typically accomplished by synchronizing on some
         * object that naturally encapsulates the set.
         * If no such object exists, the set should be "wrapped" using the
         * {@link Collections#synchronizedSortedSet Collections.synchronizedSortedSet}
         * method.  This is best done at creation time, to prevent accidental
         * unsynchronized access to the set: <pre>
         *   SortedSet s = Collections.synchronizedSortedSet(new TreeSet(...));</pre>
         *
         * -----》迭代器相关
         * <p>The iterators returned by this class's {@code iterator} method are
         * <i>fail-fast</i>: if the set is modified at any time after the iterator is
         * created, in any way except through the iterator's own {@code remove}
         * method, the iterator will throw a {@link ConcurrentModificationException}.
         * Thus, in the face of concurrent modification, the iterator fails quickly
         * and cleanly, rather than risking arbitrary, non-deterministic behavior at
         * an undetermined time in the future.
         *
         * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
         * as it is, generally speaking, impossible to make any hard guarantees in the
         * presence of unsynchronized concurrent modification.  Fail-fast iterators
         * throw {@code ConcurrentModificationException} on a best-effort basis.
         * Therefore, it would be wrong to write a program that depended on this
         * exception for its correctness:   <i>the fail-fast behavior of iterators
         * should be used only to detect bugs.</i>
         *
         * <p>This class is a member of the
         * <a href="{@docRoot}/../technotes/guides/collections/index.html">
         * Java Collections Framework</a>.
         *
         * 从顶部注释来看，我们就可以归纳TreeSet的要点了：
         *
         * - 实现NavigableSet接口
         *
         * - 可以实现排序功能
         *
         * - 底层实际上是一个TreeMap实例
         *
         * - 非同步
         */

        // Dummy value to associate with an Object in the backing Map
//        private static final Object PRESENT = new Object();//Map的value

        /**
         * Constructs a set backed by the specified navigable map.
         */
//        TreeSet(NavigableMap<E,Object> m) {
//            this.m = m;
//        }

        /**
         * Constructs a new, empty tree set, sorted according to the
         * natural ordering of its elements.  All elements inserted into
         * the set must implement the {@link Comparable} interface.
         * Furthermore, all such elements must be <i>mutually
         * comparable</i>: {@code e1.compareTo(e2)} must not throw a
         * {@code ClassCastException} for any elements {@code e1} and
         * {@code e2} in the set.  If the user attempts to add an element
         * to the set that violates this constraint (for example, the user
         * attempts to add a string element to a set whose elements are
         * integers), the {@code add} call will throw a
         * {@code ClassCastException}.
         *
         * 实例是TreeSet
         */
//        public TreeSet() {
//            this(new TreeMap<E,Object>());
//        }
    }


    @Test
    public void LinkedHashSetTest() {
        LinkedHashSet<Object> linkedHashSet = new LinkedHashSet<>();
        /**
         *
         * ----->迭代有序
         * -----》维护了一个双向链表
         * <p>Hash table and linked list implementation of the <tt>Set</tt> interface,
         * with predictable iteration order.  This implementation differs from
         * <tt>HashSet</tt> in that it maintains a doubly-linked list running through
         * all of its entries.  This linked list defines the iteration ordering,
         * which is the order in which elements were inserted into the set
         * (<i>insertion-order</i>).  Note that insertion order is <i>not</i> affected
         * if an element is <i>re-inserted</i> into the set.  (An element <tt>e</tt>
         * is reinserted into a set <tt>s</tt> if <tt>s.add(e)</tt> is invoked when
         * <tt>s.contains(e)</tt> would return <tt>true</tt> immediately prior to
         * the invocation.)
         *
         * -----》需要插入顺序时，才会用到它
         * <p>This implementation spares its clients from the unspecified, generally
         * chaotic ordering provided by {@link HashSet}, without incurring the
         * increased cost associated with {@link TreeSet}.  It can be used to
         * produce a copy of a set that has the same order as the original, regardless
         * of the original set's implementation:
         * <pre>
         *     void foo(Set s) {
         *         Set copy = new LinkedHashSet(s);
         *         ...
         *     }
         * </pre>
         * This technique is particularly useful if a module takes a set on input,
         * copies it, and later returns results whose order is determined by that of
         * the copy.  (Clients generally appreciate having things returned in the same
         * order they were presented.)
         *
         * -----》允许为null，拥有Set的全部方法
         * -----》性能比HashSet差一点，因为要维护双向链表
         * <p>This class provides all of the optional <tt>Set</tt> operations, and
         * permits null elements.  Like <tt>HashSet</tt>, it provides constant-time
         * performance for the basic operations (<tt>add</tt>, <tt>contains</tt> and
         * <tt>remove</tt>), assuming the hash function disperses elements
         * properly among the buckets.  Performance is likely to be just slightly
         * below that of <tt>HashSet</tt>, due to the added expense of maintaining the
         * linked list, with one exception: Iteration over a <tt>LinkedHashSet</tt>
         * requires time proportional to the <i>size</i> of the set, regardless of
         * its capacity.  Iteration over a <tt>HashSet</tt> is likely to be more
         * expensive, requiring time proportional to its <i>capacity</i>.
         *
         * -----》迭代与初始容量无关，它迭代的是双向链表
         * <p>A linked hash set has two parameters that affect its performance:
         * <i>initial capacity</i> and <i>load factor</i>.  They are defined precisely
         * as for <tt>HashSet</tt>.  Note, however, that the penalty for choosing an
         * excessively high value for initial capacity is less severe for this class
         * than for <tt>HashSet</tt>, as iteration times for this class are unaffected
         * by capacity.
         *
         * -----》非同步，若需要Set s = Collections.synchronizedSet(new LinkedHashSet(...));
         * <p><strong>Note that this implementation is not synchronized.</strong>
         * If multiple threads access a linked hash set concurrently, and at least
         * one of the threads modifies the set, it <em>must</em> be synchronized
         * externally.  This is typically accomplished by synchronizing on some
         * object that naturally encapsulates the set.
         *
         * If no such object exists, the set should be "wrapped" using the
         * {@link Collections#synchronizedSet Collections.synchronizedSet}
         * method.  This is best done at creation time, to prevent accidental
         * unsynchronized access to the set: <pre>
         *   Set s = Collections.synchronizedSet(new LinkedHashSet(...));</pre>
         *
         * -----》迭代相关
         * <p>The iterators returned by this class's <tt>iterator</tt> method are
         * <em>fail-fast</em>: if the set is modified at any time after the iterator
         * is created, in any way except through the iterator's own <tt>remove</tt>
         * method, the iterator will throw a {@link ConcurrentModificationException}.
         * Thus, in the face of concurrent modification, the iterator fails quickly
         * and cleanly, rather than risking arbitrary, non-deterministic behavior at
         * an undetermined time in the future.
         *
         * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
         * as it is, generally speaking, impossible to make any hard guarantees in the
         * presence of unsynchronized concurrent modification.  Fail-fast iterators
         * throw <tt>ConcurrentModificationException</tt> on a best-effort basis.
         * Therefore, it would be wrong to write a program that depended on this
         * exception for its correctness:   <i>the fail-fast behavior of iterators
         * should be used only to detect bugs.</i>
         *
         * <p>This class is a member of the
         * <a href="{@docRoot}/../technotes/guides/collections/index.html">
         * Java Collections Framework</a>.
         *
         *
         * 从顶部注释来看，我们就可以归纳LinkedHashSet的要点了：
         *
         * - 迭代是有序的
         *
         * - 允许为null
         *
         * - 底层实际上是一个HashMap+双向链表实例(其实就是LinkedHashMap)…
         *
         * - 非同步
         *
         * - 性能比HashSet差一丢丢，因为要维护一个双向链表
         *
         * - 初始容量与迭代无关，LinkedHashSet迭代的是双向链表
         */

    }

    /**
     * 四、总结
     * 可以很明显地看到，Set集合的底层就是Map，所以我都没有做太多的分析在上面，也没什么好分析的了。
     *
     * 下面总结一下Set集合常用的三个子类吧：
     *
     * HashSet：
     *
     * 无序，允许为null，底层是HashMap(散列表+红黑树)，非线程同步
     *
     * TreeSet：
     *
     * 有序，不允许为null，底层是TreeMap(红黑树),非线程同步
     *
     * LinkedHashSet：
     *
     * 迭代有序，允许为null，底层是HashMap+双向链表，非线程同步
     *
     * 从结论而言我们就可以根据自己的实际情况来使用了。
     *
     * 参考资料：
     *
     * https://zhuanlan.zhihu.com/p/29021276
     *
     * https://blog.csdn.net/panweiwei1994/article/details/76555359
     * */

}
