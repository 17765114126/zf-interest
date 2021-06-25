package other.Scheduler.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

/**
 * @ClassName Test
 * @Author zhaofu
 * @Date 2021/6/17
 * @Version V1.0
 *
 * 接下来进入测试方法，查看是否可以进行定时任务的定点停止。
 *
 * 设定一个线程名称，进行模拟。以后部署在项目中可以是动态的。
 *
 **/

@RestController
public class Test {

    @Autowired
    private DynamicTask task;
    @RequestMapping("/task")
    public void test() throws Exception {
        // 开启定时任务，对象注解Scope是多利的。
        task.startCron();

    }
    @RequestMapping("/stop")
    public void stop() throws Exception {
        // 提前测试用来测试线程1进行对比是否关闭。
        ScheduledFuture scheduledFuture = App.map.get("http-nio-8081-exec-1");
        scheduledFuture.cancel(true);
        // 查看任务是否在正常执行之前结束,正常true
        boolean cancelled = scheduledFuture.isCancelled();
        while (!cancelled) {
            scheduledFuture.cancel(true);
        }


//        //提前测试用来测试线程1进行对比是否关闭
//        Iterator iterator = ThreadDemoApplication.map.keySet().iterator();
//        while (iterator.hasNext()){
//
//            ScheduledFuture scheduledFuture = ThreadDemoApplication.map.get(iterator.next().toString());
//            scheduledFuture.cancel(true);
//            //查看任务是否正常执行之前结束，正常true
//            boolean cancelled = scheduledFuture.isCancelled();
//            while (!cancelled){
//                scheduledFuture.cancel(true);
//            }
//        }

    }
}
