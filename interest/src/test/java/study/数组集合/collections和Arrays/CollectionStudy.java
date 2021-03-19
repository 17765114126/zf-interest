package study.数组集合.collections和Arrays;

import com.example.springboot.model.Student;
import java.util.*;
/**
 * @ClassName CollectionStudy
 * @Author zhaofu
 * @Date 2019/12/10
 * @Version V1.0
 **/
public class CollectionStudy {
    /**
     * Java中的Collection集合以及Collection集合实现类实例
     * 一 、Collection：
     * <p>
     * 1、List集合
     * 1.1、 Vector集合特有的特点
     * 1.2、LinkedList集合的特有功能
     * <p>
     * 2、 Set集合
     * 2.1、 HashSet类：
     * 2.1.1、 LinkedHashSet类
     * 2.2、 TreeSet类
     * <p>
     * 3、 针对Collection集合我们应该怎么使用
     * <p>
     * 二 、Collection集合和List集合迭代器遍历实例
     * 三、使用使用HashSet类随机产生10个不重复的1到20的不重复随机数实例
     * 四、TreeSet类中自然排序和比较器排序实例
     * <p>
     * 最重要的是迭代器
     * <p>
     * 一 、Collection：
     * 集合的顶层接口，不能被实例化
     * a) 根接口Collection
     *  i. 常用子接口
     *   1. List
     *    实现类：ArrayList、Vector、LinkedList
     *   2. Set
     *    实现类：HashSet、TreeSet
     * b) 添加功能
     *  i. boolean add(object obj)添加一个元素
     *  ii. boolean addAll(Collection c)将集合c的全部元素添加到原集合元素后返回true
     *  iii. 添加功能永远返回true
     * c) 删除功能
     *  i. void clear();移除所有元素
     *  ii. boolean remove(Object o)移除一个元素
     *  iii. boolean removeAll（Collection c）移除一个集合的元素，只要有一个被移除就返回true，改变原集合，删除原集合中和c中相同的元素
     *  iv. 删除功能只有删除成功后才返回true
     * d) 判断功能
     *  i. boolean contain(object o)判断集合中是否包含指定的元素。
     *  ii. boolean containsAll(Collection c)判断原集合中是否包含指定集合c的所有元素，有则true，
     *  iii. boolean isEmpty()判断集合是否为空
     * e) 获取功能
     *  i. Iterator iterator()迭代器，集合的专用方式，实现遍历的功能
     *  ii. Object next()获取当前元素，并移动到下一个位置
     *  iii. boolean hasNext()判断此位置是否有元素
     *  iv. 迭代器遍历实例在下面
     * f) 长度功能
     *  i. int size()元素的个数
     *  ii. 数组和字符串中都是length()方法获取元素个数，集合中是size()方法
     *   因为object包括集合、字符串、数组，所以其不能直接用length方法。
     * g) 交集功能boolean retainAll(Collection c)
     *  两个集合交集的元素给原集合，并判断原集合是否改变，改变则true，不变则false
     * h) 把集合转换为数组
     *  i. Object [] toArray()
     * <p>
     * 1、List集合
     * Collection集合的子类
     * 特点：
     * a) 有序（存储和取出的元素顺序一致），可重复
     * b) 特有功能
     *  i. 添加功能
     *   void add(int index,Object element)在指定位置添加元素（原索引处的元素后延）
     *  ii. 获取功能
     *   Object get(int index)获取指定位置的元素
     *  iii. 列表迭代器
     *   1. ListIterator listIterator() List集合特有的迭代器
     *   2. Iterator迭代器的子类，所以其可以用Iterator中的boolean hasNext()、Object next()方法
     *   3. 特有的方法：
     *    a) Object previous ()返回此处位置的前一个的元素，并移动到前一个位置。
     *    b) boolean hasPrevious()判断此处的前一个位置是否有元素
     *    c) 逆向遍历必须先正向遍历使指针指到后面位置才能使用（使用意义不大）
     *   4. 迭代器遍历元素时不能直接通过集合修改元素，怎么办？
     *    a) 迭代器修改元素
     *     i. List迭代器有修改方法，Collection中的迭代器没有
     *     ii. 通过迭代器中add(object obj)方法添加，跟在刚才迭代元素后面
     *    b) 通过集合遍历元素，并用集合修改元素（for循环遍历）
     *     i. 通过集合中add(object obj)方法添加，跟在集合最后面
     *   5. 迭代器遍历实例实例在下面给出
     *  iv. 删除功能
     *   Object remove(int index)根据索引删除指定的元素，并返回删除的元素
     *  v. 修改功能、
     *    Object set(int index,Object element)根据索引修改元素，返回被修改的元素
     *  vi. 数组转成集合
     *   1. public static List asList(T… a)，返回类型为List类型
     *   2. a为集合，此处的… 代表可变参数，也就是a的数组元素个数可变
     *   3. 此方法是Arrays类中的静态方法
     *   4. 数组转变为集合，实质还是数组需要保证长度不变，所以不支持增删集合元素，可以修改元素
     * c) List子类的特点：
     *  i. ArrayList:
     *   1. 底层数据结构是数组，查询快、增删慢
     *   2. 线程不安全，效率高
     *  ii. Vector：
     *   1. 底层数据结构是数组，查询快，增删慢
     *   2. 线程安全，效率底
     *  iii. LinkedList：
     *   1. 底层数据结构是链表，查询慢，增删快
     *   2. 线程不安全，效率高
     * <p>
     * 1.1、 Vector集合特有的特点
     * a) 添加功能
     *  i. public void addElement(Object obj)
     * b) 获取功能
     *  i. public Object elementAt(int index)
     *  ii. public Enumeration elements ()
     *   1. 也是用来遍历集合
     *   2. boolean hasMoreElements()
     *   3. Object nextElement()
     *   4. 基本不用这个，都是直接用上面的迭代器实现遍历
     * <p>
     * 1.2、LinkedList集合的特有功能
     * a) 添加功能
     *  i. public void addFirst(Object e)
     *  ii. public void addLast(Object e)
     * b) 获取功能
     *  i. public Object getFirst()
     *  ii. public Object getLast()
     * c) 删除功能
     *  i. public Object removeFirst()
     *  ii. public Object removeLast()
     * <p>
     * 2、 Set集合
     *  a) 无序（存储和取出顺序不一致，有可能会一致），但是元素唯一，不能重复
     *  b) b) 实现类
     *   i. HashSet
     *    1. 底层数据是哈希表
     *    2. 通过两个方法hashCode()和equals()保证元素的唯一性，方法自动生成
     *    3. 子类LinkedHashSet底层数据结构是链表和哈希表，由链表保证元素有序，
     *     由哈希表保证元素唯一。
     *   ii. TreeSet
     *    1. 底层数据是红黑二叉树
     *    2. 排序方式：自然排序、比较器排序
     *    3. 通过比较返回值是否为0来保证元素的唯一性。
     * <p>
     * 2.1、 HashSet类：
     *  a) 不保证set的迭代顺序，
     *  b) 当存储对象时需要重写equals（）和hashCode（）方法（可以用Eclipse自动生成此方法）
     * <p>
     * 2.1.1、 LinkedHashSet类
     *  a) HashSet的子类
     *  b) 可预知的迭代顺序，底层数据结构由哈希表和链表组成
     *   i. 哈希表：保证元素的唯一性
     *   ii. 链表：保证元素有序（存储和取出顺序一致）
     * <p>
     * 2.2、 TreeSet类
     *  a) 能够保证元素唯一性（根据返回值是否是0来决定的），并且按照某种规则排序
     *   i. 自然排序，无参构造方法（元素具备比较性）
     *    按照compareTo()方法排序，让需要比较的元素所属的类实现自然排序接口Comparable，
     *    并重写compareTo()方法
     *   ii. 1. 让集合的构造方法接收一个比较器接口的子类对象（compareator）
     *   （此处的Comparator为接口，需要写一个接口实现类，在实现类中重写compare()方法，
     *    并在这里创建接口实现类的对象，可以用匿名内部类来创建实现类对象）
     *  b) 底层是自平衡二叉树结构
     *   i. 二叉树有前序遍历、后序遍历、中序遍历
     *   ii. TreeSet类是按照从根节点开始，按照从左、中、右的原则依此取出元素
     *  c) 当使用无参构造方法，也就是自然排序，需要根据要求重写compareTo()方法，这个不能自动生成
     * <p>
     * 3、 针对Collection集合我们应该怎么使用
     * a) 是否元素唯一
     *  i. 是：Set
     *   1. 是否排序
     *    a) 是：TreeSet
     *    b) 否：HashSet
     *   如果不知道用哪个Set就用HashSet
     *  ii. 否：List
     *   1. 是否对安全性有要求
     *    a) 是：Vector
     *    b) 否：
     *      查询多：ArrayList
     *      增删多：LinkedList
     *   如果不知道用哪个List用ArrayList
     * 如果知道是用集合但不知道用哪个用ArrayList
     */
    /**
     * 二 、Collection集合和List集合迭代器遍历实例
     *
     *     Collection集合中的方法，子集合都可以用
     *     	Iterator<E> iterator()迭代器，集合的专用方式子集合都可以用
     *     	Object next()获取当前元素，并移动到下一个位置
     *     	boolean hasNext()判断此位置是否有元素
     *
     *     List集合中的方法
     *     	void add(int index,Object element)在指定位置添加元素（原索引处的元素后延）
     *     	Object get(int index)获取指定位置的元素
     *
     *     list迭代器中的特有方法
     *     	Object previous ()返回此处位置的前一个的元素，并移动到前一个位置。
     *     	boolean hasPrevious()判断此处的前一个位置是否有元素
     */
    public static void main(String[] args) {

        //Collection集合的遍历
        Collection c = new ArrayList();
        c.add("zfliu");
        c.add("18");
        c.add("java");

        Iterator i = c.iterator();
        while (i.hasNext()) {
            //向下转型
            String s = (String) i.next();
            System.out.println(s);
        }
        System.out.println("-----------------");
        //List集合的遍历
        List l = new ArrayList();
        l.add(0, "zfliu");
        l.add(1, "18");
        l.add(1, "java");

        ListIterator li = l.listIterator();

        //后向遍历
        while (li.hasNext()) {
            String s = (String) li.next();
            System.out.println(s);
        }
        System.out.println("-----------------");

        //前向遍历
        while (li.hasPrevious()) {
            String s = (String) li.previous();
            System.out.println(s);
        }
        System.out.println("-----------------");

        //get方法遍历
        for (int x = 0; x < l.size(); x++) {
            String s = (String) l.get(x);
            System.out.println(s);
        }

        // 三、使用使用HashSet类随机产生10个不重复的1到20的不重复随机数实例

        //使用HashSet类随机产生10个不重复的1到20的不重复随机数

        Random r = new Random();

        HashSet<Integer> hs = new HashSet<Integer>();

        while (hs.size() < 10) {
            hs.add((r.nextInt(20) + 1));
        }
        for (Integer q : hs) {
            System.out.println(q);
        }

        // 四、TreeSet类中自然排序和比较器排序实例
        // Student类

        //此处实现的为自然排序接口，如果仅仅使用比较器排序此接口可以不实现
 /*     public class Student implements Comparable<Student>{
      	private int age;

      	private String name;

      	public Student() {
      		super();
      	}

      	public Student(String name,int age) {
      		super();
      		this.age = age;
      		this.name = name;
      	}

      	public int getAge() {
      		return age;
      	}

      	public void setAge(int age) {
      		this.age = age;
      	}

      	public String getName() {
      		return name;
      	}

      	public void setName(String name) {
      		this.name = name;
      	}

      	public int compareTo(Student s) {
      		//此处的this和s前后位置改变会影响排序方式
      		int num1=this.age-s.age;

      		int num2=num1==0?this.name.compareTo(s.name):num1;

      		return num2;
      	}
      }*/
        // 1、自然排序
        //TreeSet类存储对象，自然排序
        //规定:按照年龄进行排序
        TreeSet<Student> ts = new TreeSet<Student>();

        Student s1 = new Student("zfliu", 18);
        Student s2 = new Student("zfliu", 20);
        Student s3 = new Student("zfliu", 18);
        Student s4 = new Student("ZFLIU", 18);
        Student s5 = new Student("Java", 18);
//      		ts.add(s1);
//      		ts.add(s2);
//      		ts.add(s3);
//      		ts.add(s4);
//      		ts.add(s5);
        for (Student s : ts) {
            System.out.println(s.getName() + s.getAge());
        }

        // 2、比较器排序
        //TreeSet类存储对象，比较器排序Variable 's1' is already defined in the scope
        //规定:按照年龄进行排序

        TreeSet<Student> ts1 = new TreeSet<Student>(new Comparator<Student>() {
            //匿名内部类实现比较器排序接口
            public int compare(Student s1, Student s2) {
                int num1 = s1.getAge() - s2.getAge();
                int num2 = num1 == 0 ? s1.getName().compareTo(s2.getName()) : num1;
                return num2;
            }
        });

        Student q1 = new Student("zfliu", 18);
        Student q2 = new Student("zfliu", 20);
        Student q3 = new Student("zfliu", 18);
        Student q4 = new Student("ZFLIU", 18);
        Student q5 = new Student("Java", 18);
        ts1.add(q1);
        ts1.add(q2);
        ts1.add(q3);
        ts1.add(q4);
        ts1.add(q5);

        for (Student s : ts1) {
            System.out.println(s.getName() + s.getAge());
        }
    }

