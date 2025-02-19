package com.example.application.config;

import com.example.application.utils.ThreadPoolUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 初始化加载系统常用资源
 */
@Component
@Order(3)
public class ThreadPoolConfig implements CommandLineRunner {

//    @Value("${threadPoolExecutor.corePoolSize:15}")
    private Integer corePoolSize = 5;

//    @Value("${threadPoolExecutor.maximumPoolSize:30}")
    private Integer maximumPoolSize = 10;

//    @Value("${threadPoolExecutor.keepAliveTime:30}")
    private Integer keepAliveTime = 1;


    @Override
    public void run(String... args) {
        // 初始线程池资源
        ThreadPoolUtils threadPoolUtils = ThreadPoolUtils.getInstance();
        ThreadPoolExecutor threadPoolExecutor = threadPoolUtils.getThreadPoolExecutor();
        if(threadPoolExecutor == null){
            // 线程队列使用SynchronousQueue
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,
                    new SynchronousQueue());
            threadPoolUtils.setThreadPoolExecutor(threadPoolExecutor);
        }
        threadPoolUtils.getThreadPoolInfo();
    }
}
