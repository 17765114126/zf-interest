package study.java8特性;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * @ClassName LambdaFive
 * @Author zhaofu
 * @Date 2019/12/13
 * @Version V1.0
 **/
public class LambdaFive {
    /**
     * Lambda表达式学习（二）Lambda基础语法
     *     Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符，箭头操作符将 Lambda 表达式拆分成两部分：
     *     左侧：Lambda 表达式的参数列表；
     *     右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体；
     *     对应的每种语法，这里都会给出一个栗子，方便大家的理解。
    */
    //语法格式一：无参数，无返回值
    //() -> System.out.println("Hello Lambda!");

    public void test1(){
        int num = 0;//jdk 1.7 前，必须是 final
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!" + num);
            }
        };
        r.run();
        System.out.println("-------------------------------");
        Runnable r1 = () -> System.out.println("Hello Lambda!");
        r1.run();
    }


    //语法格式二：有一个参数，并且无返回值
    //(x) -> System.out.println(x)
    public void test2(){
//        Consumer<String> con = (x) -> System.out.println(x);
//        con.accept("有一个参数，并且无返回值");
    }


    //语法格式三：若只有一个参数，小括号可以省略不写
    //x -> System.out.println(x)
    public void test3(){
//        Consumer<String> con = x -> System.out.println(x);
//        con.accept("若只有一个参数，小括号可以省略不写");
    }


    //语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
    //Comparator<Integer> com = (x, y) -> {
    //System.out.println("函数式接口");
    //return Integer.compare(x, y);
    //};
    public void test4(){
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    //语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
    //Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    public void test5(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

    //语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
    //(Integer x, Integer y) -> Integer.compare(x, y);
    //(x,y) -> Integer.compare(x,y)

    //ok，讲到这里语法就差不多了在这里了，如果掌握了这些我想应该能解决平时正常的需求，
    // 这里有一副对联：
    //上联：左右遇一括号省
    //下联：左侧推断类型省
    //横批：能省则省

    //这里讲一个大家需要注意的地方：
    //Lambda 表达式需要“函数式接口”的支持
    //函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
    //可以使用注解@FunctionalInterface 修饰，可以检查是否是函数式接口。
    //函数式接口我将在下一篇文章进行讲述，这也是一个蛮重要的知识点。

/**
 * Lambda表达式学习（三）Lambda 表达式需要“函数式接口”的支持
 *
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，接下来我将讲一下Java8中内置的核心的四大函数式接口。
 * */

//接口一：
//Consumer<T> : 消费型接口
//void accept(T t);

//举个栗子，我们去外面吃饭的时候就会进行一个消费
//public void eat(double money, Consumer<Double> con){
//    con.accept(money);
//}

public void consumer(){
//  eat(10000, (m) -> System.out.println("吃饭消费了：" + m + "元"));
}

//接口二：
//Supplier<T> : 供给型接口
//T get();

//供给型就是外部给我们返回一个东西，看代码我相信你会更加的理解：

//需求：产生指定个数的整数，并放入集合中
public List<Integer> getNumList(int num, Supplier<Integer> sup){
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < num; i++) {
        Integer n = sup.get();
        list.add(n);
    }
    return list;
}

public void supplier(){
    List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
    for (Integer num : numList) {
        System.out.println(num);
    }
}

//接口三：
//Function<T, R> : 函数型接口
//R apply(T t);

////需求：用于处理字符串
public String strHandler(String str, Function<String, String> fun){
        return fun.apply(str);
}

public void function(){
    String newStr = strHandler("\t\t\t Function   ", (str) -> str.trim());
    System.out.println(newStr);
    String subStr = strHandler("Function", (str) -> str.substring(2, 5));
    System.out.println(subStr);
}

//接口四：

//Predicate<T> : 断言型接口
//boolean test(T t);

public void predicate(){
    List<String> list = Arrays.asList("Hello", "predicate", "Lambda", "www", "ok");
    List<String> strList = filterStr(list, (s) -> s.length() > 3);
    for (String str : strList) {
        System.out.println(str);
    }
}

