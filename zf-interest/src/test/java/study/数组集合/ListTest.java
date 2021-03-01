package study.数组集合;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

/**
 * @ClassName ListTest
 * @Author zhaofu
 * @Date 2021/2/18
 * @Version V1.0
 * https://mp.weixin.qq.com/s?__biz=MzI4Njg5MDA5NA==&mid=2247484130&idx=1&sn=4052ac3c1db8f9b33ec977b9baba2308&chksm=ebd743e3dca0caf51b170fd4285345c9d992a5a56afc28f2f45076f5a820ad7ec08c260e7d39&scene=21#wechat_redirect
 **/
public class ListTest {
    @Test
    public void ArrayListTest() {

        ArrayList<String> arrayList = new ArrayList<>();

/**ArrayList的属性*/
        /**
         * Default initial capacity.(默认初始容量.)
         */
//        private static final int DEFAULT_CAPACITY = 10;

        /**
         * Shared empty array instance used for empty instances.(指定该ArrayList容量为0时，返回该空数组。)
         */
//        private static final Object[] EMPTY_ELEMENTDATA = {};

        /**
         * Shared empty array instance used for default sized empty instances. We
         * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
         * first element is added.
         * (它与EMPTY_ELEMENTDATA的区别就是：该数组是默认返回的，而后者是在用户指定容量为0时返回)
         */
//        private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

        /**
         * The array buffer into which the elements of the ArrayList are stored.
         * The capacity of the ArrayList is the length of this array buffer. Any
         * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
         * will be expanded to DEFAULT_CAPACITY when the first element is added.
         * (保存添加到ArrayList中的元素，当以一次添加元素进入ArrayList中时，将数组扩容值DEFAULT_CAPACITY)
         */
//        transient Object[] elementData; // non-private to simplify nested class access

        /**
         * The size of the ArrayList (the number of elements it contains).(ArrayList的大小（它包含的元素数）)
         *
         * @serial
         */
//        private int size;


/**1.2构造方法*/

        /**
         * Constructs an empty list with the specified initial capacity.
         *（如果指定了容量，那么数组就初始化成对应的容量）
         */
        //    public ArrayList( int initialCapacity){
        //            if (initialCapacity > 0) {
        //                this.elementData = new Object[initialCapacity];
        //            } else if (initialCapacity == 0) {
        //                this.elementData = EMPTY_ELEMENTDATA;
        //            } else {
        //                throw new IllegalArgumentException("Illegal Capacity: " +
        //                        initialCapacity);
        //            }
        //        }

        /**
         * Constructs an empty list with an initial capacity of ten.
         * （否则返回的是DEFAULTCAPACITY_EMPTY_ELEMENTDATA）
         */
        //    public ArrayList() {
        //            this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        //        }


/**1.3 Add方法*/

        arrayList.add("1");

        /**
         * Appends the specified element to the end of this list.
         * （直接添加元素）
         *
         * 我们看源码就可以知道add(E e)的基本实现了：
         *
         * 首先去检查一下数组的容量是否足够
         *
         * 扩容到原来的1.5倍
         *
         * 第一次扩容后，如果容量还是小于minCapacity，就将容量扩充为minCapacity。
         *
         * 足够：直接添加
         *
         * 不足够：扩容
         *
         */
//        public boolean add(E e) {
//            ensureCapacityInternal(size + 1);  // Increments modCount!!
//            elementData[size++] = e;
//            return true;
//        }
        /**
         * */
        /**
         * Inserts the specified element at the specified position in this
         * list. Shifts the element currently at that position (if any) and
         * any subsequent elements to the right (adds one to their indices).
         *（插入特定的位置上）
         *
         * 检查角标
         * 空间检查，如果有需要进行扩容
         * 插入元素
         *
         */
//        public void add(int index, E element) {
//            rangeCheckForAdd(index);//检查角标是否越界
//
//            ensureCapacityInternal(size + 1);  // Increments modCount!!（扩容）
//            System.arraycopy(elementData, index, elementData, index + 1,
//                    size - index);//调用arraycopy进行插入
//            elementData[index] = element;
//            size++;
//        }

        /**
         * 们发现，与扩容相关ArrayList的add方法底层其实都是arraycopy()来实现的
         *
         * 看到arraycopy()，我们可以发现：该方法是由C/C++来编写的，并不是由Java实现：
         *
         * 总的来说：arraycopy()还是比较可靠高效的一个方法。
         * */

/**1.4 get方法*/

        arrayList.get(0);

        /**
         * Returns the element at the specified position in this list.
         * （返回此列表中指定位置的元素.）
         */
//        public E get(int index) {
//            rangeCheck(index);//检查角标
//
//            return elementData(index);//返回元素
//        }

/**1.5 set方法*/

        arrayList.set(0, "2");

        /**
         * Replaces the element at the specified position in this list with
         * the specified element.
         */
//        public E set(int index, E element) {
//            rangeCheck(index);
//
//            E oldValue = elementData(index);
//            elementData[index] = element;//代替元素，返回旧值
//            return oldValue;
//        }

/**1.6 remove方法*/

        arrayList.remove(0);

        /**
         * Removes the element at the specified position in this list.
         * Shifts any subsequent elements to the left (subtracts one from their indices).
         *
         * 检查角标
         *
         * 删除元素
         *
         * 计算出需要移动的个数，并移动
         *
         * 设置为null，让Gc回收
         */
//        public E remove(int index) {
//            rangeCheck(index);
//
//            modCount++;
//            E oldValue = elementData(index);
//
//            int numMoved = size - index - 1;//需要左移的个数
//            if (numMoved > 0)
//                System.arraycopy(elementData, index+1, elementData, index,
//                        numMoved);
//            elementData[--size] = null; // clear to let GC do its work
//
//            return oldValue;
//        }

/**1.7细节再说明*/
/**     ArrayList是基于动态数组实现的，在增删时候，需要数组的拷贝复制。

 ArrayList的默认初始化容量是10，每次扩容时候增加原先容量的一半，也就是变为原来的1.5倍

 删除元素时不会减少容量，若希望减少容量则调用trimToSize()

 它不是线程安全的。
 它能存放null值。
 */
    }


