package study.java提高篇.集合.Java集合细节;

/**
 * @ClassName 概述
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class 概述 {
    //Java 集合细节（一）：请为集合指定初始容量
    //集合是我们在 Java 编程中使用非常广泛的，它就像大海，海纳百川，像万能容器，盛装万物，而且这个大海，万能容器还可以无限变大（如果条件允许）。当这个海、容器的量变得非常大的时候，它的初始容量就会显得很重要了，因为挖海、扩容是需要消耗大量的人力物力财力的。同样的道理，Collection 的初始容量也显得异常重要。所以：对于已知的情景，请为集合指定初始容量。
    //
    //
    //    public static void main(String[] args) {
    //            StudentVO student = null;
    //            long begin1 = System.currentTimeMillis();
    //            List<StudentVO> list1 = new ArrayList<>();
    //            for(int i = 0 ; i < 1000000; i++){
    //                student = new StudentVO(i,"chenssy_"+i,i);
    //                list1.add(student);
    //            }
    //            long end1 = System.currentTimeMillis();
    //            System.out.println("list1 time：" + (end1 - begin1));
    //
    //            long begin2 = System.currentTimeMillis();
    //            List<StudentVO> list2 = new ArrayList<>(1000000);
    //            for(int i = 0 ; i < 1000000; i++){
    //                student = new StudentVO(i,"chenssy_"+i,i);
    //                list2.add(student);
    //            }
    //            long end2 = System.currentTimeMillis();
    //            System.out.println("list2 time：" + (end2 - begin2));
    //        }
    //上面代码两个 list 都是插入 1000000 条数据，只不过 list1 没有没有申请初始化容量，而 list2 初始化容量 1000000。那运行结果如下：
    //
    //
    //    list1 time：1638
    //    list2 time：921
    //从上面的运行结果我们可以看出 list2 的速度是 list1 的两倍左右。在前面 LZ 就提过，ArrayList 的扩容机制是比较消耗资源的。我们先看 ArrayList 的 add 方法：
    //
    //
    //    public boolean add(E e) {
    //            ensureCapacity(size + 1);
    //            elementData[size++] = e;
    //            return true;
    //        }
    //
    //        public void ensureCapacity(int minCapacity) {
    //            modCount++;         //修改计数器
    //            int oldCapacity = elementData.length;
    //            //当前需要的长度超过了数组长度，进行扩容处理
    //            if (minCapacity > oldCapacity) {
    //                Object oldData[] = elementData;
    //                //新的容量 = 旧容量 * 1.5 + 1
    //                int newCapacity = (oldCapacity * 3)/2 + 1;
    //                    if (newCapacity < minCapacity)
    //                        newCapacity = minCapacity;
    //              //数组拷贝，生成新的数组
    //             elementData = Arrays.copyOf(elementData,   newCapacity);
    //            }
    //        }
    //ArrayList 每次新增一个元素，就会检测 ArrayList 的当前容量是否已经到达临界点，如果到达临界点则会扩容 1.5 倍。然而 ArrayList 的扩容以及数组的拷贝生成新的数组是相当耗资源的。所以若我们事先已知集合的使用场景，知道集合的大概范围，我们最好是指定初始化容量，这样对资源的利用会更加好，尤其是大数据量的前提下，效率的提升和资源的利用会显得更加具有优势。


    //Java 集合细节（二）：asList 的缺陷
    //在实际开发过程中我们经常使用 asList 讲数组转换为 List，这个方法使用起来非常方便，但是 asList 方法存在几个缺陷：
    //
    //一、避免使用基本数据类型数组转换为列表
    //使用 8 个基本类型数组转换为列表时会存在一个比较有味的缺陷。先看如下程序：
    //
    //
    //    public static void main(String[] args) {
    //            int[] ints = {1,2,3,4,5};
    //            List list = Arrays.asList(ints);
    //            System.out.println("list'size：" + list.size());
    //        }
    //        ------------------------------------
    //        outPut：
    //        list'size：1
    //程序的运行结果并没有像我们预期的那样是 5 而是逆天的 1，这是什么情况？先看源码：
    //
    //
    //    public static <T> List<T> asList(T... a) {
    //            return new ArrayList<>(a);
    //        }
    //asList 接受的参数是一个泛型的变长参数，我们知道基本数据类型是无法发型化的，也就是说 8 个基本类型是无法作为 asList 的参数的， 要想作为泛型参数就必须使用其所对应的包装类型。但是这个这个实例中为什么没有出错呢？因为该实例是将 int 类型的数组当做其参数，而在 Java 中数组是一个对象，它是可以泛型化的。所以该例子是不会产生错误的。既然例子是将整个 int 类型的数组当做泛型参数，那么经过 asList 转换就只有一个 int 的列表了。如下：
    //
    //
    //    public static void main(String[] args) {
    //        int[] ints = {1,2,3,4,5};
    //        List list = Arrays.asList(ints);
    //        System.out.println("list 的类型:" + list.get(0).getClass());
    //        System.out.println("list.get(0) == ints：" + list.get(0).equals(ints));
    //    }
    //    --------------------------------------------
    //    outPut:
    //    list 的类型:class [I
    //    list.get(0) == ints：true
    //从这个运行结果我们可以充分证明 list 里面的元素就是 int 数组。弄清楚这点了，那么修改方法也就一目了然了：将 int 改变为 Integer。
    //
    //
    //    public static void main(String[] args) {
    //            Integer[] ints = {1,2,3,4,5};
    //            List list = Arrays.asList(ints);
    //            System.out.println("list'size：" + list.size());
    //            System.out.println("list.get(0) 的类型:" +  list.get(0).getClass());
    //            System.out.println("list.get(0) == ints[0]：" + list.get(0).equals(ints[0]));
    //        }
    //        ----------------------------------------
    //        outPut:
    //        list'size：5
    //        list.get(0) 的类型:class java.lang.Integer
    //        list.get(0) == ints[0]：true
    //Java 细节（2.1）：在使用 asList 时不要将基本数据类型当做参数。
    //
    //二、asList 产生的列表不可操作
    //对于上面的实例我们再做一个小小的修改：
    //
    //
    //    public static void main(String[] args) {
    //            Integer[] ints = {1,2,3,4,5};
    //            List list = Arrays.asList(ints);
    //            list.add(6);
    //        }
    //该实例就是讲 ints 通过 asList 转换为 list 类别，然后再通过 add 方法加一个元素，这个实例简单的不能再简单了，但是运行结果呢？打出我们所料：
    //
    //
    //    Exception in thread "main" java.lang.UnsupportedOperationException
    //        at java.utils.AbstractList.add(Unknown Source)
    //        at java.utils.AbstractList.add(Unknown Source)
    //        at com.chenssy.test.arrayList.AsListTest.main(AsListTest.java:10)
    //运行结果尽然抛出 UnsupportedOperationException 异常，该异常表示 list 不支持 add 方法。这就让我们郁闷了，list 怎么可能不支持 add 方法呢？难道 JDK 脑袋堵塞了？我们再看 asList 的源码：
    //
    //
    //    public static <T> List<T> asList(T... a) {
    //            return new ArrayList<>(a);
    //        }
    //asList 接受参数后，直接 new 一个 ArrayList，到这里看应该是没有错误的啊？别急，再往下看:
    //
    //
    //    private static class ArrayList<E> extends AbstractList<E>
    //        implements RandomAccess, java.io.Serializable{
    //            private static final long serialVersionUID = -2764017481108945198L;
    //            private final E[] a;
    //
    //            ArrayList(E[] array) {
    //                if (array==null)
    //                    throw new NullPointerException();
    //                a = array;
    //            }
    //            //.................
    //        }
    //这是 ArrayList 的源码,从这里我们可以看出,此 ArrayList 不是 java.utils.ArrayList，他是 Arrays 的内部类。该内部类提供了 size、toArray、get、set、indexOf、contains 方法，而像 add、remove 等改变 list 结果的方法从 AbstractList 父类继承过来，同时这些方法也比较奇葩，它直接抛出 UnsupportedOperationException 异常：
    //
    //
    //    public boolean add(E e) {
    //            add(size(), e);
    //            return true;
    //        }
    //
    //        public E set(int index, E element) {
    //            throw new UnsupportedOperationException();
    //        }
    //
    //        public void add(int index, E element) {
    //            throw new UnsupportedOperationException();
    //        }
    //
    //        public E remove(int index) {
    //            throw new UnsupportedOperationException();
    //        }
    //通过这些代码可以看出 asList 返回的列表只不过是一个披着 list 的外衣，它并没有 list 的基本特性（变长）。该 list 是一个长度不可变的列表，传入参数的数组有多长，其返回的列表就只能是多长。所以：
    //
    //Java 细节（2.2）：不要试图改变 asList 返回的列表，否则你会自食苦果。


    //Java 集合细节（三）：subList 的缺陷
    //我们经常使用 subString 方法来对 String 对象进行分割处理，同时我们也可以使用 subList、subMap、subSet 来对 List、Map、Set 进行分割处理，但是这个分割存在某些瑕疵。
    //
    //一、subList 返回仅仅只是一个视图
    //首先我们先看如下实例：
    //
    //
    //    public static void main(String[] args) {
    //            List<Integer> list1 = new ArrayList<Integer> ();
    //            list1.add(1);
    //            list1.add(2);
    //
    //            //通过构造函数新建一个包含list1的列表 list2
    //            List<Integer> list2 = new ArrayList<Integer>(list1);
    //
    //            //通过subList生成一个与list1一样的列表 list3
    //            List<Integer> list3 = list1.subList(0, list1.size());
    //
    //            //修改list3
    //            list3.add(3);
    //
    //            System.out.println("list1 == list2：" + list1.equals(list2));
    //            System.out.println("list1 == list3：" + list1.equals(list3));
    //        }
    //这个例子非常简单，无非就是通过构造函数、subList 重新生成一个与 list1 一样的 list，然后修改 list3，最后比较 list1 == list2?、list1 == list3?。按照我们常规的思路应该是这样的：因为 list3 通过 add 新增了一个元素，那么它肯定与 list1 不等，而 list2 是通过 list1 构造出来的，所以应该相等，所以结果应该是：
    //
    //
    //    list1 == list2：true
    //    list1 == list3: false
    //首先我们先不论结果的正确与否，我们先看 subList 的源码：
    //
    //
    //    public List<E> subList(int fromIndex, int toIndex) {
    //            subListRangeCheck(fromIndex, toIndex, size);
    //            return new SubList(this, 0, fromIndex,  toIndex);
    //        }
    //subListRangeCheck 方式是判断 fromIndex、toIndex 是否合法，如果合法就直接返回一个 subList 对象，注意在产生该 new 该对象的时候传递了一个参数 this ，该参数非常重要，因为他代表着原始 list。
    //
    //
    //    /**
    //         * 继承AbstractList类，实现RandomAccess接口
    //         */
    //        private class SubList extends AbstractList<E> implements RandomAccess {
    //            private final AbstractList<E> parent;    //列表
    //            private final int parentOffset;
    //            private final int offset;
    //            int size;
    //
    //            //构造函数
    //            SubList(AbstractList<E> parent,
    //                    int offset, int fromIndex, int toIndex) {
    //                this.parent = parent;
    //                this.parentOffset = fromIndex;
    //                this.offset = offset + fromIndex;
    //                this.size = toIndex - fromIndex;
    //                this.modCount = ArrayList.this.modCount;
    //            }
    //
    //            //set方法
    //            public E set(int index, E e) {
    //                rangeCheck(index);
    //                checkForComodification();
    //                E oldValue = ArrayList.this.elementData(offset + index);
    //                ArrayList.this.elementData[offset + index] = e;
    //                return oldValue;
    //            }
    //
    //            //get方法
    //            public E get(int index) {
    //                rangeCheck(index);
    //                checkForComodification();
    //                return ArrayList.this.elementData(offset + index);
    //            }
    //
    //            //add方法
    //            public void add(int index, E e) {
    //                rangeCheckForAdd(index);
    //                checkForComodification();
    //                parent.add(parentOffset + index, e);
    //                this.modCount = parent.modCount;
    //                this.size++;
    //            }
    //
    //            //remove方法
    //            public E remove(int index) {
    //                rangeCheck(index);
    //                checkForComodification();
    //                E result = parent.remove(parentOffset + index);
    //                this.modCount = parent.modCount;
    //                this.size--;
    //                return result;
    //            }
    //        }
    //该 SubLsit 是 ArrayList 的内部类，它与 ArrayList 一样，都是继承 AbstractList 和实现 RandomAccess 接口。同时也提供了 get、set、add、remove 等 list 常用的方法。但是它的构造函数有点特殊，在该构造函数中有两个地方需要注意：
    //
    //1、this.parent = parent;而 parent 就是在前面传递过来的 list，也就是说 this.parent 就是原始 list 的引用。
    //
    //2、this.offset = offset + fromIndex;this.parentOffset = fromIndex;。同时在构造函数中它甚至将 modCount（fail-fast机制）传递过来了。
    //
    //我们再看 get 方法，在 get 方法中 return ArrayList.this.elementData(offset + index);这段代码可以清晰表明 get 所返回就是原列表 offset + index位置的元素。同样的道理还有 add 方法里面的：
    //
    //
    //    parent.add(parentOffset + index, e);
    //    this.modCount = parent.modCount;
    //remove 方法里面的
    //
    //
    //    E result = parent.remove(parentOffset + index);
    //    this.modCount = parent.modCount;
    //诚然，到了这里我们可以判断 subList 返回的 SubList 同样也是 AbstractList 的子类，同时它的方法如 get、set、add、remove 等都是在原列表上面做操作，它并没有像 subString 一样生成一个新的对象。所以 subList 返回的只是原列表的一个视图，它所有的操作最终都会作用在原列表上。
    //
    //那么从这里的分析我们可以得出上面的结果应该恰恰与我们上面的答案相反：
    //
    //
    //    list1 == list2：false
    //    list1 == list3：true
    //Java 细节（3.1）：subList 返回的只是原列表的一个视图，它所有的操作最终都会作用在原列表上
    //
    //二、subList 生成子列表后，不要试图去操作原列表
    //从上面我们知道 subList 生成的子列表只是原列表的一个视图而已，如果我们操作子列表它产生的作用都会在原列表上面表现，但是如果我们操作原列表会产生什么情况呢？
    //
    //
    //    public static void main(String[] args) {
    //            List<Integer> list1 = new ArrayList<Integer>();
    //            list1.add(1);
    //            list1.add(2);
    //
    //            //通过subList生成一个与list1一样的列表 list3
    //            List<Integer> list3 = list1.subList(0, list1.size());
    //            //修改list3
    //            list1.add(3);
    //
    //            System.out.println("list1'size：" + list1.size());
    //            System.out.println("list3'size：" + list3.size  ());
    //        }
    //该实例如果不产生意外，那么他们两个 list 的大小都应该都是 3，但是偏偏事与愿违，事实上我们得到的结果是这样的：
    //
    //
    //    list1'size：3
    //    Exception in thread "main" java.utils.ConcurrentModificationException
    //        at java.utils.ArrayList$SubList.checkForComodification(Unknown Source)
    //        at java.utils.ArrayList$SubList.size(Unknown Source)
    //        at com.chenssy.test.arrayList.SubListTest.main(SubListTest.java:17)
    //list1 正常输出，但是 list3 就抛出 ConcurrentModificationException 异常，看过我另一篇博客的同仁肯定对这个异常非常，fail-fast？不错就是 fail-fast 机制，在 fail-fast 机制中，LZ 花了很多力气来讲述这个异常，所以这里 LZ 就不对这个异常多讲了（更多请点这里：Java 提高篇（三四）—–fail-fast 机制 ）。我们再看 size 方法：
    //
    //
    //    public int size() {
    //                checkForComodification();
    //                return this.size;
    //            }
    //size 方法首先会通过 checkForComodification 验证，然后再返回this.size。
    //
    //
    //    private void checkForComodification() {
    //                if (ArrayList.this.modCount != this.modCount)
    //                    throw new ConcurrentModificationException();
    //            }
    //该方法表明当原列表的 modCount 与 this.modCount 不相等时就会抛出 ConcurrentModificationException。同时我们知道 modCount 在 new 的过程中 “继承”了原列表 modCount，只有在修改该列表（子列表）时才会修改该值（先表现在原列表后作用于子列表）。而在该实例中我们是操作原列表，原列表的 modCount 当然不会反应在子列表的 modCount 上啦，所以才会抛出该异常。
    //
    //对于子列表视图，它是动态生成的，生成之后就不要操作原列表了，否则必然都导致视图的不稳定而抛出异常。最好的办法就是将原列表设置为只读状态，要操作就操作子列表：
    //
    //
    //    //通过subList生成一个与list1一样的列表 list3
    //    List<Integer> list3 = list1.subList(0, list1.size());
    //
    //    //对list1设置为只读状态
    //    list1 = Collections.unmodifiableList(list1);
    //Java 细节（3.2）：生成子列表后，不要试图去操作原列表，否则会造成子列表的不稳定而产生异常
    //
    //三、推荐使用 subList 处理局部列表
    //在开发过程中我们一定会遇到这样一个问题：获取一堆数据后，需要删除某段数据。例如，有一个列表存在 1000 条记录，我们需要删除 100-200 位置处的数据，可能我们会这样处理：
    //
    //
    //    for(int i = 0 ; i < list1.size() ; i++){
    //       if(i >= 100 && i <= 200){
    //           list1.remove(i);
    //           /*
    //            * 当然这段代码存在问题，list remove之后后面的元素会填充上来，
    //            * 所以需要对i进行简单的处理，当然这个不是这里讨论的 问题。
    //            */
    //       }
    //    }
    //这个应该是我们大部分人的处理方式吧，其实还有更好的方法，利用 subList。在前面 LZ 已经讲过，子列表的操作都会反映在原列表上。所以下面一行代码全部搞定：
    //
    //
    //    list1.subList(100, 200).clear();
    //简单而不失华丽！！！！！




    //Java 集合细节（四）：保持 compareTo 和 equals 同步
    //在 Java 中我们常使用 Comparable 接口来实现排序，其中 compareTo 是实现该接口方法。我们知道 compareTo 返回 0 表示两个对象相等，返回正数表示大于，返回负数表示小于。同时我们也知道 equals 也可以判断两个对象是否相等，那么他们两者之间是否存在关联关系呢？
    //
    //
    //    public class Student implements Comparable<Student>{
    //        private String id;
    //        private String name;
    //        private int age;
    //
    //        public Student(String id,String name,int age){
    //            this.id = id;
    //            this.name = name;
    //            this.age = age;
    //        }
    //
    //        public boolean equals(Object obj){
    //            if(obj == null){
    //            return false;
    //            }
    //
    //            if(this == obj){
    //                return true;
    //            }
    //
    //            if(obj.getClass() != this.getClass()){
    //                return false;
    //            }
    //
    //            Student student = (Student)obj;
    //            if(!student.getName().equals(getName())){
    //                return false;
    //            }
    //
    //            return true;
    //        }
    //
    //        public int compareTo(Student student) {
    //            return this.age - student.age;
    //        }
    //
    //        /** 省略getter、setter方法 */
    //    }
    //Student 类实现 Comparable 接口和实现 equals 方法，其中 compareTo 是根据 age 来比对的，equals 是根据 name 来比对的。
    //
    //
    //     public static void main(String[] args){
    //            List<Student> list = new ArrayList<>();
    //            list.add(new Student("1", "chenssy1", 24));
    //            list.add(new Student("2", "chenssy1", 26));
    //
    //            Collections.sort(list);   //算法排序
    //
    //            Student student = new Student("2", "chenssy1", 26);
    //
    //            //检索student在list中的位置
    //            int index1 = list.indexOf(student);
    //            int index2 = Collections.binarySearch(list, student);
    //
    //            System.out.println("index1 = " + index1);
    //            System.out.println("index2 = " + index2);
    //       }
    //按照常规思路来说应该两者 index 是一致的，因为他们检索的是同一个对象，但是非常遗憾，其运行结果：
    //
    //
    //    index1 = 0
    //    index2 = 1
    //为什么会产生这样不同的结果呢？这是因为 indexOf 和 binarySearch 的实现机制不同，indexOf 是基于 equals 来实现的只要 equals 返回 TRUE 就认为已经找到了相同的元素。而 binarySearch 是基于 compareTo 方法的，当 compareTo 返回 0 时就认为已经找到了该元素。在我们实现的 Student 类中我们覆写了 compareTo 和 equals 方法，但是我们的 compareTo、equals 的比较依据不同，一个是基于 age、一个是基于 name。比较依据不同那么得到的结果很有可能会不同。所以知道了原因，我们就好修改了：将两者之间的比较依据保持一致即可。
    //
    //对于 compareTo 和 equals 两个方法我们可以总结为：compareTo 是判断元素在排序中的位置是否相等，equals 是判断元素是否相等，既然一个决定排序位置，一个决定相等，所以我们非常有必要确保当排序位置相同时，其 equals 也应该相等。
    //
    //细节（4.1）：实现了 compareTo 方法，就有必要实现 equals 方法，同时还需要确保两个方法同步。
}
