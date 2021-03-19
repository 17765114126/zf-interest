package study.java8特性;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @ClassName LambdaTestTow
 * @Author zhaofu
 * @Date 2019/11/26
 * @Version V1.0
 **/
public class LambdaTestTow {
    public static void main(String[] args) {

        String[] players = {"Rafael Nadal--1", "Novak Djokovic--2",
                "Stanislas Wawrinka--3", "David Ferrer--4",
                "Roger Federer--5", "Andy Murray--6",
                "Tomas Berdych--7", "Juan Martin Del Potro--8",
                "Richard Gasquet--9", "John Isner--10"};

        for (String player : players) {
            System.out.println(player);
        }
        System.out.println("1:-------");
//      1.1 使用匿名内部类根据 name 算法排序 players
        Arrays.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

//      使用lambdas,可以通过下面的代码实现同样的功能:

//      1.2 使用 lambda expression 算法排序 players
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));
        Arrays.sort(players, sortByName);
        for (String player : players) {
            System.out.println(player);
        }
        System.out.println("2:-------");

//      1.3 也可以采用如下形式:
        Arrays.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world !");
            }
        }).start();

        new Thread(() -> System.out.println("Hello world !")).start();


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
        System.out.println("3:-------");
        race2.run();
        System.out.println("4:-------");

        String[] atp = {"Rafael Nadal--1", "Novak Djokovic--2",
                "Stanislas Wawrinka--3",
                "David Ferrer--4", "Roger Federer--5",
                "Andy Murray--6", "Tomas Berdych--7",
                "Juan Martin Del Potro--8"};
        List<String> players1 = Arrays.asList(atp);
// 以前的循环方式
        for (String player : players1) {
            System.out.println(player + "; ");
        }
        System.out.println("5:-------");

// 使用 lambda 表达式以及函数操作(functional operation)
        players1.forEach((player1) -> System.out.print(player1 + "; "));
        System.out.println("6:-------");

// 在 Java 8 中使用双冒号操作符(double colon operator)
        players1.forEach(System.out::println);

    }
}
