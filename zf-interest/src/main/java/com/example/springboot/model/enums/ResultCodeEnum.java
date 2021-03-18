package com.example.springboot.model.enums;

import com.example.springboot.model.form.BizException;

import java.util.Map;

public enum ResultCodeEnum {
    INNER_ERROR(-1, "内部错误"),
    DATE_INFO_ERROR(-2, "数据信息异常"),
    NOT_LOGIN(1001, "用户未登录"),
    USER_NOT_EXIST(1002, "用户不存在"),
    PASSWORD_ERROR(1003, "密码错误"),
    PASSWORD_BEYOND(1004, "密码重试次数过多,请稍后再试"),
    USER_INVALID(1005, "用户被冻结"),
    CHECK_CODE_ERROR(1006, "验证码有误"),
    TOKEN_ERROR(1007, "TOKEN错误"),
    NOT_AUTH(1008, "用户无权限"),
    NOT_ZC(1009, "该用户没有注册"),
    CLIENT_PARAM_ERROR(1010, "客户端参数错误!"),
    MOBILE_FORMAT_ERROR(1011, "手机号格式错误!"),
    PASSWORD_FORMAT_ERROR(1012, "密码格式错误!"),
    CMS_USER_INFO_ERROR(1013, "员工信息不存在!"),
    MOBILE_REPETITION_ERROR(1014, "手机号重复!"),
    CMS_USER_DISABLE_ERROR(1015, "该员工已被禁用!"),
    CMS_USER_DELETE_ERROR(1016, "该员工已被删除!"),
    CMS_USER_ENABLE_ERROR(1017, "该员工已激活!"),
    USER_ALREADY_EXIST(1018, "用户已存在!"),
    CANNOT_SET_SAME_IDENTITY_ERROR(1019, "不可以重复设置身份!"),
    BACKMONEY_ISNULL_ERROR(1020, "没有退款金额"),
    PAYNUMBER_ISNULL_ERROR(1021, "没有支付编号"),
    PAYTYPE_ISNULL_ERROR(1022, "没有支付方式"),
    MONEY_ISNULL_ERROR(1023, "没有支付金额"),
    EXAMINE_PHONE_ERROR(1024, "没有需要审核手机号"),
    REFUND_ING_ERROR(1025, "此用户正在申请退vip，请先处理退vip，在进行此操作"),
    USER_HKMONEY_NOTENOUGH_ERROR(1026, "用户货款不足"),
    VIP_TYPE_ISNULL_ERROR(1027, "用户vip信息异常"),
    VIP_IS_STORE_MANAGER_OR_PARTNER(1028, "用户vip身份已是店长/合伙人"),
    NO_SELECTED_ERROR(1029, "未获取选择信息"),
    BUSINESS_NOT_EXIST(1030, "商家不存在"),
    BUSINESS_MONEY_ENOUGH_ERROR(1031, "商家余额不足"),
    ROLES_IS_EMPTY_ERROR(1032, "操作失败, 用户未绑定角色!"),
    UPLOAD_PICTURE_SIZE_ERROR(1033, "图片超过1M无法上传!"),
    NO_JUMP_URL_ERROR(1034, "跳转输入不能为空!"),
    NO_GOODS_ERROR(1035, "查不到对应商品信息!"),
    CMS_ORDER_NOT_FOUND(3001, "订单不存在!");

    private static Map<Integer, ResultCodeEnum> lookup;

//    static {
//        lookup = new HashMap<>();
//        Arrays.stream(values()).forEach(e -> lookup.put(e.code, e));
//        lookup = Collections.unmodifiableMap(lookup);
//    }

    private Integer code;

    private String msg;

    ResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ResultCodeEnum setMessage(String msg) {
        this.msg = msg;
        return this;
    }

    public void throwIt() {
        throw new BizException(code, msg);
    }

    public BizException getError() {
        throw new BizException(code, msg);
    }

    public static ResultCodeEnum of(Integer code) {
        return lookup.get(code);
    }

    public static String find(Integer code) {
        return of(code).getMsg();
    }
}
