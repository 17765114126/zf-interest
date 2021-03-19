package com.example.springboot.config.过滤器;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Author zhaofu
 * @Date 2019/8/5
 * @Version V1.0
 **/

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        //指定过滤器的执行顺序
        filterRegistrationBean.setOrder(2);
        filterRegistrationBean.addUrlPatterns("/hello");
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean2() {
        FilterRegistrationBean filterRegistrationBean =
                new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MyFilterTwo());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/hello");
        return filterRegistrationBean;
    }
}
//执行成功，且顺序和设置的一致。