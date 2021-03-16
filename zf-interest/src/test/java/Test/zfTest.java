package Test;

import com.example.springboot.utils.AESEncryptUtil;
import com.example.springboot.utils.Md5Util;
import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName zfTest
 * @Author zhaofu
 * @Date 2019/9/9
 * @Version V1.0
 **/
public class zfTest {
    @Test
    public void test() {
        int ceil = (int) Math.ceil(26 / 20) + 1;
        List<Object> objects = new ArrayList<>();

        System.out.println(ceil);
        for (int i = 0; i < ceil; i++) {
            for (int j = 0; j <= 20; j++) {
                if (i == ceil - 1 && j == 26 - (i * 20)) break;
                if (i == 1) {
                    System.out.println(i + "---" + j);
                } else {
                    System.out.println(i + "---" + j);
                }
            }
            int x = 3;
            System.out.println("====================");
        }
    }

    //生成随机数字
    @Deprecated
    public static String getIsvNo(String prefix) {
        long time = System.currentTimeMillis();
        int random = new Random().nextInt(100000);
        System.out.println(random);
        return prefix + time + random;
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


    public static void main(String[] args) {
        getIsvNo("zhaofu");


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
        String prefix = "123";
        String isvNo = getIsvNo(prefix);
        System.out.println(isvNo);
    }

    /**
     * 判断文件大小
     *
     * @param :multipartFile:上传的文件
     * @param size:                限制大小
     * @param unit:限制单位（B,K,M,G)
     * @return boolean:是否大于
     */
    public boolean checkFileSize(MultipartFile multipartFile, int size, String unit) {
        long len = multipartFile.getSize();//上传文件的大小, 单位为字节.
        //准备接收换算后文件大小的容器
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        //如果上传文件大于限定的容量
        if (fileSize > size) {
            return true;
        }
        return false;
    }


}
