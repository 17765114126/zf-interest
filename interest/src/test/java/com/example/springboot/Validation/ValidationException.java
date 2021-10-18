package com.example.springboot.Validation;

import com.example.springboot.utils.Validator.ArgumentInvalidResult;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常抛出的异常信息
 *
 * @Author: weicl
 * @Date : 2021/6/5 15:34
 * @Modified By :
 */
@Getter
@Setter
public class ValidationException extends RuntimeException {

    /**
     *  错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 记录异常信息
     */
    private List<com.example.springboot.utils.Validator.ArgumentInvalidResult> invalidResultList = Lists.newArrayList();

    /**
     *
     * @param errorCode
     */
    public ValidationException (String errorCode) {
        super(errorCode);
        this.errorCode = errorCode;
    }

    /**
     *
     * @param errorCode
     * @param errorMsg
     */
    public ValidationException(String errorCode,String errorMsg) {
        super(errorCode);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }


    /**
     *
     * @param field 字段
     * @param defaultMessage 异常信息
     */
    public void addErrInfo(String field,String defaultMessage){
        addErrInfo(field,null,defaultMessage);
    }

    /**
     *
     * @param field 字段
     * @param rejectedValue 拒绝值
     * @param defaultMessage 异常信息
     */
    public void addErrInfo(String field,Object rejectedValue,String defaultMessage){
        com.example.springboot.utils.Validator.ArgumentInvalidResult argumentInvalidResult = new ArgumentInvalidResult();
        argumentInvalidResult.setField(field);
        argumentInvalidResult.setRejectedValue(rejectedValue);
        argumentInvalidResult.setDefaultMessage(defaultMessage);
        invalidResultList.add(argumentInvalidResult);
    }
}
