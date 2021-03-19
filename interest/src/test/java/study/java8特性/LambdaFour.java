package study.java8特性;

import com.example.springboot.model.Student;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ClassName LambdaFour
 * @Author zhaofu
 * @Date 2019/11/26
 * @Version V1.0
 **/
public class LambdaFour {
    /**
     * 简介
     * (译者认为: 超过3行的逻辑就不适用Lambda表达式了。虽然看着很先进，其实Lambda表达式的本质只是一个"语法糖",由编译器推断并帮你转换包装为常规的代码,
     * 因此你可以使用更少的代码来实现同样的功能。本人建议不要乱用,因为这就和某些很高级的黑客写的代码一样,简洁,难懂,难以调试,维护人员想骂娘.)
     * Lambda表达式是Java SE 8中一个重要的新特性。lambda表达式允许你通过表达式来代替功能接口。
     * lambda表达式就和方法一样,它提供了一个正常的参数列表和一个使用这些参数的主体(body,可以是一个表达式或一个代码块)。
     * <p>
     * Lambda表达式还增强了集合库。
     * Java SE 8添加了2个对集合数据进行批量操作的包:
     * java.util.function 包以及 java.util.stream 包。
     * <p>
     * 流(stream)就如同迭代器(iterator),但附加了许多额外的功能。
     * 总的来说,lambda表达式和 stream 是自Java语言添加泛型(Generics)和注解(annotation)以来最大的变化。
     * <p>
     * 在本文中,我们将从简单到复杂的示例中见认识lambda表达式和stream的强悍。
     * 环境准备
     * 如果还没有安装Java 8,那么你应该先安装才能使用lambda和stream(译者建议在虚拟机中安装,测试使用)。
     * 像NetBeans 和IntelliJ IDEA 一类的工具和IDE就支持Java 8特性,包括lambda表达式,可重复的注解,紧凑的概要文件和其他特性。
     * <p>
     * Lambda表达式的语法
     * 基本语法:
     * (parameters) -> expression
     * 或
     * (parameters) ->{ statements; }
     */
    public static void main(String[] args) {

//  下面是Java lambda表达式的简单例子:

// 1. 不需要参数,返回值为 5
//    () -> 5;

// 2. 接收一个参数(数字类型),返回其2倍的值
//    x -> 2 * x;

// 3. 接受2个参数(数字),并返回他们的差值
//    (x, y) -> x – y

// 4. 接收2个int型整数,返回他们的和
//    (int x, int y) -> x + y

// 5. 接受一个 string 对象,并在控制台打印,不返回任何值(看起来像是返回void)
//    (String s) -> System.out.print(s)

//        基本的Lambda例子
//        现在,我们已经知道什么是lambda表达式,让我们先从一些基本的例子开始。
//        在本节中,我们将看到lambda表达式如何影响我们编码的方式。
//        假设有一个玩家List ,程序员可以使用 for 语句 ("for 循环")来遍历,在Java SE 8中可以转换为另一种形式:

        System.out.println("循环--------------");
        String[] atp = {"a-Rafael Nadal", "b-Novak Djokovic",
                "c-Stanislas Wawrinka",
                "d-David Ferrer", "e-Roger Federer",
                "f-Andy Murray", "g-Tomas Berdych",
                "h-Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }

        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);
//      正如您看到的,lambda表达式可以将我们的代码缩减到一行。

//      另一个例子是在图形用户界面程序中,匿名类可以使用lambda表达式来代替。
//      同样,在实现Runnable接口时也可以这样使用:

//      使用匿名内部类
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });

//        或者使用 lambda expression
//        btn.setOnAction(event -> System.out.println("Hello World!"));
//        下面是使用lambdas 来实现 Runnable接口 的示例:


//      1.1使用匿名内部类

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

//      1.2使用 lambda expression
        new Thread(() -> System.out.println("Hello world !")).start();

//      2.1使用匿名内部类
        Runnable race1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        };

//      2.2使用 lambda expression
        Runnable race2 = () -> System.out.println("Hello world !");

//      直接调用 run 方法(没开新线程哦!)
        race1.run();
        race2.run();

