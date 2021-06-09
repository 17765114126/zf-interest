package Test;

import org.junit.Test;

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
    public void currentTimeMillis() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            int temp = 0;
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("程序执行时间" + time + "秒");

        System.out.println("Java 运行时环境版本：" + System.getProperty("java.version"));
        System.out.println("当前操作系统是：" + System.getProperty("os.name"));
        System.out.println("当前用户是：" + System.getProperty("user.name"));
        System.out.println("系统默认编码：" + System.getProperty("file.encoding"));

        /**
         * java.version:java运行时版本
         * java.vendor:java运行时环境供应商
         * java.vendor.url:java供应商url
         * java.home;java安装目录
         * java.vm.specification.version:java虚拟机规范版本
         * java.vm.specification.vendor:java虚拟机规范供应商
         * java.vm.specification.name:java虚拟机规范名称
         * java.vm.version:java虚拟机实现版本
         * java.vm.vendor:java虚拟机实现供应商
         * java.vm.name:java虚拟机实现名称
         * java.specification.version:java运行时环境规范版本
         * java.specification.vendor:java运行时环境规范运营商
         * java.specification.name:java运行时环境规范名称
         * java.class.version:java类格式版本
         * java.class.path:java类路径
         * java.library.path:加载库是搜索的路径列表
         * java.io.tmpdir:默认的临时文件路径
         * java.compiler:要使用的JIT编译器的路径
         * java.ext.dirs:一个或者多个扩展目录的路径
         * os.name:操作系统的名称
         * os.arch:操作系统的架构
         * os.version:操作系统的版本
         * file.separator:文件分隔符（在unix系统中是“/”）
         * path.separator:路径分隔符（在unix系统中是“:”）
         * line.separator:行分隔符（在unix系统中是“/n”）
         * user.name:用户的账户名称
         * user.home:用户的主目录
         * user.dir:用户的当前工作目录
         * */

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

}
