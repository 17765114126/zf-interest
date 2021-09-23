package Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.model.Student;
import com.example.springboot.utils.AESEncryptUtil;
import com.example.springboot.utils.HttpClientUtil;
import com.example.springboot.utils.Md5Util;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

/**
 * @ClassName zfTest
 * @Author zhaofu
 * @Date 2019/9/9
 * @Version V1.0
 **/
public class zfTest {

    public static void main(String[] args) {
        //每个类加载都有一个父类加载器
        ClassLoader classLoader = zfTest.class.getClassLoader();
        System.out.println("1ClassLoader :" + classLoader);
        ClassLoader parent = zfTest.class.getClassLoader().getParent();
        System.out.println("2Parent :" + parent);
        ClassLoader parent1 = zfTest.class.getClassLoader().getParent().getParent();
        System.out.println("3Parent tow :" + parent1);
//        AppClassLoader的父类加载器为ExtClassLoader
//         ExtClassLoader的父类加载器为null，
//         null并不代表ExtClassLoader没有父类加载器，
//         而是 BootstrapClassLoader 。
    }

    public static String aesEncrypt() {
        String key = "zhaofu";
        String md5key = Md5Util.encode(key).toUpperCase();
        return md5key;
    }

    public static String getEncrypt(String token) {
        String aesToken = AESEncryptUtil.aesEncrypt(token, aesEncrypt());
        return aesToken;
    }

    public static String aesDecrypt(String token) {
        String AESToken = AESEncryptUtil.aesDecrypt(token, aesEncrypt());
        return AESToken;
    }

    /**
     * 加密
     */
    @Test
    public void Test() {
        String str = "$@$tz1@@7ndq8jypnifo";
        String salt = "C86nfc";
        SimpleHash md5 = new SimpleHash("md5", str, salt, 22);
        System.out.println(md5);
    }

    @Test
    public void Test1() throws Throwable {
        System.out.println(SpringUtils.testEnv());
        throw new Exception("dcj");
    }

    /**
     * 十进制转二进制
     *
     * 原理：给定的数循环除以2，直到商为0或者1为止。将每一步除的结果的余数记录下来，然后反过来就得到相应的二进制了。
     * 比如8转二进制，第一次除以2等于4（余数0），第二次除以2等于2（余数0），第三次除以2等于1（余数0），最后余数1，得到的余数依次是 0 0 0 1 ，
     * 反过来就是1000，计算机内部表示数的字节长度是固定的，比如8位，16位，32位。所以在高位补齐，java中字节码是8位的，所以高位补齐就是00001000.
     * */
    @Test
    public void Test2() {
        System.out.println(toBinary(1));
        System.out.println(toBinary(2));
        System.out.println(toBinary(3));
        System.out.println(toBinary(4));
        System.out.println(toBinary(5));
        System.out.println(toBinary(6));
        System.out.println(toBinary(7));
        System.out.println(toBinary(8));
        System.out.println(toBinary(9));
        System.out.println(toBinary(10));

        /**
         * 二进制转十进制
         *
         * 计算也很简单，比如8的二进制表示位00001000，去掉补齐的高位就是1000.
         * 此时从个位开始计算2的幂（个位是0，依次往后推）乘以对应位数上的数，然后得到的值相加
         * 于是有了，（2的0次幂）0+（2的1次幂）0+（2的2次幂）0+（2的3次幂）1 = 8
         * */
        System.out.println(Integer.parseInt("1000",2)); //运行结果：8

    }

    static String toBinary(int num) {
        String str = "";
        while (num != 0) {
            str = num % 2 + str;
            num = num / 2;
        }
        return str;
    }

    /**
     * 远程服务调用
     */
    @Test
    public void Test3() {
        Student student = new Student();
        student.setAge(1);
        student.setName("fdsc");
        String dataJson = HttpClientUtil.sendPostByJson("http://localhost:7001/a", JSON.toJSONString(student), 3);
        System.out.println(dataJson);
    }

}
