package com.example.springboot.model.form;

import com.example.springboot.model.enums.ResultCodeEnum;
import lombok.Data;

@Data
public class Result<T> {

    /**
     * 0 成功
     */
    private int code = 0;

    /**
     * 说明
     */
    private String msg;

    /**
     * 业务数据
     */
    private T model;

    /**
     * 总记录数 分页用
     */
    private int total;

    public Result() {
    }

    public Result(T model) {
        this.model = model;
    }

    public static <T> Result<T> with(T model) {
        return new Result(model);
    }

    public static <T> Result<T> empty() {
        return new Result();
    }

    public static Result with(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static Result with(ResultCodeEnum resultCodeEnum) {
        Result r = new Result();
        r.setCode(resultCodeEnum.getCode());
        r.setMsg(resultCodeEnum.getMsg());
        return r;
    }

    public static Result withException(BizException e) {
        Result r = new Result();
        r.setCode(e.code());
        r.setMsg(e.getMessage());
        return r;
    }

    public boolean success() {
        return code == 0;
    }


    public static Result buildFail(String msg) {
        Result r = new Result();
        r.setCode(-1);
        r.setMsg(msg);
        return r;
    }

    public static Result buildFail() {
        Result r = new Result();
        r.setCode(-1);
        r.setMsg("操作失败");
        return r;
    }

    public static <T> Result<T> buildSuccess(T t) {
        Result r = new Result();
        r.setCode(0);
        r.setMsg("ok");
        r.setModel(t);
        return r;
    }

    public static Result buildSuccess() {
        Result r = new Result();
        r.setCode(0);
        r.setMsg("操作成功");
        return r;
    }

    public static Result buildSuccess(String msg) {
        Result r = new Result();
        r.setCode(0);
        r.setMsg(msg);
        return r;
    }
}
