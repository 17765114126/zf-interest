package com.example.application.config;

import com.alibaba.fastjson.JSON;
import com.example.application.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Receiver {

    @Autowired
    //注入线程池
    private ThreadPoolTaskExecutor threadTaskExecutor;

    @Resource
    private RedisUtil redisUtil;

    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 过期时间
     */
    private static final long OVERDUE_TIME = 60;

    /**
     * 分布式锁的key
     */
    private static final String KEY_PREFIX_LOCK = "MSG_TOPIC:";

    public void receiveMessage(String message) {
        System.out.println("corePoolSize:" + threadTaskExecutor.getCorePoolSize());
        log.info("receiveMessage接收消息: <" + message + ">");
        //execute无反参线程池(submit:有反参线程池)
        threadTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Map<String, String> map = JSON.parseObject(message, Map.class);
                String mobile = map.get("mobile");
                try {
                    //获得锁才会执行业务逻辑(避免多服务器重复执行问题)
                    if (redisUtil.setNx(KEY_PREFIX_LOCK + mobile, OVERDUE_TIME)) {
                        redisUtil.expire(KEY_PREFIX_LOCK + mobile, OVERDUE_TIME, TimeUnit.SECONDS);
                        log.info("method:{} 获取锁:{},开始运行！", Thread.currentThread(), KEY_PREFIX_LOCK + mobile);
                        //todo
                        System.out.println("运行:"+mobile);
                        return;
                    }
                } catch (Exception e) {
                    log.error("receiveMessage运行错误:{}", e);
                }
                log.info("线程id: <" + Thread.currentThread().getId() + ">");
                System.out.println(latch.getCount());
                latch.countDown();
            }
        });


    }
}