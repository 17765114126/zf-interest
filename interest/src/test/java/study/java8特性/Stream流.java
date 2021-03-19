package study.java8特性;

import com.example.springboot.model.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * @ClassName Stream流
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class Stream流 {
    /**
     * C.Stream流
     *
     * 1.思考
     *
     * 计算字符串List中长度大于2的元素的数量
     * 分析：使用增强for循环
     *
     * a.代码冗余
     * b.实现并行计算很麻烦
     * c.代码无法及时传递程序员的意图 ，必须读完代码

     * 2.外部迭代
     *  forEach工作原理：代码底层使用Iterator进行迭代的，是串行化操作

     * 3.内部迭代

     * 4.Stream概述
     *
     * 是用函数式编程方式在集合类上进行复杂操作的工具，更像一个高级版本的 Iterator
     * 原始版本的 Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作
     * 高级版本的 Stream，用户只要给出需要对其包含的元素执行什么操作
     * Stream 会隐式地在内部进行遍历，做出相应的数据转换
     * 而和迭代器又不同的是，Stream 可以并行化操作
     * 借助于 Lambda 表达式，极大的提高编程效率和程序可读性

     * 5.常用操作
     *
     * a.forEach
     *
     * 迭代集合中元素。接收一个 Lambda 表达式
     * 然后在 Stream 的每一个元素上执行该表达式
     * 此操作是及早求值方法

     * b.collect(toList()) 
     * 由Stream 里的值生成一个列表，是一个及早求值操作
     * 很多Stream 操作都是惰性求值，因此调用Stream 一系列方法之后
     * 还需最后再调用一个类似collect 的及早求值方法返回集合
     *
     * 开篇的例子：(再将符合要求的字符串放到一个新的集合里)
     * 使用filter过滤器：遍历数据并检查其中的元素
     *
     * */
    public static void main(String[] args) {
        // 创建集合
        List<String> list = new ArrayList<>();
        // 添加元素
        list.add("1sdf");
        list.add("a");
        list.add("asdf");
        list.add("d");
        list.add("basdfgh");
        System.out.println("filter-----------------");
        // 统计长度大于2的
        long count = list.stream().filter((s) -> s.length() > 2).count();
        System.out.println(count);

        Stream<String> stringStream1 = list.stream().filter(s -> s.length() > 2);
        System.out.println(Arrays.toString(stringStream1.toArray()));

        // 将符合要求的放入集合
        List<String> list2 = list.stream().filter((s) -> s.length() > 2).collect(Collectors.toList());
        System.out.println(list2);

        Stream<String> stringStream = list2.stream().filter((s) -> s.length() > 2);
        List<String> list3 = stringStream .collect(Collectors.toList());
        list3.forEach(System.out :: println);

    /**
     * 6.map
     *
     * 如果有一个函数可以将一种类型的值转换成另外一种类型
     *
     * map 操作就可以使用该函数，将一个流中的值转换成一个新的流
     *
     * 需求：将字符串全转换成大写
     *
     * */
        System.out.println("map-----------------");
        // 转换为大写
        List<String> list5 = list.stream().map((s) -> s.toUpperCase()).collect(Collectors.toList());
        list5.forEach(System.out :: println);

    /**
     * 7.filter
     *
     * 遍历数据并检查其中的元素。例如获取字符串List中以数字开始的字符集合
     * */
        System.out.println("filter-----------------");
    // 获取数字开头的
    List<String> list7 = list.stream().filter((s) -> Character.isDigit(s.charAt(0))).collect(Collectors.toList());
    list7.forEach(System.out::println);

    /**
     * 8.flatMap 
     *
     * 可用Stream 替换值， 然后将多个Stream 连接成一个Stream
     *
     * map 操作可用一个新的值代替Stream 中的值
     *
     * 若用户希望让map操作有点变化
     *
     * 生成一个新的Stream 对象取而代之则flatMap适用
     *
     * 假设有一个包含多个列表的流，现在希望得到所有数字的序列
     *
     * */
        System.out.println("flatMap-----------------大雾。。。。。");
        Stream<?> flatMap = Stream.of(Arrays.asList("a", "b"), Arrays.asList(1, 2, 3)).flatMap((s) -> s.stream());
        flatMap.forEach(System.out :: println);
    /**
     *
     * 9.max和min
     *
     * 获取Stream中最大值或最小值，获取字符串List中长度最长的字符串长度
     *
     * */
        List<String> list8 = new ArrayList<>();

        list8.add("abc");
        list8.add("ab");
        list8.add("abcd");
        list.add("abcde");

        System.out.println("max和min-----------------");
        // 获取最大值
        int max = list8.stream().map((s) -> s.length()).max(Integer::compareTo).get();
        System.out.println(max);

        // 获取最小值，另一种方法
        int min = list8.stream().min(Comparator.comparing((s) -> s.length())).get().length();
        System.out.println(min);

    /**
     * 10.reduce
     *
     * 通过指定的函数把stream中的多个元素汇聚为一个元素，min max等是它的特例
     *
     * 格式：reduce(初始值，(r, i) -> r + i) 或者 reduce((r, i) -> r + "*" i)
     *
     * 计算1~100的和
     *
     * */
        System.out.println("reduce-----------");
        List<Long> list9 = new ArrayList<>();
        // 封装到集合
        for (long i = 1; i <= 100; i++) {
            list9.add(i);
        }

        // 计算
        // reduce：参1，和的初始值
        Long sum = list9.stream().parallel().reduce(0L, (r, l) -> r + l);
        System.out.println(sum);

    /**
     * 11.练习
     *
     * a.获取Student集合中年龄小于20岁的集合中年龄最大的学生信息
     *
     * */
        System.out.println("获取集合中年龄小于20岁的集合中年龄最大的信息---------------");
        List<Student> list10 = new ArrayList<>();

        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(21);
        Student s2 = new Student();
        s2.setName("李四");
        s2.setAge(19);
        Student s3 = new Student();
        s3.setName("王五");
        s3.setAge(18);
        Student s4 = new Student();
        s4.setName("程六");
        s4.setAge(17);
        Student s5 = new Student();
        s5.setName("赵七");
        s5.setAge(20);

        list10.add(s1);
        list10.add(s2);
        list10.add(s3);
        list10.add(s4);
        list10.add(s5);

        // 筛选
        Student student = list10.stream().filter((s) -> s.getAge() < 20).max(Comparator.comparing((s) -> s.getAge())).get();
        System.out.println(student);


    /**
     * b.查找集合中以a开头的字符的长度集合
     * */
        System.out.println("查找集合中以a开头的字符的长度集合----------");
        List<Integer> list11 = Stream.of("abc", "b", "a", "abcd").filter((s) -> s.startsWith("a")).map((s) -> s.length())
                .collect(Collectors.toList());
        System.out.println(list11);
    }

}
