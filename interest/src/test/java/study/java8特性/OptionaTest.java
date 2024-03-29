package study.java8特性;

import java.util.Optional;

/**
 * @ClassName OptionaTest
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class OptionaTest {
    public static void main(String[] args) {
        /**
         * 4.1 Optional
         *     Java应用中最常见的bug就是空值异常。在Java 8之前，
         *     Google Guava引入了Optionals类来解决NullPointerException，从而避免源码被各种null检查污染，以便开发者写出更加整洁的代码。
         *     Java 8也将Optional加入了官方库。
         *     Optional仅仅是一个容易：存放T类型的值或者null。
         *     它提供了一些有用的接口来避免显式的null检查，可以参考Java 8官方文档了解更多细节。
         *     接下来看一点使用Optional的例子：可能为空的值或者某个类型的值：
         *
         */

            Optional<Object> ofNull = Optional.ofNullable(null);
            System.out.println(ofNull.isPresent());
            System.out.println(ofNull.map(s->"xxx"+s).orElse("hahahah"));

            Optional<String> fullName = Optional.ofNullable(null);
            System.out.println("Full Name is set? " + fullName.isPresent());
//            System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
            System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

        /**
         *     如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false；
         *     orElseGet()方法，Optional实例持有null，则可以接受一个lambda表达式生成的默认值；
         *     map()方法可以将现有的Opetional实例的值转换成新的值；
         *     orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值。
         */
            Optional<String> firstName = Optional.of("Tom");
            System.out.println("First Name is set? " + firstName.isPresent());
            //System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
            System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }
}
