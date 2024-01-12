package Test;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.springboot.model.User;
import com.example.springboot.utils.CollectionUtil;
import com.example.springboot.utils.ID.SnowIdUtils;
import com.example.springboot.utils.date.DateUtil;
import com.example.springboot.utils.number.MathUtil;
import com.example.springboot.utils.string.StringUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.gson.Gson;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestBody;


import javax.naming.*;
import java.io.File;
import java.math.BigDecimal;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
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

        //BigDecima类型相加
        BigDecimal e = BigDecimal.valueOf(0);
        BigDecimal ex = BigDecimal.valueOf(0.01);
        System.out.println(e.add(ex));
        //加法 add()函数
        //减法subtract()函数
        //乘法multipy()函数
        //除法divide()函数
        //绝对值abs()函数

        //BigDecima类型相加
        BigDecimal s = BigDecimal.valueOf(2.01);
        BigDecimal x = BigDecimal.valueOf(3.12);
        System.out.println(s.subtract(x));
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

    public static void main(String[] args) {
        BigDecimal rate = new BigDecimal(100);

        BigDecimal ExecutoryPay = new BigDecimal("291500.00");
        BigDecimal AdvancePayment = new BigDecimal("0.01");

        //支付中心 待付金额
        BigDecimal nonPayAmount = new BigDecimal("29149999");
        BigDecimal nonPayAmount1 = new BigDecimal("29149999.00");

        boolean b = nonPayAmount.compareTo(nonPayAmount1) == 0;
        System.out.println(b);


        BigDecimal subtract = ExecutoryPay.subtract(AdvancePayment).multiply(rate);

        System.out.println(subtract);
        if (!subtract.equals(nonPayAmount)) {
            System.out.println("---------sdvv-------");

        }


        nonPayAmount = nonPayAmount.divide(rate).add(AdvancePayment);
        //发飞书消息
        if (!ExecutoryPay.equals(nonPayAmount)) {

            System.out.println("----------------");
        }

//        String rPWH3KF85BdDZQTn = signResult("rPWH3KF85BdDZQTn");
//        System.out.println(rPWH3KF85BdDZQTn);
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
        BigDecimal bigDecimal = new BigDecimal(5.00);
//        System.out.println(bigDecimal.negate());
//        System.out.println(new BigDecimal(-3).negate());
//        System.out.println(new BigDecimal(0).negate());
//        System.out.println(bigDecimal != new BigDecimal(0));
//        System.out.println(!bigDecimal .equals(new BigDecimal(0)));
        BigDecimal b = new BigDecimal(1.05);

        System.out.println("------" + bigDecimal.subtract(b));

        int i = bigDecimal.compareTo(b);
        boolean equals = bigDecimal.equals(BigDecimal.ZERO);


        BigDecimal rate = new BigDecimal(100);
        System.out.println(b.divide(rate).add(bigDecimal));
        System.out.println(MathUtil.isIntegerValue(b));


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
            if (o1.getId() == o2.getId()){
                return -1;
            }
            return com.compare(o1.getTest5(), o2.getTest5());
        });
        System.out.println("---------------------------"+JSON.toJSONString(arrayList));
        Long countPayAmt = arrayList.stream().filter(e -> e.getId() > 1).map(User::getId).reduce(0L, (a, b) -> a + b);


        System.out.println(countPayAmt);

        System.out.println("-------------------------------------------------------");

