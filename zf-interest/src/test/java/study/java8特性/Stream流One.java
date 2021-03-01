package study.java8特性;

import com.example.springboot.model.Student;

import java.util.*;
import java.util.stream.*;

/**
 * @ClassName Stream流One
 * @Author zhaofu
 * @Date 2019/12/11
 * @Version V1.0
 **/
public class Stream流One {
    public static void main(String[] args) {
        /**
         * 强大的Stream API
         * */

        /**
         *        Stream的操作分为三个步骤：
         *         1、创建Stream
         *         2、中间操作（对数据进行操作）
         *         3、终止操作（如果没有终止操作，中间操作是不执行的）
         *         Stream创建的方式Collection 提供了两个方法一种是stream()，还一种parallelStream()，
         *         stream()是是创建串行流，parallelStream()创建的是并行流，知道了它的创建方式
         */
//        一、集合创建Stream
        List<String> list = new ArrayList<>();
        Stream<String> stream2 = list.stream();
//      这样就可以获取一个集合的Stream。

//        二、数组创建Stream
        Integer[] nums = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(nums);

//        三、通过 Stream 类中静态方法 of()
        Stream<Integer> stream4 = Stream.of(1, 2, 3, 4, 5, 6);

//        四，创建无限流
//        迭代
        Stream<Integer> stream3 = Stream.iterate(0, (x) -> x + 1);
//        这里通过迭代的方式，从0开始，这样就可以获取无限流。

/**
 *        首先说一下中间操作：多个中间操作可以连接起来形成一个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何的处理！
 *        而在终止操作时一次性全部处理，称为“惰性求值”。
 */
/**     一、筛选与切片
 *
 *        filter(Predicate p) 接收 Lambda ， 从流中排除某些元素。
 *
 *        distinct() 筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
 *
 *        limit(long maxSize) 截断流，使其元素不超过给定数量。
 *
 *        skip(long n) 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
 */
//        1、filter(Predicate p)，需求：就是过滤出年龄大于20岁的员工
//                这里是一个员工的集合
        List<Student> students = Arrays.asList(
                new Student("张三", 19),
                new Student("李四", 102),
                new Student("王五", 103),
                new Student("赵六", 104),
                new Student("田七", 105),
                new Student("田七", 105)
        );
        System.out.println("filter过滤------");
        students.stream().filter((e) -> e.getAge() > 20).forEach(System.out::println);

//        System.out::println 这里使用的是方法引用中的 对象的引用 :: 实例方法名,关于这个我在只后的文章中都不提了，
//        如果还有不理解的请阅读我的这篇文章Lambda表达式学习（四）Lambda 表达式方法引用、构造器引用和数组引用
//       （https://www.jianshu.com/p/4435d240e76e）

//        2、distinct()方法就是去重，上面的解释也很清楚，这里就不给出代码了，注意的是要重写hashCode()方法和equals()方法。
        System.out.println("distinct去重------");
        students.stream().distinct().forEach(System.out::println);
//        3、limit(long maxSize) 截取前面maxSize条数据,在上面的代码的基础上我加了个方法
        System.out.println("limit截取前面几条------");
        students.stream().filter((e) -> e.getAge() > 20).limit(2).forEach(System.out::println);

//        4、与limit相反的就是skip(long n),跳过前面几条，代码和上面差不多，就不写出来了。
        System.out.println("skip跳过前面几条------");
        students.stream().skip(2L).forEach(System.out::println);
/**
 *        二、映射
 *
 *        map(Function f) 接收 Lambda ，将元素转换成其他形式或提取信息;接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 *
 *        mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 DoubleStream。
 *
 *        mapToInt(ToIntFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 IntStream。
 *
 *        mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的 LongStream。
 *
 *        flatMap(Function f) 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
 */
//      1、map(Function f）
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc");

        Stream<String> stream = strList.stream().map(String::toUpperCase);
        System.out.println("map的toUpperCase所有元素转换大写------");
        stream.forEach(System.out::println);

        System.out.println("map的toLowerCase所有元素转换小写------");
        Stream<String> stringStream = strList.stream().map(String::toLowerCase);
        stringStream.forEach(System.out::println);

        Stream<Integer> integerStream = strList.stream().map(String::length);
        integerStream.forEach(System.out::println);

//        strList.stream().flatMap().forEach(System.out::println);

//        2、flatMap(Funtion f)
        String StreamApiTest = "abcdefg";
        System.out.println("(大雾。。。。。。。 )");
        Stream<Character> characterStream = filterCharacter(StreamApiTest);
        characterStream.forEach((character) -> {
            System.out.println(character);
        });

//        Stream<Stream<Character>> stream5 = strList.stream()
//                .map(StreamApiTest::characterStream);
//        stream5.forEach((sm) -> {
//            sm.forEach(System.out::print);
//        });

//        Stream<Character> stream6 = strList.stream()
//                .flatMap(StreamApiTest::filterCharacter);
//        stream6.forEach(System.out::print);

//        这两种方式实现的效果是一样的，但是可以看map的返回的是一个Stream流中的泛型是一个流，
//        而flatMap返回的流的泛型的是一个Character，然后冲forEach的方式也可以看出其中的差异。

        List<Double> doubles = Arrays.asList(1D, 2D, 3D, 4D);

        DoubleStream ds = doubles.stream()
                .mapToDouble((d) -> d * 2);
        System.out.println("返回相应的DoubleStream流------");
        ds.forEach(System.out::println);

        List<Long> longs = Arrays.asList(1L, 2L, 3L, 4L);
        LongStream longStream = longs.stream().mapToLong(l -> l + 3);
        System.out.println("返回相应的LongStream流------");
        longStream.forEach(System.out::println);

        List<Integer> ints = Arrays.asList(1, 2, 3, 4);
        IntStream intStream = ints.stream().mapToInt(l -> l - 1);
        System.out.println("返回相应的IntStream流------");
        intStream.forEach(System.out::println);


/**      sorted() 产生一个新流，其中按自然顺序排序
 *        sorted(Comparator comp) 产生一个新流，其中按比较器顺序排序
 */
        List<Student> studentss = Arrays.asList(
                new Student("一三", 19),
                new Student("田七", 102),
                new Student("王五", 103),
                new Student("赵一", 18),
                new Student("田七", 105),
                new Student("田六公开", 105)
        );

//      1、sorted()
        System.out.println("sorted根据名字自然排序？？？（大雾）------");
        studentss.stream().map(Student::getName).sorted().forEach(System.out::println);
//      根据名字去进行一个自然排序,也就是流中是什么顺序，然后输出的就是什么顺序，看一下排序的结果：
//      结果和我们期待的是一样的，在看看带有参数的排序的方式

        System.out.println("先按年龄排序，若年龄一样按名字排序------");
        studentss.stream()
                .sorted((x, y) -> {
                    if (x.getAge() == y.getAge()) {
                        return x.getName().compareTo(y.getName());
                    } else {
                        return Integer.compare(x.getAge(), y.getAge());
                    }
                }).forEach(System.out::println);

//      这个排序是先按照年龄进行排序，如果年龄一样就按照名字进行排序
/**
 *
 *  Stream终止操作大概分为这几种：
 *
 *         查找与匹配
 *         归约
 *         收集
 *         接下来围绕这3种进行讲解
 *
 *         一、查找与匹配
 *
 *         allMatch——检查是否匹配所有元素
 *         anyMatch——检查是否至少匹配一个元素
 *         noneMatch——检查是否没有匹配的元素
 *         findFirst——返回第一个元素
 *         findAny——返回当前流中的任意元素
 *         count——返回流中元素的总个数
 *         max——返回流中最大值
 *         min——返回流中最小值
 */
//        1、allMatch
        boolean b1 = studentss.stream().allMatch((e) -> e.getName().contains("五"));
        System.out.println("allMatch——检查是否匹配所有元素");
        System.out.println(b1);
//        这个意思就是说，查看每个名字中是否都包含"五",从上面的集合中显而易见，不是每个名字中都包含"五",所以打印出false。

//        2、anyMatch
        boolean b2 = studentss.stream().anyMatch((e) -> e.getName().contains("五"));
        System.out.println("anyMatch——检查是否至少匹配一个元素");
        System.out.println(b2);

//        3、noneMatch
        boolean b3 = studentss.stream().noneMatch((e) -> e.getName().contains("五"));
        System.out.println("noneMatch——检查是否没有匹配的元素");
        System.out.println(b3);

//        4.findFirst
        Optional<Student> first = studentss.stream().findFirst();
        System.out.println("findFirst——返回第一个元素");
        System.out.println(first);

//        5.findAny
        Optional<Student> b4 = studentss.stream().findAny();
        System.out.println("findAny——返回当前流中的任意元素");
        System.out.println(b4);

// *      6.count——返回流中元素的总个数
        long count = studentss.stream().count();
        System.out.println("count——返回流中元素的总个数");
        System.out.println(count);

// *      7.max——返回流中最大值
        Optional<Student> max = studentss.stream().max((e1, e2) -> Double.compare(e1.getAge(), e2.getAge()));
        System.out.println("max——返回流中最大值");
        System.out.println(max);

// *      8.min——返回流中最小值
        Optional<Student> min = studentss.stream().min((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        System.out.println("min——返回流中最小值");
        System.out.println(min);

        Optional<Student> min1 = studentss.stream().min((e1, e2) -> {
            return (e1.getAge() < e2.getAge()) ? -1 : ((e1.getAge() == e2.getAge()) ? 0 : 1);
        });
/**
 *        二、归约
 *
 *         reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。
 *         返回 T
 *
 *         reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。
 *         返回 Optional<T>
 *
 *         备注：map 和 reduce 的连接通常称为 map-reduce 模式，因 Google 用它来进行网络搜索而出名。
 */
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sum = list2.stream().reduce(0, (x, y) -> x + y);
        System.out.println("reduce可以将流中元素反复结合起来，得到一个值");
        System.out.println(sum);
//        一个整数集合，然后通过reduce，从0开始，0会被当做x，然后在集合中取1当做y值，
//        然后进行x+y操作返回1，然后在把1当做x值，在到集合中取一个2当做y值，
//        以此类推，然后求和sum就是等于55。

//        在举一个栗子：
        Optional<Integer> op = studentss.stream().map(Student::getAge).reduce(Integer::sum);
        System.out.println("reduce可以将流中元素反复结合起来，得到一个值");
        System.out.println(op.get());
//      显示Age的总和。
/**
 *         三、收集
 *         collect(Collector c) 将流转换为其他形式。接收一个 Collector接口的
 *         实现，用于给Stream中元素做汇总的方法，Collector 接口中方法的实现决定了如何对流执行收集操作(如收集到 List、Set、Map)。
 *         但是 Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例。
 *         这里面的静态方法有很多。大家可以去查看一下API。。
 *
 *         toList List<T> 把流中元素收集到List
 *
 *         toSet Set<T> 把流中元素收集到Set
 *
 *         toCollection Collection<T> 把流中元素收集到创建的集合
 *
 *         counting Long 计算流中元素的个数
 *
 *         summingInt Integer 对流中元素的整数属性求和
 *
 *         averagingInt Double 计算流中元素Integer属性的平均值
 *
 *         summarizingInt IntSummaryStatistics 收集流中Integer属性的统计值。
 */
        List<String> list1 = studentss.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println("toList List<T> 把流中元素收集到List");
        list1.forEach(System.out::println);

        Set<String> set = studentss.stream().map(Student::getName).collect(Collectors.toSet());
        System.out.println("toSet Set<T> 把流中元素收集到Set");
        set.forEach(System.out::println);
//      将名字全部拿出来放到集合中，如果名字有重复的，而你又想去重，你可以放到Set集合中。

        Double qc = studentss.stream().collect(Collectors.summingDouble(Student::getAge));
        System.out.println("summingInt Integer 对流中元素的整数属性求和");
        System.out.println(qc);

        Double avg = studentss.stream().collect(Collectors.averagingDouble(Student::getAge));
        System.out.println("averagingInt Double 计算流中元素Integer属性的平均值");
        System.out.println(avg);
//      求和，平均值，这些都可以。

//      引自链接：https://www.jianshu.com/p/27e432919636
        System.out.println("-----------------------------");
        Double[] arr = new Double[]{78.0, -0.56, 1.23, 0.0, 12.2, 123.6};
        List<Double> list3 = Arrays.asList(arr);

        //输出大于0的元素
        list3.stream().filter(element -> element > 0).forEach(System.out::println);
        System.out.println("1-----------");
        //大于0的元素*2，再输出
        list3.stream().filter(element -> element > 0).map(elemnt -> elemnt * 2).forEach(System.out::println);
        System.out.println("2-----------");
        //大于0的元素，排序后， *2，取前三个，再输出
        list3.stream().filter(element -> element > 0).sorted().map(element -> element * 2).limit(3).forEach(System.out::println);
        System.out.println("3-----------");
        //元素排序，大于0的元素， *2，取前三个，再输出
        list3.stream().sorted().filter(element -> element > 0).map(element -> element * 2).limit(3).forEach(System.out::println);
        System.out.println("4-----------");
        //大于0的元素，排序后， *2，取前2个，封装成list
        List<Double> result = list3.stream().filter(element -> element > 0).sorted().map(element -> element * 2)
                .limit(2).collect(Collectors.toList());
        System.out.println(result);
        System.out.println("5-----------");

    }


    //      这是将一个字符串转化为一个字符集合，然后通过流的方式返回
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }
}
