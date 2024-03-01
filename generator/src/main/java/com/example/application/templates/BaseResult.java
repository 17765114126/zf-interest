package com.example.application.templates;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * 统一返回对象
 * Created by zhengmm on 2020-04-16.
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = -8676181355403088497L;

    /**
     * 调用是否正常
     */
    boolean success;

    /**
     * 返回对象
     */
    T data;

    /**
     * 返回码，错误码
     */
    String respCode;

    /**
     * 返回消息
     */
    String respMsg;


    public BaseResult() {

    }

    public BaseResult(boolean success) {
        this.success = success;
    }

    public BaseResult(String msg) {
        this.success = false;
        this.respMsg = msg;
    }

    public BaseResult(String code, String msg) {
        this.respCode = code;
        this.success = false;
        this.respMsg = msg;
    }
}
