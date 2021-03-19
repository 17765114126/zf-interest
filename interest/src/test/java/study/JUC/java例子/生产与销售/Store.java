package study.JUC.java例子.生产与销售;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
/**
 *
 * 商店
 *
 * */
public class Store {

    public void sell(int val){
        // 销售val台设备，必须等待销售完成才继续采购
        CountDownLatch latch = new CountDownLatch(val);
        try {
            for (int i=0;i<val;i++){
                new DoSell(latch).start();
            }
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("全部销售完成..");
    }

    static class DoSell extends Thread {
        private CountDownLatch latch;
        public DoSell(CountDownLatch latch){
            this.latch = latch;
        }
        @Override
        public void run() {
            try {
                System.out.println("开始进行销售...");
                Random r = new Random();
                // 随机时间销售出去，最长10秒
                int mills = r.nextInt(10000);
                Thread.sleep(mills);
                System.out.println("成功销售一台设备!");
                latch.countDown();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}