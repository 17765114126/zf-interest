package study.java提高篇.集合.HashSet;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class 概述 {
    //在前篇博文中（Java 提高篇（二三）—–HashMap）详细讲解了 HashMap 的实现过程，对于 HashSet 而言，它是基于 HashMap 来实现的，底层采用 HashMap 来保存元素。所以如果对 HashMap 比较熟悉，那么 HashSet是 so easy!!
    //
    //一、定义
    //
    //    public class HashSet<E>
    //        extends AbstractSet<E>
    //        implements Set<E>, Cloneable, java.io.Serializable
    //HashSet 继承 AbstractSet 类，实现 Set、Cloneable、Serializable 接口。其中 AbstractSet 提供 Set 接口的骨干实现，从而最大限度地减少了实现此接口所需的工作。Set 接口是一种不包括重复元素的 Collection，它维持它自己的内部排序，所以随机访问没有任何意义。
    //
    //基本属性
    //
    //
    //    //基于HashMap实现，底层使用HashMap保存所有元素
    //            private transient HashMap<E,Object> map;
    //
    //            //定义一个Object对象作为HashMap的value
    //            private static final Object PRESENT = new Object();
    //构造函数
    //
    //
    //    /**
    //             * 默认构造函数
    //             * 初始化一个空的HashMap，并使用默认初始容量为16和加载因子0.75。
    //             */
    //            public HashSet() {
    //                map = new HashMap<>();
    //            }
    //
    //            /**
    //             * 构造一个包含指定 collection 中的元素的新 set。
    //             */
    //            public HashSet(Collection<? extends E> c) {
    //                map = new HashMap<>(Math.max((int) (c.size()/.75f) + 1, 16));
    //                addAll(c);
    //            }
    //
    //            /**
    //             * 构造一个新的空 set，其底层 HashMap 实例具有指定的初始容量和指定的加载因子
    //             */
    //            public HashSet(int initialCapacity, float  loadFactor) {
    //                map = new HashMap<>(initialCapacity, loadFactor);
    //            }
    //
    //            /**
    //             * 构造一个新的空 set，其底层 HashMap 实例具有指定的初始容量和默认的加载因子（0.75）。
    //             */
    //             public HashSet(int initialCapacity) {
    //               map = new HashMap<>(initialCapacity);
    //            }
    //
    //            /**
    //             * 在API中我没有看到这个构造函数，今天看源码才发现 （原来访问权限为包权限，不对外公开的）
    //             * 以指定的initialCapacity和loadFactor构造一个新的空链接哈希集合。
    //             * dummy 为标识 该构造函数主要作用是对LinkedHashSet起到一个支持作用
    //             */
    //            HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    //               map = new LinkedHashMap<>(initialCapacity, loadFactor);
    //            }
    //从构造函数中可以看出 HashSet 所有的构造都是构造出一个新的 HashMap，其中最后一个构造函数，为包访问权限是不对外公开，仅仅只在使用 LinkedHashSet 时才会发生作用。
    //
    //二、方法
    //既然 HashSet 是基于 HashMap，那么对于 HashSet 而言，其方法的实现过程是非常简单的。
    //
    //
    //    public Iterator<E> iterator() {
    //            return map.keySet().iterator();
    //        }
    //iterator() 方法返回对此 set 中元素进行迭代的迭代器。返回元素的顺序并不是特定的。底层调用 HashMap 的 keySet 返回所有的 key，这点反应了 HashSet 中的所有元素都是保存在 HashMap 的 key 中，value 则是使用的 PRESENT 对象，该对象为 static final。
    //
    //
    //    public int size() {
    //            return map.size();
    //        }
    //size() 返回此 set 中的元素的数量（set 的容量）。底层调用 HashMap的 size 方法，返回 HashMap 容器的大小。
    //
    //
    //    public boolean isEmpty() {
    //            return map.isEmpty();
    //        }
    //isEmpty()，判断 HashSet() 集合是否为空，为空返回 true，否则返回false。
    //
    //
    //    public boolean contains(Object o) {
    //            return map.containsKey(o);
    //        }
    //contains()，判断某个元素是否存在于 HashSet() 中，存在返回 true，否则返回 false。更加确切的讲应该是要满足这种关系才能返回true：(o==null ? e==null : o.equals(e))。底层调用 containsKey 判断 HashMap 的 key 值是否为空。
    //
    //
    //    public boolean add(E e) {
    //            return map.put(e, PRESENT)==null;
    //        }
    //add() 如果此 set 中尚未包含指定元素，则添加指定元素。如果此 Set 没有包含满足 (e==null ? e2==null : e.equals(e2)) 的 e2 时，则将 e2 添加到 Set 中，否则不添加且返回 false。由于底层使用 HashMap 的 put 方法将 key = e，value=PRESENT 构建成 key-value 键值对，当此 e 存在于 HashMap 的 key 中，则 value 将会覆盖原有 value，但是 key 保持不变，所以如果将一个已经存在的 e 元素添加中 HashSet 中，新添加的元素是不会保存到 HashMap 中，所以这就满足了 HashSet 中元素不会重复的特性。
    //
    //
    //    public boolean remove(Object o) {
    //            return map.remove(o)==PRESENT;
    //        }
    //remove 如果指定元素存在于此 set 中，则将其移除。底层使用 HashMap 的 remove 方法删除指定的 Entry。
    //
    //
    //    public void clear() {
    //            map.clear();
    //        }
    //clear 从此 set 中移除所有元素。底层调用 HashMap 的 clear 方法清除所有的 Entry。
    //
    //
    //    public Object clone() {
    //            try {
    //                HashSet<E> newSet = (HashSet<E>) super.clone();
    //                newSet.map = (HashMap<E, Object>) map.clone();
    //                return newSet;
    //            } catch (CloneNotSupportedException e) {
    //                throw new InternalError();
    //            }
    //        }
    //clone 返回此 HashSet 实例的浅表副本：并没有复制这些元素本身。
    //
    //后记：
    //
    //由于 HashSet 底层使用了 HashMap 实现，使其的实现过程变得非常简单，如果你对 HashMap 比较了解，那么 HashSet 简直是小菜一碟。有两个方法对 HashMap 和 HashSet 而言是非常重要的，下篇将详细讲解 hashcode 和 equals。
}
