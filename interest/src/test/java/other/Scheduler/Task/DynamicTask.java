package other.Scheduler.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;


/**
 *
 * ThreadPoolTaskScheduler的使用，定时任务开启与关闭
 *
 * @ClassName DynamicTask
 * @Author zhaofu
 * @Date 2021/6/17
 * @Version V1.0
 *
 * 这里用的是Springboot框架。进行测试开发的，其实都是一样的。只不过这样比较方便测试。
 *
 * 利用定时线程池任务调度，多利模式，
 *
 * 定点查询任务，开启或关闭简单案例，如果任务有需求，那么就拼接cron表达式，传入定时任务即可。
 *
 **/
@Component
@Scope("prototype")
public class DynamicTask {

//    private final static Logger logger = LoggerFactory.getLogger(DynamicTask.class);
    private String cron;

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture future;

    public void startCron() {
        cron = "0/1 * * * * ?";
        System.out.println(Thread.currentThread().getName());
        String name = Thread.currentThread().getName();
        future = threadPoolTaskScheduler.schedule(new myTask(name), new CronTrigger(cron));
        App.map.put(name, future);
    }

    public void stop() {
        if (future != null) {
            future.cancel(true);
        }
    }

    private class myTask implements Runnable {
        private String name;

        myTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("test" + name);
        }
    }
}