    /**
     * collection主要方法

     * boolean add(Object o)添加对象到集合

     * boolean remove(Object o)删除指定的对象

     * int size()返回当前集合中元素的数量

     * boolean contains(Object o)查找集合中是否有指定的对象

     * boolean isEmpty()判断集合是否为空

     * Iterator iterator()返回一个迭代器

     * boolean containsAll(Collection c)查找集合中是否有集合c中的元素

     * boolean addAll(Collection c)将集合c中所有的元素添加给该集合

     * void clear()删除集合中所有元素

     * void removeAll(Collection c)从集合中删除c集合中也有的元素

     * void retainAll(Collection c)从集合中删除集合c中不包含的元素

     * collection主要子接口对象：


     * ├List(抽象接口，可重复有序)
     * list主要方法：

     * void add(int index,Object element)在指定位置上添加一个对象

     * boolean addAll(int index,Collection c)将集合c的元素添加到指定的位置

     * Object get(int index)返回List中指定位置的元素

     * int indexOf(Object o)返回第一个出现元素o的位置.

     * Object remove(int index)删除指定位置的元素

     * Object set(int index,Object element)用元素element取代位置index上的元素,返回被取代的元素

     * void sort()

     * 1.List主要子接口对象

     * LinkedList没有同步方法

     * ArrayList非同步的（unsynchronized）

     * Vector(同步) 非常类似ArrayList，但是Vector是同步的

     * Stack 记住 push和pop方法，还有peek方法得到栈顶的元素，empty方法测试堆栈是否为空，search方法检测一个元素在堆栈中的位置。
     * 注意：Stack刚创建后是空栈。

     * 2.Set不包含重复的元素

     * HashSet

     * SortSet

     * TreeSet

     * 另外：-Queue(继承collection)---Deque

     * 3.Map
     * Map没有继承Collection接口，Map提供key到value的映射。

     * 方法：

     * boolean equals(Object o)比较对象

     * boolean remove(Object o)删除一个对象

     * put(Object key,Object value)添加key和value

     * Hashtable 任何非空（non-null）的对象。同步的

     * HashMap 可空的对象。不同步的 ，但是效率高，较常用。 注：迭代子操作时间开销和HashMap的容量成比例。
     * 因此，如果迭代操作的性能相当重要的话，不要将HashMap的初始化容量设得过高，或者load factor过低。

     * └WeakHashMap 改进的HashMap，它对key实行“弱引用”，如果一个key不再被外部所引用，那么该key可以被GC回收。

     * SortMap---TreeMap

     * 4.总结：
     * a.如果涉及到堆栈，队列（先进后出）等操作，应该考虑用List，
     * 对于需要快速插入，删除元素，应该使用LinkedList，
     * 如果需要快速随机访问元素，应该使用ArrayList。

     * b.如果程序在单线程环境中，或者访问仅仅在一个线程中进行，考虑非同步的类，其效率较高，
     * 如果多个线程可能同时操作一个类，应该使用同步的类。

     * c.要特别注意对哈希表的操作，作为key的对象要正确复写equals和hashCode方法。

     * d.尽量返回接口而非实际的类型，如返回List而非ArrayList，这样如果以后需要将ArrayList换成LinkedList时，客户端代码不用改变。这就是针对抽象编程。

     * e.ArrayList、HashSet/LinkedHashSet、PriorityQueue、LinkedList是线程不安全的，
     * 可以使用synchronized关键字，或者类似下面的方法解决:

     * 1.List list = Collections.synchronizedList(new ArrayList(...));


     * 5.几个面试常见问题：

     * 1.Q:ArrayList和Vector有什么区别？HashMap和HashTable有什么区别？

     * A:Vector和HashTable是线程同步的（synchronized）。性能上，ArrayList和HashMap分别比Vector和Hashtable要好。

     * 2.Q:大致讲解java集合的体系结构

     * A:List、Set、Map是这个集合体系中最主要的三个接口。

     * 其中List和Set继承自Collection接口。

     * Set不允许元素重复。HashSet和TreeSet是两个主要的实现类。

     * List有序且允许元素重复。ArrayList、LinkedList和Vector是三个主要的实现类。

     * Map也属于集合系统，但和Collection接口不同。Map是key对value的映射集合，其中key列就是一个集合。key不能重复，但是value可以重复。
     * HashMap、TreeMap和Hashtable是三个主要的实现类。

     * SortedSet和SortedMap接口对元素按指定规则排序，SortedMap是对key列进行排序。

     * 3.Q：Comparable和Comparator区别
     * A:调用java.utils.Collections.sort(List list)方法来进行排序的时候，List内的Object都必须实现了Comparable接口。
     * java.utils.Collections.sort(List list，Comparator c)，可以临时声明一个Comparator 来实现排序。

     * Collections.sort(imageList, new Comparator() {
     * public int compare(Object a, Object b) {
     * int orderA = Integer.parseInt( ( (Image) a).getSequence());
     * int orderB = Integer.parseInt( ( (Image) b).getSequence());
     * return orderA - orderB;
     * }
     * });
     * 如果需要改变排列顺序
     * 改成return orderb - orderA 即可。

     * 6.其他注意点
     * List接口对Collection进行了简单的扩充，它的具体实现类常用的有ArrayList和LinkedList。
     * 你可以将任何东西放到一个List容器中，并在需要时从中取出。
     * ArrayList从其命名中可以看出它是一种类似数组的形式进行存储，因此它的随机访问速度极快，
     * 而LinkedList的内部实现是链表，它适合于在链表中间需要频繁进行插入和删除操作。
     * 在具体应用时可以根据需要自由选择。
     * 前面说的Iterator只能对容器进行向前遍历，而ListIterator则继承了Iterator的思想，并提供了对List进行双向遍历的方法。

     * Set接口也是Collection的一种扩展，而与List不同的时，在Set中的对象元素不能重复，也就是说你不能把同样的东西两次放入同一个Set容器中。
     * 它的常用具体实现有HashSet和TreeSet类。
     * HashSet能快速定位一个元素，但是你放到HashSet中的对象需要实现hashCode()方法，它使用了前面说过的哈希码的算法。
     * 而TreeSet则将放入其中的元素按序存放，这就要求你放入其中的对象是可排序的，这就用到了集合框架提供的另外两个实用类Comparable和Comparator。
     * 一个类是可排序的，它就应该实现Comparable接口。
     * 有时多个类具有相同的排序算法，那就不需要在每分别重复定义相同的排序算法，只要实现Comparator接口即可。
     * 集合框架中还有两个很实用的公用类：collections和Arrays。
     * Collections提供了对一个Collection容器进行诸如排序、复制、查找和填充等一些非常有用的方法，
     * Arrays则是对一个数组进行类似的操作。

     * Map是一种把键对象和值对象进行关联的容器，而一个值对象又可以是一个Map，依次类推，这样就可形成一个多级映射。
     * 对于键对象来说，像Set一样，一个Map容器中的键对象不允许重复，这是为了保持查找结果的一致性;
     * 如果有两个键对象一样，那你想得到那个键对象所对应的值对象时就有问题了，可能你得到的并不是你想的那个值对象，
     * 结果会造成混乱，所以键的唯一性很重要，也是符合集合的性质的。
     * 当然在使用过程中，某个键所对应的值对象可能会发生变化，这时会按照最后一次修改的值对象与键对应。
     * 对于值对象则没有唯一性的要求。你可以将任意多个键都映射到一个值对象上，这不会发生任何问题
     * （不过对你的使用却可能会造成不便，你不知道你得到的到底是那一个键所对应的值对象）。
     * Map有两种比较常用的实现：
     * HashMap和TreeMap。
     * HashMap也用到了哈希码的算法，以便快速查找一个键，
     * TreeMap则是对键按序存放，因此它便有一些扩展的方法，比如firstKey(),lastKey()等，你还可以从TreeMap中指定一个范围以取得其子Map。
     * 键和值的关联很简单，用pub(Object key,Object value)方法即可将一个键与一个值对象相关联。用get(Object key)可得到与此key对象所对应的值对象。
     */

//     * 遍历Map的方式：

