package Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.model.Person;
import com.example.springboot.model.constant.RandomType;
import com.example.springboot.utils.ID.SnowIdUtils;
import com.example.springboot.utils.string.StringUtil;
import com.example.springboot.utils.string.StringUtilss;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrTest {

    public static void main(String[] args) {
        //创建一个对象
        Person user1 = new Person("1号", 3, "男");
        Person user2 = new Person("2号", 3, "男");
        Person user3 = new Person("3号", 3, "男");
        Person user4 = new Person("4号", 3, "男");
        List<Person> list = new ArrayList();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);

        System.out.println(" *******Java对象 转 JSON字符串*******");
        String str1 = JSON.toJSONString(list);
        System.out.println(" JSON.toJSONString(list)==>" + str1);
        String str2 = JSON.toJSONString(user1);
        System.out.println(" JSON.toJSONString(user1)==>" + str2);

        System.out.println(" ****** JSON字符串 转 Java对象*******");
        Person jp_user1 = JSON.parseObject(str2, Person.class);
        System.out.println("JSON.parseObject(str2,User.class)==>" + jp_user1);

        System.out.println(" ****** Java对象 转 JSON对象 ******");
        JSONObject jsonObject1 = (JSONObject) JSON.toJSON(user2);
        System.out.println("(JSONObject) JSON.toJSON(user2)==>" + jsonObject1.getString("name"));

        System.out.println(" ****** JSON对象 转 Java对象 ******");
        Person to_java_user = JSON.toJavaObject(jsonObject1, Person.class);
        System.out.println(" JSON.toJavaObject(jsonObject1, User.class)==>" + to_java_user);
    }

    /**
     * 十进制转二进制
     * 原理：给定的数循环除以2，直到商为0或者1为止。将每一步除的结果的余数记录下来，然后反过来就得到相应的二进制了。
     * 比如8转二进制，第一次除以2等于4（余数0），第二次除以2等于2（余数0），第三次除以2等于1（余数0），最后余数1，得到的余数依次是 0 0 0 1 ，
     * 反过来就是1000，计算机内部表示数的字节长度是固定的，比如8位，16位，32位。所以在高位补齐，java中字节码是8位的，所以高位补齐就是00001000.
     */
    @Test
    public void Test1() {
        System.out.println("二进制数是：" + Integer.toBinaryString(5));
        System.out.println("八进制数是：" + Integer.toHexString(5));
        System.out.println("十进制数是：" + Integer.toString(5));
        System.out.println("十六进制数是：" + Integer.toOctalString(5));

        System.out.println(toBinary(1));
        System.out.println(toBinary(5));
        System.out.println(toBinary(8));
        System.out.println(toBinary(10));
        /**
         * 二进制转十进制
         * 计算也很简单，比如8的二进制表示位00001000，去掉补齐的高位就是1000.
         * 此时从个位开始计算2的幂（个位是0，依次往后推）乘以对应位数上的数，然后得到的值相加
         * 于是有了，（2的0次幂）0+（2的1次幂）0+（2的2次幂）0+（2的3次幂）1 = 8
         * */
        System.out.println(Integer.parseInt("1000", 2)); //运行结果：8
    }

    static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }


    @Test
    public void test8() {
        String str = "1234567.txt";
        //返回此字符串中第一次出现 指定子字符串的索引。
        if (str.indexOf(".tx") != -1) {
            System.out.println("包含该字符串");
        } else {
            System.out.println("不包含该字符串");
        }

        //替换字符
        String expressNumber = "  1213    3134  12  ";
        System.out.println(expressNumber.replaceAll(" ", ""));

        //字符串处理
        String[] split = "16666666666,17765114126".split(",");
        String string = "";
        for (String s : split) {
            string += "\"" + s + "\"" + ",";
        }
        String str1 = "[" + string.substring(0, string.length() - 1) + "]";
        System.out.println(str1);
    }

    @Test
    public void Test9() {
        Long a = 456456L;
        Long b = 456456L;
        int i1 = a.hashCode();
        int i2 = b.hashCode();
        System.out.println("hashCode:" + i1 + "-------" + i2);
        System.out.println(a != b);// =：对比内存中的地址是否相同
        System.out.println(!a.equals(b));// equals：对比字符串是否相同
        System.out.println("--------------------------");

        String str = "[\"16666666666\",\"17765114126\"]";
        System.out.println(str.hashCode());

        String[] split = new String[]{"17765114126", "18203655200"};
        String[] sp = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            sp[i] = split[i] + "1";
            System.out.println(sp[i]);
        }
        System.out.println("--------------------------");
        String str1 = "123456789,";
        if (StringUtils.isNotBlank(str1) && str1.substring(str1.length() - 1).equals(",")) {
            System.out.println(str1.substring(0, str1.length() - 1));
        }
        Integer i = 1;
        System.out.println(i.toString().equals("1"));
    }

    @Test
    public void Test10() {
        System.out.println(StringUtils.isAnyEmpty("", "bar"));
        System.out.println(StringUtils.isAnyBlank(" ", "bar"));

        System.out.println(StringUtils.isEmpty(" "));
        System.out.println(StringUtils.isEmpty("\n"));

        System.out.println(StringUtils.isWhitespace("\n"));
        System.out.println(StringUtils.isWhitespace(" "));

        String productIds = "1,2,3,4,5";
        String[] split = productIds.split(",", 3);
        System.out.println(JSON.toJSONString(split));
        System.out.println(JSON.toJSONString(StringUtils.split(productIds, ",", 2)));
        System.out.println(JSON.toJSONString(StringUtils.split("a:b:c", ':')));
    }

    /**
     * 判断是否数字与字母
     */
    @Test
    public void Test11() {
        HashMap<String, String> map = new HashMap<>();
        System.out.println(StringUtil.isBlank(map.get("ds")));

        String expressNumber = "。";
        if (!isChinese(expressNumber)) {//非数字
            System.out.println("1111111111111");
        }
    }

    public boolean isChinese(String str) {
        //判断是否数字与字母
        String regex = "^[a-z0-9A-Z]+$";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

    @Test
    public void Test14() {
//        String Str = new String("Welcome to Yiibai.com");

//        System.out.println(StringUtil.startsWithIgnoreCase(Str, "select"));
//        System.out.println(StringUtil.containsIgnoreCase(Str, "delect"));
        //字符串是否包含字符
//        System.out.println(!StringUtil.containsIgnoreCase(Str, "{0}"));
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Welcome"));
//
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Tutorials"));
//
//        System.out.print("Return Value :");
//        System.out.println(Str.startsWith("Yiibai", 11));

        // 随机字符串生成
        System.out.println(StringUtilss.random(33, RandomType.ALL));
        System.out.println(StringUtilss.random(33, RandomType.ALL));
        System.out.println(StringUtilss.random(33, RandomType.ALL));
        //雪花ID
        System.out.println(SnowIdUtils.uniqueLong());

        //判断集合是否为空
        boolean notEmpty = CollectionUtils.isNotEmpty(new ArrayList());
        System.out.println(notEmpty);
    }


    @Test
    public void Test16() {
        String sql = "select id from order WHERE id = 2";
        String[] names = sql.split("[whereWHERE]");
        System.out.println(names[names.length - 1]);
    }

    @Test
    public void Test17() {
        String trim = " AS1221acs".trim();
        System.out.println(trim);
        System.out.println(trim.toUpperCase());

        if (Arrays.asList(7, 8, 9).contains(9)) {
            System.out.println("---------------------");
        }

        String str = null;
        if (Objects.isNull(str) && !"000".equals("000")) {
            System.out.println("-------cdscscsd");
        }
    }

    @Test
    public void Test22() {
//        String[] split = "Z001A002,Z001A003".split(",");
////        List<String> strings = Arrays.asList(split);
////        boolean qd = strings.contains("Z001A002");
////        System.out.println(qd);
        boolean equals = "2".equals(null);
        System.out.println(equals);
//        if (new Date().compareTo(cn.hutool.core.date.DateUtil.parse("2022-09-01 00:00:00")) >= 0) {
//            System.out.println("____________________________");
//        }
    }

    @Test
    public void Test24() {
        long payOrderRefundId = SnowIdUtils.uniqueLong();
        for (int i = 0; i < 47; i++) {
            String str = "INSERT INTO `future_community`.`wl_childcare_bespeak`" +
                    "(`institution_id`, `bespeak_name`, `bespeak_mobile`, `child_name`, `child_sex`, `child_age`, `status`, `reserve_time_id`, `wl_time_period_id`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                    "VALUES \n" +
                    "('1663384218053922816', '预约人姓名', '15268518665', '幼儿姓名', '1', '3', '1', '" + payOrderRefundId + "', '1663384218116837376', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', '0');";
            System.out.println(str);
            String str1 = "INSERT INTO `future_community`.`wl_venues_reserve_time` \n" +
                    "(`id`, `institution_id`, `AM_open`, `PM_open`, `type`, `open_data`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                    "VALUES \n" +
                    "('" + payOrderRefundId + "', '1645310176615964672', '1', '1', '3', '2023-03-21', 'qwrwaedfzsd', '2023-04-10 14:18:09', 'qwrwaedfzsd', '2023-04-10 14:18:09', '0');";
            System.out.println(str1);
        }
    }
}
