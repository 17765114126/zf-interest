package study.java8特性;

import java.util.function.*;

/**
 * @ClassName Test3
 * @Author zhaofu
 * @Date 2019/8/7
 * @Version V1.0
 **/
public class Test2 {
    /**
     * 1 Java8函数式编程语法入门
     * Java8中函数式编程语法能够精简代码。
     * 使用Consumer作为示例，它是一个函数式接口，包含一个抽象方法accept，这个方法只有输入而无输出。
     * 现在我们要定义一个Consumer对象，传统的方式是这样定义的：
     *
     * Consumer c = new Consumer() {
     *     @Override
     *     public void accept(Object o) {
     *         System.out.println(o);
     *     }
     * };
     *
     * 而在Java8中，针对函数式编程接口，可以这样定义：
     *
     * Consumer c = (o) -> {
     *     System.out.println(o);
     * };
     * 上面已说明，函数式编程接口都只有一个抽象方法，因此在采用这种写法时，编译器会将这段函数编译后当作该抽象方法的实现。
     * 如果接口有多个抽象方法，编译器就不知道这段函数应该是实现哪个方法的了。
     * 因此，=后面的函数体我们就可以看成是accept函数的实现。
     *
     * 输入：->前面的部分，即被()包围的部分。此处只有一个输入参数，实际上输入是可以有多个的，如两个参数时写法：(a, b);当然也可以没有输入，此时直接就可以是()。
     * 函数体：->后面的部分，即被{}包围的部分；可以是一段代码。
     * 输出：函数式编程可以没有返回值，也可以有返回值。如果有返回值时，需要代码段的最后一句通过return的方式返回对应的值。
     * 当函数体中只有一个语句时，可以去掉{}进一步简化：
     *
     * Consumer c = (o) -> System.out.println(o);
     * 然而这还不是最简的，由于此处只是进行打印，调用了System.out中的println静态方法对输入参数直接进行打印，因此可以简化成以下写法：
     *
     * Consumer c = System.out::println;
     * 它表示的意思就是针对输入的参数将其调用System.out中的静态方法println进行打印。
     * 到这一步就可以感受到函数式编程的强大能力。
     * 通过最后一段代码，我们可以简单的理解函数式编程，Consumer接口直接就可以当成一个函数了，这个函数接收一个输入参数，然后针对这个输入进行处理；
     * 当然其本质上仍旧是一个对象，但我们已经省去了诸如老方式中的对象定义过程，直接使用一段代码来给函数式接口对象赋值。
     * 而且最为关键的是，这个函数式对象因为本质上仍旧是一个对象，因此可以做为其它方法的参数或者返回值，可以与原有的代码实现无缝集成！

     * 下面对Java中的几个预先定义的函数式接口及其经常使用的类进行分析学习。
     *
     * 2 Java函数式接口
     * 2.1 Consumer
     * Consumer是一个函数式编程接口； 顾名思义，Consumer的意思就是消费，即针对某个东西我们来使用它，因此它包含有一个有输入而无输出的accept接口方法；
     * 除accept方法，它还包含有andThen这个方法；
     * 其定义如下：
     *
     * default Consumer<T> andThen(Consumer<? super T> after) {
     *     Objects.requireNonNull(after);
     *     return (T t) -> { accept(t); after.accept(t); };
     * }
     * 可见这个方法就是指定在调用当前Consumer后是否还要调用其它的Consumer；
     * 使用示例：
     */
      public static void consumerTest() {
          Consumer f = System.out::println;
          Consumer f2 = n -> System.out.println(n + "-F2");
          Consumer f3 = x-> System.out.println(x+"ss");
          //执行完F后再执行F2的Accept方法
          f.andThen(f2).accept("test");

          //连续执行F的Accept方法
          f.andThen(f).andThen(f).andThen(f).accept("test1");
      }
   /** 2.2 Function
     * Function也是一个函数式编程接口；它代表的含义是“函数”，而函数经常是有输入输出的，因此它含有一个apply方法，包含一个输入与一个输出；
     * 除apply方法外，它还有compose与andThen及indentity三个方法，其使用见下述示例；
     * Function测试
     */