    //最常规的一种遍历方法，最常规就是最常用的，虽然不复杂，但很重要，这是我们最熟悉的，就不多说了！！
    public static void work(Map<String, Student> map) {
        Collection<Student> c = map.values();

        Iterator it = c.iterator();
        for (; it.hasNext(); ) {
            System.out.println(it.next());
        }
    }

    // 利用keyset进行遍历，它的优点在于可以根据你所想要的key值得到你想要的 values，更具灵活性！！

    public static void workByKeySet(Map<String, Student> map) {
        Set<String> key = map.keySet();

        for (Iterator it = key.iterator(); it.hasNext(); ) {
            String s = (String) it.next();
            System.out.println(map.get(s));
        }
    }
    // 比较复杂的一种遍历在这里，暴力!!，它的灵活性太强了，想得到什么就能得到什么~~

    public static void workByEntry(Map<String, Student> map) {
        Set<Map.Entry<String, Student>> set = map.entrySet();

        for (Iterator<Map.Entry<String, Student>> it = set.iterator(); it.hasNext(); ) {
            Map.Entry<String, Student> entry = (Map.Entry<String, Student>) it.next();
            System.out.println(entry.getKey() + "—>" + entry.getValue());
        }
    }
    //Map.Entry的另外一种简练写法(foreach遍历方式)

