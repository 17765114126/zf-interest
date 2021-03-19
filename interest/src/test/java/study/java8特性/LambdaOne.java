package study.java8特性;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
/**
 * 2.什么是Lambda表达式
 * 带有参数变量的表达式，是一段可以传递的代码，可以被一次或多次执行
 * 是一种精简的字面写法，其实就是把匿名内部类中“一定”要做的工作省略掉
 * 然后由JVM通过推导把简化的表达式还原
 * 格式： 
 * (parameters参数) -> expression表达式或方法体
 * paramaters：
 * 类似方法中的形参列表，这里的参数是函数式接口里的参数
 * ->：可理解为“被用于”的意思
 * 方法体：
 * 可以是表达式也可以代码块，是函数式接口里方法的实现
 * 如果负责运算的代码无法用表达式表示，可以使用编写方法实现
 * 但必须用{}包围并按需明确使用 return语句
 * 需求：对字符串数组按字符串长度排序
 */

/**
 * @ClassName LambdaOne
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class LambdaOne {

    public static void main(String[] args) {
        // 定义字符串数组
        String[] strArr = {"abc", "cd", "abce", "a"};
        // 传统方法
        // 匿名内部类
        Arrays.sort(strArr, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Integer.compare(s2.length(), s1.length());
            }
        });
        // 输出排序结果
        for (String s : strArr) {
            System.out.println(s);
        }

        System.out.println("-----------------------------");
        // Lambda表达式
        Arrays.sort(strArr, (s1, s2) -> Integer.compare(s2.length(), s1.length()));
        // 输出
        for (String s : strArr) {
            System.out.println(s);
        }
        System.out.println("----------------------------");

        /**
         * 需求：用Lambda实现多线程
         * */

        // Lambda表达式
        new Thread(() -> System.out.println(-1 + "hello world")).start();
        System.out.println("----------------");

        // 方法体
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(i + "hello world");
            }
        }).start();
        System.out.println("Test();--------");
        Test();
        System.out.println("Test1();--------");
        Test1();
        System.out.println("Test2();--------");
        Test2();
        System.out.println("Test3();--------");
        Test3();

    }
/**
 * 3.何时使用
 *
 * 通过上面的两个需求，发现Lamdba表达式很简单，那何时使用呢
 *
 * 需要显示创建函数式接口对象的地方，都可以使用
 *
 * 实际上函数式接口的转换是Lambda表达式唯一能做的事情
 *
 * 即lambda必须和Functional Interface配套使用
 *
 * 主要用于替换以前广泛使用的内部匿名类，各种回调
 *
 * 比如事件响应器、传入Thread类的Runnable等
 *
 * */

/**
 *
 * 4.函数式接口分类
 * 图
 * a.系统与定义函数接口(Comparator, Runnable)
 *
 * b.用户自定义函数接口(注解必须有，表达式是直接通过参数列表来实现的，只能有一个有效方法)
 *
 * */
    @FunctionalInterface
    public interface MyInterface {
        String info(String tip);
    }
/**
 *
 * 5.公共定义的函数式接口
 *
 * 从jdk1.8开始为了方便用户开发专门提供了一个新的包：java.utils.function
 *
 * 在这个包里面针对于用户有可能做的函数式接口做了一个公共定义
 *
 * 最为核心的有四个接口：
 *
 * a.功能性接口：Function<T, R>
 *
 * 有输入参数，有返回值
 *
 * 是对接收一个T类型参数，返回R类型的结果的方法的抽象
 *
 * 通过调用apply方法执行内容
 *
 * 需求：给定一个字符串，返回字符串长度
 *
 * */

public static void Test() {
    // 定义字符串
    String str = "hello--world";

    // 调用方法
    // 在调用的时候写方法体，方法比较灵活
    int length = testFun(str, (s) -> s.length());

    System.out.println(length);
}

    /**
     * @param str 输入参数
     * @param fun 表达式 String 为输入类型，Integer为输出类型
     * @return 返回字符串长度
     */
    public static int testFun(String str, Function<String, Integer> fun) {
        // 执行
        Integer length = fun.apply(str);

        return length;
    }

/**
 *
 * b.消费型接口：Consumer<T>
 *
 * 有输入参数，没返回值
 *
 * 对应的方法类型为接收一个参数，没有返回值
 *
 * 一般来说使用Consumer接口往往伴随着一些期望状态的改变
 *
 * 或者事件的发生，典型的forEach就是使用的Consumer接口
 *
 * 虽然没有任何的返回值，但是向控制台输出结果
 *
 * Consumer 使用accept对参数执行行为
 *
 * 需求：输出字符串
 *
 *
 */


public static void Test1() {
    // 创建字符串
    String str = "hello world";

    // 调用
    testCon(str, (s) -> System.out.println(s));
}

    /**
     * @param str 传入参数
     * @param con
     */
    public static void testCon(String str, Consumer<String> con) {
        // 执行
        con.accept(str);
    }

/**
 *
 * c.供给型接口：Supplier<T>
 *
 * 无传入参数，有返回值
 *
 * 该接口对应的方法类型不接受参数，但是提供一个返回值
 *
 * 使用get()方法获得这个返回值
 *
 * */
public static void Test2() {
    // 创建字符串
    String str = "hello world";

    // 调用
    String sup = testSup(() -> str);

    System.out.println(sup);
}

    /**
     * @param sup
     * @return
     */
    public static String testSup(Supplier<String> sup) {
        // 执行
        String s = sup.get() + "  xxx";
        return s;
    }

/**
 * d.断言型接口：Predicate<T>
 *
 * 有传入参数，有返回值Boolean
 *
 * 该接口对应的方法为接收一个参数，返回一个Boolean类型值
 *
 * 多用于判断与过滤，使用test()方法执行这段行为
 *
 * 需求：输入字符串，判断长度是否大于0
 */

public static void Test3() {
    // 创建字符串
    String str = "hello world";

    // 调用
    boolean flag = testPre(str, (s) -> s.length() > 0);
    System.out.println(flag);
}

    /**
     * @param str
     * @param pre
     * @return
     */
    public static boolean testPre(String str, Predicate<String> pre) {
        // 执行
        boolean flag = pre.test(str);

        return flag;
    }

/**
 *
 * 6.Lambda的优点
 *
 * a.极大的减少代码冗余，同时可读性也好过冗长的匿名内部类
 *
 * b.与集合类批处理操作结合，实现内部迭代，并充分利用现代多核CPU进行并行计算。之前集合类的迭代都是外部的，
 * 即客户代码。而内部迭代意味着由Java类库来进行迭代，而不是客户代码

 * 7.和匿名内部类的区别
 *
 * a.在lambda中，this不是指向lambda表达式产生的那个对象，而是它的外部对象
 *
 * b.Java 编译器编译 Lambda 表达式并将他们转化为类里面的私有函数，它使用 Java 7 中新加的 invokedynamic 指令动态绑定该方法，
 * 但每一个匿名内部类编译器会为其创建一个类文件
 *
 * */
}