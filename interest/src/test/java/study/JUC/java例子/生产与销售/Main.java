package study.JUC.java例子.生产与销售;

import java.util.concurrent.CyclicBarrier;
/**
 *
 * 本次自定义了一个实践内容，同时学习使用Java并发编程：CountDownLatch、CyclicBarrier和Semaphore
 *学习地址 ：https://www.cnblogs.com/dolphin0520/p/3920397.html
 *
 *
 * 工厂进行生产和销售。
 * 生产车间机器有限，使用需要排队。
 *
 * 主函数
 *
 * */
public class Main {

    // CountDownLatch:
    //  --> CountDownLatch(int count) , 等待count个线程结束，通过计数器方法判断线程执行完毕，之后继续向下执行。
    // 使用await()进行阻塞等待，线程结束后使用countDown() 对计数器进行-1操作。
    // CyclicBarrier:
    //   --> CyclicBarrier(int parties),执行parties个任务，所有任务结束后，继续向下执行，和CountDownLatch类似，
    // 内部是加计数器，加到目标值认为任务完成，await()进行阻塞等待，自动计数。
    // Semaphore:
    //   --> Semaphore(int permits),声明permits个资源，通过acquire获取一个资源，通过release释放一个资源，资源不够时，
    // acquire会进行等待，直到获得资源，可以设置等待资源的超时时间。

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(1, new Runnable() {
            @Override
            public void run() {
                System.out.println("卖光了，明天来吧！");
            }
        });
        new DoRun(barrier).start();
    }

    static class DoRun extends Thread {
        private CyclicBarrier barrier;
        public DoRun(CyclicBarrier barrier){
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try {
                System.out.println("开始运营啦..");
                MachineFactoryImpl factory = new MachineFactoryImpl();
                // 每天生产和销售的数量，自行进行设置
                factory.production(100);
                factory.consume(100);
                barrier.await();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