    @Test
    public void VectorTest() {

        Vector<String> vector = new Vector<>();

        /**
         * Vector是jdk1.2的类了，比较老旧的一个集合类。
         *
         * Vector底层也是数组，与ArrayList最大的区别就是：同步(线程安全)
         *
         * Vector是同步的，我们可以从方法上就可以看得出来~(public synchronized boolean add(E e))
         * */
        /**
         * Appends the specified element to the end of this Vector.
         */
//        public synchronized boolean add(E e) {
//            modCount++;
//            ensureCapacityHelper(elementCount + 1);
//            elementData[elementCount++] = e;
//            return true;
//        }
        vector.add("1");

        /**
         * 在要求非同步的情况下，我们一般都是使用ArrayList来替代Vector的了~
         *
         * 如果想要ArrayList实现同步，可以使用Collections的方法：
         * List list = Collections.synchronizedList(new ArrayList(...));，
         * 就可以实现同步了~
         *
         * 还有另一个区别：
         *
         * ArrayList在底层数组不够用时在原来的基础上扩展0.5倍，Vector是扩展1倍。、
         * */
    }

    @Test
    public void LinkedListTest() {

        LinkedList<String> linkedList = new LinkedList<>();

        /**
         * LinkedList底层是双向链表~如果对于链表不熟悉的同学可先看看【Java实现单向链表】
         *
         * 理解了单向链表，双向链表也就不难了。
         *
         * 内部类，双向链表
         * */
//        private static class Node<E> {
//            E item;//节点值
//            Node<E> next;//后继节点
//            Node<E> prev;//前驱节点
//
//            Node(Node<E> prev, E element, Node<E> next) {
//                this.item = element;
//                this.next = next;
//                this.prev = prev;
//            }
//        }
        /**
         * 从结构上，我们还看到了LinkedList实现了Deque接口，因此，我们可以操作LinkedList像操作队列和栈一样~
         *
         */

        /**
         * LinkedList变量就这么几个，因为我们操作单向链表的时候也发现了：有了头结点，其他的数据我们都可以获取得到了。(双向链表也同理)
         * */

        /**
         * Pointer to first node.（指向第一个节点的指针）
         */
//        transient Node<E> first;

        /**
         * Pointer to last node.（指向最后一个节点的指针。）
         */
//        transient Node<E> last;

/**3.1 构造方法*/

        /**
         * Constructs an empty list.（空构造方法：）
         */
//        public LinkedList() {
//        }

        /**
         * Constructs a list containing the elements of the specified
         * collection, in the order they are returned by the collection's
         * iterator.
         * （用已有的集合创建链表的构造方法：）
         */
//        public LinkedList(Collection < ? extends E > c){
//            this();
//            addAll(c);
//        }

/**3.2 add方法*/
        linkedList.add("1");

        /**
         * 如果做过链表的练习，对于下面的代码并不陌生的~
         *
         * add方法实际上就是往链表最后添加元素
         *
         * Links e as last element.
         */
//        void linkLast(E e) {
//            final Node<E> l = last;
//            final Node<E> newNode = new Node<>(l, e, null);
//            last = newNode;
//            if (l == null)
//                first = newNode;
//            else
//                l.next = newNode;
//            size++;
//            modCount++;
//        }


/**3.4 get方法*/

        linkedList.get(0);
        /**
         * Returns the (non-null) Node at the specified element index.
         */
//        Node<E> node(int index) {
//            // assert isElementIndex(index);
//
//            if (index < (size >> 1)) {
        //下标小于长度的一半，那就从头遍历
//                Node<E> x = first;
//                for (int i = 0; i < index; i++)
//                    x = x.next;
//                return x;
//            } else {
//                Node<E> x = last;
        //否则从尾便利
//                for (int i = size - 1; i > index; i--)
//                    x = x.prev;
//                return x;
//            }
//        }

/**3.5 set方法*/

        linkedList.set(0,"1");

        /**
         * set方法和get方法其实差不多，根据下标来判断是从头遍历还是从尾遍历
         * Replaces the element at the specified position in this list with the specified element.
         */
//        public E set(int index, E element) {
//            checkElementIndex(index);
//            Node<E> x = node(index);
//            E oldVal = x.item;
//            x.item = element;
//            return oldVal;
//        }

/**3.3 remove方法*/

        linkedList.remove("1");

        /**
         * Removes the first occurrence of the specified element from this list,
         * if it is present.  If this list does not contain the element, it is
         * unchanged.  More formally, removes the element with the lowest index
         */
//        public boolean remove(Object o) {
//            if (o == null) {
//                for (Node<E> x = first; x != null; x = x.next) {
//                    if (x.item == null) {
//                        unlink(x);//删除元素
//                        return true;
//                    }
//                }
//            } else {
//                for (Node<E> x = first; x != null; x = x.next) {
//                    if (o.equals(x.item)) {//看元素是否在里面
//                        unlink(x);
//                        return true;
//                    }
//                }
//            }
//            return false;
//        }


        /**
         * ……LinkedList的方法比ArrayList的方法多太多了，这里我就不一一说明了。具体可参考：
         *
         * https://blog.csdn.net/panweiwei1994/article/details/77110354
         *
         * https://zhuanlan.zhihu.com/p/24730576
         *
         * https://zhuanlan.zhihu.com/p/28373321
         * */

    }

/**
 *
 * 四、总结
 * 其实集合的源码看起来并不是很困难，遇到问题可以翻一翻，应该是能够看懂的~
 *
 * ArrayList、LinkedList、Vector算是在面试题中比较常见的的知识点了。下面我就来做一个简单的总结：
 *
 * ArrayList：
 *
 * 底层实现是数组
 *
 * ArrayList的默认初始化容量是10，每次扩容时候增加原先容量的一半，也就是变为原来的1.5倍
 *
 * 在增删时候，需要数组的拷贝复制(navite 方法由C/C++实现)
 *
 * LinkedList：
 *
 * 底层实现是双向链表[双向链表方便实现往前遍历]
 *
 * Vector：
 *
 * 底层是数组，现在已少用，被ArrayList替代，原因有两个：
 *
 * Vector所有方法都是同步，有性能损失。
 *
 * Vector初始length是10 超过length时 以100%比率增长，相比于ArrayList更多消耗内存。
 *
 * 参考资料：https://www.zhihu.com/question/31948523/answer/113357347
 *
 * 总的来说：查询多用ArrayList，增删多用LinkedList。
 *
 * ArrayList增删慢不是绝对的(在数量大的情况下，已测试)：
 *
 * 如果增加元素一直是使用add()(增加到末尾)的话，那是ArrayList要快
 *
 * 一直删除末尾的元素也是ArrayList要快【不用复制移动位置】
 *
 * 至于如果删除的是中间的位置的话，还是ArrayList要快！
 *
 * 但一般来说：增删多还是用LinkedList，因为上面的情况是极端的~
 *
 * 参考资料：
 *
 * https://blog.csdn.net/panweiwei1994/article/details/76555359
 *
 * https://zhuanlan.zhihu.com/p/28216267
 *
 * 《Core Java》
 *
 * */

}
