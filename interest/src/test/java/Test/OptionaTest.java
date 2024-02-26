package Test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.Student;
import com.example.springboot.model.User;
import com.example.springboot.model.entity.MallRegion;
import org.junit.Test;

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
         */

        Optional<Object> ofNull = Optional.ofNullable(null);
        System.out.println(ofNull.isPresent());
        System.out.println(ofNull.map(s -> "xxx" + s).orElse("hahahah"));

        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println("Full Name is set? " + fullName.isPresent());
        System.out.println("Full Name: " + fullName.orElseGet(() -> "[none]"));
        System.out.println(fullName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));

        /**
         *     如果Optional实例持有一个非空值，则isPresent()方法返回true，否则返回false；
         *     orElseGet()方法，Optional实例持有null，则可以接受一个lambda表达式生成的默认值；
         *     map()方法可以将现有的Opetional实例的值转换成新的值；
         *     orElse()方法与orElseGet()方法类似，但是在持有null的时候返回传入的默认值。
         */
        Optional<String> firstName = Optional.of("Tom");
        System.out.println("First Name is set? " + firstName.isPresent());
        System.out.println("First Name: " + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> "Hey " + s + "!").orElse("Hey Stranger!"));
    }

    @Test
    public void test() {
        String x = "1";
        String y = null;

        System.out.println(Optional.ofNullable(x).get());
        System.out.println(Optional.ofNullable(y));

        System.out.println(Optional.ofNullable(y).orElse("aszx"));


        // orElse和orElseGet的用法如下所示，
        // 都是在构造函数传入的value值为null时，进行调用的。
        // 相当于value值为null时，给予一个默认值:
        Student student = new Student();
        student.setName("zhangsan");

        System.out.println("------------");
        Student x1 = Optional.ofNullable(student).orElse(student);
        System.out.println(JSON.toJSONString(x1));

        System.out.println("------------");
        Student x2 = Optional.ofNullable(student).orElseGet(() -> student);
        System.out.println(JSON.toJSONString(x2));
    }


    /**
     * 实例
     */

    @Test
    public void test1() throws Exception {
        User user = new User();
        System.out.println(getCity1(user));
        System.out.println(getCity(user));
    }

    //以前
    public String getCity(User user) throws Exception {
        if (user != null) {
            if (user.getAddress() != null) {
                MallRegion address = user.getAddress();
                if (address.getName() != null) {
                    return address.getName();
                }
            }
        }
        throw new Exception("取值错误");
    }

    //java8
    public String getCity1(User user) throws Exception {
        return Optional.ofNullable(user)
                .map(u -> u.getAddress())
                .map(a -> a.getName())
                .orElseThrow(() -> new Exception("取指错误"));
    }

    @Test
    public void test2() throws Exception {
        User user = null;
        //以前
        if (user != null) {
            getCity(user);
        }
        //java8
        Optional.ofNullable(user)
                .ifPresent(u -> {
                    try {
                        getCity(u);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    @Test
    public void test3() {
        User user = null;
        System.out.println(getUser(user));
        System.out.println(getUser1(user));
    }

    //以前
    public User getUser(User user) {
        if (user != null) {
            String name = user.getUserName();
            if ("zhangsan".equals(name)) {
                return user;
            }
        } else {
            user = new User();
            user.setUserName("zhangsan");
            return user;
        }
        return user;
    }

    //java8
    public User getUser1(User user) {
        return Optional.ofNullable(user)
                .filter(u -> "zhangsan".equals(u.getUserName()))
                .orElseGet(() -> {
                    User user1 = new User();
                    user1.setUserName("zhangsan");
                    return user1;
                });
    }

}
