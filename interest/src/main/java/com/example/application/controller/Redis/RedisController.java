package com.example.application.controller.Redis;

import com.alibaba.fastjson.JSON;
import com.example.application.config.annotation.CacheLock;
import com.example.application.model.Student;
import com.example.application.model.User;
import com.example.application.model.entity.CmsUser;
import com.example.application.service.cache.RedisCacheUserService;
import com.example.application.model.form.Result;
import com.example.application.utils.date.DateUtil;
import com.example.application.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * 非公平的可重入锁：指定超时时间
     * */
    public void lock() {
        List<User> arrayList = data();
        for (User user4 : arrayList) {
            RLock lock = redisson.getLock("lock" + user4.getId());
            if (!lock.isLocked()) {
                try {
                    //加锁与超时时间
                    lock.lock(15, TimeUnit.SECONDS);
                } catch (Exception e) {
                    log.info("error:{}", e.getMessage());
                } finally {
                    //若锁被当前线程持有，才释放锁,否则释放锁回报错
                    if (lock.isLocked() && lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }
                }
            }
        }
    }

    public List<User> data() {
        User user = new User();
        user.setId(1L);
        user.setDate(DateUtil.getDateNoTime("2021-09-18 11:15:35"));

        User user1 = new User();
        user1.setId(2L);
        user1.setDate(DateUtil.getDate());

        User user2 = new User();
        user2.setId(2L);
        user2.setDate(DateUtil.getDateNoTime("2021-09-22 15:56:41"));

        User user3 = new User();
        user3.setId(4L);
        user3.setDate(DateUtil.getDateNoTime("2021-09-22 15:57:41"));

        List<User> arrayList = new ArrayList();
        arrayList.add(user);
        arrayList.add(user1);
        arrayList.add(user2);
        return arrayList;
    }

}
