package com.example.springboot.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Date: 2019/8/27
 * @Author: zhaofu
 * @Description: 时间工具类
 **/
public class DateUtil {

    private static final Logger log = LoggerFactory.getLogger(DateUtil.class);

    public final static String CHINESE_DATE = "yyyy年MM月dd日";

    public final static String DEFAULT_SEQ_TIME = "yyyyMMddHHmmss";

    public final static String DEFAULT_SEQ_ONLY_TIME = "HHmmss";

    public final static String DEFAULT_SEQ_DATE = "yyyyMMdd";

    public final static String DEFAULT_DATE = "yyyy-MM-dd";

    public final static String NO_SECOND_TIME = "yyyy-MM-dd HH:mm";

    //public final static String seqBillDateTime = "yyyyMMdd HH:mm:ss";

    public final static String DEFAULT_TIME = "yyyy-MM-dd HH:mm:ss";

    public final static String DEFAULT_SEQ_TIME_SSS = "yyyyMMddHHmmssSSS";

    public final static String DEFAULT_SEQ_DATE_TIME = "yyyyMMddHHmm";

    public static final String DATE_TIME_FORMAT_T = "yyyy-MM-dd'T'HH:mm:ss";


    /**
     * 一个月的天数
     */
    public final static int DAYS_MONTH = 30;

    /**
     * 一年的天数
     */
    public final static int DAYS_YEAR = 365;

    /**
     * 生成批次号的时间
     */
    public final static String DEFAULT_BATCH_NO_TIME = "yyyyMMddHH";