//      Runnable 的 lambda表达式,使用块格式,将五行代码转换成单行语句。 接下来,在下一节中我们将使用lambdas对集合进行排序。
//      使用Lambdas排序集合
//      在Java中,Comparator 类被用来排序集合。 在下面的例子中,我们将根据球员的 name, surname, name 长度 以及最后一个字母。
//      和前面的示例一样,先使用匿名内部类来排序,然后再使用lambda表达式精简我们的代码。
//      在第一个例子中,我们将根据name来排序list。

//      使用旧的方式,代码如下所示:

//      1.1 使用匿名内部类根据 name 算法排序 players
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

//      使用lambdas,可以通过下面的代码实现同样的功能:

//      1.2 使用 lambda expression 算法排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(atp, sortByName);

//      1.3 也可以采用如下形式:
        Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));

//      其他的排序如下所示。 和上面的示例一样,代码分别通过匿名内部类和一些lambda表达式来实现Comparator :


// 1.1 使用匿名内部类根据 surname 算法排序 players
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.substring(s1.indexOf(" ")).compareTo(s2.substring(s2.indexOf(" "))));
            }
        });

// 1.2 使用 lambda expression 算法排序,根据 surname
        Comparator<String> sortBySurname = (String s1, String s2) ->
                ( s1.substring(s1.indexOf(" ")).compareTo( s2.substring(s2.indexOf(" ")) ) );
        Arrays.sort(atp, sortBySurname);

// 1.3 或者这样,怀疑原作者是不是想错了,括号好多...
        Arrays.sort(atp, (String s1, String s2) ->
                ( s1.substring(s1.indexOf(" ")).compareTo( s2.substring(s2.indexOf(" ")) ) )
        );

// 2.1 使用匿名内部类根据 name lenght 算法排序 players
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.length() - s2.length());
            }
        });

// 2.2 使用 lambda expression 算法排序,根据 name lenght
        Comparator<String> sortByNameLenght = (String s1, String s2) -> (s1.length() - s2.length());
        Arrays.sort(atp, sortByNameLenght);

// 2.3 or this
        Arrays.sort(atp, (String s1, String s2) -> (s1.length() - s2.length()));

// 3.1 使用匿名内部类排序 players, 根据最后一个字母
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
            }
        });

// 3.2 使用 lambda expression 算法排序,根据最后一个字母
        Comparator<String> sortByLastLetter =
                (String s1, String s2) ->
                        (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1));
        Arrays.sort(atp, sortByLastLetter);

/**        3.3    or this
 *         Arrays.sort(players, (String s1, String s2) -> (s1.charAt(s1.length() - 1) - s2.charAt(s2.length() - 1)));
 *         就是这样,简洁又直观。 在下一节中我们将探索更多lambdas的能力,并将其与 stream 结合起来使用。
 *         使用Lambdas和Streams
 *         Stream是对集合的包装,通常和lambda一起使用。
 *         使用lambdas可以支持许多操作,如 map, filter, limit, sorted, count, min, max, sum, collect 等等。
 *         同样,Stream使用懒运算,他们并不会真正地读取所有数据,遇到像getFirst() 这样的方法就会结束链式语法。
 *         在接下来的例子中,我们将探索lambdas和streams 能做什么。 我们创建了一个Person类并使用这个类来添加一些数据到list中,将用于进一步流操作。
 *         Person 只是一个简单的POJO类:

*/

//        接下来,我们将创建两个list,都用来存放Person对象:
        List<Student> javaProgrammers = new ArrayList<Student>() {
            {
                add(new Student("Elsdon",43));
                add(new Student("Tamsen",23));
                add(new Student("Floyd", 33));
                add(new Student("Sindy", 32));
                add(new Student("Vere", 22));
                add(new Student("Maude",27));
                add(new Student("Shawn", 30));
                add(new Student("Jayden",35));
                add(new Student("Palmer", 33));
                add(new Student("Addison", 34));
            }
        };

        List<Student> phpProgrammers = new ArrayList<Student>() {
            {
                add(new Student("Jarrod",34));
                add(new Student("Clarette", 23));
                add(new Student("Victor",  32));
                add(new Student("Tori", 21));
                add(new Student("Osborne", 32));
                add(new Student("Rosalind", 25));
                add(new Student("Fraser",  36));
                add(new Student("Quinn", 21));
                add(new Student("Alvin",  38));
                add(new Student("Evonne", 40));
            }
        };
