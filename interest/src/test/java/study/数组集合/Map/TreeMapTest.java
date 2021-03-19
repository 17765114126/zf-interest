package study.数组集合.Map;

import com.example.springboot.model.Student;
import org.junit.Test;

import java.util.*;

/**
 * @ClassName TreeMapTest
 * @Author zhaofu
 * @Date 2021/2/20
 * @Version V1.0
 **/
public class TreeMapTest {

    /**
     *
     * 一、TreeMap剖析
     *
     * -----》底层是红黑树，实现NavigableMap这个接口，可以根据key自然排序，也可以在构造方法上传递Comparator实现Map的排序
     * A Red-Black tree based {@link NavigableMap} implementation.
     * The map is sorted according to the {@linkplain Comparable natural
     * ordering} of its keys, or by a {@link Comparator} provided at map
     * creation time, depending on which constructor is used.
     *
     * -----》时间复杂度为log(n)
     * <p>This implementation provides guaranteed log(n) time cost for the
     * {@code containsKey}, {@code get}, {@code put} and {@code remove}
     * operations.  Algorithms are adaptations of those in Cormen, Leiserson, and
     * Rivest's <em>Introduction to Algorithms</em>.
     *
     * -----》有序的Map一般是使用compareTo或者compare方法来比较key，只要该两个方法认为相等，在map的角度就认为这两个元素相等
     * <p>Note that the ordering maintained by a tree map, like any sorted map, and
     * whether or not an explicit comparator is provided, must be <em>consistent
     * with {@code equals}</em> if this sorted map is to correctly implement the
     * {@code Map} interface.  (See {@code Comparable} or {@code Comparator} for a
     * precise definition of <em>consistent with equals</em>.)  This is so because
     * the {@code Map} interface is defined in terms of the {@code equals}
     * operation, but a sorted map performs all key comparisons using its {@code
     * compareTo} (or {@code compare}) method, so two keys that are deemed equal by
     * this method are, from the standpoint of the sorted map, equal.  The behavior
     * of a sorted map <em>is</em> well-defined even if its ordering is
     * inconsistent with {@code equals}; it just fails to obey the general contract
     * of the {@code Map} interface.
     *
     * -----》 非同步 若要同步SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));
     * <p><strong>Note that this implementation is not synchronized.</strong>
     * If multiple threads access a map concurrently, and at least one of the
     * threads modifies the map structurally, it <em>must</em> be synchronized
     * externally.  (A structural modification is any operation that adds or
     * deletes one or more mappings; merely changing the value associated
     * with an existing key is not a structural modification.)  This is
     * typically accomplished by synchronizing on some object that naturally
     * encapsulates the map.
     * If no such object exists, the map should be "wrapped" using the
     * {@link Collections#synchronizedSortedMap Collections.synchronizedSortedMap}
     * method.  This is best done at creation time, to prevent accidental
     * unsynchronized access to the map: <pre>
     *   SortedMap m = Collections.synchronizedSortedMap(new TreeMap(...));</pre>
     *
     * -----》迭代器内容
     * <p>The iterators returned by the {@code iterator} method of the collections
     * returned by all of this class's "collection view methods" are
     * <em>fail-fast</em>: if the map is structurally modified at any time after
     * the iterator is created, in any way except through the iterator's own
     * {@code remove} method, the iterator will throw a {@link
     * ConcurrentModificationException}.  Thus, in the face of concurrent
     * modification, the iterator fails quickly and cleanly, rather than risking
     * arbitrary, non-deterministic behavior at an undetermined time in the future.
     *
     * <p>Note that the fail-fast behavior of an iterator cannot be guaranteed
     * as it is, generally speaking, impossible to make any hard guarantees in the
     * presence of unsynchronized concurrent modification.  Fail-fast iterators
     * throw {@code ConcurrentModificationException} on a best-effort basis.
     * Therefore, it would be wrong to write a program that depended on this
     * exception for its correctness:   <em>the fail-fast behavior of iterators
     * should be used only to detect bugs.</em>
     *
     * <p>All {@code Map.Entry} pairs returned by methods in this class
     * and its views represent snapshots of mappings at the time they were
     * produced. They do <strong>not</strong> support the {@code Entry.setValue}
     * method. (Note however that it is possible to change mappings in the
     * associated map using {@code put}.)
     *
     * <p>This class is a member of the
     * <a href="{@docRoot}/../technotes/guides/collections/index.html">
     * Java Collections Framework</a>.
     */