    /**
     * 获取NTP时间
     *
     * @return
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * 获取当前日期 剔除时间
     *
     * @return
     */
    public static Date getNowDateNoTime() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        String str = dateFormat.format(getDate());
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            log.error("getNowDateNoTime error:", e);
        }
        return null;
    }

    /**
     * 获取系统日期的前一天日期，返回Date 剔除时间
     *
     * @return
     */
    public static Date getBeforeDateNoTime() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_SEQ_DATE);
        try {
            return dateFormat.parse(dateFormat.format(getFutureDay(-1)));
        } catch (ParseException e) {
            log.error("getBeforeDateNoTime error.", e);
        }
        return null;
    }

    /**
     * 获取系统日期的当天日期，返回Date 剔除时间
     *
     * @return
     */
    public static Date getTodDateNoTimeYYYYMMDD() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return dateFormat.parse(dateFormat.format(getFutureDay(0)));
        } catch (ParseException e) {
            log.error("getBeforeDateNoTime error.", e);
        }
        return null;
    }

    /**
     * 获取系统日期的前一天日期，返回Date 剔除时间
     *
     * @return
     */
    public static Date getBeforeDateNoTimeYYYYMMDD() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return dateFormat.parse(dateFormat.format(getFutureDay(-1)));
        } catch (ParseException e) {
            log.error("getBeforeDateNoTime error.", e);
        }
        return null;
    }

    /**
     * 获取系统日期的前两天日期，返回Date 剔除时间
     *
     * @return
     */
    public static Date getBeforeYesDateNoTimeYYYYMMDD() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return dateFormat.parse(dateFormat.format(getFutureDay(-2)));
        } catch (ParseException e) {
            log.error("getBeforeDateNoTime error.", e);
        }
        return null;
    }

    /**
     * 获取系统日期的后15天日期，返回Date 剔除时间
     *
     * @return
     */
    public static Date getDateTimeYYYYMMDD() {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return dateFormat.parse(dateFormat.format(getFutureDay(15)));
        } catch (ParseException e) {
            log.error("getBeforeDateNoTime error.", e);
        }
        return null;
    }

    /**
     * 根据日期字符串返回date(无时间)
     *
     * @param dateStr 日期字符串. 无时间
     * @return
     */
    public static Date getDateNoTime(String dateStr) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_SEQ_DATE);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据 指定字符 转换为指定字符格式的date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date getDateForStrAndSqe(String dateStr, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串转换为时间
     *
     * @param time 时间
     * @return
     * @throws ParseException
     */
    public static Date getStrToTime(String time) {
        try {
            return new SimpleDateFormat(DEFAULT_TIME).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将yyyyMMddHHmmss格式的字符串转换为时间
     *
     * @param time 时间
     * @return
     * @throws ParseException
     */
    public static Date getDefaultStrToTime(String time) {
        try {
            return new SimpleDateFormat(DEFAULT_SEQ_TIME).parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将yyyy-MM-dd格式的字符串转换为日期
     *
     * @param date 日期
     * @return
     * @throws ParseException
     */
    public static Date getStrToDate(String date) throws ParseException {
        return new SimpleDateFormat(DEFAULT_DATE).parse(date);
    }

    /**
     * 获取当前时间 用户打印默认的时间 格式:yyyy-MM-dd HH:mm:ss
     */
    public static String getNowTimeStr() {
        return new SimpleDateFormat(DEFAULT_TIME).format(getDate());
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss"的时间转换为字符串
     *
     * @param time
     * @return
     */
    public static String getTimeToStr(Date time) {
        return new SimpleDateFormat(DEFAULT_TIME).format(time);
    }

    /**
     * 将时间转换成"yyyy-MM-dd"字符串
     *
     * @param time
     * @return
     */
    public static String getDateToStr(Date time) {
        return new SimpleDateFormat(DEFAULT_DATE).format(time);
    }

    /**
     * 获取当前时间的 日期 剔除时间
     *
     * @return
     */
    public static String getDateNoTime() {
        return new SimpleDateFormat(DEFAULT_SEQ_DATE).format(getDate());
    }

    /**
     * 获取指定日期的时间
     *
     * @return
     */
    public static Date getDateNoTime(Date date) {
        String dateStr = new SimpleDateFormat(DEFAULT_DATE).format(date);
        try {
            date = DateUtil.getStrToDate(dateStr);
        } catch (ParseException e) {
            log.error("" + e);
        }
        return date;
    }

    /**
     * 获取当前时间的 日期 只有时间
     *
     * @return
     */
    public static String getDateOnlyTime() {
        return new SimpleDateFormat(DEFAULT_SEQ_ONLY_TIME).format(getDate());
    }


    /**
     * 获取当前时间,精确到秒 14位数字 格式:yyyyMMddHHmmss 可以用在 订单号
     *
     * @return
     */
    public static String getNowTimeSeqStr() {
        Date date = getDate();
        SimpleDateFormat dataFormat = new SimpleDateFormat(DEFAULT_SEQ_TIME);
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * 获取当前时间,精确到毫秒 17数字 格式:yyyyMMddHHmmssSSS 可以用在 订单号流水
     *
     * @return
     */
    public static String getNowTimeSSS() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(DEFAULT_SEQ_TIME_SSS);
        Date date = getDate();
        String timeString = dataFormat.format(date);
        return timeString;
    }

    /**
     * 返回今天开始时间 返回的时间格式为 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static Date getToday() {
        //TODO 方法名字有歧义，实际上是得到当天的开始时间
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDate());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    private static DateFormat getNewDateFormat(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        df.setLenient(false);
        return df;
    }

    /**
     * 计算当前时间几小时之后的时间
     *
     * @param date
     * @param hours
     * @return
     */
    public static Date getAddHoursDate(Date date, long hours) {
        return getAddMinutesDate(date, hours * 60);
    }

    /**
     * 计算当前时间几分钟之后的时间
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date getAddMinutesDate(Date date, long minutes) {
        return getAddSecondsDate(date, minutes * 60);
    }

    /**
     * @param date
     * @param secs
     * @return
     */

    public static Date getAddSecondsDate(Date date, long secs) {
        return new Date(date.getTime() + (secs * 1000));
    }

    /**
     * 根据date得到"yyyy-MM-dd HH:mm:ss"格式的时间
     *
     * @param date 指定时间
     * @return
     */
    public static String getNewFormatDateString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_TIME);
        return getDateString(date, dateFormat);
    }

    /**
     * 根据date得到"yyyy-MM-dd"格式的时间
     *
     * @param date 指定时间
     * @return
     */
    public static String getNewFormatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);
        return getDateString(date, dateFormat);
    }

    /**
     * 获取指定日期的格式化字符串
     *
     * @param date
     * @param seqDateTime
     * @return
     */
    public static String getDateStringSeq(Date date, String seqDateTime) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(seqDateTime).format(date);
    }

    /**
     * 根据传入的日期返回指定格式的日期
     *
     * @param date
     * @param seqDateTime
     * @return
     */
    public static Date getDateTimeForSeq(Date date, String seqDateTime) {
        if (date == null) {
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat(seqDateTime);
        String dateStr = dateFormat.format(date);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将指定的日期按照指定的格式进行转换
     *
     * @param date
     * @param dateFormat
     * @return
     */
    private static String getDateString(Date date, DateFormat dateFormat) {
        if (date == null || dateFormat == null) {
            return null;
        }
        return dateFormat.format(date);
    }

    /**
     * 取得“X年X月X日”的日期格式
     *
     * @param date
     * @return
     */
    public static String getChineseDateStr(Date date) {
        DateFormat dateFormat = getNewDateFormat(CHINESE_DATE);
        return getDateString(date, dateFormat);
    }

    /**
     * 获取yyyyMMddHHmmss
     *
     * @param date 指定日期
     * @return
     */
    public static String getSeqDateStr(Date date) {
        DateFormat dateFormat = getNewDateFormat(DEFAULT_SEQ_TIME);
        return getDateString(date, dateFormat);
    }

    /**
     * 取得两个日期间隔秒数（日期1-日期2）
     *
     * @param one
     * @param two
     * @return 间隔秒数
     */
    public static long getDiffSeconds(Date one, Date two) {
        Calendar oneDate = new GregorianCalendar();
        oneDate.setTime(one);
        Calendar twoDate = new GregorianCalendar();
        twoDate.setTime(two);
        return (oneDate.getTimeInMillis() - twoDate.getTimeInMillis()) / 1000;
    }

    /**
     * 得到两个时间相隔分钟数
     *
     * @param one
     * @param two
     * @return 间隔分钟数
     */
    public static long getDiffMinutes(Date one, Date two) {
        Calendar oneDate = new GregorianCalendar();
        oneDate.setTime(one);
        Calendar toDate = new GregorianCalendar();
        toDate.setTime(two);
        return (oneDate.getTimeInMillis() - toDate.getTimeInMillis()) / (60 * 1000);
    }

    /**
     * 取得两个日期的间隔天数, 即使昨天的23点与今天凌晨1点, 也是相差1天.
     *
     * @param before
     * @param after
     * @return 间隔天数
     */
    public static long getDiffDays2(Date before, Date after) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(before);
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(after);
        c2.set(Calendar.HOUR_OF_DAY, 0);
        c2.set(Calendar.MINUTE, 0);
        c2.set(Calendar.SECOND, 0);
        c2.set(Calendar.MILLISECOND, 0);
        return (c2.getTimeInMillis() - c1.getTimeInMillis()) / (24 * 60 * 60 * 1000);
    }

    /**
     * 获取当前时间之后（之前）的日期
     *
     * @param days 天数，可以为负数
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static Date getFutureDay(int days) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            Date date = getDate();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间之后（之前）的日期
     *
     * @param seconds
     * @return
     */
    public static Date getFutureDate(int seconds) {
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = getDate();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 获取某个日期的结束时间，如：23:59:59
     *
     * @param days 可用加减，为0表示今天
     * @return
     * @throws ParseException
     */
    public static Date getFutureDayEndTime(int days) throws ParseException {
        Date futureDate = getFutureDay(days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 23:59:59";
        return df.parse(targetDateStr);
    }

    /**
     * 获取某个日期的开始时间，如：00:00:00
     *
     * @param days 可用加减，为0表示今天
     * @return
     * @throws ParseException
     */
    public static Date getFutureDayStartTime(int days) throws ParseException {
        Date futureDate = getFutureDay(days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 00:00:00";
        return df.parse(targetDateStr);
    }

    /**
     * 获取指定时间之后（之前）的日期
     *
     * @param days    天数，可以为负数
     * @param fixDate 指定的日期
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static Date getFixDayFutureDay(Date fixDate, int days) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fixDate);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取指定月之后（之前）的日期
     *
     * @param fixDate 指定的日期
     * @param months  月数可以为负数
     * @return
     */
    public static Date getFixDayFutureMonth(Date fixDate, int months) {
        try {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(fixDate);
            calendar.add(Calendar.MONTH, months);
            return calendar.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取两个时间之间的月数  不足一月的舍去
     * e.g.	2.5 - 3.4	返回   0
     * 2.5 - 3.5	返回   1
     * 2.5 - 3.6	返回   1
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getMonthBetweenDown(Date startDate, Date endDate) {
        try {
            //结束时间早于开始时间  直接返回0
            if (endDate.getTime() <= startDate.getTime()) {
                return 0;
            }

            Calendar startCalendar = GregorianCalendar.getInstance();
            startCalendar.setTime(startDate);
            Calendar endCalendar = GregorianCalendar.getInstance();
            endCalendar.setTime(endDate);

            int startYear = startCalendar.get(Calendar.YEAR);
            int endYear = endCalendar.get(Calendar.YEAR);

            int startMonth = startCalendar.get(Calendar.MONTH);
            int endMonth = endCalendar.get(Calendar.MONTH);

            int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
            int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);

            int month = (endYear - startYear) * 12 + (endMonth - startMonth);

            if (endDay < startDay) {
                month = month - 1;
            }
            return month;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取两个时间范围内，最后一个月的实际天数
     * e.g.	1.5 - 8.4	返回   30     最后一个月的第一天为  7.5
     * e.g.	7.5 - 8.4	返回   30
     * 1.5 - 8.6	返回   1      最后一个月的第一天为  8.5
     * 7.5 - 8.6	返回   1
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static int getLastMonthDays(Date startDate, Date endDate) {
        try {
            Calendar startCalendar = GregorianCalendar.getInstance();
            Calendar endCalendar = GregorianCalendar.getInstance();
            startCalendar.setTime(startDate);
            endCalendar.setTime(endDate);

            int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
            int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
            if (endDay > startDay) {
                return endDay - startDay;
            } else {
                startCalendar.setTime(endDate);
                startCalendar.set(Calendar.DAY_OF_MONTH, startDay);
                startCalendar.add(Calendar.MONTH, -1);
            }
            return (int) DateUtil.getDiffDays2(startCalendar.getTime(), endDate);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0;
        }
    }

    /**
     * 获取某个日期的结束时间，如：23:59:59
     *
     * @param days    可用加减，为0表示今天
     * @param fixDate 指定的日期
     * @return
     * @throws ParseException
     */
    public static Date getFixDayFutureDayEndTime(Date fixDate, int days) throws ParseException {
        Date futureDate = getFixDayFutureDay(fixDate, days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 23:59:59";
        return df.parse(targetDateStr);
    }

    /**
     * 获取某个日期的开始时间，如：00:00:00
     *
     * @param fixDate 指定的日期
     * @param days    可用加减，为0表示今天
     * @return
     * @throws ParseException
     */
    public static Date getFixDayFutureDayStartTime(Date fixDate, int days) throws ParseException {
        Date futureDate = getFixDayFutureDay(fixDate, days);
        DateFormat df = new SimpleDateFormat(DEFAULT_TIME);
        String targetDateStr = df.format(futureDate).substring(0, 10) + " 00:00:00";
        return df.parse(targetDateStr);
    }

    /**
     * 判断当前时间，是否在起始时间和结束时间之间，可以精确到秒(不校验等于)
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return
     */
    public static boolean betweenStartAndEndDate(Date fixDate, Date startDate, Date endDate) {
        if (null == fixDate) {
            fixDate = getDate();
        }
        return fixDate.before(endDate) && fixDate.after(startDate);
    }

    /**
     * 判断当前时间，是否在起始时间和结束时间之间，可以精确到秒（校验等于）
     *
     * @param startDate 起始时间
     * @param endDate   结束时间
     * @return
     */
    public static boolean betweenStartAndEndDateTow(Date fixDate, Date startDate, Date endDate) {
        if (null == fixDate) {
            fixDate = getDate();
        }
        return !fixDate.before(startDate) && !fixDate.after(endDate);
    }


    /**
     * 获取指定日期是周几
     *
     * @param date
     * @return 周几
     */
    public static String getWeekByDate(Date date) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * 获取当前时间之后（之前）的日期取出日期
     *
     * @param days 天数，可以为负数
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static String getFutureOnLyDay(int days) {
        SimpleDateFormat dataFormat = new SimpleDateFormat(DEFAULT_SEQ_DATE);
        return dataFormat.format(getFutureDay(days));
    }

    /**
     * 判断两个日期是否同一天
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return
     */
    public static boolean isSameDate(Date date1, Date date2) {
        String date1Str = getDateToStr(date1);
        String date2Str = getDateToStr(date2);
        return date1Str.trim().equals(date2Str.trim());
    }

    /**
     * 判断从某个时间到当前的耗时(单位：ms)
     *
     * @param endDate
     * @return
     */
    public static long getCostTimeMillis(Date endDate) {
        long costMillis = 0L;
        Date startDate = getDate();
        costMillis = endDate.getTime() - startDate.getTime();
        return costMillis;
    }

    /**
     * 生产批次号的时间，以小时为单位
     */
    public static String getBatchNoTime() {
        DateFormat df = new SimpleDateFormat(DEFAULT_BATCH_NO_TIME);
        return df.format(getDate());
    }

    /**
     * 根据给定模式生成序列
     *
     * @param pattern
     * @return
     */
    public static String getSequenceByPattern(String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(getDate());
    }

    /**
     * 根据开始日期和结束日期计算相差的天数
     * 不依赖时分秒
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long calSubDaysWithoutHour(Date startDate, Date endDate) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = sdf2.parse(sdf1.format(startDate));
        Date date2 = sdf2.parse(sdf1.format(endDate));
        return (date2.getTime() - date1.getTime()) / (1000 * 3600 * 24);
    }

    /**
     * 将时间转换成"yyyyMMdd"字符串
     *
     * @param time
     * @return
     */
    public static String getDateToAnotherStr(Date time) {
        return new SimpleDateFormat(DEFAULT_SEQ_DATE).format(time);
    }

    /**
     * Unix时间戳格式：1489000690(10)位
     *
     * @return
     */
    public static Long getTimestamp() {
        return getDate().getTime() / 1000L;
    }

    /**
     * 根据日期增加/减少指定的天数
     *
     * @param date 原始日期
     * @param days 增加/减少的天数
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 将时间戳转换为时间
     *
     * @param s
     * @return
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 根据月份获取当月第一天
     *
     * @param Month
     * @return
     */
/*    public static String getFirstDayByMonth(String Month) {
        if (StringUtils.isNotBlank(Month)) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            Date parse = null;
            try {
                parse = sdf1.parse(Month);
                Calendar cal = Calendar.getInstance();
                cal.setTime(parse);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, firstDay);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String firstDayOfMonth = sdf.format(cal.getTime());
                return firstDayOfMonth;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }*/

    /**
     * 根据月份获取当月最后一天
     *
     * @param Month
     * @return
     */
    public static String getLastDayByMonth(String Month) {
        if (StringUtils.isNotBlank(Month)) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
            Date parse = null;
            try {
                parse = sdf1.parse(Month);
                Calendar cal = Calendar.getInstance();
                cal.setTime(parse);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                cal.set(Calendar.DAY_OF_MONTH, lastDay);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String lastDayOfMonth = sdf.format(cal.getTime());
                return lastDayOfMonth;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 获取当前是第几月
     *
     * @param
     */
    public static Integer getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取当前是哪年第几月
     *
     * @param
     */
    public static String getYearAndMonth() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        StringBuffer sb = new StringBuffer();
        StringBuffer append = sb.append(year + "-" + month);
        return append.toString();
    }

    /**
     * 获取这周一的日期
     *
     * @param date
     * @return
     */
    public static String getThisWeekMonday(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return sdf.format(cal.getTime());
    }

    /**
     * 获取这周日的日期
     *
     * @param date
     * @return
     */
    public static String getThisWeekSunday(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (dayWeek == 1) {
            dayWeek = 8;
        }

        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - dayWeek);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        Date mondayDate = cal.getTime();
        String weekBegin = sdf.format(mondayDate);

        cal.add(Calendar.DATE, 4 + cal.getFirstDayOfWeek());
        Date sundayDate = cal.getTime();
        return sdf.format(sundayDate);
    }

    /**
     * 获得指定时间当天起点时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        df.setLenient(false);

        String dateString = df.format(date);

        try {
            return df.parse(dateString);
        } catch (ParseException e) {
            return date;
        }
    }
    /**
     * @return 与当前时间比较大小
     *
     */
    public static Boolean gainBigOrSmall(String date) {
        boolean bool = false;
        try {
            SimpleDateFormat df = new SimpleDateFormat(DEFAULT_TIME);
            if (df.parse(date).before(df.parse(df.format(new Date())))){
                bool = true;
            }
        }catch (ParseException e){
            return null;
        }
        return bool;
    }
/*    public static Date getDayEnd(Date date) {
        String dateString = format(date, "yyyy-MM-dd 23:59:59");
        return parseDateNewFormat(dateString);
    }*/

    /**
     * @return 两个时间比较大小
     *
     */
    public static Boolean bigOrSmall(Date beginDate,Date endDate) {
        int compareTo = beginDate.compareTo(endDate);
        if (compareTo == 1){
            return true;
        }
        return false;
    }

    public static Date parseDateNewFormat(String sDate) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_TIME);
        Date d = null;
        dateFormat.setLenient(false);
        if ((sDate != null) && (sDate.length() == DEFAULT_TIME.length())) {
            try {
                d = dateFormat.parse(sDate);
            } catch (ParseException ex) {
                return null;
            }
        }
        return d;
    }

    /**
     * @return 当天的时间格式化为"yyyyMMdd"
     */
    public static String getDateString(Date date) {
        DateFormat df = getNewDateFormat(DEFAULT_SEQ_DATE);
        return df.format(date);
    }

    /**
     * long时间戳转String类型日期
     *
     * @return 当天的时间格式化为"yyyy-MM-dd HH:mm:ss"
     */
    public static String getDateString(String time) {
        return new SimpleDateFormat(DEFAULT_TIME).format(new Date(Long.parseLong(time)));
    }

    public static void main(String[] args) {
        System.out.println(getDay(new Date(), 10));
    }

    /**
     * 获取传入时间之后（之前）的日期
     * @param days data 时间
     * @param days 天数，可以为负数
     * @return 指定日期格式的日期增加指定天数的日期
     */
    public static Date getDay(Date data ,int days) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE);

            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.DAY_OF_MONTH, days);
            return dateFormat.parse(dateFormat.format(calendar.getTime()));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime,
                                         Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

}