//        现在我们使用forEach方法来迭代输出上述列表:

        System.out.println("所有程序员的姓名:");
        javaProgrammers.forEach((p) -> System.out.printf( p.getName() ));
        phpProgrammers.forEach((p) -> System.out.printf( p.getName()));

//        我们同样使用forEach方法,增加程序员的工资5%:

        System.out.println("给程序员加薪 5% :");

        javaProgrammers.forEach(e -> e.setAge(e.getAge() / 100 * 5 + e.getAge()));
        phpProgrammers.forEach(e -> e.setAge(e.getAge() / 100 * 5 + e.getAge()));
//        另一个有用的方法是过滤器filter() ,让我们显示月薪超过1400美元的PHP程序员:

        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream()
                .filter((p) -> (p.getAge() > 30))
                .forEach((p) -> System.out.printf(p.getName()));
//        我们也可以定义过滤器,然后重用它们来执行其他操作:


//        定义 filters
        Predicate<Student> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Student> salaryFilter = (p) -> (p.getAge() > 1400);
        Predicate<Student> genderFilter = (p) -> ("female".equals(p.getName()));

//        System.out.println("下面是年龄大于 24岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf(p.getName()));


//        重用filters
        System.out.println("年龄大于 24岁的女性 Java programmers:");
        javaProgrammers.stream()
                .filter(ageFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.printf(p.getName()));
//        使用limit方法,可以限制结果集的个数:

        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p) -> System.out.printf(p.getName()));

        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.printf(p.getName()));
//        排序呢? 我们在stream中能处理吗? 答案是肯定的。 在下面的例子中,我们将根据名字和薪水排序Java程序员,放到一个list中,然后显示列表:


// 静态引入
        System.out.println("根据 name 算法排序,并显示前5个 Java programmers:");
        List<Student> sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted((p, p2) -> (p.getName().compareTo(p2.getName())))
                .limit(5)
                .collect(Collectors.toList());

        sortedJavaProgrammers.forEach((p) -> System.out.printf(p.getName()));

        System.out.println("根据 salary 算法排序 Java programmers:");
        sortedJavaProgrammers = javaProgrammers
                .stream()
                .sorted( (p, p2) -> (p.getAge() - p2.getAge()) )
                .collect( Collectors.toList() );

        sortedJavaProgrammers.forEach((p) -> System.out.printf(p.getName()));
//        如果我们只对最低和最高的薪水感兴趣,比排序后选择第一个/最后一个 更快的是min和max方法:

        System.out.println("工资最低的 Java programmer:");
        Student pers = javaProgrammers
                .stream()
                .min((p1, p2) -> (p1.getAge() - p2.getAge()))
                .get();

        System.out.printf( pers.getName(),  pers.getAge());

        System.out.println("工资最高的 Java programmer:");
        Student person = javaProgrammers
                .stream()
                .max((p, p2) -> (p.getAge() - p2.getAge()))
                .get();

        System.out.printf( person.getName(), person.getAge());
//        上面的例子中我们已经看到 collect 方法是如何工作的。
//        结合 map 方法,我们可以使用 collect 方法来将我们的结果集放到一个字符串,一个 Set 或一个TreeSet中:

        System.out.println("将 PHP programmers 的 first name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(Student::getName)
                .collect(Collectors.joining(" ; ")); // 在进一步的操作中可以作为标记(token)

        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> javaDevFirstName = javaProgrammers
                .stream()
                .map(Student::getName)
                .collect(Collectors.toSet());

        System.out.println("将 Java programmers 的 first name 存放到 TreeSet:");
        TreeSet<String> javaDevLastName = javaProgrammers
                .stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(TreeSet::new));
//        Streams 还可以是并行的(parallel)。 示例如下:

        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getAge())
                .sum();
//        我们可以使用summaryStatistics方法获得stream 中元素的各种汇总数据。
//        接下来,我们可以访问这些方法,比如getMax, getMin, getSum或getAverage:

//        计算 count, min, max, sum, and average for numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());
//        OK,就这样,希望你喜欢它!
//        总结
//        在本文中,我们学会了使用lambda表达式的不同方式,从基本的示例,到使用lambdas和streams的复杂示例。
//        此外,我们还学习了如何使用lambda表达式与Comparator 类来对Java集合进行排序
    }
}
