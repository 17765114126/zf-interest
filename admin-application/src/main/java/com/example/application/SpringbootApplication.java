package com.example.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan({"com.example.application.mapper", "com.example.application.templates.mapper"})//可以填多个
@ComponentScans({
        @ComponentScan
                ({
                        "com.example.application"
                })
})
@EnableAsync // 开启异步注解功能
 @EnableScheduling//开启定时功能
public class SpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }

}