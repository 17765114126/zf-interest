package com.example.springboot.Some;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Scanner;

/**
 * @ClassName 自定义加密和解密文件
 * @Author zhaofu
 * @Date 2020/11/24
 * @Version V1.0
 * url: https://blog.csdn.net/xiaomingpeng/article/details/109928318?utm_medium=distribute.pc_category.none-task-blog-hot-8.nonecase&depth_1-utm_source=distribute.pc_category.none-task-blog-hot-8.nonecase
 **/
public class Tool {
    public static FileInputStream fis = null;
    public static FileOutputStream fos = null;
    public static long start;//计时
    public static long end;
    public static String jiamilj = null;
    public static String jiemilj = null;
    public static FileChannel fc = null;//文件大小


    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        System.out.println("你要加密还是解密：");
        String input = in.next();

        if (input.equals("加密")) {

            Scanner in01 = new Scanner(System.in);
            System.out.println("你要加密的文件路径：（E:\\321.txt）:");
            String input01 = in01.next();
            System.out.println(input01);

            jiami(input01);
            System.out.println("加密成功，加密路径：" + jiamilj);
            System.out.println("加密用时" + (end - start) + "毫秒");
            size(1);
            System.out.println("文件大小" + fc.size() + "Byte");
            fis.close();
            fos.close();
        } else {
            Scanner in02 = new Scanner(System.in);
            System.out.println("你要解密的文件路径：（E:\\b.txt）:");
            String input02 = in.next();
            jiemi(input02);
            System.out.println("解密成功,解密路径为：" + jiemilj);
            System.out.println("解密密用时" + (end - start) + "毫秒");
            size(2);
            System.out.println("文件大小" + fc.size() + "Byte");
            fis.close();
            fos.close();
        }
    }

    //加密
    public static void jiami(String input02) throws IOException {
        start = System.currentTimeMillis();
        in01(input02);
        end = System.currentTimeMillis();
    }

    //解密
    public static void jiemi(String input02) throws IOException {
        start = System.currentTimeMillis();
        in02(input02);
        end = System.currentTimeMillis();
    }

    //配套加密
    public static void in01(String input03) throws IOException {
        fis = new FileInputStream(input03);
        int i;  //核心代码
        while ((i = fis.read()) != -1) {
            out01(i);
        }
    }

    public static void out01(int i) throws IOException {
        fos = new FileOutputStream("E:\\b.txt", true);
        fos.write(i + 1);
        jiamilj = "E:\\b.txt";
    }

    //配套解密
    public static void in02(String input03) throws IOException {
        fis = new FileInputStream(input03);
        int i;  //核心代码
        while ((i = fis.read()) != -1) {
            out02(i);
        }
    }

    public static void out02(int i) throws IOException {
        fos = new FileOutputStream("E:\\c.txt", true);
        fos.write(i - 1);
        jiemilj = "E:\\c.txt";
    }

    //求文件大小
    public static void size(int s) throws FileNotFoundException {
        if (s == 1) {
            FileInputStream fis01 = new FileInputStream("E:\\b.txt");
            fc = fis01.getChannel();
        } else {
            FileInputStream fis01 = new FileInputStream("E:\\c.txt");
            fc = fis01.getChannel();
        }
    }
}