    public static void workByEntry1(Map<String, Student> map) {
        Set<Map.Entry<String, Student>> set = map.entrySet();

        for (Map.Entry<String, Student> me : set) {
            System.out.println(me.getKey() + "—>" + me.getValue());
        }
    }

    /**
     * 7.Queue
     *
     * Queue和List有两个区别：
     *
     * 前者有“队头”的概念，取元素、移除元素、均为对“队头”的操作（通常但不总是FIFO，即先进先出），
     * 而后者只有在插入时需要保证在尾部进行；
     * 前者对元素的一些同一种操作提供了两种方法，在特定情况下抛异常/返回特殊值——add()/offer()、remove()/poll()、element()/peek()。
     * 不难想到，在所谓的两种方法中，抛异常的方法完全可以通过包装不抛异常的方法来实现，这也是AbstractQueue所做的。
     *
     * Deque接口继承了Queue，但是和AbstractQueue没有关系。
     * Deque同时提供了在队头和队尾进行插入和删除的操作。
     *
     * PriorityQueue
     * PriorityQueue用于存放含有优先级的元素，插入的对象必须可以比较。
     * 该类内部同样封装了一个数组。
     * 与其抽象父类AbstractQueue不同，PriorityQueue的offer()方法在插入null时会抛空指针异常——null是无法与其他元素比较通常意义下的优先级的；
     * 此外，add()方法是直接包装了offer()，没有附加的行为。
     * 　　由于其内部的数据结构是数组的缘故，很多操作都需要先把元素通过indexOf()转化成对应的数组下标，
     *    再进行进一步的操作，如remove()、removeEq()、contains()等。
     *    其实这个数组保持优先级队列的方式，是采用堆(Heap)的方式，具体可以参考任意一本算法书籍，比如《算法导论》等，这里就不展开解释了。
     *    和堆的特性有关，在寻找指定元素时，必须从头至尾遍历，而不能使用二分查找。
     * LinkedList
     * LinkedList既是List，也是Queue(Deque)，其原因是它是双向的，内部的元素(Entry)同时保留了上一个和下一个元素的引用。
     * 使用头部的引用header，取其previous，就可以获得尾部的引用。
     * 通过这一转换，可以很容易实现Deque所需要的行为。
     * 也正因此，可以支持栈的行为，天生就有push()和pop()方法。
     * 简而言之，是Java中的双向链表，其支持的操作和普通的双向链表一样。
     * 　　和数组不同，根据下标查找特定元素时，只能遍历地获取了，因而在随机访问时效率不如ArrayList。
     *    尽管如此，作者还是尽可能地利用了LinkedList的特性做了点优化，尽量减少了访问次数：
     *
     *  private Entry<E> entry(int index) {
     *  if (index < 0 || index >= size)
     *  throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
     *  Entry<E> e = header;
     *  if (index < (size >> 1)) {
     *  for (int i = 0; i <= index; i++)
     *  e = e.next;
     *  } else {
     *  for (int i = size; i > index; i--)
     *  e = e.previous;
     *  }
     *  return e;
     *  }
     * LinkedList对首部和尾部的插入都支持，但继承自Collection接口的add()方法是在尾部进行插入。
     *
     * */


}
