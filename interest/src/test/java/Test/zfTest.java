package Test;

import com.alibaba.fastjson.JSON;
import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.model.Student;
import com.example.springboot.utils.ID.SnowIdUtils;
import com.example.springboot.utils.sign.AESEncryptUtil;
import com.example.springboot.utils.HttpClientUtil;
import com.example.springboot.utils.sign.Md5Util;
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
