package Test;

import com.example.springboot.utils.DateUtil;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateTest
 * @Author zhaofu
 * @Date 2020/8/3
 * @Version V1.0
 **/
public class DateTest {
    public static void main(String[] args) {

        //判断当前时间，是否在起始时间和结束时间之间，可以精确到秒
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
        try {
            Date now = df.parse("2020-07-29 00:00:00");
            Date beginTime = df.parse("2020-07-30 00:00:00");
            Date endTime = df.parse("2021-08-09 00:00:00");
            Boolean flag = DateUtil.belongCalendar(now, beginTime, endTime);
            System.out.println(flag);
            System.out.println("---------------");
            System.out.println(DateUtil.bigOrSmall(endTime,beginTime));
            Boolean flag1 = DateUtil.betweenStartAndEndDateTow(now, beginTime, endTime);
            System.out.println("---------------");
            System.out.println(flag1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void Test() {
        Date date = new Date();

        System.out.println(DateUtil.getDateToStr(DateUtil.addDays(date, 15)));

        Date dateTimeYYYYMMDD = DateUtil.getDateTimeYYYYMMDD();
        System.out.println(dateTimeYYYYMMDD);
        System.out.println(DateUtil.getDateToStr(DateUtil.getDateTimeYYYYMMDD()));


        int compareTo = dateTimeYYYYMMDD.compareTo(new Date());
        System.out.println(compareTo);

        int compareTo2 = new Date().compareTo(dateTimeYYYYMMDD);
        System.out.println(compareTo2);
        System.out.println("------------------------------------------");

        System.out.println(DateUtil.bigOrSmall(dateTimeYYYYMMDD, new Date()));
        System.out.println(DateUtil.bigOrSmall(new Date(), dateTimeYYYYMMDD));

        System.out.println(DateUtil.getDateToStr(DateUtil.getDay(new Date(), 16)));
        System.out.println("------------------------------------------");
        System.out.println(new Date());
        System.out.println(DateUtil.getTimeToStr(new Date()));
    }
}
