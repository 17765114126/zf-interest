package other;

import org.junit.Test;
import org.omg.SendingContext.RunTime;

import java.io.IOException;

/**
 * @ClassName SystemClass
 * @Author zhaofu
 * @Date 2021/6/9
 * @Version V1.0
 * System 类中提供了一些系统级的操作方法，
 * 常用的方法有 arraycopy()、currentTimeMillis()、exit()、gc() 和 getProperty()。
 **/
public class SystemClass {
    @Test
    public void SystemTest() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            int temp = 0;
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("程序执行时间" + time + "秒");


        System.out.println("Java运行时环境版本：" + System.getProperty("java.version"));
        System.out.println("当前操作系统是：" + System.getProperty("os.name"));
        System.out.println("操作系统的架构：" + System.getProperty("os.arch"));
        System.out.println("操作系统的版本：" + System.getProperty("os.version"));

        System.out.println("用户的账户名称：" + System.getProperty("user.name"));
        System.out.println("用户的主目录：" + System.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + System.getProperty("user.dir"));
        System.out.println("系统默认编码：" + System.getProperty("file.encoding"));

//        System.out.println("运行时环境供应商：" + System.getProperty("java.vendor"));
//        System.out.println("java供应商url：" + System.getProperty("java.vendor.url"));
//        System.out.println("java安装目录：" + System.getProperty("java.home"));
//        System.out.println("java虚拟机规范版本：" + System.getProperty("java.vm.specification.version"));
//        System.out.println("java虚拟机规范供应商：" + System.getProperty("java.vm.specification.vendor"));
//        System.out.println("java虚拟机规范名称：" + System.getProperty("java.vm.specification.name"));
//        System.out.println("java虚拟机实现版本：" + System.getProperty("java.vm.version"));
//        System.out.println("java虚拟机实现供应商：" + System.getProperty("java.vm.vendor"));
//        System.out.println("java虚拟机实现名称：" + System.getProperty("java.vm.name"));
//        System.out.println("java运行时环境规范版本：" + System.getProperty("java.specification.version"));
//        System.out.println("java运行时环境规范运营商：" + System.getProperty("java.specification.vendor"));
//        System.out.println("java运行时环境规范名称：" + System.getProperty("java.specification.name"));
//        System.out.println("java类格式版本：" + System.getProperty("java.class.version"));
//        System.out.println("java类路径：" + System.getProperty("java.class.path"));
//        System.out.println("加载库是搜索的路径列表：" + System.getProperty("java.library.path"));
//        System.out.println("默认的临时文件路径：" + System.getProperty("java.io.tmpdir"));
//        System.out.println("要使用的JIT编译器的路径：" + System.getProperty("java.compiler"));
//        System.out.println("个或者多个扩展目录的路径：" + System.getProperty("java.ext.dirs"));
//        System.out.println("文件分隔符（在unix系统中是“/”）：" + System.getProperty("file.separator"));
//        System.out.println("路径分隔符（在unix系统中是“:”）：" + System.getProperty("path.separator"));
//        System.out.println("行分隔符（在unix系统中是“/n”）：" + System.getProperty("line.separator"));

        /**
         *该方法的作用是请求系统进行垃圾回收，完成内存中的垃圾清除。
         *
         *至于系统是否立刻回收，取决于系统中垃圾回收算法的实现以及系统执行时的情况。
         * */
        System.gc();

        /**
         *
         * 该方法的作用是终止当前正在运行的 Java 虚拟机
         * status 的值为 0 时表示正常退出，非零时表示异常退出。
         * 使用该方法可以在图形界面编程中实现程序的退出功能等。
         * */
        System.exit(0);


        System.out.println("--------------------- ");
    }

    /**
     * @ClassName runTimeTest
     * @Author zhaofu
     * @Date 2021/6/9
     * @Version V1.0
     * 在每一个JVM进程里面都会存在有一个Runtime类的对象，这个类的主要功能是取得一些与运行时有关的环境属性，或者创建新的进程。
     **/
    @Test
    public void runTimeTest() throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Java虚拟机中的空闲内存量:"+runtime.freeMemory());
        System.out.println("Java 虚拟机试图使用的最大内存量:"+ runtime.maxMemory());
        System.out.println("返回 Java 虚拟机中的内存总量:"+ runtime.totalMemory());

        String str="";
        for(int i = 0;i<20000;i++){
            str+=i;
        }

        System.out.println("**** 计算后空闲的内存" + runtime.freeMemory());
        runtime.gc();  //垃圾回收
        System.out.println("**** 垃圾回收后空闲的内存" + runtime.freeMemory());

        Process process = runtime.exec("C:\\Windows\\notepad.exe");//打开记事本程序，并返回一个进程
        Thread.sleep(3000); //让当前程序停止3秒。
        process.destroy();

    }

}
