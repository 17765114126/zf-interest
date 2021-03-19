package study.java8特性;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @ClassName 方法引用
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class 方法引用 {
    /**
     *
     * 1.概述
     *
     * 某些lambda表达式里面仅仅是调用了一个已存在的方法，在这种情况下
     *
     * 直接通过方法名称引用方法的形式可读性更高一些，这种形式就是方法引用
     *
     * 方法引用是一种更简洁易懂的lambda 表达式替换
     *
     * 其唯一用途就是支持Lambda的简写
     *
     * 函数接口中抽象方法的参数列表，必须与方法引用方法的参数列表保持一致
     *
     * 方法引用中::后只是方法名，不能加();
     *
     * forEach()也是jdk8的新特性
     *
     * 比如：list.forEach((s) -> System.out.println(s));---list.forEach(Syetem.out::println);
     *
     * */
    public static void main(String[] args) {
        System.out.println("Test()------");
        Test();
        System.out.println("Test1()------");
        Test1();
        System.out.println("Test2()------");
        Test2();
    }

    /**
     * 2.类静态方法引用
     *
     * 求绝对值，使用Function实现
     * */
    public static void Test() {
        // 调用
        long l1 = testAbs(-10L, (s) -> Math.abs(s));
        // 改进
        long l2 = testAbs(-10L, Math::abs);
        System.out.println(l1);
        System.out.println(l2);
    }
    public static long testAbs(long s, Function<Long, Long> fun) {
        Long l = fun.apply(s);
        return l;
    }

    /**
     *3.实例方法引用
     *
     * 循环集合中元素，使用forEach方法
     *
     * (s) -> System.out.println(s)的类型是Consumer类型
     *
     * 其accept接受参数和println一致
     * */

    public static void Test1() {
        // 创建集合
        List<String> list = new ArrayList<>();
        // 添加元素
        list.add("e");
        list.add("c");
        list.add("a");
        list.add("d");
        list.add("b");
        // 算法排序
        list.sort((s1, s2) -> s1.compareTo(s2));
        // 遍历
        list.forEach((s) -> System.out.println(s));
        list.forEach(System.out::println);
    }
/**
 * 4.构造方法引用
 *
 * 在引用构造器的时候，构造器参数列表要与接口中抽象方法的参数列表一致
 *
 * 格式为 类名::new
 *
 * */
    public static void Test2() {
        getString(String :: new);
    }

    public static void getString(Supplier<String> su) {
        String s = su.get();
        System.out.println(s == null);
        System.out.println(s);
    }

/**
 *
 * 5.任意对象的实例方法
 *
 * 若Lambda表达式的参数列表的第一个参数，是实例方法的调用者
 *
 * 第二个参数(或无参)是实例方法的参数时，就可以使用这种方法
 *
 * 参考第3条中的排序
 *
 * */
}
