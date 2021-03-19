package study.java提高篇.四舍五入;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @ClassName Test
 * @Author zhaofu
 * @Date 2020/7/24
 * @Version V1.0
 **/
public class Test {

    public static void main(String[] args) {
        System.out.println("12.5的四舍五入值：" + Math.round(12.5));
        System.out.println("-12.5的四舍五入值：" + Math.round(-12.5));

//    Output:
//            12.5的四舍五入值：13
//            -12.5的四舍五入值：-12


//保留位
//方式一：四舍五入

        double f = 111231.5585;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, RoundingMode.HALF_UP).doubleValue();
        System.out.println(f1);
//在这里使用 BigDecimal ，并且采用 setScale 方法来设置精确度，同时使用 RoundingMode.HALF_UP 表示使用最近数字舍入法则来近似计算。
// 在这里我们可以看出 BigDecimal 和四舍五入是绝妙的搭配。
//
//方式二：
//
        DecimalFormat df = new DecimalFormat("#.00");
//    df.format(你要格式化的数字);
//例：
        String format = new DecimalFormat("#.00").format(3.1415926);
        System.out.println(format);
//
//.00 表示两位小数 #.0000四位小数 以此类推…

//方式三：

        double d = 3.1415926;

        String result = String.format("%.2f");
        System.out.println(result);
//    %.2f %. 表示 小数点前任意位数   2 表示两位小数 格式后的结果为f 表示浮点型。

//方式四：
//此外如果使用 struts 标签做输出的话，有个 format 属性,设置为 format=”0.00″就是保留两位小数
//
//例如：
//
//
//    <bean:write name="entity" property="dkhAFSumPl"  format="0.00" />
//
//    或者
//
//    <fmt:formatNumber type="number" value="${10000.22/100}" maxFractionDigits="0"/>
//
//    maxFractionDigits表示保留的位数
    }

}

//这是四舍五入的经典案例，从这儿结果中我们发现这两个绝对值相同的数字，为何近似值会不同呢？其实这与 Math.round 采用的四舍五入规则来决定。
//
//四舍五入其实在金融方面运用的非常多，尤其是银行的利息。
// 我们都知道银行的盈利渠道主要是利息差，它从储户手里收集资金，然后放贷出去，期间产生的利息差就是银行所获得的利润。
// 如果我们采用平常四舍五入的规则话，这里采用每 10 笔存款利息计算作为模型，如下：
//
//四舍：0.000、0.001、0.002、0.003、0.004。这些舍的都是银行赚的钱。
//
//五入：0.005、0.006、0.007、0.008、0.009。这些入的都是银行亏的钱，分别为：0.005、0.004、.003、0.002、0.001。
//
//所以对于银行来说它的盈利应该是 0.000 + 0.001 + 0.002 + 0.003 + 0.004 – 0.005 – 0.004 – 0.003 – 0.002 – 0.001 = -0.005。
// 从结果中可以看出每 10 笔的利息银行可能就会损失 0.005 元，千万别小看这个数字，这对于银行来说就是一笔非常大的损失。
// 面对这个问题就产生了如下的银行家涉入法了。
// 该算法是由美国银行家提出了，主要用于修正采用上面四舍五入规则而产生的误差。如下：
//
//舍去位的数值小于 5 时，直接舍去。
//
//舍去位的数值大于 5 时，进位后舍去。
//
//当舍去位的数值等于 5 时，若 5 后面还有其他非 0 数值，则进位后舍去，若 5 后面是 0 时，则根据5前一位数的奇偶性来判断，奇数进位，偶数舍去。
//
//对于上面的规则我们举例说明
//
//11.556 = 11.56 ——六入
//
//11.554 = 11.55 —–四舍
//
//11.5551 = 11.56 —–五后有数进位
//
//11.545 = 11.54 —–五后无数，若前位为偶数应舍去
//
//11.555 = 11.56 —–五后无数，若前位为奇数应进位
//
//下面实例是使用银行家舍入法：
//
//
//    public static void main(String[] args) {
//            BigDecimal d = new BigDecimal(100000);      //存款
//            BigDecimal r = new BigDecimal(0.001875*3);   //利息
//            BigDecimal i = d.multiply(r).setScale(2,RoundingMode.HALF_EVEN);     //使用银行家算法
//
//            System.out.println("季利息是："+i);
//            }
//    Output:
//    季利息是：562.50


//在上面简单地介绍了银行家舍入法，目前 Java 支持7中舍入法：
//
//1、 ROUND_UP：远离零方向舍入。向绝对值最大的方向舍入，只要舍弃位非0即进位。
//
//2、 ROUND_DOWN：趋向零方向舍入。向绝对值最小的方向输入，所有的位都要舍弃，不存在进位情况。
//
//3、 ROUND_CEILING：向正无穷方向舍入。向正最大方向靠拢。若是正数，舍入行为类似于 ROUND_UP，若为负数，舍入行为类似于 ROUND_DOWN。 Math.round() 方法就是使用的此模式。
//
//4、 ROUND_FLOOR：向负无穷方向舍入。向负无穷方向靠拢。若是正数，舍入行为类似于 ROUND_DOWN；若为负数，舍入行为类似于 ROUND_UP。
//
//5、 HALF_UP：最近数字舍入(5进)。这是我们最经典的四舍五入。
//
//6、 HALF_DOWN：最近数字舍入(5舍)。在这里5是要舍弃的。
//
//7、 HAIL_EVEN：银行家舍入法。
//
//提到四舍五入那么保留位就必不可少了，在 Java 运算中我们可以使用多种方式来实现保留位。
//
//保留位
//方式一：四舍五入
//
//    double   f   =   111231.5585;
//    BigDecimal   b   =   new   BigDecimal(f);
//    double   f1   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();
//在这里使用 BigDecimal ，并且采用 setScale 方法来设置精确度，同时使用 RoundingMode.HALF_UP 表示使用最近数字舍入法则来近似计算。在这里我们可以看出 BigDecimal 和四舍五入是绝妙的搭配。
//
//方式二：
//
//    java.text.DecimalFormat   df   =new   java.text.DecimalFormat(”#.00″);
//    df.format(你要格式化的数字);
//例：new java.text.DecimalFormat(”#.00″).format(3.1415926)
//
//.00 表示两位小数 #.0000四位小数 以此类推…
//方式三：
//
//    double d = 3.1415926;
//
//    String result = String .format(”%.2f”);
//
//    %.2f %. 表示 小数点前任意位数   2 表示两位小数 格式后的结果为f 表示浮点型。
//方式四：
//此外如果使用 struts 标签做输出的话，有个 format 属性,设置为 format=”0.00″就是保留两位小数
//
//例如：
//
//
//    <bean:write name="entity" property="dkhAFSumPl"  format="0.00" />
//
//    或者
//
//    <fmt:formatNumber type="number" value="${10000.22/100}" maxFractionDigits="0"/>
//
//    maxFractionDigits表示保留的位数
