package other;

import java.io.IOException;

/**
 * @ClassName read
 * @Author zhaofu
 * @Date 2021/6/9
 * @Version V1.0
 **/
public class read {

    public static void main(String[] args) {
        byte[] byteData = new byte[100]; // 声明一个字节数组
        System.out.println("请输入英文：");
        try {
            System.in.read(byteData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("您输入的内容如下：");
        for (int i = 0; i < byteData.length; i++) {
            System.out.print((char) byteData[i]);
        }
    }

}
