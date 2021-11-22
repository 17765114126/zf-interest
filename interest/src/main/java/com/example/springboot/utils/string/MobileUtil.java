package com.example.springboot.utils.string;

import java.util.regex.Pattern;

public class MobileUtil {

    private final static Pattern PATTERN_IS_NUMBER = Pattern.compile("[^0-9]");

    /**
     * 隐藏手机号中间四位
     *
     * @param mobile
     * @return
     */
    public static String protect(String mobile) {
        if (StringUtil.isBlank(mobile)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{6}(\\d{2})", "$1****$2");
    }

    /**
     * 提取有效手机号，如未找到则返回空
     *
     * @param mobile
     * @return
     */
    public static String extract(String mobile) {
        if (mobile == null) {
            return null;
        }
        ;
        //提取所有数字
        mobile = PATTERN_IS_NUMBER.matcher(mobile).replaceAll("");
        //小于11位判定不是手机号
        if (mobile.length() < 11) {
            return null;
        }
        //截取后11位
        mobile = mobile.substring(mobile.length() - 11);
        //不是'1'开头判断不是手机号 第二位是 '1'或者'2'的不是手机号
        if (mobile.charAt(0) != '1' || mobile.charAt(1) == '1' || mobile.charAt(1) == '2') {
            return null;
        }

        // 连续6位自然数判断
//        String pattern = "(?:(?:0(?=1)|1(?=2)|2(?=3)|3(?=4)|4(?=5)|5(?=6)|6(?=7)|7(?=8)|8(?=9)){5}|(?:9(?=8)|8(?=7)|7(?=6)|6(?=5)|5(?=4)|4(?=3)|3(?=2)|2(?=1)|1(?=0)){5})\\d";
//        Pattern pa = Pattern.compile(pattern);
//        if(pa.matcher(mobile.substring(1,7)).matches()){
//            return null;
//        }
//        if(pa.matcher(mobile.substring(2,8)).matches()){
//            return null;
//        }
//        if(pa.matcher(mobile.substring(3,9)).matches()){
//            return null;
//        }
//        if(pa.matcher(mobile.substring(4,10)).matches()){
//            return null;
//        }
//        if(pa.matcher(mobile.substring(4,10)).matches()){
//            return null;
//        }

        //检测是否有连续6个相同数字
//        char x = '-';
//        int i = 0;
//        for (char c : mobile.toCharArray()) {
//            if (x == c) {
//                i++;
//                if (i >= 7) {
//                    break;
//                }
//            } else {
//                x = c;
//                i = 1;
//            }
//        }
//        if (i >= 7) {
//            return null;
//        }
        return mobile;
    }
}
