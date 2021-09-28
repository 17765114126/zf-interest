package Test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.model.Student;
import com.example.springboot.model.User;
import com.example.springboot.utils.DateUtil;
import com.example.springboot.utils.MathUtil;
import com.example.springboot.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @ClassName SuiShouTest
 * @Author zhaofu
 * @Date 2020/3/3
 * @Version V1.0
 **/
public class SuiShouTest {

    @Test
    public void test() {
        int ceil = (int) Math.ceil(26 / 20) + 1;
        System.out.println(ceil);

        for (int i = 0; i < ceil; i++) {
            for (int j = 0; j <= 20; j++) {
                if (i == ceil - 1 && j == 26 - (i * 20)) break;
                if (i == 1) {
                    System.out.println(i + "---" + j);
                } else {
                    System.out.println(i + "---" + j);
                }
            }
            int x = 3;
            System.out.println("====================");
        }
    }

    @Test
    public void tese1() {
        float payMoney = 100;
        Integer num = 2;

        double sum = 0;
        for (int i = 0; i < 2; i++) {
            double mul = MathUtil.mul(payMoney, num);
            sum = MathUtil.add(sum, mul);
        }
        System.out.println("------------------------------------");
        System.out.println(sum);
    }

    @Test
    public void test2() {
//        List objects = new ArrayList(10);
//        List list = Collections.synchronizedList(new ArrayList(10));

//        Map map = new HashMap(16);
//        map.put("1",1);
        System.out.println(Math.ceil(9.1));
        yqhGetPrice(100.6);
    }

