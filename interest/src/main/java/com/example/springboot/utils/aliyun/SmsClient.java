package com.example.springboot.utils.aliyun;

import lombok.Data;

/**
 * @ClassName SmsClient
 * @Author zhaofu
 * @Date 2020/8/26
 * @Version V1.0
 * @Description:私人定制短信客户端参数
 **/
@Data
public class SmsClient {
    /*
    *名称
    */
    private String name;
    /*
    *手机号
    */
    private String phone;

    public SmsClient(String name,String phone) {
        this.name = name;
        this.phone = phone;
    }
}
