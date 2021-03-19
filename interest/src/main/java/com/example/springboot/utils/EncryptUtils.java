package com.example.springboot.utils;

import java.security.MessageDigest;

/**
 * @author <a href="mailto:clf@dora.com">clf</a>
 * @Description: 加密工具
 * @Date Create on 2019-05-23 13:56
 * @since version1.0
 */
public class EncryptUtils {

    /***
     * MD5加码 生成32位md5码
     */


    public static String md5(String inStr) {
        return md5(inStr, null);
    }

    public static String md5(String inStr, String encoding) {
        StringBuilder sb = new StringBuilder();
        if (StringUtil.hasBlank(encoding)) {
            encoding = "UTF-8";
        }
        String part = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(inStr.getBytes(encoding));
            for (int i = 0; i < md5.length; i++) {
                part = Integer.toHexString(md5[i] & 0xFF);
                if (part.length() == 1) {
                    part = "0" + part;
                }
                sb.append(part);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return sb.toString();

    }

    public static void main(String[] args) {
        String s = "{\"operateTime\":\"2017-12-15 16:10:00\",\"operator\":\"王五\",\"orderId\":\"21\",\"traceMark\":\"拒收\",\"traceNode\":\"拒收1\",\"vendorCode\":\"021K383036\",\"vendorName\":\"测试商家\",\"waybillCode\":\"QWD00010798683\"}2019-05-29 10:57:37euHoNVDkUQvHn8sK";
        System.out.println(md5(s));
        System.out.println(md5(s, "GBK"));
    }


}
