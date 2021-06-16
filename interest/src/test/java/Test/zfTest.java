package Test;

import com.example.springboot.config.Shiro.security.SpringUtils;
import com.example.springboot.utils.AESEncryptUtil;
import com.example.springboot.utils.Md5Util;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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


    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    @Test
    public void Test2() {
        try {
            List<String> records = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                records.add("xiancheng :" + i);
            }
            CountDownLatch latch = new CountDownLatch(records.size());
            for (String record : records) {
                executor.execute(() -> {
                    try {
                        if (record.equals("xiancheng :5")) {
                            System.out.println("sleep--------");
                            Thread.sleep(2000);
                        }
                        System.out.println(record);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
