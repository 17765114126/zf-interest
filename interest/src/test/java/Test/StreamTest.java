package Test;

import com.alibaba.fastjson.JSON;
import com.example.application.model.User;
import com.example.application.utils.date.DateUtil;
import org.junit.Test;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.*;
import java.util.stream.Collectors;

public class StreamTest {

    @Test
    public void Test1() {
        //Supplier  没有输入 只有输出
        Supplier<String> supplier = () -> "Hello Supplier";
        System.out.println(supplier.get());

        //Consumer  只有一个输入 没有输出
        Consumer<String> consumer = x -> System.out.println("Hello " + x);
        consumer.accept("Consumer");

        //Function  输入T 输出R
        Function<Integer, Integer> function = i -> i * i;
        System.out.println("Function demo:" + function.apply(9));

        //UnaryOperator 输入输出都是T
        UnaryOperator<Integer> unaryOperator = i -> i * i;
        System.out.println("UnaryOperator demo:" + unaryOperator.apply(9));

        //BiFunction 输入T,U 输出R
        BiFunction<Integer, Integer, String> biFunction = (i, j) -> i + "*" + j + "=" + i * j;
        System.out.println("BiFunction demo:" + biFunction.apply(8, 9));
    }

    @Test
    public void Test2() {
        User user = new User();
        user.setId(1L);
        user.setTest5("张1");
        user.setDate(DateUtil.getDateNoTime("2021-09-18 11:15:35"));
        User user1 = new User();
        user1.setTest5("阿2");
        user1.setId(4L);
        user1.setDate(DateUtil.getDate());
        User user2 = new User();
        user2.setTest5("阿3");
        user2.setId(3L);
        user2.setDate(DateUtil.getDateNoTime("2021-09-22 15:56:41"));
        User user3 = new User();
        user3.setTest5("丁4");
        user3.setId(4L);
        user3.setDate(DateUtil.getDateNoTime("2021-09-22 15:57:41"));

        List<User> arrayList = new ArrayList();
        arrayList.add(user);
        arrayList.add(user1);
        arrayList.add(user2);
        arrayList.add(user3);

        // 首字母排序
        arrayList.sort((o1, o2) -> {
            Comparator<Object> com = Collator.getInstance(Locale.CHINA);
            if (o1.getId() == o2.getId()) {
                return -1;
            }
            return com.compare(o1.getTest5(), o2.getTest5());
        });
        System.out.println("---------------------------" + JSON.toJSONString(arrayList));
        Long countPayAmt = arrayList.stream()
                .filter(e -> e.getId() > 1)
                .map(User::getId)
                .reduce(0L, (a, b) -> a + b);
        System.out.println(countPayAmt);
        System.out.println("-------------------------------------------------------");
//        arrayList.add(1,user3);
        System.out.println(JSON.toJSONString(arrayList));

        /**
         * 排序
         * */
        Comparator<User> reversed = Comparator.comparing(User::getTest6).reversed();
        //按时间排序
        List<User> collect = arrayList.stream()
                .sorted(Comparator.comparing(User::getDate).reversed())
                .collect(Collectors.toList());
        //naturalOrder()表示自然排序(一般是升序)，没有处理属性的null值，排序时可能会空指针
        List<User> orderList1 = arrayList.stream()
                .sorted(Comparator.comparing(User::getDate, Comparator.naturalOrder()))
                .collect(Collectors.toList());
        //Comparator.reverseOrder()表示 降序，没有处理属性的null值，排序时可能会空指针
        List<User> orderList2 = arrayList.stream()
                .sorted(Comparator.comparing(User::getDate, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        //Comparator.nullsLast()表示如果属性为null，就放到最后面
        List<User> orderList3 = arrayList.stream()
                .sorted(Comparator.comparing(User::getDate, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        //Comparator.nullsFirst()表示如果属性为null，就放到最前面。
        List<User> orderList4 = arrayList.stream()
                .sorted(Comparator.comparing(User::getDate, Comparator.nullsFirst(Comparator.naturalOrder())))
                .collect(Collectors.toList());
        //多字段排序：如果是三个以上的字段排序，就继续用 thenComparing 连接。
        List<User> orderList5 = arrayList.parallelStream()
                .sorted(Comparator.comparing(User::getDate, Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(User::getTest6, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());

        /**reversed()和Comparator.reverseOrder()的区别：
         *
         *         Comparator.comparing(对象的类名::属性的方法名).reversed();
         *         Comparator.comparing(对象的类名::属性的方法名,Comparator.reverseOrder());
         *
         *         reversed()是得到排序结果后再反转，
         *         Comparator.reverseOrder()是对属性按照降序进行排序，
         *         reversed()在多字段排序时，很容易混乱，不建议使用。
         *         Comparator.reverseOrder()更好理解，也更好用些。
         * */


        /**过滤*/
        List<User> collect1 = arrayList.stream()
                .filter(s -> s.getId() == 1L)
                .collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect1));


        /**map*/
        //相加
        Long reduce = arrayList.stream().map(User::getId).reduce(0L, (a, b) -> a + b);
        System.out.println("相加" + reduce);

        /**分组*/
        //根据日
        //, LinkedHashMap::new, Collectors.toList() 默认hashmap是无序的，传入LinkedHashMap有序
//        Map<String, List<WlClockLogResp>> collect = wlClockLogResps.stream()
//                .collect(Collectors.groupingBy(p -> p.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), LinkedHashMap::new, Collectors.toList()));

        //根据周分组
//        Map<WlClockLogResp, List<WlClockLogResp>> collect = wlClockLogResps.stream()
//                .collect(Collectors.groupingBy(p -> p.getCreateTime().getDayOfWeek()));

        //先筛选（filter），然后根据字段CreateTime按日分组（groupingBy）且返回日期最早（collectingAndThen）的数据
//        Map<String, WlClockLogDTO> collect = list.stream()
//                .filter(e -> e.getCreateTime() != null && e.getPointType() == 1)
//                .collect(Collectors.groupingBy(p -> p.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                        Collectors.collectingAndThen(Collectors.reducing((c1, c2) -> c1.getCreateTime().isAfter(c2.getCreateTime()) ? c1 : c2), Optional::get)));
        //map转List
//        List<LocalDateTime> averageDateList = collect.values()
//                .stream().map(WlClockLogDTO::getCreateTime)
//                .collect(Collectors.toList());
    }
}
