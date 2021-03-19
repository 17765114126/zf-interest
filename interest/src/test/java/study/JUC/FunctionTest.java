package study.JUC;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @ClassName FunctionTest
 * @Author zhaofu
 * @Date 2021/1/26
 * @Version V1.0
 * <p>
 * lambda表达式、链式编程、函数式接口、Stream流式计算
 * <p>
 * 函数式接口： 只有一个方法的接口
 * <p>
 * 泛型、枚举、反射
 * 超级多FunctionalInterface
 * 简化编程模型，在新版本的框架底层大量应用！
 * foreach(消费者类的函数式接口)
 **/
public class FunctionTest {

    @FunctionalInterface
    public interface Runnable {
        public abstract void run();
    }


    /**
     * Function 函数型接口, 有一个输入参数，有一个输出
     * 只要是 函数型接口 可以 用 lambda表达式简化
     */
    @Test
    public void Test() {
//        Function<String,String> function = new Function<String,String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };
        Function<String, String> function = str -> {
            return str;
        };
        System.out.println(function.apply("asd"));
    }

    /**
     * 断定型接口：有一个输入参数，返回值只能是 布尔值！
     */
    @Test
    public void Test1() {
        // 判断字符串是否为空
//        Predicate<String> predicate = new Predicate<String>(){
////            @Override
////            public boolean test(String str) {
////                return str.isEmpty();
////            }
////        };
        Predicate<String> predicate = (str) -> {
            return str.isEmpty();
        };
        System.out.println(predicate.test(""));
    }

    /**
     * Consumer 消费型接口: 只有输入，没有返回值
     */
    @Test
    public void Test2() {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        };
        Consumer<String> consumer = (str) -> {
            System.out.println(str);
        };
        consumer.accept("sdadasd");
    }

    /**
     * Supplier 供给型接口 没有参数，只有返回值
     */
    @Test
    public void Test3() {
//        Supplier supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                System.out.println("get()");
//                return 1024;
//            }
//        };
        Supplier supplier = () -> {
            return 1024;
        };
        System.out.println(supplier.get());
    }

}