//        arrayList.add(1,user3);

        System.out.println(JSON.toJSONString(arrayList));


        Comparator<User> reversed = Comparator.comparing(User::getTest6).reversed();
        //按时间排序
        List<User> collect = arrayList.stream().sorted(Comparator.comparing(User::getDate).reversed()).collect(Collectors.toList());
        //过滤
        List<User> collect1 = arrayList.stream().filter(s -> s.getId() == 1L).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect1));
        //相加
        Long reduce = arrayList.stream().map(User::getId).reduce(0L, (a, b) -> a + b);

        //根据日分组
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



        System.out.println("相加" + reduce);

        //判断集合是否为空
        boolean notEmpty = CollectionUtils.isNotEmpty(arrayList);
        System.out.println(notEmpty);

        List list = new ArrayList<>();

        Map map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "2");

        list.add(map);

        Map map1 = new HashMap<>();

        map1.put("id", 3L);
        map1.put("name", "acs");
        list.add(map1);
        System.out.println(JSON.toJSONString(list));

    }

    @Test
    public void Test132() {

        List<WlClockLog> wlClockLogs = new ArrayList<>();
        WlClockLog wlClockLog = new WlClockLog();
        wlClockLog.setCreateTime(LocalDateTime.now());
        wlClockLogs.add(wlClockLog);

        WlClockLog wlClockLog1 = new WlClockLog();
        wlClockLog1.setCreateTime(LocalDateTime.now().minusDays(9));
        wlClockLogs.add(wlClockLog1);

        WlClockLog wlClockLog2 = new WlClockLog();
        wlClockLog2.setCreateTime(LocalDateTime.now().minusDays(3));
        wlClockLogs.add(wlClockLog2);

        WlClockLog wlClockLog3 = new WlClockLog();
        wlClockLog3.setCreateTime(LocalDateTime.now().minusDays(3));
        wlClockLogs.add(wlClockLog3);

        WlClockLog wlClockLog4 = new WlClockLog();
        wlClockLog4.setCreateTime(LocalDateTime.now().minusDays(33));
        wlClockLogs.add(wlClockLog4);

        //7天内
        Map<DayOfWeek, List<WlClockLog>> collect = wlClockLogs.stream()
                .filter(e -> e.getCreateTime() != null && e.getCreateTime().isAfter(LocalDateTime.now().minusDays(7)))
                .collect(Collectors.groupingBy(p -> p.getCreateTime().getDayOfWeek()));
        System.out.println(collect.size());
        //30天内
        Map<Integer, List<WlClockLog>> collect1 = wlClockLogs.stream()
                .filter(e -> e.getCreateTime() != null && e.getCreateTime().isAfter(LocalDateTime.now().minusDays(30)))
                .collect(Collectors.groupingBy(p -> p.getCreateTime().getDayOfMonth()));
        System.out.println(collect1.size());
    }

    @Test
    public void Test15() {
        String sql = "select id from order WHERE id = 2";
        String[] names = sql.split("[whereWHERE]");

        System.out.println(names[names.length - 1]);
    }

    @Test
    public void Test16() {
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
    public void Test17() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("123");
        System.out.println(objects.isEmpty());


        if (DateUtil.getDateNoTime("2021-06-16 00:00:00").compareTo(cn.hutool.core.date.DateUtil.parseDate("2022-01-01 00:00:00")) < 0) {
//            return ResultBean.success(null);
            System.out.println("111-------------");
        } else {
            //日期赋值
            System.out.println("222-------------");
//            date = bizOrder.getPayDepositTime();
        }


        System.out.println(formatNumber(new BigDecimal("60.00")));
    }

    public Integer formatNumber(BigDecimal bigDecimal) {
//        String priceStr = CURRENCY_FORMAT.format(bigDecimal).substring(1);
        String priceStr = String.valueOf(bigDecimal);
        if (StringUtils.isBlank(priceStr)) {
            return 0;
        }
        int i = priceStr.indexOf(".");
        String substring = priceStr.substring(0, i);
        Integer integer = Integer.valueOf(substring);
        return integer;
    }


    @Test
    public void Test18() {

        Map<String, Object> paramMap = new HashMap<>();

        String payOrderId = String.valueOf(paramMap.get("attach"));
        System.out.println(StringUtils.isNotBlank(payOrderId));

        BigDecimal q = new BigDecimal(2000.00);
        //成功支付金额
        BigDecimal w = new BigDecimal(100);

        BigDecimal multiply = q.multiply(w);
        System.out.println(multiply);


        BigDecimal nonPayAmount = new BigDecimal(0);
        //成功支付金额
        BigDecimal LoanAmt = new BigDecimal(0.01);

        //定金
        BigDecimal amonut = new BigDecimal(0.01);

        if (!(LoanAmt.compareTo(amonut) == -1 && nonPayAmount.compareTo(amonut) > -1)) {
            Boolean isUpdateStatus = false;
        }
//        if (!(succeedPay.compareTo(amonut) == -1 && notifyResult.getCallBackPaymentResult().getHasPayAmount().compareTo(amonut) > -1)) {
        if (!new BigDecimal(0.03).equals(new BigDecimal(0)) && !new BigDecimal(0.02).equals(new BigDecimal(0.02))) {
            System.out.println("=================as1");
        }


        //前提为a、b均不能为null
        if (nonPayAmount.compareTo(LoanAmt) == -1) {
            System.out.println("a小于b");
        }

        if (nonPayAmount.compareTo(LoanAmt) == 0) {
            System.out.println("a等于b");
        }

        if (nonPayAmount.compareTo(LoanAmt) == 1) {
            System.out.println("a大于b");
        }

        if (nonPayAmount.compareTo(LoanAmt) > -1) {
            System.out.println("a大于等于b");
        }

        if (nonPayAmount.compareTo(LoanAmt) < 1) {
            System.out.println("a小于等于b");
        }


        //判断BigDecimal类型是否为0
        System.out.println(LoanAmt.compareTo(nonPayAmount) == 0);


        if (nonPayAmount.compareTo(new BigDecimal(0.00)) == 0) {
            System.out.println("---------ssss-----");
        }

        System.out.println(LoanAmt);

        Set<Long> longs = new HashSet<>();
        longs.add(1L);
        longs.add(2L);
        if (longs.contains(1L)) {
            System.out.println("--------------");
        }

    }

    @Test
    public void Test19() {
        long snowID = SnowIdUtils.uniqueLong();
        System.out.println(snowID);

        long snowID1 = SnowIdUtils.uniqueLong();
        System.out.println(snowID1);
    }


    @Test
    public void testCotyOf() {
        ImmutableSet<String> imSet = ImmutableSet.of("peida", "jerry", "harry", "lisa");
        System.out.println("imSet：" + imSet);

        //set直接转list
        ImmutableList<String> imlist = ImmutableList.copyOf(imSet);
        System.out.println("imlist：" + imlist);

        //list直接转SortedSet
        ImmutableSortedSet<String> imSortSet = ImmutableSortedSet.copyOf(imSet);
        System.out.println("imSortSet：" + imSortSet);

        List<String> list = new ArrayList<String>();
        for (int i = 0; i <= 10; i++) {
            list.add(i + "x");
        }
        System.out.println("list：" + list);

        //截取集合部分元素
        ImmutableList<String> imInfolist = ImmutableList.copyOf(list.subList(2, 8));
        System.out.println("imInfolist：" + imInfolist);


    }


    @Test
    public void Test20() {


        boolean isWe = Objects.equals(7, 1)
                || Objects.equals(7, 2);
        //不为WE版的，若国补价格不同抛出

        BigDecimal bigDecimal = new BigDecimal("0");
        BigDecimal bigDecimal1 = new BigDecimal("0.00");
        if (!isWe && !(new BigDecimal("0").compareTo(bigDecimal1) == 0)) {
            System.out.println("------------------------");

        }


        if (Arrays.asList(new BigDecimal("12600"), new BigDecimal("0"), new BigDecimal("8820")).contains(new BigDecimal("34"))) {
//            System.out.println("------------------------");
        }


        List<Integer> objects = new ArrayList<>();
        objects.add(1);
        objects.add(2);
//        System.out.println(objects);
        System.out.println(objects.contains(2));
        System.out.println(objects.contains(Arrays.asList(1, 2)));

        Date parse = null;
        Date parse1 = null;
        try {
            parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-01 00:00:00");
            parse1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-05-01 00:00:00");
        } catch (ParseException var3) {
            throw new RuntimeException(var3);
        }

        Long versionId = 2L;
//        if ((Objects.equals(versionId.intValue(), ModelVersionEnum.WE.getCode()) || Objects.equals(versionId.intValue(), ModelVersionEnum.SUPER_WE.getCode())) && parse1.compareTo(parse) == 1) {
//            System.out.println("--------------------");
//        }
    }

    @Test
    public void Test21() {
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
    public void Test22() {

        List<String> list = new ArrayList<String>();
        list.add("AAA");
        list.add("BBB");
        list.add("BBB");
        list.add("CCC");
//若直接操作，会报ConcurrentModificationException异常（并发修改异常）
//        for (String str : list) {
//            if("BBB".equals(str)){
//                list.remove(str);
//            }
//        }
//需要添加到CopyOnWriteArrayList在处理
        List<String> newList = new CopyOnWriteArrayList<String>();
        newList.addAll(list);
        for (String str : newList) {
            if ("BBB".equals(str)) {
                newList.remove(str);
            }
        }
        System.out.println();
    }

    @Test
    public void Test23() {
            long payOrderRefundId = SnowIdUtils.uniqueLong();
        for (int i = 0; i < 47; i++) {
            String str = "INSERT INTO `future_community`.`wl_childcare_bespeak`" +
                    "(`institution_id`, `bespeak_name`, `bespeak_mobile`, `child_name`, `child_sex`, `child_age`, `status`, `reserve_time_id`, `wl_time_period_id`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                    "VALUES \n" +
                    "('1663384218053922816', '预约人姓名', '15268518665', '幼儿姓名', '1', '3', '1', '"+payOrderRefundId+"', '1663384218116837376', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', 'a08d223d4ebe491c971e50325b8fc1a7', '2023-04-03 11:23:43', '0');";
            System.out.println(str);
            String str1 = "INSERT INTO `future_community`.`wl_venues_reserve_time` \n" +
                    "(`id`, `institution_id`, `AM_open`, `PM_open`, `type`, `open_data`, `create_user_uuid`, `create_time`, `last_update_user_uuid`, `last_update_time`, `del_flag`) \n" +
                    "VALUES \n" +
                    "('"+payOrderRefundId+"', '1645310176615964672', '1', '1', '3', '2023-03-21', 'qwrwaedfzsd', '2023-04-10 14:18:09', 'qwrwaedfzsd', '2023-04-10 14:18:09', '0');";
            System.out.println(str1);
        }


    }
}