    public static void functionTest() {
        Consumer c = new Consumer() {
          @Override
          public void accept(Object o) {
              System.out.println(o);
          }
      };
        Consumer c1 = (o) -> {
          System.out.println(o);
      };
     Function<Integer, Integer> f = s -> s++;
     Function<Integer, Integer> g = s -> s * 2;

        /**
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
     System.out.println(f.compose(g).apply(1));

          /**
         *       表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         *       相当于以下代码
         *       Integer a = f.apply(1);
         *       System.out.println(g.apply(a));
         */
     System.out.println(f.andThen(g).apply(1));

         /**
         *       identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
     System.out.println(Function.identity().apply("a"));
     }
             /**2.3Predicate
     *Predicate为函数式接口，predicate的中文意思是“断定”，即判断的意思，判断某个东西是否满足某种条件；因此它包含test方法，根据输入值来做逻辑判断，其结果为True或者False。
            *它的使用方法示例如下：
            *
            * /**
     *  * Predicate测试
     *  */

    private static void predicateTest() {
     Predicate<String> p = o -> o.equals("test");
     Predicate<String> g = o -> o.startsWith("t");

     /**
         *      * negate: 用于对原来的Predicate做取反处理；
         *      * 如当调用p.test("test")为True时，调用p.negate().test("test")就会是False；
         *      */
    // Assert.assertFalse(p.negate().test("test"));

          /**
         *      * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         *      */
    // Assert.assertTrue(p.and(g).test("test"));


    // or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False

    // Assert.assertTrue(p.or(g).test("ta"));
     }

     /**
     3函数式编程接口的使用
     * 通过Stream以及Optional两个类，可以进一步利用函数式接口来简化代码。

            3.1Stream
     Stream可以对多个元素进行一系列的操作，也可以支持对某些操作进行并发处理。

     3.1.1Stream对象的创建
     Stream对象的创建途径有以下几种

      a.创建空的Stream对象

        Stream stream = Stream.empty();

      b.通过集合类中的stream或者parallelStream方法创建；

        List<String> list = Arrays.asList("a", "b", "c", "d");

        Stream listStream = list.stream();                   //获取串行的Stream对象

        Stream parallelListStream = list.parallelStream();   //获取并行的Stream对象

      c.通过Stream中的of方法创建：


    Stream s = Stream.of("test");

    Stream s1 = Stream.of("a", "b", "c", "d");
             d.通过Stream中的iterate方法创建：
            iterate方法有两个不同参数的方法：


    public static <T> Stream<T> iterate(final T seed, final UnaryOperator<T> f);


    public static <T> Stream<T> iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
            *其中第一个方法将会返回一个无限有序值的Stream对象：它的第一个元素是seed，第二个元素是f.apply(seed);
            *第N个元素是f.apply(n-1个元素的值)；生成无限值的方法实际上与Stream的中间方法类似，在遇到中止方法前一般是不真正的执行的。
            *因此无限值的这个方法一般与limit等方法一起使用，来获取前多少个元素。
            *当然获取前多少个元素也可以使用第二个方法。
            *第二个方法与第一个方法生成元素的方式类似，不同的是它返回的是一个有限值的Stream；中止条件是由hasNext来断定的。

            *第二种方法的使用示例如下：

            * /**
     * 本示例表示从1开始组装一个序列，第一个是1，第二个是1+1即2，第三个是2+1即3..，直接10时中止；
     * 也可简化成以下形式：
     *        Stream.iterate(1,
     *        n -> n <= 10,
     *        n -> n+1).forEach(System.out::println);
     * 写成以下方式是为简化理解
     */
/*            Stream.iterate(1,
            new Predicate<Integer>(){
     @Override
     public boolean test (Integer integer){
     return integer <= 10;
     }
     }
            new UnaryOperator<Integer>(){
     @Override
     public Integer apply (Integer integer){
     return integer + 1;
     }
     })

    forEach(System.out::println);*/

             /**e.通过Stream中的generate方法创建
     与iterate中创建无限元素的Stream类似，不过它的每个元素与前一元素无关，且生成的是一个无序的队列。也就是说每一个元素都可以随机生成。因此一般用来创建常量的Stream以及随机的Stream等。
            示例如下：

            * /**
     *  * 随机生成10个Double元素的Stream并将其打印
     *  */
//            Stream.generate(new Supplier<Double>()
//
//    {
//     @Override
//     public Double get () {
//     return Math.random();
//     }
//     });
//
//    limit(10).
//
//    forEach(System.out::println);*
//              //上述写法可以简化成以下写法：
//             Stream.generate(()->Math.random()).
//
//    limit(10).
//
//    forEach(System.out::println);
    /**
             f.通过Stream中的concat方法连接两个Stream对象生成新的Stream对象
     这个比较好理解不再赘述。

    3.1.2Stream对象的使用

     Stream对象提供多个非常有用的方法，这些方法可以分成两类：
     *      中间操作：将原始的Stream转换成另外一个Stream；如filter返回的是过滤后的Stream。
     *      终端操作：产生的是一个结果或者其它的复合操作；如count或者forEach操作。

     *      其清单如下所示，方法的具体说明及使用示例见后文。
     *
     *      所有中间操作
     *  sequential 返回一个相等的串行的Stream对象，如果原Stream对象已经是串行就可能会返回原对象
     *  parallel 返回一个相等的并行的Stream对象，如果原Stream对象已经是并行的就会返回原对象
     *  unordered 返回一个不关心顺序的Stream对象，如果原对象已经是这类型的对象就会返回原对象
     *  onClose 返回一个相等的Steam对象，同时新的Stream对象在执行Close方法时会调用传入的Runnable对象
     *  close 关闭Stream对象
     *  filter 元素过滤：对Stream对象按指定的Predicate进行过滤，返回的Stream对象中仅包含未被过滤的元素
     *  map 元素一对一转换：使用传入的Function对象对Stream中的所有元素进行处理，返回的Stream对象中的元素为原元素处理后的结果
     *  mapToInt 元素一对一转换：将原Stream中的使用传入的IntFunction加工后返回一个IntStream对象
     *  flatMap 元素一对多转换：对原Stream中的所有元素进行操作，每个元素会有一个或者多个结果，然后将返回的所有元素组合成一个统一的Stream并返回；
     *  distinct 去重：返回一个去重后的Stream对象
     *  sorted 算法排序：返回排序后的Stream对象
     *  peek 使用传入的Consumer对象对所有元素进行消费后，返回一个新的包含所有原来元素的Stream对象
     *  limit 获取有限个元素组成新的Stream对象返回
     *  skip 抛弃前指定个元素后使用剩下的元素组成新的Stream返回
     *  takeWhile 如果Stream是有序的（Ordered），那么返回最长命中序列（符合传入的Predicate的最长命中序列）组成的Stream；如果是无序的，那么返回的是所有符合传入的Predicate的元素序列组成的Stream。
     *  dropWhile 与takeWhile相反，如果是有序的，返回除最长命中序列外的所有元素组成的Stream；如果是无序的，返回所有未命中的元素组成的Stream。

     *      所有终端操作
     *
     *  iterator 返回Stream中所有对象的迭代器;
     *  spliterator 返回对所有对象进行的spliterator对象
     *  forEach 对所有元素进行迭代处理，无返回值
     *  forEachOrdered 按Stream的Encounter所决定的序列进行迭代处理，无返回值
     *  toArray 返回所有元素的数组
     *  reduce 使用一个初始化的值，与Stream中的元素一一做传入的二合运算后返回最终的值。每与一个元素做运算后的结果，再与下一个元素做运算。它不保证会按序列执行整个过程。
     *  collect 根据传入参数做相关汇聚计算
     *  min 返回所有元素中最小值的Optional对象；如果Stream中无任何元素，那么返回的Optional对象为Empty
     *  max 与Min相反
     *  count 所有元素个数
     *  anyMatch 只要其中有一个元素满足传入的Predicate时返回True，否则返回False
     *  allMatch 所有元素均满足传入的Predicate时返回True，否则False
     *  noneMatch 所有元素均不满足传入的Predicate时返回True，否则False
     *  findFirst 返回第一个元素的Optioanl对象；如果无元素返回的是空的Optional；如果Stream是无序的，那么任何元素都可能被返回。
     *  findAny 返回任意一个元素的Optional对象，如果无元素返回的是空的Optioanl。
     *  isParallel 判断是否当前Stream对象是并行的
     *
     *  下面就几个比较常用的方法举例说明其用法：

     *3.1.2.1filter

     *用于对Stream中的元素进行过滤，返回一个过滤后的Stream

     *其方法定义如下：

    Stream<T> filter(Predicate<? super T> predicate);
             *使用示例：

    Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
     * //查找所有包含t的元素并进行打印
     s.filter(n ->n.contains("t")).
     forEach(System.out::println);

     3.1.2.2 map
     *      元素一对一转换。
     *      它接收一个Funcation参数，用其对Stream中的所有元素进行处理，返回的Stream对象中的元素为Function对原元素处理后的结果
     *      其方法定义如下：

    <R> Stream<R> map(Function<? super T, ? extends R> mapper);
     示例，假设我们要将一个String类型的Stream对象中的每个元素添加相同的后缀.txt，如a变成a.txt，其写法如下：

    Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
    s.map(n ->n.concat(".txt")).

    forEach(System.out::println);

    3.1.2.3flatMap

     *元素一对多转换：对原Stream中的所有元素使用传入的Function进行处理，每个元素经过处理后生成一个多个元素的Stream对象，
     * 然后将返回的所有Stream对象中的所有元素组合成一个统一的Stream并返回；
            *方法定义如下：


    <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
             *示例，假设要对一个String类型的Stream进行处理，将每一个元素的拆分成单个字母，并打印：

    Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa");
     *s.flatMap(n ->Stream.of(n.split(""))).

    forEach(System.out::println);

     *3.1.2.4takeWhile
     *方法定义如下：

    default Stream<T> takeWhile(Predicate<? super T> predicate)

     *如果Stream是有序的（Ordered），那么返回最长命中序列（符合传入的Predicate的最长命中序列）组成的Stream；
     * 如果是无序的，那么返回的是所有符合传入的Predicate的元素序列组成的Stream。
     *与Filter有点类似，不同的地方就在当Stream是有序时，返回的只是最长命中序列。
     *如以下示例，通过takeWhile查找”test”, “t1”, “t2”, “teeeee”, “aaaa”, “taaa”这几个元素中包含t的最长命中序列：

    Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
     * //以下结果将打印： "test", "t1", "t2", "teeeee"，最后的那个taaa不会进行打印
     *s.takeWhile(n ->n.contains("t")).
    forEach(System.out::println);

     *3.1.2.5dropWhile
     *与takeWhile相反，如果是有序的，返回除最长命中序列外的所有元素组成的Stream；如果是无序的，返回所有未命中的元素组成的Stream;其定义如下：

    default Stream<T> dropWhile(Predicate<? super T> predicate)
     *如以下示例，通过dropWhile删除”test”, “t1”, “t2”, “teeeee”, “aaaa”, “taaa”这几个元素中包含t的最长命中序列：

    Stream<String> s = Stream.of("test", "t1", "t2", "teeeee", "aaaa", "taaa");
     * //以下结果将打印："aaaa", "taaa" 　
     *s.dropWhile(n ->n.contains("t")).

    forEach(System.out::println);

     *3.1.2.6reduce与collect
     *关于reduce与collect由于功能较为复杂，在后续将进行单独分析与学习，此处暂不涉及。

     *3.2Optional
     *用于简化Java中对空值的判断处理，以防止出现各种空指针异常。
     *Optional实际上是对一个变量进行封装，它包含有一个属性value，实际上就是这个变量的值。

     *3.2.1Optional对象创建
     *它的构造函数都是private类型的，因此要初始化一个Optional的对象无法通过其构造函数进行创建。它提供了一系列的静态方法用于构建Optional对象:
     *
     *3.2.1.1empty
     *用于创建一个空的Optional对象；其value属性为Null。
     *如：

    Optional o = Optional.empty();
     *3.2.1.2of
     *根据传入的值构建一个Optional对象;
     *传入的值必须是非空值，否则如果传入的值为空值，则会抛出空指针异常。
            *使用：
            *o =Optional.of("test");
             *3.2.1.3ofNullable
     *根据传入值构建一个Optional对象
     *传入的值可以是空值，如果传入的值是空值，则与empty返回的结果是一样的。
            *3.2.2方法
     *Optional包含以下方法：

    方法名 说明
     *   get 获取Value的值，如果Value值是空值，则会抛出NoSuchElementException异常；因此返回的Value值无需再做空值判断，只要没有抛出异常，都会是非空值。
     *   isPresent Value是否为空值的判断；
     *   ifPresent 当Value不为空时，执行传入的Consumer；
     *   ifPresentOrElse Value不为空时，执行传入的Consumer；否则执行传入的Runnable对象；
     *   filter 当Value为空或者传入的Predicate对象调用test(value)返回False时，返回Empty对象；否则返回当前的Optional对象
     *   map 一对一转换：当Value为空时返回Empty对象，

    否则返回传入的Function执行apply(value)后的结果组装的Optional对象；
     *   flatMap 一对多转换：当Value为空时返回Empty对象，

    否则传入的Function执行apply(value)后返回的结果（其返回结果直接是Optional对象）
     *   or 如果Value不为空，则返回当前的Optional对象；否则，返回传入的Supplier生成的Optional对象；
     *   stream 如果Value为空，返回Stream对象的Empty值；否则返回Stream.of(value)的Stream对象；
     *   orElse Value不为空则返回Value，否则返回传入的值；
     *   orElseGet Value不为空则返回Value，否则返回传入的Supplier生成的值；
     *   orElseThrow Value不为空则返回Value，否则抛出Supplier中生成的异常对象；
     *3.2.3使用场景
     *常用的使用场景如下：

     *3.2.3.1判断结果不为空后使用
     *如某个函数可能会返回空值，以往的做法：

    String s = test();
     *if(null!=s)

    {
     *System.out.println(s);
     *}
             *现在的写法就可以是：

    Optional<String> s = Optional.ofNullable(test());
     *s.ifPresent(System.out::println);
     *乍一看代码复杂度上差不多甚至是略有提升；那为什么要这么做呢？
     *一般情况下，我们在使用某一个函数返回值时，要做的第一步就是去分析这个函数是否会返回空值；如果没有进行分析或者分析的结果出现偏差，导致函数会抛出空值而没有做检测，那么就会相应的抛出空指针异常！
     *而有了Optional后，在我们不确定时就可以不用去做这个检测了，所有的检测Optional对象都帮忙我们完成，我们要做的就是按上述方式去处理。
     *
     *3.2.3.2变量为空时提供默认值
     *如要判断某个变量为空时使用提供的值，然后再针对这个变量做某种运算；
     *以往做法：

            *if(null==s)

    {
     *s = "test";
     *}
     *System.out.println(s);

     *现在的做法：

    Optional<String> o = Optional.ofNullable(s);
     *System.out.println(o.orElse("test"));

     *3.2.3.3变量为空时抛出异常，否则使用
     *以往写法：

            *if(null==s)

    {
     *throw new Exception("test");
     *}
     *System.out.println(s);

     *现在写法：
    Optional<String> o = Optional.ofNullable(s);
     System.out.println(o.orElseThrow(()->new

    Exception("test")));

             其它场景待补充。
            ---------------------
            原文链接：https://blog.csdn.net/icarusliu/article/details/79495534
     *
     * */
}
