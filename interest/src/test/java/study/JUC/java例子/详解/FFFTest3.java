package study.JUC.java例子.详解;
/**
 *
 * 4、设置线程的优先级
 *
 *      每个线程执行时都有一个优先级的属性，优先级高的线程可以获得较多的执行机会，而优先级低的线程则获得较少的执行机会。与线程休眠类似，线程的优先级仍然无法保障线程的执行次序。只不过，优先级高的线程获取CPU资源的概率较大，优先级低的也并非没机会执行。
 *
 * 每个线程默认的优先级都与创建它的父线程具有相同的优先级，在默认情况下，main线程具有普通优先级。
 *
 * 注：Thread类提供了setPriority(int newPriority)和getPriority()方法来设置和返回一个指定线程的优先级，其中setPriority方法的参数是一个整数，范围是1~·0之间，也可以使用Thread类提供的三个静态常量：
 *
 * */
public class FFFTest3 {
   static final int MAX_PRIORITY   =10;

    static final int MIN_PRIORITY   =1;

    static final int NORM_PRIORITY   =5;

    public static void main(String[] args) throws InterruptedException {
        new MyThread3("高级", MAX_PRIORITY).start();
        new MyThread3("低级", MIN_PRIORITY).start();
    }
}

class MyThread3 extends Thread {
    public MyThread3(String name,int pro) {
        super(name);//设置线程的名称
        setPriority(pro);//设置线程的优先级
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(this.getName() + "线程第" + (i+1) + "次执行！");
        }
    }
}
/**
 *
 * 注：虽然Java提供了10个优先级别，但这些优先级别需要操作系统的支持。不同的操作系统的优先级并不相同，而且也不能很好的和Java的10个优先级别对应。所以我们应该使用MAX_PRIORITY、MIN_PRIORITY和NORM_PRIORITY三个静态常量来设定优先级，这样才能保证程序最好的可移植性。
 *
 * */


/***
 *
 * 5、后台（守护）线程
 *
 *      守护线程使用的情况较少，但并非无用，举例来说，JVM的垃圾回收、内存管理等线程都是守护线程。还有就是在做数据库应用时候，使用的数据库连接池，连接池本身也包含着很多后台线程，监控连接个数、超时时间、状态等等。调用线程对象的方法setDaemon(true)，则可以将其设置为守护线程。守护线程的用途为：
 *      • 守护线程通常用于执行一些后台作业，例如在你的应用程序运行时播放背景音乐，在文字编辑器里做自动语法检查、自动保存等功能。
 *
 *      • Java的垃圾回收也是一个守护线程。守护线的好处就是你不需要关心它的结束问题。例如你在你的应用程序运行的时候希望播放背景音乐，如果将这个播放背景音乐的线程设定为非守护线程，那么在用户请求退出的时候，不仅要退出主线程，还要通知播放背景音乐的线程退出；如果设定为守护线程则不需要了。
 *
 * setDaemon方法的详细说明：
 *
 * 复制代码
 * public final void setDaemon(boolean on)        将该线程标记为守护线程或用户线程。当正在运行的线程都是守护线程时，Java 虚拟机退出。
 *          该方法必须在启动线程前调用。 该方法首先调用该线程的 checkAccess 方法，且不带任何参数。这可能抛出 SecurityException（在当前线程中）。
 *   参数：
 *      on - 如果为 true，则将该线程标记为守护线程。
 *   抛出：
 *     IllegalThreadStateException - 如果该线程处于活动状态。
 *     SecurityException - 如果当前线程无法修改该线程。
 * 复制代码
 * 注：JRE判断程序是否执行结束的标准是所有的前台执线程行完毕了，而不管后台线程的状态，因此，在使用后台县城时候一定要注意这个问题。
 *
 *
 *
 * */