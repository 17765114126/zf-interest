package study.java8特性;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName Time日期时间
 * @Author zhaofu
 * @Date 2019/8/1
 * @Version V1.0
 **/
public class Time日期时间 {

    /**
     * E.Time日期时间
     * 1.旧的日期时间缺点
     * a.非线程安全

     *  java.util.Date不是线程安全的

     * 因此开发者必须在使用日期处理并发性问题

     * 新的日期时间API是不可变的，并且没有setter方法

     * b.设计不佳

     * 默认的开始日期从1900年，开始每月从1天从0开始，所以没有统一

     * 不直接使用方法操作日期。新的API提供了这样操作实用方法

     * c. 困难的时区处理

     * 开发人员必须编写大量的代码来处理时区的问题

     * 新的API设计开发保持特定领域设计

     * 2.Instat

     * a.概述

     * 时间戳，即时间轴上的一个点

     * 从元年1970-1-1 00:00:00到现在的nanosecond数

     * b. Instant.now();获取当前时间
     */

    public static void main(String[] args) {
        // 获取当前时间
        Instant now = Instant.now();

        // T代表东西经0°经线区时：伦敦时间
        System.out.println(now);// 2017-11-25T14:06:57.079Z

        // c.Instant.ofEpochMilli(new Date().getTime()); 旧日期转为新日期
        // 获取当前时间
        now = Instant.ofEpochMilli(new Date().getTime());
        System.out.println(now);

        //d. Instant.parse();日期文本格式转换为时间格式
        // 获取当前时间
        now = Instant.parse("1993-02-06T10:12:35Z");
        System.out.println(now);
/**
 *3.LocalDate
 *
 * 表示不带时区的日期，比如2014-01-14
 *
 * a.LocalDateTime.now();获取当前日期时间
 *
 * b.now.minusDays(2);将当前日期时间减去两天
 *
 * c.LocalDateTime.of(2016, 10, 23);构造一个指定日期时间的对象
 *
 * */
        // 当前时间
        LocalDate now0 = LocalDate.now();
        System.out.println(now0);

        // 往前推两天
        LocalDate date = now0.minusDays(2);
        System.out.println(date);

        // 制定一个日期
        LocalDate localDate = LocalDate.of(1993, 2, 6);
        System.out.println(localDate);
/**
 *
 * 4.LocalTime
 *
 * 表示不带时区的时间
 *
 * a. LocalTime.now();当前时间
 *
 * b. LocalTime.of(22, 33);确定的时间
 *
 * c.LocalTime.ofSecondOfDay(4503); 一天中的第4503秒
 *
 * */
        // 当前时间
        LocalTime now1 = LocalTime.now();
        System.out.println(now1);

        // 22:33
        LocalTime localTime = LocalTime.of(22, 33);
        System.out.println(localTime);

        // 一天中的4503秒
        LocalTime ofDay = LocalTime.ofSecondOfDay(4503);
        System.out.println(ofDay);
/**
 * 5.LocalDateTim
 *
 *  是LocalDate和LocalTime的组合体，表示的是不带时区的日期及时间
 *
 * a.LocalDateTime.now();当前时间
 *
 * b.localDateTime.plusHours(25).plusMinutes(3); 当前时间加上25小时３分钟
 *
 * c.LocalDateTime.of(2014, 1, 20, 3, 30, 20)；转换
 *
 * */
        // 当前时间
        LocalDateTime now2 = LocalDateTime.now();
        System.out.println(now);

        // 当前时间加上25小时３分钟
        LocalDateTime plusMinutes = now2.plusHours(24).plusMinutes(3);
        System.out.println(plusMinutes);

        // 转换
        LocalDateTime of = LocalDateTime.of(1993, 2, 6, 11, 23, 30);
        System.out.println(of);
/**
 *
 * 6.ZoneDateTime
 *
 * 含有时区信息的时间
 *
 * a.ZonedDateTime.now();获取包含时区的当前日期时间
 *
 * b.ZonedDateTime.of(LocalDateTime.of(2014, 1, 20, 3, 30, 20), ZoneId.of("+08"));创建时区的日期时间对象
 *
 * */
        // 当前时间
        ZonedDateTime now3 = ZonedDateTime.now();
        System.out.println(now3);

        // 创建时区的日期时间对象
        ZonedDateTime of1 = ZonedDateTime.of(LocalDateTime.of(1993, 2, 6, 11, 23, 30), ZoneId.of("+08"));
        System.out.println(of1);
/**
 * 7.DateTimeFormatter
 *
 * 格式化日期和解析日期格式字符串。DateTimeFormatter是不可变类
 *
 * a.格式化：日期对象转换为格式字符串
 * */

        // 格式化
        String time = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss").format(LocalDateTime.now());
        System.out.println(time);


/**
 * b.解析：格式字符串转换为日期对象
 * */
        // 格式化
        LocalDateTime parse = LocalDateTime.parse("2017.01.01 08:08:08", DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"));
        System.out.println(parse);

/**
 * 8. 遗留代码操作
 *
 * a.Date --> Instant
 *
 * b.Calendar-->Instant
 * */
        Instant timestamp = new Date().toInstant();

        Instant instant = Calendar.getInstance().toInstant();

        System.out.println(timestamp);
        System.out.println(instant);


        timeTest();
    }


    public static void timeTest() {
        /**
         * LocalDate
         * */
        LocalDate today = LocalDate.now(); //当前日期 yyyy-dd-mm
        System.out.println(today.getMonth()+"---//APRIL 当前日期-月 getvalue 可以获得数字");
        System.out.println(today.getMonthValue()+"---//当前日期-月");
        System.out.println(today.getYear()+"---//当前日期-年");
        System.out.println(today.getDayOfMonth()+"---//当前日期-日");
        System.out.println(today.getDayOfWeek()+"---//返回当前日期是星期几");
        System.out.println(today.getDayOfWeek().getValue()+"---//getValue 可以获取1-7数字星期");
        System.out.println(today.getDayOfYear()+"//获取 当前 年到 今天的天数,包含今天");
        System.out.println(today.plusDays(1)+"//获得当前日期之后的日期");
        System.out.println(today.minusDays(1)+"//获得当前日期之前的日期");
        System.out.println(today.atTime(LocalTime.now())+"//2019-10-31T16:04:44.557");

        LocalDate DayofYear = LocalDate.ofYearDay(2019, 304);//返回哪一年，多少天后的日期
        System.out.println(DayofYear+"//返回哪一年，多少天后的日期 ==> 2019-10-31");
        LocalDate EpochDay = LocalDate.ofEpochDay(0);//获得1970-01-01后的日期，1970-01-01后的0天还是1970-01-01，如果是ofEpochDay(1) 则返回 1970-01-02
        System.out.println(EpochDay+"//获得1970-01-01后的日期，1970-01-01后的0天还是1970-01-01，如果是ofEpochDay(1) 则返回 1970-01-02");
        LocalDate Dateoftaking = LocalDate.of(2019,10,31);//根据年月日返回日期 yyyy-dd-mm
        System.out.println(Dateoftaking+"//根据年月日返回日期 yyyy-dd-mm");
        LocalDate endOfFeb = LocalDate.parse("2019-10-31"); // 严格按照ISO yyyy-MM-dd验证，02写成2都不行
        System.out.println(endOfFeb+"// 严格按照ISO yyyy-MM-dd验证，02写成2都不行");
        LocalDate.parse("2018-02-28"); // 无效日期无法通过：java.time.DateTimeException: Invalid date 'February 29' as '2018' is not a leap year

        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth()); // 2019-10-01
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2); // 2019-10-02
        // 取本月最后一天，不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth()); // 2019-10-31
        // 取下一天：
        LocalDate firstDayOf = lastDayOfThisMonth.plusDays(1); // 变成了2019-11-01
        // 取2019年2月第一个周一
        LocalDate firstMondayOf = LocalDate.parse("2019-02-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)); //2019-02-04

        /**
         * LocalTime
         * */
        LocalTime now = LocalTime.now(); //16:21:08.430  获得当前时间
        System.out.println(now.getHour()+"//时");
        System.out.println(now.getMinute()+"//分");
        System.out.println(now.getNano()+"//纳秒");
        System.out.println(now.getSecond()+"//秒");
        System.out.println(now.of(21, 30, 59, 11011)+"//或的21:30:59:11011");
        System.out.println(now.plusHours(3)+"//当前时间3小时后");
        System.out.println(now.plusMinutes(3)+"//当前时间3分钟后");
        System.out.println(now.plusSeconds(3)+"//当前时间3秒后");
        System.out.println(now.plusNanos(3)+"//当前时间3纳秒后");
        System.out.println(now.minusHours(3)+"//当前时间3小时前");
        System.out.println(now.minusMinutes(3)+"//当前时间3分钟前");
        System.out.println(now.minusSeconds(3)+"//当前时间3秒前");
        System.out.println(now.minusNanos(3)+"//当前时间3纳秒前");

        //构造时间
        LocalTime zero = LocalTime.of(0, 0, 0); // 00:00:00
        LocalTime mid = LocalTime.parse("12:00:00"); // 12:00:00

        LocalDateTime localDateTime = now.atDate(LocalDate.now()); //获得当前日期时间  2018-04-22T16:23:07.232
        System.out.println(localDateTime);
    }

}
