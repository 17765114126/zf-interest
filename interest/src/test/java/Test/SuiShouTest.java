package Test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.model.User;
import com.example.springboot.utils.MathUtil;
import com.example.springboot.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName SuiShouTest
 * @Author zhaofu
 * @Date 2020/3/3
 * @Version V1.0
 **/
public class SuiShouTest {
    @Test
    public void tese1() {
        Map<String, String> map = new HashMap(2);
        map.put("applyJson", "521");
        System.out.println(map);
    }

    @Test
    public void tese3() {
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
    public void test4() {
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
    public void test5() {
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

    /**
     * 手机号正则表达式
     */
    @Test
    public void Test10() {
        boolean matches = "19165114126".matches("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
        System.out.println(matches);
    }

    @Test
    public void Test11() {
        String mobile = null;
        if (StringUtil.isBlank(mobile)) {
            mobile = "2314";
        }
        System.out.println(mobile);

        int q = 2;
        int a = 2;
        String z = "12";
        int w = 542;
        if (q == 2 && a == 2 && (z == null || z != null && (w == 28 || w == 42))) {
            System.out.println("已发货");
        }

    }

    @Test
    public void Test12() {
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
    public void Test13() {
        Float mayMoney = 0F;
        List<? extends Number> numbers = Arrays.asList(99.98, 100, -26.61);
        for (Number number : numbers) {
            mayMoney = MathUtil.addFloat(mayMoney, number.floatValue());
        }
    }

    @Test
    public void Test15() {
        Long q = 456456L;
        Long w = 456456L;
        int i1 = q.hashCode();
        int i2 = w.hashCode();
        System.out.println(q != w);
        System.out.println(!q.equals(w));
        System.out.println("--------------------------");

        String str = "[\"16666666666\",\"17765114126\"]";
        boolean equals = str.equals("");
        System.out.println(equals);
        int x = str.hashCode();
        System.out.println(x);

        String[] split = new String[]{"17765114126", "18203655200"};

        String[] sp = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            sp[i] = split[i] + "1";
        }
        User user = new User();
        user.setUserName("548");
        System.out.println(user.getUserName());

    }

    @Test
    public void Test16() {
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
    }

    @Test
    public void Test17() {
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


    @Test
    public void Test19() {
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

        String str = "123456789,";
        if (StringUtils.isNotBlank(str) && str.substring(str.length() - 1).equals(",")) {
            String substring = str.substring(0, str.length() - 1);
            System.out.println(substring);
        }
        System.out.println(str);
        BigDecimal ssd = new BigDecimal(Float.toString(xx));
        System.out.println(ssd);
    }

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    @Test
    public void Test21() {
        try {
            List<String> records = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                records.add("xiancheng :" + i);
            }
            CountDownLatch latch = new CountDownLatch(records.size());
            for (String record : records) {
                executor.execute(() -> {
                    try {
                        if (record.equals("xiancheng :5")) {
                            System.out.println("sleep--------");
                            Thread.sleep(2000);
                        }
                        System.out.println(record);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否数字与字母
     */
    @Test
    public void Test22() {
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
    public void Test20() throws Throwable {
        System.out.println(SpringUtils.testEnv());
        throw new Exception("dcj");
    }

    /**
     * 加密
     */
    @Test
    public void Test24() {
        String str = "$@$tz1@@7ndq8jypnifo";
        String salt = "C86nfc";
        SimpleHash md5 = new SimpleHash("md5", str, salt, 22);
        System.out.println(md5);
    }

    @Test
    public void Test26() {
        String xsPrice = "123,";
        System.out.println(xsPrice.substring(0, xsPrice.length() - 1));
        for (int i = 0; i < 4; i++) {
            System.out.println(i);
        }
    }

}
