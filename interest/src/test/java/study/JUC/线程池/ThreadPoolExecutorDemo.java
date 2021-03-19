package study.JUC.线程池;

import study.JUC.线程池.MyRunnable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 *url：https://mp.weixin.qq.com/s/HCwCJw5CCDsue6H5XHfO0g
 * */
public class ThreadPoolExecutorDemo {

    //int corePoolSize,//线程池的核心线程数量
    private static final int CORE_POOL_SIZE = 5;
    //int maximumPoolSize,//线程池的最大线程数
    private static final int MAX_POOL_SIZE = 10;
    //workQueue：任务队列为 ArrayBlockingQueue，并且容量为 100;
    private static final int QUEUE_CAPACITY = 100;
    //keepAliveTime : 等待时间为 1L。
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            //创建WorkerThread对象（WorkerThread类实现了Runnable 接口）
            Runnable worker = new MyRunnable("" + i);
            //执行Runnable
            executor.execute(worker);
        }
        //终止线程池
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
