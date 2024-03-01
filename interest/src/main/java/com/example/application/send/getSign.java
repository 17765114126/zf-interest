package com.example.application.send;

import com.alibaba.fastjson.JSON;
import com.example.application.utils.date.DateUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class getSign {

    public static void main(String[] args) {
        Map<String, Object> params = new HashMap<>();
        final String S_INT = "0123456789";
        final String S_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        final String S_ALL = S_INT + S_STR;

        final ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] buffer = new char[32];
        for (int i = 0; i < 32; i++) {
            buffer[i] = S_ALL.charAt(random.nextInt(S_ALL.length()));
        }
        String nonce = new String(buffer);


        Map<String, Object> param = new HashMap<>();
        params.put("accessKey","COCKPIT");
        params.put("nonce",nonce);
        params.put("timestamp", DateUtils.getTime());
        param.putAll(params);

        String sign = OpenApiUtils.genSign(param);
        param.put("sign",sign);
        System.out.println(JSON.toJSONString(param));
    }


}
