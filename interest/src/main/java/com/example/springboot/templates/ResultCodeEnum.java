package com.example.springboot.templates;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ResultCodeEnum {


    SUCCESS("100200", "成功"),

    LIVE_SUCCESS("200", "成功"),

    USER_NOT_LOGIN("10005", "用户未登录"),

    ACCOUNT_NUMBER_ALREADY_USED("10006", "用户名已被使用"),

    FAILED_TO_GET_USER_INFORMATION("10007", "获取用户信息失败"),

    WRONG_USER_NAME_OR_PASSWORD("10008", "密码错误"),

    USER_INFORMATION_ALREADY_EXISTS("10009", "用户信息已存在"),

    USER_INFORMATION_DOES_NOT_EXIST("10010", "用户信息不存在"),

    PRIVILEGE_GRANT_FAILED("10011", "授权失败"),

    MOBILE_PHONE_NUMBER_OCCUPIED("10012", "手机号已被占用"),

    VALIDATION_FAILED("10013", "验证码失效"),


    BASE_ERROR("101400", "当前系统异常，请稍后再试"),

    FORBIDDEN("101611", "无访问权限"),

    INVALID_TOKEN("101612", "无效的令牌"),

    TOKEN_NOT_EXIST("101604", "登录失效，请重新登录"),


    /**
     * 基本错误定义
     */

    PARAMETER_ERROR("50001", "参数错误或数据异常"),

    PASSWORD_MD5_ENCRYPT_FAIL("50002", "密码MD5加密失败"),

    WECHAT_AUTHORIZATION_FAILED("50003", "微信授权失败"),

    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("50005", "请求方法错误，应使用方法："),

    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("50006", "缺少所需的请求正文"),


    /**
     * 业务错误定义
     */

    ;


    String code;

    String message;


}
