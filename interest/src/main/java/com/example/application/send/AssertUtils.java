package com.example.application.send;


public class AssertUtils {
    public static void notEmpty(String... strs) {
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("参数错误");
            }
        }
    }
}
