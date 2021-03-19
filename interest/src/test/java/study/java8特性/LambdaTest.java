package study.java8特性;

import com.example.springboot.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
//import sun.plugin2.util.ParameterNames;

import java.lang.annotation.*;
//import java.lang.reflect.Array;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Supplier;


@RunWith(SpringRunner.class)
public class LambdaTest {
    //重复注解
    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class)
    public @interface Filter {
        String value();
    }

    ;

    @Filter("filter1")
    @Filter("filter2")
    public interface Filterable {
    }

    //方法引用
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable create(Supplier<Defaulable> supplier) {
            return supplier.get();
        }
    }

    @Test
    @SuppressWarnings("unused")
    public static void main(String[] args) {


//        Optional<String> fullName = Optional.ofNullable(null);
//        System.out.println("Full Name is set? " + fullName.isPresent());
//        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
//        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
//        如果Optional类的实例为非空值的话，isPresent()返回true，否从返回false。为了防止Optional为空值，orElseGet()方法通过回调函数来产生一个默认值。
//        map()函数对当前Optional的值进行转化，然后返回一个新的Optional实例。orElse()方法和orElseGet()方法类似，但是orElse接受一个默认值而不是一个回调函数。
//        System.out.println("----------------");
//        Optional<String> firstName = Optional.of("Tom");
//        System.out.println("First Name is set? " + firstName.isPresent());
//        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
//        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
//        System.out.println();


        //重复注解
        for (Filter filter : Filterable.class.getAnnotationsByType(Filter.class)) {
//            System.out.println(filter.value());
        }
        //方法引用
        Defaulable defaulable = DefaulableFactory.create(DefaultableImpl::new);
//        System.out.println(defaulable.notRequired());

        defaulable = DefaulableFactory.create(OverridableImpl::new);
//        System.out.println(defaulable.notRequired());

        String num = "0";
        Arrays.asList("a", "s", "d").forEach(e -> {
        });
        Student student1 = new Student();
        List<Student> student = new ArrayList<>();
        student.add(student1);
        student.add(student1);
        student.forEach(stu -> {
//            System.out.println(stu.getName()+num);
        });
        Arrays.asList("a", "b", "d").sort((e1, e2) -> e1.compareTo(e2));
        Arrays.asList("a", "b", "c", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
//            System.out.println(e1);
//            System.out.println(e2);
//            System.out.print(result);
//            System.out.println("--------------");
            return result;
        });
        //Java编译器的新特性
        //1 参数名字
//        Method method = ParameterNames.class.getMethod( "main", String[].class );
//          for( final Parameter parameter: method.getParameters() ) {
//              System.out.println( "Parameter: " + parameter.getName() );
//          }

        //扩展注解的支持
        final Holder<String> holder = new @NonEmpty Holder<String>();
        @NonEmpty Collection<@NonEmpty String> strings = new ArrayList<>();
//        System.out.println(strings.size());

    }

    //扩展注解的支持
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    //接口的默认方法与静态方法
    @Test
    public static LambdaTest create(final Supplier<LambdaTest> supplier) {
        return supplier.get();
    }

    @Test
    public static void collide(final LambdaTest car) {
        System.out.println("Collided " + car.toString());
    }

    @Test
    public void follow(final LambdaTest another) {
        System.out.println("Following the " + another.toString());
    }

    @Test
    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    @Test
    public void x() {
        // * 第一种方法引用是构造器引用，它的语法是Class::new，或者更一般的Class< T >::new。请注意构造器没有参数。
        final LambdaTest car = LambdaTest.create(LambdaTest::new);
        final List<LambdaTest> cars = Arrays.asList(car);
        // * 第二种方法引用是静态方法引用，它的语法是Class::static_method。请注意这个方法接受一个Car类型的参数。
        cars.forEach(LambdaTest::collide);
        // * 第三种方法引用是特定类的任意对象的方法引用，它的语法是Class::method。请注意，这个方法没有参数。
        cars.forEach(LambdaTest::repair);
        // * 最后，第四种方法引用是特定对象的方法引用，它的语法是instance::method。请注意，这个方法接受一个Car类型的参数
        final LambdaTest police = LambdaTest.create(LambdaTest::new);
        cars.forEach(police::follow);
    }

    @Test
    public void s() {
        System.out.println("Repaired ");
    }

}
