package study.反射;


import com.alibaba.druid.sql.dialect.mysql.ast.MySqlObject;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhaofu
 * @date 2020-{MONTH}-{DAY}
 */
public class 概述 {

    /**
     * Java 反射机制可以让我们在编译期(Compile Time)之外的运行期(Runtime)检查类，接口，变量以及方法的信息。
     * 反射还可以让我们在运行期实例化对象，调用方法，通过调用 get/set 方法获取变量的值。
     * <p>
     *
     * Java 反射机制功能强大而且非常实用。
     * 目前在互联网上已经有不胜枚举的 Java 反射指南，
     * 然而大多数的指南包括 Sun 公司所发布的反射指南中都仅仅只是介绍了一些反射的表面内容以及它的潜能。
     * <p>
     *
     * 在这个系列的文章中，我们会比其他指南更深入的去理解 Java 反射机制，
     * 它会阐述 Java 反射机制的基本原理包括如何去使用数组，
     * 注解，泛型以及动态代理还有类的动态加载以及类的重载的实现。
     * 同时也会向你展示如何实现一些比较有特性的功能，
     * 比如从一个类中读取所有的 get/set 方法，或者访问一个类的私有变量以及私有方法。
     * <p>
     *
     * 在这个系列的指南中同时也会说明一些非反射相关的但是令人困惑的问题，
     * 比如哪些泛型信息在运行时是有效的，一些人声称所有的泛型信息在运行期都会消失，其实这是不对的。
     *
     * 转自:https://wiki.jikexueyuan.com/project/java-reflection/
     */

    /**
     * 下面是一个 Java 反射的简单例子：
     */
    public static void main(String[] args) {
        Method[] methods = MySqlObject.class.getMethods();

        for (Method method : methods) {
            System.out.println("method = " + method.getName());
            System.out.println("Annotations = " + Arrays.toString(method.getAnnotations()));
        }
        //在这个例子中通过调用 MyObject 类的 class 属性获取对应的 Class 类的对象，
        // 通过这个 Class 类的对象获取 MyObject 类中的方法集合。
        // 迭代这个方法的集合并且打印每个方法的名字。
    }
}
