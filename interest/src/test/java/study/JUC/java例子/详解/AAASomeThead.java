package study.JUC.java例子.详解;
/**
 *
 *  线程对象是可以产生线程的对象。比如在Java平台中Thread对象，Runnable对象。线程，是指正在执行的一个指点令序列。
 *  在java平台上是指从一个线程对象的start()开始，运行run方法体中的那一段相对独立的过程。相比于多进程，多线程的优势有：
 *
 *     （1）进程之间不能共享数据，线程可以；
 *
 *     （2）系统创建进程需要为该进程重新分配系统资源，故创建线程代价比较小；
 *
 *     （3）Java语言内置了多线程功能支持，简化了java多线程编程。
 *
 * 一、创建线程和启动
 *
 *   （1）继承Thread类创建线程类
 *
 * 通过继承Thread类创建线程类的具体步骤和具体代码如下：
 *
 *    • 定义一个继承Thread类的子类，并重写该类的run()方法；
 *
 *    • 创建Thread子类的实例，即创建了线程对象；
 *
 *    • 调用该线程对象的start()方法启动线程。
 *
 * */
class AAASomeThead extends Thread   {
    public void run()   {
        System.out.println("=====");
        //do something here
    }
    public static void main(String[] args){
        AAASomeThead oneThread = new AAASomeThead();
        //步骤3：启动线程：

        try {
            oneThread.sleep(1000);
            oneThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


