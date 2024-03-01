package com.example.application.config.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CacheLock {
    String lockedKey() default "";   //redis锁key的前缀

    long expireTime() default 10;    //key在redis里存在的时间 单位：秒

    boolean release() default true; //释放在方法执行完成之后释放锁

    long waitTime() default 0; //获取锁的最大等待时间，单位：秒，默认不等待，0即为快速失败

    boolean throwException() default false;//是否抛出异常 默认不抛出
}
