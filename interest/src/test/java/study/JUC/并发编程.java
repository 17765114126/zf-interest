package study.JUC;

import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName 并发编程
 * @Author zhaofu
 * @Date 2020/9/26
 * @Version V1.0
 * @url http://www.tianshouzhi.com/api/tutorials/mutithread/62
 **/
public class 并发编程 {
    @Test
    public void test1() {
        //用于打印主线程的名称
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void test2() {
        //创建一个线程对象，覆盖其run方法，传入参数为线程的名字
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 1; i <= 100; i++) {
                    System.out.println("自定义线程循环：" + i + "次");
                }
            }
        };

        //调用start方法启动线程
        t1.start();
        for (int i = 1; i <= 100; i++) {
            System.out.println("主线程循环：" + i + "次");
        }
    }

    @Test
    public void test3() {
        // java程序启动至少会启动几个线程

        //这段代码的作用是打印出当前JVM中运行的所有线程信息，这使用到了JMX的API
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + "-" + threadInfo.getThreadName());
        }
        //5-Attach Listener
        //4-Signal Dispatcher //分发处理发送给JVM信号的线程
        //3-Finalizer //调用对象的finalize方法的线程，就是垃圾回收的线程
        //2-Reference Handler //清除reference的线程
        //1-main //主线程
    }

    @Test
    public void test4() {

        new Thread("custom thread") {
            @Override
            public void run() {
                System.out.println("当前线程1:" + Thread.currentThread().getName());
            }
        }.start();

        new Thread("sdfgdf") {
            @Override
            public void run() {
                System.out.println("当前线程3:" + Thread.currentThread().getName());
            }
        }.start();

        System.out.println("当前线程2:" + Thread.currentThread().getName());
    }

    @Test
    public void test5() throws InterruptedException {
        // 这段代码我们使用了一个方法Thread.currentThread().sleep(miliseconds)来模拟文件的模拟和处理操作。
        // 其作用是让当前线程休眠，休眠的含义是在指定的时间范围内，线程不会再向CPU发送执行的请求。
        // 等到休眠时间已过，才会重新请求CPU执行。
        // 因为我们的代码都是在main方法即主线程中运行，因此当主线程休眠的时候，就相当于程序停止了运行，等到休眠时间已过，程序才会继续运行，然后又休眠,运行...。
        final long start = System.currentTimeMillis();
        System.out.println("----------程序开始运行---------");
        System.out.println("读取A文件开始...");
        Thread.currentThread().sleep(5000);
        System.out.println("读取A文件结束，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒...开始处理A文件");
        Thread.currentThread().sleep(2000);
        System.out.println("A文件处理完成...，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
        System.out.println("读取B文件开始...");
        Thread.currentThread().sleep(5000);
        System.out.println("读取B文件结束，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒...开始处理B文件");
        Thread.currentThread().sleep(2000);
        System.out.println("B文件处理完成...，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒");
        // 需要注意的是，上面的代码，资源利用率是很低的。
        // 原因在于从磁盘中读取文件的时候，大部分的CPU时间用于等待磁盘去读取数据。
        // 在这段时间里，CPU非常的空闲。其深层次的原因是对于IO操作，往往是通过硬件直接存取器(DMA)来执行的，
        // 也就是说，CPU只需要将发送一个指令给DMA去执行对应的IO操作即可，指令发送是一瞬间的事，发送完成CPU就可以干其他的事了,
        // 我们说的IO操作需要执行5秒事实上是DMA执行这个操作需要5秒的时间，而不是CPU。
    }

    @Test
    public void test6() throws InterruptedException {
        //因此CPU现在很空闲，它可以做一些别的事情。通过改变操作的顺序，就能够更好的使用CPU资源。
        //CPU等待第一个文件被读取完。然后开始读取第二个文件。当第二文件在被读取的时候，CPU会去处理第一个文件。
        final long start = System.currentTimeMillis();
        System.out.println("----------程序开始运行---------");
        System.out.println("读取A文件开始...");
        Thread.currentThread().sleep(5000);
        System.out.println("读取A文件结束，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒...开始处理A文件，同时开始读取B文件..");
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println("读取B文件开始...");
                    Thread.currentThread().sleep(5000);
                    System.out.println("读取B文件结束，耗时：" + (System.currentTimeMillis() - start) / 1000 + "秒...开始处理B文件");
                    Thread.currentThread().sleep(2000);
                    System.out.println("B文件处理完成...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();
        Thread.currentThread().sleep(2000);
        System.out.println("A文件处理完成...");
        t1.join();
        System.out.println("总耗时:" + (System.currentTimeMillis() - start) / 1000 + "秒");
        // 在改进后的代码中，我们将B文件的操作放在了另外一个线程中执行,所以效率可以得到提升。
        // 这是因为我们在A文件读取完成之后，同时开始了A文件的处理和B文件的处理工作。
    }

    @Test
    public void Test7() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            new Thread("" + i) {
                public void run() {
                    System.out.println("Thread: " + getName() + "running");
                }
            }.start();
        }
    }

    @Test
    public void Test8() {
        LocalTime now = LocalTime.now(); //16:21:08.430  获得当前时间
        LocalDate now0 = LocalDate.now();
        new Thread() {
            @Override
            public void run() {
                //我们希望不断的去检查服务器的状态，所以讲将检查的代码放入一个死循环中
                while (true) {
                    //用打印一句话表示检查服务器状态
                    System.out.println("检查服务器状态....当前时间:" + now0.toString() + " " + now.toString());
                    try {
                        //休眠3秒，以免检查台频繁
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Test
    public void Test9() throws InterruptedException {
        LocalTime now = LocalTime.now();
        LocalDate now0 = LocalDate.now();
        Thread t = new Thread() {
            @Override
            public void run() {
                //预期循环10次
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(2000);
                        System.out.println("自定义线程:当前时间：" + now0.toString() + " " + now.toString());
                    } catch (InterruptedException e) {//这个异常由sleep方法抛出
                        e.printStackTrace();
                        System.out.println("自定义线程:收到中断信号，总共循环了" + i + "次...");
                        //接受到中断信号时，停止运行
                        return;
                    }
                }
            }
        };
        t.start();
        //主线程休眠9秒
        Thread.sleep(9000);
        System.out.println("主线程：等待9秒后发送中断信号...");
        t.interrupt();
    }

    @Test
    public void Test10() throws InterruptedException {
        Thread t = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    //每次打印前都判断是否被中断
                    if (!Thread.interrupted()) {
                        i++;
                        System.out.println("自定义线程，打印...." + i + "次");
                    } else {//如果被中断，停止运行
                        System.out.println("自定义线程：被中断...");
                        return;
                    }

                }

            }
        };
        t.start();
        //主线程休眠1毫秒，以便自定义线程执行
        Thread.sleep(1);
        System.out.println("主线程:休眠1毫秒后发送中断信号...");
        t.interrupt();
    }

    @Test
    public void Test11() throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("程序开始运行...");
        Thread t = new Thread() {
            @Override
            public void run() {
                try {//模拟自定义线程干某个事花了5秒
                    System.out.println("自定义线程执行开始...");
                    Thread.sleep(5000);
                    System.out.println("自定义线程执行完毕...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();

        //模拟主线干其他事花了2秒
        Thread.sleep(2000);
        System.out.println("主线程执行完毕，等待t线程执行完成...");
        //主线程2秒后就可以继续执行，但是其必须执行的条件是t线程必须执行完成，此时调用t的join方法
        long joinstart = System.currentTimeMillis();
        t.join();
        System.out.println("主线程：t执行已经执行完毕...，等待了" + (System.currentTimeMillis() - joinstart) / 1000 + "秒");
        System.out.println("程序运行总时间..." + (System.currentTimeMillis() - start) / 1000 + "秒");
    }

    @Test
    public void Test12() {
        System.out.println("最大优先级;" + Thread.MAX_PRIORITY);
        System.out.println("正常优先级;" + Thread.NORM_PRIORITY);
        System.out.println("最小优先级;" + Thread.MIN_PRIORITY);
        System.out.println("主线程优先级;" + Thread.currentThread().getPriority());
        Thread t = new Thread();
        System.out.println("创建一个线程默认的优先级:" + t.getPriority());
    }

    @Test
    public void Test13() {
        new Thread(new Running(), "RunningThread").start();
        new Thread(new TimeWaiting(), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread-1").start();
        new Thread(new Waiting(), "WaitingThread-2").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
        //代码分析：

        // RunningThread线程运行一个不断自增加法，持续时间会很长，线程一直应该处于RUNNABLE状态。

        // TimeWaiting线程里面是一个死循环，每次休眠100毫秒，因此大部分情况下，应该处于TIME-WAITING状态。

        // WaitingThread-1和WaitingThread-2共同竞争一个类锁：Waiting.class。
        // 而同步快里面，又调用了wait()方法，先得到锁的线程会释放锁，因此最终2个线程都处于Waiting状态。

        // BlockedThread-1和BlockedThread-2线程也是共同竞争一个类锁：Blocked.class。
        // 而同步快里面，是一个死循环，然后调用了SleepUtils.sleep()方法，因此一直不会释放锁。
        // 所以二者，应该是有一个大部分情况下处于Time-Waiting状态，另一个处于Blocked状态。
    }

    /**
     * 表示线程正在运行
     *
     * @author TIANSHOUZHI336
     */
    static class Running implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < Long.MAX_VALUE; i++) {
                i++;
                System.out.println(Thread.currentThread().getName() + " :" + i);
            }
        }
    }

    /**
     * 该线程不断的进行睡眠
     */
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            System.out.println("----------------");
            Integer i = 0;
            while (true) {
                i++;
                System.out.println(Thread.currentThread().getName() + " :" + i);
                SleepUtils.second(100);
            }
        }
    }

    /**
     * 该线程在Waiting.class实例上等待
     */
    static class Waiting implements Runnable {
        @Override
        public void run() {
            System.out.println("----------------");
            Integer i = 0;
            while (true) {
                synchronized (Waiting.class) {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " :" + i);
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 该线程在Blocked.class实例上加锁后，不会释放该锁
     */
    static class Blocked implements Runnable {
        public void run() {
            System.out.println("-------------------");
            Integer i = 0;
            synchronized (Blocked.class) {
                while (true) {
                    i++;
                    System.out.println(Thread.currentThread().getName() + " :" + i);
                    SleepUtils.second(100);
                }
            }
        }
    }

    public static class SleepUtils {
        public static final void second(long seconds) {
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
            }
        }
    }

    static int count = 0;
    @Test
    public void Test14() throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5000000; i++) {
                    count++;
                }
                System.out.println("自定义线程:计算完成...，耗时" + (System.currentTimeMillis() - start));
            }
        }.start();
        for (int i = 0; i < 5000000; i++) {
            count++;
        }
        System.out.println("主线程:计算完成....，耗时" + (System.currentTimeMillis() - start));
        Thread.sleep(5000);
        System.out.println("count:" + count);
        //可以看到的结果并不是我们期望中的一千万。原因在于，count++并不是一个原子操作。每次自增实际上是分为3个步骤：
        //获取count变量的当前值
        //将当前值加1
        //将加1后的值存储到count变量中
        //因为线程是并行执行的，因此这就可能出现问题。例如假设count变量当前值是0，主线程和自定义线程同时获取到这个值，主线程先完成自增的操作，将count变量的值设置为1。自定义线程随后完成自增的操作，因为自定义线程也是在0的基础上加1，然后将值赋值给count变量，最终导致实际上进行了两次自增操作，但实际上确只加了1。
    }

    @Test
    public void Test15() throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5000000; i++) {
                    synchronized (并发编程.class) {
                        count++;
                    }
                }
                System.out.println("自定义线程:计算完成...，耗时" + (System.currentTimeMillis() - start));
            }
        }.start();
        for (int i = 0; i < 5000000; i++) {
            synchronized (并发编程.class) {
                count++;
            }
        }
        System.out.println("主线程:计算完成....，耗时" + (System.currentTimeMillis() - start));
        Thread.sleep(5000);
        System.out.println("count:" + count);
        //上述案例实际上仅仅将count++操作放到一个同步代码块(synchronized关键字，ThreadCompetitionDemo.class可以认为是一个锁)。
        //可以看到结果是计算正确的，不管运行多少次，结果总是这样。这里要注意的是，我们每次线程完成五百万次累加操作的时间，都在2秒左右，而之前大概只需要20毫秒左右，花费的时间接近100倍。这说明同步代码块虽然可以帮助我们将结果计算正确，但是在性能上却有非常大的影响。
    }

    @Test
    public void Test100() {

    }
}
