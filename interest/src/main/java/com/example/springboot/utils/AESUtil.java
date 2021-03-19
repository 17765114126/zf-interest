package com.example.springboot.utils;

public class AESUtil {
    /**
    *  给key也加密一次
    **/
    public static String aesEncrypt(){
        String key = "zhaofu";
        String md5key= Md5Util.encode(key).toUpperCase();
        return md5key;
    }
    /**
     * AES加密
     */
    public static String getEncrypt(String token){
        String aesToken = AESEncryptUtil.aesEncrypt(token,aesEncrypt());
        return aesToken;
    }
    /**
     * AES解密
     */
    public static String aesDecrypt(String token){
        String AESToken = AESEncryptUtil.aesDecrypt(token, aesEncrypt());
        return AESToken;
    }
}