    /**
     *
     * 在注释中提到的要点，我来总结一下：
     *
     * - TreeMap实现了NavigableMap接口，而NavigableMap接口继承着SortedMap接口，致使我们的TreeMap是有序的！
     *
     * - TreeMap底层是红黑树，它方法的时间复杂度都不会太高:log(n)~
     *
     * - 非同步
     *
     * - 使用Comparator或者Comparable来比较key是否相等与排序的问题~
     *
     * 对我而言，Comparator和Comparable我都忘得差不多了~~~
     * 下面就开始看TreeMap的源码来看看它是怎么实现的，并且回顾一下Comparator和Comparable的用法吧！
     *
     * */

/**1.1TreeMap的域*/

    /**
     * The comparator used to maintain order in this tree map, or
     * null if it uses the natural ordering of its keys.
     * comparator维护了一个变量，如果该变量为null，则使用自然排序
     */
//    private final Comparator<? super K> comparator;

//    private transient Entry<K,V> root;//红黑树的根节点

    /**
     * The number of entries in the tree
     * 红黑树的大小
     */
//    private transient int size = 0;

    /**
     * The number of structural modifications to the tree.
     * 结构性修改的次数
     */
//    private transient int modCount = 0;


/**1.2 TreeMap构造方法*/

    /**
     * TreeMap的构造方法有4个：
     *
     * 可以发现，TreeMap的构造方法大多数与comparator有关：
     *
     * Constructs a new, empty tree map, using the natural ordering of its
     * keys.  All keys inserted into the map must implement the {@link
     * Comparable} interface.  Furthermore, all such keys must be
     * <em>mutually comparable</em>: {@code k1.compareTo(k2)} must not throw
     * a {@code ClassCastException} for any keys {@code k1} and
     * {@code k2} in the map.  If the user attempts to put a key into the
     * map that violates this constraint (for example, the user attempts to
     * put a string key into a map whose keys are integers), the
     * {@code put(Object key, Object value)} call will throw a
     * {@code ClassCastException}.
     *
     * comparator维护的变量为null
     */
//    public TreeMap() {
//        comparator = null;
//    }

    /**
     * Constructs a new, empty tree map, ordered according to the given
     * comparator.  All keys inserted into the map must be <em>mutually
     * comparable</em> by the given comparator: {@code comparator.compare(k1,
     * k2)} must not throw a {@code ClassCastException} for any keys
     * {@code k1} and {@code k2} in the map.  If the user attempts to put
     * a key into the map that violates this constraint, the {@code put(Object
     * key, Object value)} call will throw a {@code ClassCastException}.
     *
     * comparator维护的变量设置为传递进来的
     */
//    public TreeMap(Comparator<? super K> comparator) {
//        this.comparator = comparator;
//    }

    /**
     * Constructs a new tree map containing the same mappings as the given
     * map, ordered according to the <em>natural ordering</em> of its keys.
     * All keys inserted into the new map must implement the {@link
     * Comparable} interface.  Furthermore, all such keys must be
     * <em>mutually comparable</em>: {@code k1.compareTo(k2)} must not throw
     * a {@code ClassCastException} for any keys {@code k1} and
     * {@code k2} in the map.  This method runs in n*log(n) time.
     *
     * 使用putAll()将Map转化为TreeMap
     */
//    public TreeMap(Map<? extends K, ? extends V> m) {
//        comparator = null;
//        putAll(m);
//    }

