package study.JUC;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 什么是Stream流式计算：数据：存储 + 计算
 *
 * 集合、MySQL 本质就是存储东西的；
 *
 * 计算都应该交给流来操作！
 *
 * 题目要求：一分钟内完成此题，只能用一行代码实现！
 * 现在有5个用户！筛选：
 * 1、ID 必须是偶数
 * 2、年龄必须大于23岁
 * 3、用户名转为大写字母
 * 4、用户名字母倒着排序
 * 5、只输出一个用户！
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        // 集合就是存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);


        Stream<String> stringStream = list.stream()
                .map(u -> {
                    return u.getName().toUpperCase();
                });


        List<Stream<String>> streams = Arrays.asList(stringStream);
        // 计算交给Stream流
        // lambda表达式、链式编程、函数式接口、Stream流式计算
        list.stream()
                .filter(u -> {
                    return u.getId() % 2 == 0;
                })
                .filter(u -> {
                    return u.getAge() > 23;
                })
                .map(u -> {
                    return u.getName().toUpperCase();
                })
                .sorted((uu1, uu2) -> {
                    return uu2.compareTo(uu1);
                })
                .limit(1)
                .forEach(System.out::println);
    }
}


// 有参，无参构造，get、set、toString方法！
@Data
@NoArgsConstructor
@AllArgsConstructor
class User {
    private int id;
    private String name;
    private int age;
}
