package com.example.springboot.model.enums;

/**
 * 敏感信息枚举类
 *
 * @author kenny
 * @version v1.0
 **/
public enum SensitiveTypeEnum {

    /**
     * 自定义
     */
    CUSTOMER,
    /**
     * 用户名, 王*贤, 杨*
     */
    CHINESE_NAME,
    /**
     * 身份证号, 110110********1234
     */
    ID_CARD,
    /**
     * 座机号, ****1234
     */
    FIXED_PHONE,
    /**
     * 手机号, 134****8961
     */
    MOBILE_PHONE,
    /**
     * 地址, 北京********
     */
    ADDRESS,
    /**
     * 电子邮件, s*****o@xx.com
     */
    EMAIL,
    /**
     * 银行卡, 622202************1234
     */
    BANK_CARD,
    /**
     * 密码, 永远是 ******, 与长度无关
     */
    PASSWORD,
    /**
     * 密钥, 永远是 ******, 与长度无关
     */
    KEY


}