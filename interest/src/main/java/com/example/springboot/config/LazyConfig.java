package com.example.springboot.config;

import com.example.springboot.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @ClassName LazyConfig
 * @Author zhaofu
 * @Date 2020/10/10
 * @Version V1.0
 **/
public class LazyConfig {

    @Bean
    @Lazy//不加会立即打印，加上不会
    public Person person() {
        return new Person("李四", 55,"男");
    }

}
