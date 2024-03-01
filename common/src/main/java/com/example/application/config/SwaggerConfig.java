package com.example.application.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @ClassName SwaggerConfig
 * @Author zhaofu
 * @Date 2020/12/24
 * @Version V1.0
 * <p>
 * https://blog.csdn.net/ljm_csdn/article/details/87615670
 * 失败了 配置com.example.springboot.config.WebConfig#addResourceHandlers后成功
 * <p>
 * 访问http://localhost:8080/swagger-ui.html#/
 **/
//配置类
@Configuration
//开启Swagger2的自动配置
@EnableSwagger2
public class SwaggerConfig {
    //配置文档信息
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        // 标题
                        "养老文档",
                        // 描述
                        "养老文档描述",
                        // 版本
                        "v1.0",
                        // 组织链接
                        "http://39.101.184.47:8080/index",
                        // 联系人信息
                        new Contact("zf", "http://39.101.184.47:8080/index", "17765114126@163.com"),
                        // 许可
                        "Apach 2.0 许可",
                        // 许可连接
                        "许可链接",
                        // 扩展
                        new ArrayList<>()
                ))
                .enable(true) //配置是否启用Swagger，如果是false，在浏览器将无法访问
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
//                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.controller"))
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot.templates.controller"))
                // 配置如何通过path过滤,即这里只扫描请求以/kuang开头的接口
//                .paths(PathSelectors.ant("/kuang/**"))
                .build();
    }
}
