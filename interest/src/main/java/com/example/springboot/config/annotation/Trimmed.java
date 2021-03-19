package com.example.springboot.config.annotation;

import java.lang.annotation.*;

/**
 * @Description 清除字符串前后的空格
 * @Author xwz
 * @Date 2020/4/28 13:47
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Trimmed {
//    enum TrimmerType {
//        /**
//         * 去除空格
//         */
//        SIMPLE,
//        /**
//         * 去除空格
//         */
//        ALL_WHITESPACES,
//        /**
//         * 去除空格
//         */
//        EXCEPT_LINE_BREAK;
//    }

//    TrimmerType value() default TrimmerType.ALL_WHITESPACES;
}
