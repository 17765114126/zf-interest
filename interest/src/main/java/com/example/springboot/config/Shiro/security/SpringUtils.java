package com.example.springboot.config.Shiro.security;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext ctx;
    @Override
    public void setApplicationContext(ApplicationContext contex) throws BeansException {
        this.ctx=contex;
    }
    public static ApplicationContext getContext(){
        return ctx;
    }
    public static <T> T getBean(String name){
        return (T)ctx.getBean(name);
    }
    public static String getProperty(String key){
        if(ctx == null){
            return null;
        }
        return ctx.getEnvironment().getProperty(key);
    }
    public static boolean testEnv(){
        String env = getProperty("spring.profiles.active");
        return "dev".equals(env);
    }
}