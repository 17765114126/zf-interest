package com.example.springboot.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:clf@dora.com">clf</a>
 * @Description: SpringUtil
 * @Date Create on 2019-05-15 10:33
 * @since version1.0
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return applicationContext.getBean(name, clazz);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static String getProperty(String key) {
        return applicationContext.getBean(Environment.class).getProperty(key);
    }
    //获取是否测试
    public static boolean testEnv(){
        String env = getProperty("spring.profiles.active");
        return "dev".equals(env);
    }
    //获取是否预发
    public static boolean preEnv(){
        String env = getProperty("spring.profiles.active");
        return "pre".equals(env);
    }

}
