package other.Scheduler.Task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName App
 * @Author zhaofu
 * @Date 2021/6/17
 * @Version V1.0
 *
 *
 * 创建一个map用来存储定时任务的回调对象。用于接下来的判断。并且初始化调度的线程池
 **/

@SpringBootApplication
public class App {
    // 线程存储器
    public static ConcurrentHashMap<String, ScheduledFuture> map = new ConcurrentHashMap<String, ScheduledFuture>();
    // 启动入口
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    // 创建线程
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler executor = new ThreadPoolTaskScheduler();
        executor.setPoolSize(20);
        executor.setThreadNamePrefix("taskExecutor-");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
