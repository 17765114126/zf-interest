package com.example.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class EnvUtils {

    private static Environment environmentStatic;

    @Autowired
    private Environment environment;

    @PostConstruct
    private void init() {
        environmentStatic = environment;
    }

    private static final String PROD_ENV = "prod";
    private static final String PRE_ENV = "pre";
    private static final String DEV_ENV = "dev";

    public static String[] getActiveProfiles() {
        return environmentStatic.getActiveProfiles();
    }

    /**
     * 是否正式环境 或 预发环境
     *
     * @return
     */
    public static boolean preOrProdActive() {
        String[] profiles = getActiveProfiles();

        if (Arrays.asList(profiles).contains(PROD_ENV) || Arrays.asList(profiles).contains(PRE_ENV)) {
            return true;
        }

        return false;
    }

    /**
     * 是否开发环境
     *
     * @return
     */
    public static boolean devActive() {
        String[] profiles = getActiveProfiles();

        if (Arrays.asList(profiles).contains(DEV_ENV)) {
            return true;
        }

        return false;
    }
}