    /**
     * 一起火计算价格
     *
     * @param xyPrice
     * @return
     */
    public Map<String, Object> yqhGetPrice(Double xyPrice) {//进货价
        xyPrice = MathUtil.toInteger(xyPrice * 0.95);//打九五折并取整数
        if (xyPrice <= 100) {
            xyPrice = xyPrice * 3;
        } else if (xyPrice > 100) {
            xyPrice = xyPrice * 2.7;
        }
        String tempPrice = String.valueOf((int) Math.ceil(xyPrice));
        int i = Integer.parseInt(tempPrice.substring(tempPrice.length() - 1));
        if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6) {
            i = 6;
        } else if (i == 7 || i == 8) {
            i = 8;
        } else if (i == 9) {
            i = 9;
        }
        String xsPrice = tempPrice.substring(0, tempPrice.length() - 1) + String.valueOf(i);
        String hxPrice = String.valueOf(Math.round(Float.parseFloat(xsPrice) * 2.1));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("clos4", hxPrice);
        map.put("clos5", xsPrice);
        System.out.println(hxPrice);
        System.out.println(xsPrice);
        return map;
    }


    @Test
    public void Test3() {
        int q = 2;
        int a = 2;
        String z = "12";
        int w = 542;
        if (q == 2 && a == 2 && (z == null || z != null && (w == 28 || w == 42))) {
            System.out.println("已发货");
        }
    }

    @Test
    public void Test4() {
        BigDecimal q = new BigDecimal(1.55);
        BigDecimal a = new BigDecimal(1.45);
        BigDecimal z = new BigDecimal(0.61);
        BigDecimal bigDecimal = q.add(a).add(z);
        float v = bigDecimal.floatValue();
        System.out.println(v);

        /**
         * 小可以转大，大不可以转小
         * */
        Byte w = 2;
        Double s = Double.valueOf(w);
        System.out.println(s);
        Integer integer = Integer.valueOf(w);
    }

    @Test
    public void Test5() {
        Float mayMoney = 0F;
        List<? extends Number> numbers = Arrays.asList(99.98, 100, -26.61);
        for (Number number : numbers) {
            mayMoney = MathUtil.addFloat(mayMoney, number.floatValue());
        }
    }

    @Test
    public void Test6() {
        //BigDecimal本身提供了加减乘除的方法
        //
        //加法 add()函数
        //减法subtract()函数
        //乘法multipy()函数
        //除法divide()函数
        //绝对值abs()函数

        //BigDecima类型相加
        BigDecimal s = BigDecimal.valueOf(2.01);
        BigDecimal x = BigDecimal.valueOf(3.12);
        System.out.println(s.add(x));
        System.out.println(s.add(x).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("---------------------");

//        Integer stockNum = 0;
//        stockNum = stockNum + Integer.parseInt("55.0");
//        System.out.println(DateUtil.getNewFormatDateString(new Date()));
//        System.out.println(stockNum);
        BigDecimal bigDecimal = new BigDecimal(3.555F);

        BigDecimal ecimal = new BigDecimal(3.211F);
        BigDecimal add = bigDecimal.add(ecimal).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(add);
        Float i = 6.71F;
        Integer i1 = 2;
        System.out.println(MathUtil.mul(i, i1));

        /**
         * 四舍五入截取为小数点两位
         * */
        Float z = 6.718888F;
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println(df.format(z - 2));

    }

    @Test
    public void Test7() {
        BigDecimal c = BigDecimal.valueOf(3.55);
        Integer g = 2;
        BigDecimal bignum2 = new BigDecimal(g);
        BigDecimal multiply = c.multiply(bignum2);

        Float xx = 200001.11f;
        Float yy = 200000.08f;
        Float tt = xx - yy;

        BigDecimal b1 = new BigDecimal(Float.toString(xx));
        BigDecimal b2 = new BigDecimal(Float.toString(yy));
        float ss = b1.subtract(b2).floatValue();
        System.out.println("tttttt-----" + tt);
        System.out.println("ssss----" + ss);

        BigDecimal ssd = new BigDecimal(Float.toString(xx));
        System.out.println(ssd);
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
    public void Test12() {
        BigDecimal bigDecimal = new BigDecimal(0);
        System.out.println(bigDecimal.negate());
        System.out.println(new BigDecimal(-3).negate());
        System.out.println(new BigDecimal(0).negate());


        System.out.println(bigDecimal != new BigDecimal(0));
        System.out.println(!bigDecimal .equals(new BigDecimal(0)));

    }

    @Test
    public void Test13() throws InterruptedException {
        System.out.println("213");
//        Thread.sleep(3000);

        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(j + "*" + i + "=" + j * i + "\t");
            }
            System.out.println();
        }

//        for(int i = 1;i < 10;i ++){
//            for(int j = 1; j <= i;j ++){
//                if(j == i){
//                    System.out.println(j +" * " + i + "="+ (j * i) + " ");
//                }else{
//                    System.out.print(j +" * " + i + "="+ (j * i) + " ");
//                }
//            }
//        }

    }


    @Test
    public void Test14() {
        String Str = new String("Welcome to Yiibai.com");

//        System.out.println(StringUtil.startsWithIgnoreCase(Str, "select"));
//        System.out.println(StringUtil.containsIgnoreCase(Str, "delect"));
        //字符串是否包含字符
        System.out.println(!StringUtil.containsIgnoreCase(Str, "{0}"));
        System.out.print("Return Value :");
        System.out.println(Str.startsWith("Welcome"));

        System.out.print("Return Value :");
        System.out.println(Str.startsWith("Tutorials"));

        System.out.print("Return Value :");
        System.out.println(Str.startsWith("Yiibai", 11));


        User user = new User();
        user.setId(1L);
        user.setDate(DateUtil.getDateNoTime("2021-09-18 11:15:35"));

        User user1 = new User();
        user1.setId(2L);
        user1.setDate(DateUtil.getDate());

        User user2 = new User();
        user2.setId(3L);
        user2.setDate(DateUtil.getDateNoTime("2021-09-22 15:56:41"));

        List<User> arrayList = new ArrayList();
        arrayList.add(user);
        arrayList.add(user1);
        arrayList.add(user2);

        //按时间排序
        List<User> collect = arrayList.stream().sorted(Comparator.comparing(User::getDate).reversed()).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect));
    }

}
