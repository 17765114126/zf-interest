package com.example.springboot.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author jzl
 * @create 2019-07-05
 **/
public class RandomUtil {

    private static final Integer USER_SALT_LENGTH = 6;

    /**
     * 用户盐
     *
     * @return
     */
    public static String userSalt() {
        return RandomStringUtils.randomAlphanumeric(USER_SALT_LENGTH);
    }

    /**
     * 获取指定长度随机字符串
     *
     * @param length
     * @return
     */
    public static String randomStr(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }
}
