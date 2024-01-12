package com.example.springboot.send;

import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class OpenApiUtils {
    /** 调用方时间戳格式 */
    public static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /** 调用方唯一key参数名 */
    public static final String ACCESSKEY_PARAM_NAME = "accessKey";
    /** 调用方密钥参数名 */
    public static final String SECRETKEY_PARAM_NAME = "secretKey";
    /** 调用方时间戳参数名 */
    public static final String TIMESTAMP_PARAM_NAME = "timestamp";
    /** 调用方随机数参数名 */
    public static final String NONCE_PARAM_NAME = "nonce";
    /** 调用方签名参数名 */
    public static final String SIGN_PARAM_NAME = "sign";
    /** 允许调用方请求时间戳超时时间 */
    private static final long ALLOW_TIME = 1000L * 60 * 15;
    /** 调用方随机数长度 */
    private static final int ALLOW_NONCE_LENGTH = 32;

    /** 存储调用方随机数，生产中可放入Redis，并设置过期时间 */
    private static Map<String, String> nonceMap = new ConcurrentHashMap<>();
//    /** 用户唯一key及密钥，生产中可放入数据库和Redis */
//    private static UserConfig userConfig;


    /**
     * 生成签名
     *
     * @param params 参数
     * @return
     */
    public static String genSign(Map<String, Object> params) {
        String accessKey = (String) params.get(ACCESSKEY_PARAM_NAME);
        String secretKey = "m2ARcroJ4k1HYh3hSpx6KMWA7iXgQgDsoWyZ82Me06i8Gj0G2DN4D4R2l6lnDAn8";
        AssertUtils.notEmpty(secretKey);
        params.put(SECRETKEY_PARAM_NAME, secretKey);
        String[] array = params.keySet().toArray(new String[0]);
        // 按参数名排序
        Arrays.sort(array);
        StringBuilder stringBuilder = new StringBuilder();
        // 拼接字符串
        for (int i = 0; i < array.length; i++) {
            String key = array[i];
            stringBuilder.append(key).append("=").append(params.get(key));
            if (i != array.length - 1) {
                stringBuilder.append("&");
            }
        }
        return Md5Utils.encode(stringBuilder.toString());
    }

}