    /**
     * Constructs a new tree map containing the same mappings and
     * using the same ordering as the specified sorted map.  This
     * method runs in linear time.
     *
     * 如果是SortedMap，那就使用buildFromSorted()来转TreeMap
     */
//    public TreeMap(SortedMap<K, ? extends V> m) {
//        comparator = m.comparator();
//        try {
//            buildFromSorted(m.size(), m.entrySet().iterator(), null, null);
//        } catch (java.io.IOException cannotHappen) {
//        } catch (ClassNotFoundException cannotHappen) {
//        }
//    }

    /**
     * 也就是顶部注释说的：TreeMap有序是通过Comparator来进行比较的，如果comparator为null，那么就使用自然顺序~
     * <p>
     * 打个比方：
     * <p>
     * 如果value是整数，自然顺序指的就是我们平常排序的顺序(1,2,3,4,5..)~
     */
    @Test
    public void Test1() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(1, 5);
        treeMap.put(2, 4);
        treeMap.put(3, 3);
        treeMap.put(4, 2);
        treeMap.put(5, 1);
        for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
            String s = entry.getKey() + "zhaofu---->" + entry.getValue();
            //没有实现comparator，默认按照key的自然顺序排序
            System.out.println(s);
        }
    }

/**1.3 put方法*/

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     */
//    public V put(K key, V value) {
//        TreeMap.Entry<K,V> t = root;
//        if (t == null) {//判断key是否为空类型
//            compare(key, key); // type (and possibly null) check
//
//            root = new TreeMap.Entry<>(key, value, null);//红黑树为null，新建红黑树
//            size = 1;
//            modCount++;
//            return null;
//        }
//        int cmp;
//        TreeMap.Entry<K,V> parent;
//        // split comparator and comparable paths
//        Comparator<? super K> cpr = comparator;
//        if (cpr != null) {//comparator比较找到合适的位置插入到红黑树中
//            do {
//                parent = t;
//                cmp = cpr.compare(key, t.key);
//                if (cmp < 0)
//                    t = t.left;
//                else if (cmp > 0)
//                    t = t.right;
//                else
//                    return t.setValue(value);
//            } while (t != null);
//        }
//        else {
    //如果comparator为null，则使用key作为比较器进行比较，并且key必须实现Comparable接口
//            if (key == null)//key不能为null
//                throw new NullPointerException();
//            @SuppressWarnings("unchecked")
//            Comparable<? super K> k = (Comparable<? super K>) key;
//            do {
//                parent = t;
//                cmp = k.compareTo(t.key);
    //找到合适的位置插入到红黑树中
//                if (cmp < 0)
//                    t = t.left;
//                else if (cmp > 0)
//                    t = t.right;
//                else
//                    return t.setValue(value);
//            } while (t != null);
//        }
    //创建新节点，找到父节点位置
//        TreeMap.Entry<K,V> e = new TreeMap.Entry<>(key, value, parent);
//        if (cmp < 0)
//            parent.left = e;
//        else
//            parent.right = e;
//        fixAfterInsertion(e);//调整红黑树，使得红黑树平衡
//        size++;
//        modCount++;
//        return null;
//    }

    /**
     *下面是compare(Object k1, Object k2)方法
     *
     * Compares two keys using the correct comparison method for this TreeMap.
     */
//    @SuppressWarnings("unchecked")
//    final int compare(Object k1, Object k2) {
//        return comparator==null ? ((Comparable<? super K>)k1).compareTo((K)k2)
//                : comparator.compare((K)k1, (K)k2);
//    }
    /**
     * 如果我们设置key为null，会抛出异常的，就不执行下面的代码了。
     * */