//需求：将满足条件的字符串，放入集合中
public List<String> filterStr(List<String> list, Predicate<String> pre){
    List<String> strList = new ArrayList<>();
    for (String str : list) {
        if(pre.test(str)){
            strList.add(str);
        }
    }
    return strList;
}

//当然Java8还内置了一些其他的接口，但是都是上面的接口的之类，如果上面的用法你掌握了，其他的子接口的用法是一样的。




    /**
     * Lambda表达式学习（四）Lambda 表达式方法引用、构造器引用和数组引用
     *     Lambda
     *     方法引用
     *     构造器引用
     *     数组引用
     *     接下来我就围绕这几个关键字进行讲解。
     */

    //一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用（可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
    //引用的方式有一下几种：

    //对象的引用 :: 实例方法名
    //类名 :: 静态方法名
    //类名 :: 实例方法名
    //方法引用的第一种引用形式

    ////对象的引用 :: 实例方法名

    public void test6(){
            // 之前我们是这样写的
//        Employee emp = new Employee(101, "张三", 18, 9999);
//        Supplier<String> sup = () -> emp.getName();
//        System.out.println(sup.get());
//
//        System.out.println("----------------------------------");
//            // 现在我们是这样写的
//        Supplier<String> sup2 = emp::getName;
//        System.out.println(sup2.get());
    }

    //方法引用的第二种引用形式

    ////类名 :: 静态方法名

    public void test7(){
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
        System.out.println("-------------------------------------");
        Comparator<Integer> com2 = Integer::compare;
    }


    public void test8(){
        BiFunction<Double, Double, Double> fun = (x, y) -> Math.max(x, y);
        System.out.println(fun.apply(1.5, 22.2));

        System.out.println("--------------------------------------------------");

        BiFunction<Double, Double, Double> fun2 = Math::max;
        System.out.println(fun2.apply(1.2, 1.5));
    }

    //compare(),方法是Integer包装类中的一个静态方法，在test3()中BiFunction接口是Function的一个子接口，
    // BiFunction<Double, Double, Double>前面两个是传入的参数的类型，第三个参数是方法需要返回的类型。

    //方法引用的第三种方式

    ////类名 :: 实例方法名


    public void test9(){
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        System.out.println(bp.test("abcde", "abcde"));

        System.out.println("-----------------------------------------");

        BiPredicate<String, String> bp2 = String::equals;
        System.out.println(bp2.test("abc", "abc"));

        }
    //ok，以上就是方法引用的讲解，在这里给大家讲两个需要注意的地方:

    //①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
    //②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName

    //看这个就能明白上面说的那两句话：

    //BiPredicate<String, String> bp = (x, y) -> x.equals(y);
    //BiPredicate<String, String> bp2 = String::equals;
    //System.out.println(bp2.test("abc", "abc"));
    //函数式接口中抽象方法 boolean test(T t, U u);参数列表是t，u，在上面的代码中是x，y，返回的类型是是boolean类型，x是实例方法的调用者，x调用了equals方法，而y是实例方法的参数。


    //二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！

    //类名 :: new
    // //构造器引用

    public void test10(){
//        Function<String, Employee> fun = Employee::new;
        //两个参数
//        BiFunction<String, Integer, Employee> fun2 = Employee::new;
    }


    public void test11(){
            //无参数
//        Supplier<Employee> sup = () -> new Employee();
//        System.out.println(sup.get());

        System.out.println("------------------------------------");

//        Supplier<Employee> sup2 = Employee::new;
//        System.out.println(sup2.get());
    }
    //三、数组引用
    //类型[] :: new
    //数组引用
        public void test12(){
            Function<Integer, String[]> fun = (args) -> new String[args];
            String[] strs = fun.apply(10);
            System.out.println(strs.length);

            System.out.println("--------------------------");

//            Function<Integer, Employee[]> fun2 = Employee[] :: new;
//            Employee[] emps = fun2.apply(20);
//            System.out.println(emps.length);
        }








}
