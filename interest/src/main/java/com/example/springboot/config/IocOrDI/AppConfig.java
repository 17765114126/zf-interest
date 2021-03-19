package com.example.springboot.config.IocOrDI;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @ClassName AppConfig
 * @Author zhaofu
 * @Date 2019/10/26
 * @Version V1.0
 * @Description: 定义装配文件--
 * 注解@ComponentScan
 * basePackages 用来扫描相对应的包
 *
 * excludeFilters = {@Filter({IocTest.class})}
 **/
@ComponentScan(basePackages = "com.example.springboot.model",
        basePackageClasses = {IocTest.class},
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = IocTest.class))
public class AppConfig {}
