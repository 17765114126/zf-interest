package study.JUC.java例子.生产与销售;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
/**
 *
 * 车间
 *
 */
public class WorkShop {

    public void doWork(int val){
        // 8台机器
        int machine = 8;
        //
        try {
            Semaphore semaphore = new Semaphore(machine);
            CountDownLatch latch = new CountDownLatch(val);
            for (int i=0;i<val;i++){
                new Work(i,semaphore,latch).start();
            }
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("零件生产完成!");
    }
    static class Work extends Thread {
        private int work_no;
        private Semaphore semaphore;
        private CountDownLatch latch;
        public Work(int no, Semaphore semaphore,CountDownLatch latch){
            this.work_no = no;
            this.semaphore = semaphore;
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                // 申请机器资源
                semaphore.acquire();
                System.out.println("工人编号:"+work_no+" 申请一台机器进行生产零件");
                Thread.sleep(2000);
                System.out.println("工人编号:"+work_no+" 生产完成,释放机器");
                // 使用完毕释放机器资源
                semaphore.release();
                // 计数器-1
                latch.countDown();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}