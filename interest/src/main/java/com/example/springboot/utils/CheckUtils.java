package com.example.springboot.utils;

/**
 * @author chen
 * @create 2020-12-01
 **/
public class CheckUtils {

    private static final String MOBILE_REGEX = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
    /**
     * 密码必须包含 大、小写字母,数组,特殊字符 6-12位
     */
    private static final String NEW_PASSWORD_REGEX = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{12,}$";
    /**
     * 密码必须包含字母, 数字 6-12位
     */
    private static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,12}$";

    public static Boolean checkMobile(String mobile) {
        return mobile.matches(MOBILE_REGEX);
    }

    public static Boolean checkPassWord(String password) {
        return password.matches(NEW_PASSWORD_REGEX);
    }
}
