package com.example.springboot.controller.Redis;

import com.alibaba.fastjson.JSON;
import com.example.springboot.config.annotation.CacheLock;
import com.example.springboot.model.Student;
import com.example.springboot.model.entity.CmsUser;
import com.example.springboot.service.cache.RedisCacheUserService;
import com.example.springboot.model.form.Result;
import com.example.springboot.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisController
 * @Author zhaofu
 * @Date 2019/8/27
 * @Version V1.0
 * <p>
 * 所依赖util
 * RedisConfig
 * RedisUtil
 * 启动不成功---->本地没有启动redis导致连接不到
 **/
@RestController
@Slf4j
public class RedisController {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private Redisson redisson;

    @Resource
    RedisTemplate redisTemplate;

    @Resource
    RedisCacheUserService redisCacheUserService;

    @RequestMapping("/redisTest")
    public Result redisTest() {
        try {
            Student student = new Student();
            student.setName("专诸");
            student.setAge(18);
            log.info("Redis启动日志------------------" + student.toString());
            redisUtil.set("s",student.getName(),1000 * 50);
            String s1 = redisUtil.get("s").toString();
            System.out.println(s1);
            Thread.sleep(1000L);
            CmsUser cmsUser = redisCacheUserService.selectUserById("1");
            System.out.println(JSON.toJSONString(cmsUser));
            return Result.buildSuccess(cmsUser);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("错误==============" + e);
        }
        return Result.buildFail();
    }


    /**
     * redis分布式锁注解测试
     */
    @CacheLock(lockedKey = "job", expireTime = 30)
    public void job() {
        try {
            System.out.println("---------------------------------------------------------------------------------");
            log.info("当前执行任务的线程号ID===>{}", Thread.currentThread().getId());
            log.info("当前执行任务的线程号name===>{}", Thread.currentThread().getName());
            System.out.println("---------------------------------------------------------------------------------");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * redis分布式可重入锁测试
     */
    public void job2() {
        RLock uuidLock = redisson.getLock("job_LOCK");
        try {
            uuidLock.lock(5, TimeUnit.SECONDS);
            //todo
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            uuidLock.unlock();
        }
    }


    /**
     * redis做消息队列避免重复发送
     * (
     * 使用redis发布订阅模式 做消息队列 解耦
     * 使用线程池防止浪费线程
     * 使用分布式锁避免多服务器重复执行问题
     * )
     */
    public void job3() {
        for (int i = 0; i < 2; i++) {
            Map<Object, Object> hashMap = new HashMap<>();
            hashMap.put("mobile", "1776511412"+i);
            hashMap.put("weixinOpenid","11111111111111");
            //redis做消息队列避免重复发送
            redisTemplate.convertAndSend("RedisMQSendTopic", hashMap);
        }
    }

}
