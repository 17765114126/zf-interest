package com.example.application.config;

import com.example.application.config.annotation.CacheLock;
import com.example.application.exception.BaseException;
import com.example.application.utils.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁
 */
@Aspect
@Slf4j
@Component
public class CacheLockAspect {
    @Resource
    private RedisUtil redisUtil;
    /**
     * 分布式锁的key
     */
    private static final String KEY_PREFIX_LOCK = "CACHE_LOCK_ASPECT:";

    /**
     * 最小等待单位时间
     */
    private static final long MIN_WAIT_MILL = 300;

    @Around("@annotation(com.example.application.config.annotation.CacheLock)")
    public void cacheLockPoint(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method cacheMethod = signature.getMethod();
        if (null == cacheMethod) {
            log.info("未获取到使用方法 pjp: {}", pjp);
            return;
        }
        String lockKey = cacheMethod.getAnnotation(CacheLock.class).lockedKey();
        if (StringUtils.isBlank(lockKey)) {
            log.error("method:{}, 锁名称为空！", cacheMethod);
            return;
        }

        //锁占用时间
        long timeOut = cacheMethod.getAnnotation(CacheLock.class).expireTime();
        //等待时间
        long waitTime = cacheMethod.getAnnotation(CacheLock.class).waitTime();
        //是否抛出异常
        boolean throwException = cacheMethod.getAnnotation(CacheLock.class).throwException();
        //是否释放锁
        boolean release = cacheMethod.getAnnotation(CacheLock.class).release();
        //是否有锁
        boolean haveLock = false;

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis() + waitTime * 1000;

        try {
            do {
                if (redisUtil.setNx(KEY_PREFIX_LOCK + lockKey, timeOut)) {
                    redisUtil.expire(KEY_PREFIX_LOCK + lockKey, timeOut, TimeUnit.SECONDS);
                    log.info("method:{} 获取锁:{},开始运行！", Thread.currentThread(), KEY_PREFIX_LOCK + lockKey);
                    pjp.proceed();
                    haveLock = true;
                    return;
                }
                log.info("method:{} 未获取锁:{},开始等待！", Thread.currentThread(), KEY_PREFIX_LOCK + lockKey);
                Thread.sleep(Math.min(MIN_WAIT_MILL, waitTime * 100));
            } while (System.currentTimeMillis() <= endTime);

            log.info("获得锁失败,放弃等待,之前共等待{}ms,方法将不执行,方法名为{}", System.currentTimeMillis() - startTime, cacheMethod);
            if (throwException) {
                throw new BaseException("等待锁失败，未正常执行方法！");
            }

        } catch (Throwable e) {
            log.error("method:{},运行错误！", cacheMethod, e);
            if (throwException) {
                throw new BaseException("获取锁失败!");
            }
        } finally {
            if (release && haveLock) {
                log.info("method:{} 执行完成释放锁:{}", Thread.currentThread(), KEY_PREFIX_LOCK + lockKey);
                redisUtil.del(KEY_PREFIX_LOCK + lockKey);
            }
        }
    }
}