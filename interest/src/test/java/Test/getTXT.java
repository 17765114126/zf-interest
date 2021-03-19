package Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

/**
 * @ClassName getTXT
 * @Author zhaofu
 * @Date 2020/3/9
 * @Version V1.0
 **/
@RunWith(SpringRunner.class)
public class getTXT {
    public static final int LINELENGTH = 60;//自定义行的长度

    @Test
    public static void main(String[] args) {
        //毛选共 24869 行，目前读到 2214 行
        int currRead = 2214;
        String maoxuan = "E:/321.txt";
        File file = new File(maoxuan);
        try {
//            BufferedReader in = new BufferedReader(new FileReader(file));
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader in = new BufferedReader(isr);
            String str;
            int i = 1;
            int z = 0;
            while ((str = in.readLine()) != null) {
                if (str.isEmpty()) continue;
//                if (i > 62000 && i <= 63000) {//控制台一次打印不了全部内容，后面的会把前面的覆盖
                while (str.length() > LINELENGTH) {//将长的行自动换行
                    if (z == currRead) {
                        System.out.println("上次读到");
                    }
                    System.out.println(z + "行" + i + "t" + str.substring(0, LINELENGTH));
                    str = str.substring(LINELENGTH);
                    z++;
                }
                z++;
                System.out.println(z + "行" + i + "t" + str);//记录当前第几行，便于下次阅读
            }
            i++;
//            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
