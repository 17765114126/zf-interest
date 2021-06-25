package other.Scheduler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName executor
 * @Author zhaofu
 * @Date 2021/6/17
 * @Version V1.0
 **/
public class executor {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(100, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    @Test
    public void Test2() {
        try {
            List<String> records = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                records.add("xiancheng :" + i);
            }
            CountDownLatch latch = new CountDownLatch(records.size());
            for (String record : records) {
                executor.execute(() -> {
                    try {
                        if (record.equals("xiancheng :5")) {
                            System.out.println("sleep--------");
                            Thread.sleep(2000);
                        }
                        System.out.println(record);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
