package Test;

import com.example.springboot.utils.date.DateUtil;
import org.junit.Test;

import java.text.ParseException;
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
            System.out.println("---------------");
            boolean in = cn.hutool.core.date.DateUtil.isIn(now, beginTime, endTime);
            System.out.println(in);


            boolean inDate = isInDate(now,"09:00:00", "18:00:00");
            System.out.println(inDate);


            Date date = parseTime("2021-08-09 00:00:00");
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Date parseTime(String date) {
        return parseTime(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date parseTime(String date, String format) {
        if (date == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(format)).parse(date);
            } catch (ParseException var3) {
                throw new RuntimeException(var3);
            }
        }
    }
    /**
     * 判断时间是否在时间段内
     *
     * @param date
     *            当前时间 yyyy-MM-dd HH:mm:ss
     * @param strDateBegin
     *            开始时间 00:00:00
     * @param strDateEnd
     *            结束时间 00:05:00
     * @return
     */
    public static boolean isInDate(Date date, String strDateBegin,
                                   String strDateEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        // 截取入参时间时分秒
        int strDateH = Integer.parseInt(strDate.substring(11, 13));
        int strDateM = Integer.parseInt(strDate.substring(14, 16));
        int strDateS = Integer.parseInt(strDate.substring(17, 19));
        // 截取开始时间时分秒
        int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));
        int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));
        int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));
        // 截取结束时间时分秒
        int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));
        int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));
        int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));
        if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {
            // 当前时间小时数在开始时间和结束时间小时数之间
            if (strDateH > strDateBeginH && strDateH < strDateEndH) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM >= strDateBeginM
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间
            } else if (strDateH == strDateBeginH && strDateM == strDateBeginM
                    && strDateS >= strDateBeginS && strDateS <= strDateEndS) {
                return true;
            }
            // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数
            else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM <= strDateEndM) {
                return true;
                // 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数
            } else if (strDateH >= strDateBeginH && strDateH == strDateEndH
                    && strDateM == strDateEndM && strDateS <= strDateEndS) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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
