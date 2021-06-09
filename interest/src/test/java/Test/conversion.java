package Test;

/**
 * @ClassName conversion
 * @Author zhaofu
 * @Date 2021/6/9
 * @Version V1.0
 **/
public class conversion {
    public static void main(String[] args) {
        int num = 5;
        String str = Integer.toString(num); // 将数字转换成字符串
        String str1 = Integer.toBinaryString(num); // 将数字转换成二进制
        String str2 = Integer.toHexString(num); // 将数字转换成八进制
        String str3 = Integer.toOctalString(num); // 将数字转换成十六进制
        System.out.println(str + "的二进制数是：" + str1);
        System.out.println(str + "的八进制数是：" + str3);
        System.out.println(str + "的十进制数是：" + str);
        System.out.println(str + "的十六进制数是：" + str2);
    }
}
