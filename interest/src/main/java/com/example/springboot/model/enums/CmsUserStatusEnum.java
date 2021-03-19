package com.example.springboot.model.enums;

/**
 * @author chen
 * @create 2020-12-01
 **/
public enum CmsUserStatusEnum {

    /**
     * 全部
     */
    ALL(0),
    /**
     * 激活
     */
    ENABLE(1),

    /**
     * 禁用
     */
    DISABLE(2);

    private Integer code;

    CmsUserStatusEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
