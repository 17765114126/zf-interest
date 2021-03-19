package com.example.springboot.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * @author qinzz
 * @ClassName: MathUtil
 * @Description: BigDecimal加减乘除运算
 * <p>
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 * @date 2016年7月29日
 * @time 上午10:24:56
 */
public class MathUtil { //默认除法运算精度  
    private static final int DEF_DIV_SCALE = 10; // 这个类不能实例化

    private MathUtil() {
    }

    /**
     * 提供精确的加法运算。
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算。
     *
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static Float subFloat(Float v1, Float v2) {
        BigDecimal f1 = new BigDecimal(Float.toString(v1));
        BigDecimal f2 = new BigDecimal(Float.toString(v2));
        return f1.subtract(f2).floatValue();
    }

    /**
     * 提供精确的乘法运算。
     *
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    /**
     * 只取整数
     */
    public static double toInteger(Double v1) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal bigDecimal = b1.setScale(0, BigDecimal.ROUND_DOWN);
        return Double.parseDouble(bigDecimal.toString());
    }
    /**
     * 根据角，分，毫位四舍五入。并保留两位小数
     *
     * @param money
     * @return
     */
    public static String returnMoney(double money) {
        if (money > 0 && money < 100) {
            money = MathUtil.round(money, 2);//毫位
        } else if (money >= 100 && money < 1000) {
            money = MathUtil.round(money, 1);//分位
        } else if (money > 1000) {
            money = MathUtil.round(money, 0);//角位
        }
        DecimalFormat df = new DecimalFormat("##0.00");
        return df.format(money);
    }

    public static float returnFpSeries(float xyjTemp, int fpSeries, int series) {
        float disPrice = 0f;
        if (series == 24) {//24系
            if (fpSeries == 17) {
                disPrice = xyjTemp / 0.76f;
            } else if (fpSeries == 11) {
                disPrice = xyjTemp / 0.76f;
            } else if (fpSeries == 6) {
                disPrice = xyjTemp / 0.76f;
            } else if (fpSeries == 3) {
                disPrice = xyjTemp / 0.76f;
            } else if (fpSeries == 1) {//普票
                disPrice = xyjTemp / 0.54f;
            } else if (fpSeries == -1) {//无票
                disPrice = xyjTemp / 0.54f;
            }
        } else if (series == 12) {//12系
            if (fpSeries == 17) {
                disPrice = xyjTemp / 0.88f;
            } else if (fpSeries == 11) {
                disPrice = xyjTemp / 0.88f;
            } else if (fpSeries == 6) {
                disPrice = xyjTemp / 0.88f;
            } else if (fpSeries == 3) {
                disPrice = xyjTemp / 0.88f;
            } else if (fpSeries == 1) {//普票
                disPrice = xyjTemp / 0.61f;
            } else if (fpSeries == -1) {//无票
                disPrice = xyjTemp / 0.61f;
            }
        } else if (series == 6) {//12系
            if (fpSeries == 17) {
                disPrice = xyjTemp / 0.94f;
            } else if (fpSeries == 11) {
                disPrice = xyjTemp / 0.94f;
            } else if (fpSeries == 6) {
                disPrice = xyjTemp / 0.94f;
            } else if (fpSeries == 3) {
                disPrice = xyjTemp / 0.94f;
            } else if (fpSeries == 1) {//普票
                disPrice = xyjTemp / 0.65f;
            } else if (fpSeries == -1) {//无票
                disPrice = xyjTemp / 0.65f;
            }
        }
        return disPrice;
    }

    public static String num() {
        String val = "";
        Random random = new Random();
        for (int b = 0; b < 12; b++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                //int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                int temp = 65;//大写字母
                val += (char) (random.nextInt(26) + temp);
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(9) + 1);
            }
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if (pattern.matcher(val).matches()) {
            //如果都为数字，则替换一位为字母
            val = val.substring(0, 11) + "A";
        } else {
            if (val.matches("[a-zA-Z]+")) {
                val = val.substring(0, 11) + "1";
            }
        }
        return val;
    }

    /**
     * @param length 生成长度
     * @param temp   1：大写字母加数字  2：小写字母加数字   3：大小写字母加数字
     * @throws
     * @Title: createNum
     * @Description: 生成大写加数字字符串或小写字母加数字字符串以及大小写混合数字字符串
     */
    public static String createNum(int length, int temp) {
        String val = "";
        Random random = new Random();
        for (int b = 0; b < length; b++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if ("char".equalsIgnoreCase(charOrNum)) {
                int letter = 0;
                if (temp == 1 || temp == 2) {
                    letter = temp % 2 == 0 ? 65 : 97;
                } else {
                    letter = random.nextInt(2) % 2 == 0 ? 65 : 97;
                }
                //输出是大写字母还是小写字母
                int lt = random.nextInt(26) + letter;

                if (lt == 108 || lt == 111 || lt == 73 || lt == 79) {
                    lt += 1;
                }
                val += (char) lt;
            } else if ("num".equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(9) + 1);
            }
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        if (pattern.matcher(val).matches()) {
            //如果都为数字，则替换一位为字母
            if (temp == 1) {
                val = val.substring(0, length - 1) + "a";
            } else if (temp == 2) {
                val = val.substring(0, length - 1) + "A";
            } else {
                val = val.substring(0, length - 1) + "a";
            }
        } else {
            if (val.matches("[a-zA-Z]+")) {
                val = val.substring(0, length - 1) + "9";
            }
        }
        return val;
    }


    /**
     * 提供精确的加法运算。
     *
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static Float addFloat(Float v1, Float v2) {
        BigDecimal b1 = new BigDecimal(Float.toString(v1));
        BigDecimal b2 = new BigDecimal(Float.toString(v2));
        return b1.add(b2).floatValue();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            System.out.println(createNum(6, 3));
        }
    }






//        BigDecimal.setScale()方法用于格式化小数点
//        setScale(1)表示保留一位小数，默认用四舍五入方式
//        setScale(1,BigDecimal.ROUND_DOWN)直接删除多余的小数位，如2.35会变成2.3
//        setScale(1,BigDecimal.ROUND_UP)进位处理，2.35变成2.4
//        setScale(1,BigDecimal.ROUND_HALF_UP)四舍五入，2.35变成2.4
//
//        setScaler(1,BigDecimal.ROUND_HALF_DOWN)四舍五入，2.35变成2.3，如果是5则向下舍
//
//        setScaler(1,BigDecimal.ROUND_CEILING)接近正无穷大的舍入
//
//        setScaler(1,BigDecimal.ROUND_FLOOR)接近负无穷大的舍入，数字>0和ROUND_UP作用一样，数字<0和ROUND_DOWN作用一样
//
//        setScaler(1,BigDecimal.ROUND_HALF_EVEN)向最接近的数字舍入，如果与两个相邻数字的距离相等，则向相邻的偶数舍入。



    /**
     *@Description: float * int ，默认保留2位小数
     * @param a
     * @param b
     */
    public static Float multiply(Float a, Integer b) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return multiply(a, b.floatValue(),2);
    }

    /**
     *@Description: int * float ，默认保留2位小数
     * @param a
     * @param b
     */
    public static Float multiply(Integer a, Float b) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return multiply(a.floatValue(), b,2);
    }

    /**
     *@Description: float * float ，默认保留2位小数
     * @param a
     * @param b
     */
    public static Float multiply(Float a, Float b) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return multiply(a, b,2);
    }

    /**
     *@Description: float * float
     * @param a
     * @param b
     * @param number 小数点后保留位数
     */
    public static Float multiply(Float a, Float b, Integer number) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return new BigDecimal(a).multiply(new BigDecimal(b)).setScale(number, BigDecimal.ROUND_HALF_UP).floatValue();

    }

    public static Float divide(Float a, Integer b) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return divide(a, b.floatValue(),2);
    }

    /**
     *@Description: float / float
     * @param a
     * @param b
     * @param number 小数点后保留位数
     */
    public static Float divide(Float a, Float b, Integer number) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return new BigDecimal(a).divide(new BigDecimal(b), number, BigDecimal.ROUND_HALF_UP).floatValue();

    }


    /**
     *@Description: float add Float ，默认保留2位小数
     * @param a
     * @param b
     */
    public static Float add(Float a, Float b) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return add(a, b,2);
    }

    /**
     *@Description: float * float
     * @param a
     * @param b
     * @param number 小数点后保留位数
     */
    public static Float add(Float a, Float b, Integer number) {
        if (a == null || b == null) {
            throw new RuntimeException("传入数字不允许为空");
        }
        return new BigDecimal(a).add(new BigDecimal(b)).setScale(number,BigDecimal.ROUND_HALF_UP).floatValue();

    }

}
