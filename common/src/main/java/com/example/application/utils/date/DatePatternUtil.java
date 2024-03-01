package com.example.application.utils.date;

import lombok.AllArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期正则匹配工具类
 *
 * @author 许畅
 * @version 2019年5月15日 许畅 新建
 * @since JDK1.7
 */
public class DatePatternUtil {

    private static final Pattern PATTERN_IS_NUMBER = Pattern.compile("[-]?\\d+");

    /**
     * 构造方法
     */
    private DatePatternUtil() {
    }

    @AllArgsConstructor
    public static class FormatPattern {
        String format;
        String pattern;
    }

    public static FormatPattern FORMAT_PATTERN_1 = new FormatPattern("yyyy", "\\d{4}");
    public static FormatPattern FORMAT_PATTERN_2 = new FormatPattern("yyyy-MM", "\\d{4}-\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_3 = new FormatPattern("yyyy-MM-dd", "(\\d{4}\\-\\d{1,2}\\-\\d{1,2})");
    public static FormatPattern FORMAT_PATTERN_4 = new FormatPattern("yyyy-MM-dd HH:mm", "(\\d{4}\\-\\d{1,2}\\-\\d{1,2}\\s\\d{1,2}:\\d{1,2})");
    public static FormatPattern FORMAT_PATTERN_5 = new FormatPattern("yyyy-MM-dd HH:mm:ss", "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_6 = new FormatPattern("yyyy-MM-dd HH:mm:ss.SSS", "\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d+");
    public static FormatPattern FORMAT_PATTERN_7 = new FormatPattern("yyyy/MM/dd", "\\d{4}/\\d{1,2}/\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_8 = new FormatPattern("EEE MMM dd yyyy HH:mm:ss 'GMT+0800'", "\\w{3}\\s\\w{3}\\s\\d{1,2}\\s\\d{4}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}\\sGMT\\+0800");
    public static FormatPattern FORMAT_PATTERN_9 = new FormatPattern("yyyyMM", "\\d{4}\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_10 = new FormatPattern("yyyyMMdd", "(\\d{4}\\d{1,2}\\d{1,2})");
    public static FormatPattern FORMAT_PATTERN_11 = new FormatPattern("yyyy年MM月dd日", "(\\d{4}\\年\\d{1,2}\\月\\d{1,2}\\日)");
    public static FormatPattern FORMAT_PATTERN_12 = new FormatPattern("yyyy年MM月", "\\d{4}年\\d{1,2}\\月");
    public static FormatPattern FORMAT_PATTERN_13 = new FormatPattern("yyyy年", "\\d{4}年");
    public static FormatPattern FORMAT_PATTERN_14 = new FormatPattern("yyyy年", "\\d{2}年");
    public static FormatPattern FORMAT_PATTERN_15 = new FormatPattern("yy年", "\\d{2}年\\d{1,2}\\月");
    public static FormatPattern FORMAT_PATTERN_16 = new FormatPattern("yyyy/MM/dd HH:mm", "\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_17 = new FormatPattern("yyyy/MM/dd HH:mm:ss", "\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
    public static FormatPattern FORMAT_PATTERN_18 = new FormatPattern("yyyy/MM/dd HH:mm:ss.SSS", "\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}.\\d+");

    private static final List<FormatPattern> defaultFormatPatterns = new ArrayList<FormatPattern>() {{
        add(FORMAT_PATTERN_1);
        add(FORMAT_PATTERN_2);
        add(FORMAT_PATTERN_3);
        add(FORMAT_PATTERN_4);
        add(FORMAT_PATTERN_5);
        add(FORMAT_PATTERN_6);
        add(FORMAT_PATTERN_7);
        add(FORMAT_PATTERN_8);
        add(FORMAT_PATTERN_9);
        add(FORMAT_PATTERN_10);
        add(FORMAT_PATTERN_11);
        add(FORMAT_PATTERN_12);
        add(FORMAT_PATTERN_13);
        add(FORMAT_PATTERN_14);
        add(FORMAT_PATTERN_15);
        add(FORMAT_PATTERN_16);
        add(FORMAT_PATTERN_17);
        add(FORMAT_PATTERN_18);
    }};
    /**
     * 获取需要反序列化为正确格式的日期
     *
     * @param strDateValue 字符串类型的日期值
     * @return Date
     */
    public static Date parse(String strDateValue) {
        return parse(strDateValue, defaultFormatPatterns);
    }

    /**
     * 获取需要反序列化为正确格式的日期
     *
     * @param strDateValue 字符串类型的日期值
     * @return Date
     */
    public static Date parse(String strDateValue, List<FormatPattern> formatPatterns) {
        String value = strDateValue;
        if (value == null || "".equals(value.trim()) || "null".equalsIgnoreCase(value.trim())) {
            return null;
        }
        // 解决字符串被自动转码导致的问题，在此将转码后的字符串还原。
        if (value.indexOf('%') >= 0) {
            try {
                value = URLDecoder.decode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                //
            }
        }

        String format = getMatchFormat(value, formatPatterns);
        if (format == null) {
            // 如果以上8种时间格式都无法匹配，校验是否是时间戳格式，如果是就直接转换为Date，否则直接抛出异常
            Matcher matcher = PATTERN_IS_NUMBER.matcher(value);
            boolean isMatch = matcher.matches();
            if (isMatch) {
                return new Date(Long.valueOf(value));
            }
            throw new IllegalArgumentException("不支持的时间格式:" + value);
        }

        if (format.indexOf("GMT") > 0) {
            SimpleDateFormat objSimpleFormat = new SimpleDateFormat(format, Locale.US);
            try {
                return objSimpleFormat.parse(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException("不支持的时间格式:" + value);
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("不支持的时间格式:" + value);
        }
    }

    public static String getMatchFormat(final String value) {
        return getMatchFormat(value, defaultFormatPatterns);
    }
    /**
     * 根据值获取合适的格式
     *
     * @param value 数据
     * @return 格式
     */
    public static String getMatchFormat(final String value, List<FormatPattern> formatPatterns) {
        for (FormatPattern formatPattern : formatPatterns) {
            Pattern pattern = Pattern.compile(formatPattern.pattern);
            Matcher matcher = pattern.matcher(value);
            boolean isMatch = matcher.matches();
            if (isMatch) {
                return formatPattern.format;
            }
        }
        return null;
    }

}
    
