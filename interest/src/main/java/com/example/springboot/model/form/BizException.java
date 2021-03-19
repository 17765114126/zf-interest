package com.example.springboot.model.form;

import com.example.springboot.model.enums.ResultCodeEnum;

/**
 * @Date: 2019/8/27
 * @Author: zhaofu
 * @Description: TODO
 **/
public class BizException extends RuntimeException {

    protected Integer code;

    public BizException() {
        super();
    }

    public BizException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public BizException(String msg, Throwable throwable, Integer code) {
        super(msg, throwable);
        this.code = code;
    }

    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMsg(), null);
        this.code = resultCodeEnum.getCode();
    }

    public BizException(Integer code, String msg) {
        super(msg, null);
        this.code = code;
    }

    public Integer code() {
        return code;
    }
}
