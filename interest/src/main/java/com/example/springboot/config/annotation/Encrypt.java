package com.example.springboot.config.annotation;

import java.lang.annotation.*;

/**
 * @ClassName EncryptFormatter
 * @Author zf
 * @Date 2020/11/9
 * @Version V1.0
 * @Description 字符串加密
 **/
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {
}
