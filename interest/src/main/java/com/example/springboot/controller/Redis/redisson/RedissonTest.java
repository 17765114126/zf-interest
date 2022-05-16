package com.example.springboot.controller.Redis.redisson;

import com.example.springboot.model.User;
import com.example.springboot.utils.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissonTest {

    @Autowired
    private RestTemplate restTemplate;

    @Resource
    @Lazy
    private Redisson redisson;

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
