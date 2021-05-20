package study.数组集合;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName List去重的5种方式
 * @以下介绍五种-不同的方法去除 Java 中ArrayList中的重复数据
 * @Author zhaofu
 * @Date 2021/4/29
 * @Version V1.0
 **/
public class List去重 {

    /**
     * 1.使用LinkedHashSet删除arraylist中的重复数据
     *
     * LinkedHashSet是在一个ArrayList删除重复数据的最佳方法。
     *
     * LinkedHashSet在内部完成两件事：
     * 1：删除重复数据
     * 2：保持添加到其中的数据的顺序
     *
     *
     * Java示例使用LinkedHashSet删除arraylist中的重复项。
     * 在给定的示例中，numbersList是包含整数的arraylist，其中一些是重复的数字。
     *
     * 例如1,3和5.我们将列表添加到LinkedHashSet，然后将内容返回到列表中。结果arraylist没有重复的整数。
     */
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8);
        System.out.println(list);

        LinkedHashSet<Integer> hashSet = new LinkedHashSet<>(list);
        ArrayList<Integer> listWithoutDuplicates = new ArrayList<>(hashSet);
        System.out.println(listWithoutDuplicates);
    }

    /**
     * 2.使用java8新特性stream进行List去重
     *
     * 要从arraylist中删除重复项，我们也可以使用java 8
     * stream api。使用steam的distinct方法返回一个由不同数据组成的流，通过对象的equals方法进行比较。
     *
     * 收集所有区域数据List使用Collectors.toList。
     *
     * Java程序，用于在不使用Set的情况下从java中的arraylist中删除重复项。
     */
    @Test
    public void test() {
        List<Integer> list = Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8);
        System.out.println(list);

        List<Integer> listWithoutDuplicates = list.stream().distinct().collect(Collectors.toList());
        System.out.println(listWithoutDuplicates);
    }

    /**
     * 3.利用HashSet不能添加重复数据的特性 由于HashSet不能保证添加顺序，所以只能作为判断条件保证顺序：
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("1", "1", "2", "3", "3", "3", "4", "5", "6", "6", "6", "7", "8");
        HashSet<String> set = new HashSet(list.size());

        List<String> result = new ArrayList(list.size());

        for (String str : list) {
            if (set.add(str)) {
                result.add(str);
            }
        }
        System.out.println(result);
    }

    /**
     * 4.利用List的contains方法循环遍历,重新排序,只添加一次数据,避免重复：
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("1", "1", "2", "3", "3", "3", "4", "5", "6", "6", "6", "7", "8");

        List<String> result = new ArrayList(list.size());
        for (String str : list) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        System.out.println(result);
    }

    /**
     * 5. 双重for循环去重
     */

    @Test
    public void test3() {
        ArrayList list = new ArrayList(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i != j && list.get(i) == list.get(j)) {
                    list.remove(list.get(j));
                }
            }
        }
        System.out.println(list);
    }

}