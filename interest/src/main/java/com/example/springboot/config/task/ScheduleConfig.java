package com.example.springboot.config.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName ScheduleConfig
 * @Author zhaofu
 * @Date 2021/1/8
 * @Version V1.0
 *
 * @Configuration //1.主要用于标记配置类，兼备Component的效果。
 * @EnableScheduling // 2.开启定时任务
 **/
@Configuration
@EnableScheduling
public class ScheduleConfig {}