/**1.4 get方法*/

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code key} compares
     * equal to {@code k} according to the map's ordering, then this
     * method returns {@code v}; otherwise it returns {@code null}.
     * (There can be at most one such mapping.)
     *
     * <p>A return value of {@code null} does not <em>necessarily</em>
     * indicate that the map contains no mapping for the key; it's also
     * possible that the map explicitly maps the key to {@code null}.
     * The {@link #containsKey containsKey} operation may be used to
     * distinguish these two cases.
     *
     */
//    public V get(Object key) {
//        TreeMap.Entry<K,V> p = getEntry(key);
//        return (p==null ? null : p.value);//找到返回的value，找不到返回null
//    }
    /**
     * Returns this map's entry for the given key, or {@code null} if the map
     * does not contain an entry for the key.
     */
//    final Entry<K,V> getEntry(Object key) {
//        // Offload comparator-based version for sake of performance
//        if (comparator != null)//如果comparator不为null调的是getEntryUsingComparator(key)
//            return getEntryUsingComparator(key);
//        if (key == null)
//            throw new NullPointerException();
//        @SuppressWarnings("unchecked")
//        Comparable<? super K> k = (Comparable<? super K>) key;
//        Entry<K,V> p = root;
//        while (p != null) {//不然使用的就是compareTo()方法
//            int cmp = k.compareTo(p.key);
    //        从红黑树中找到对应的值，返回出去
//            if (cmp < 0)
//                p = p.left;
//            else if (cmp > 0)
//                p = p.right;
//            else
//                return p;
//        }
//        return null;
//    }

    /**
     * 如果Comparator不为null，接下来我们进去看看getEntryUsingComparator(Object key)，是怎么实现的
     * Version of getEntry using comparator. Split off from getEntry
     * for performance. (This is not worth doing for most methods,
     * that are less dependent on comparator performance, but is
     * worthwhile here.)
     */
//    final Entry<K,V> getEntryUsingComparator(Object key) {
//        @SuppressWarnings("unchecked")
//        K k = (K) key;
//        Comparator<? super K> cpr = comparator;
//        if (cpr != null) {
//            Entry<K,V> p = root;
//            while (p != null) {//只不过调用的是comparator自己实现的方法来获取对应的位置，总体逻辑与外面没什么区别
//                int cmp = cpr.compare(k, p.key);
//                if (cmp < 0)
//                    p = p.left;
//                else if (cmp > 0)
//                    p = p.right;
//                else
//                    return p;
//            }
//        }
//        return null;
//    }

/**1.5remove方法*/

    /**
     * Removes the mapping for this key from this TreeMap if present.
     */
//    public V remove(Object key) {
//        Entry<K,V> p = getEntry(key);//找到这个节点的位置
//        if (p == null)
//            return null;
//
//        V oldValue = p.value;//记录这个想要删除的值
//        deleteEntry(p);//删除这个节点
//        return oldValue;
//    }
/**
 * 删除节点的时候调用的是deleteEntry(Entry<K,V> p)方法，这个方法主要是删除节点并且平衡红黑树
 *
 * 平衡红黑树的代码是比较复杂的，我就不说了，你们去看吧(反正我看不懂)….
 * */

/**1.6遍历方法*/

    /**
     * 在看源码的时候可能不知道哪个是核心的遍历方法，因为Iterator有非常非常多~
     * debug一下看看，跟下去就好！
     * 于是乎，我们可以找到：TreeMap遍历是使用EntryIterator这个内部类的
     *
     * 可以发现，EntryIterator大多的实现都是在父类中：
     */

//    final class EntryIterator extends PrivateEntryIterator<Map.Entry<K, V>> {
//        EntryIterator(Entry<K, V> first) {
//            super(first);
//        }
//        public Map.Entry<K, V> next() {
//            return nextEntry();
//        }
//    }
    /**
     * Base class for TreeMap Iterators
     */
//    abstract class PrivateEntryIterator<T> implements Iterator<T> {
//        TreeMap.Entry<K,V> next;
//        TreeMap.Entry<K,V> lastReturned;//推断出这是返回的节点
//        int expectedModCount;
//
//        PrivateEntryIterator(TreeMap.Entry<K,V> first) {
//            expectedModCount = modCount;
//            lastReturned = null;
//            next = first;
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final TreeMap.Entry<K,V> nextEntry() {
//            TreeMap.Entry<K,V> e = next;
//            if (e == null)
//                throw new NoSuchElementException();
//            if (modCount != expectedModCount)
//                throw new ConcurrentModificationException();
//            next = successor(e);//获取下一个节点
//            lastReturned = e;
//            return e;
//        }
    /**
     * Returns the successor of the specified Entry, or null if no such.
     *
     * successor 其实就是一个结点的 下一个结点，所谓 下一个，是按次序排序后的下一个结点。
     * 从代码中可以看出，如果右子树不为空，就返回右子树中最小结点。
     * 如果右子树为空，就要向上回溯了。
     * 在这种情况下，t 是以其为根的树的最后一个结点。
     * 如果它是其父结点的左孩子，那么父结点就是它的下一个结点，否则，t 就是以其父结点为根的树的最后一个结点，需要再次向上回溯。
     * 一直到 ch 是 p 的右孩子为止。
     *来源：https://blog.csdn.net/on_1y/article/details/27231855
     */
//    static <K,V> TreeMap.Entry<K,V> successor(TreeMap.Entry<K,V> t) {
//        if (t == null)
//            return null;
//        else if (t.right != null) {
//            TreeMap.Entry<K,V> p = t.right;
//            while (p.left != null)
//                p = p.left;
//            return p;
//        } else {
//            TreeMap.Entry<K,V> p = t.parent;
//            TreeMap.Entry<K,V> ch = t;
//            while (p != null && ch == p.right) {
//                ch = p;
//                p = p.parent;
//            }
//            return p;
//        }
//    }


    /**
     *
     * 二、总结
     * TreeMap底层是红黑树，能够实现该Map集合有序~
     *
     * 如果在构造方法中传递了Comparator对象，那么就会以Comparator对象的方法进行比较。否则，则使用Comparable的compareTo(T o)方法来比较。
     *
     * 值得说明的是：如果使用的是compareTo(T o)方法来比较，key一定是不能为null，并且得实现了Comparable接口的。
     *
     * 即使是传入了Comparator对象，不用compareTo(T o)方法来比较，key也是不能为null的
     *
     *
     * 我们从源码中的很多地方中发现：Comparator和Comparable出现的频率是很高的，因为TreeMap实现有序要么就是外界传递进来Comparator对象，要么就使用默认key的Comparable接口(实现自然排序)
     *
     * 最后我就来总结一下TreeMap要点吧：
     *
     * - 由于底层是红黑树，那么时间复杂度可以保证为log(n)
     *
     * - key不能为null，为null为抛出NullPointException的
     *
     * - 想要自定义比较，在构造方法中传入Comparator对象，否则使用key的自然排序来进行比较
     *
     * - TreeMap非同步的，想要同步可以使用Collections来进行封装
     *
     * 参考资料：
     *
     * 《Core Java》
     *
     * https://blog.csdn.net/panweiwei1994/article/details/76555359
     *
     * https://www.cnblogs.com/chinajava/p/5808416.html
     *
     * */
    public static void main(String[] args) {
        TreeMap<Object, Object> treeMap = new TreeMap<>();
        treeMap.put("1", "--->");
        treeMap.get("1");
        treeMap.remove("1");


        TreeMap<Student, String> map = new TreeMap<Student, String>((o1, o2) -> {
            //主要条件
            int num = o1.getAge() - o2.getAge();

            //次要条件
            int num2 = num == 0 ? o1.getName().compareTo(o2.getName()) : num;

            return num2;
        });

        //创建学生对象
        Student s1 = new Student("潘安", 30);
        Student s2 = new Student("柳下惠", 35);

        //添加元素进集合
        map.put(s1, "宋朝");
        map.put(s2, "元朝");
        map.put(null, "汉朝");

        //获取key集合
        Set<Student> set = map.keySet();

        //遍历key集合
        for (Student student : set) {
            String value = map.get(student);
            System.out.println(student + "---------" + value);
        }
    }
}
