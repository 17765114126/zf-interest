package study.JUC.线程池;

import java.util.concurrent.Callable;

/**
 * @ClassName MyCallable
 * @Author zhaofu
 * @Date 2020/10/16
 * @Version V1.0
 **/

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        //返回执行当前 Callable 的线程名字
        return Thread.currentThread().getName();
    }
}
