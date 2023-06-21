package com.example.springboot.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置类
 */
@Configuration
public class TaskExecutorConfig {

    /**
     * 创建一个线程池
     *
     * @return
     */
    @Bean(name = "threadTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(5);
        //最大线程池大小
        executor.setMaxPoolSize(20);
        //任务队列大小
        executor.setQueueCapacity(500);
        //线程池中空闲线程等待工作的超时时间（单位秒）
        executor.setKeepAliveSeconds(300);
        //线程拒绝策略，此策略将丢弃最早的未处理的任务请求。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());

        return executor;
    }

}