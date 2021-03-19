package com.example.springboot.config.监听器;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 2.创建配置文件类，注册该监听器
 *
 * @ClassName ListenerConfig
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 **/
@Configuration
public class ListenerConfig {

    @Bean
    public ServletListenerRegistrationBean<CountListener> countListenerServletRegistrationBean() {
        return new ServletListenerRegistrationBean<>(new CountListener());
    }
}
