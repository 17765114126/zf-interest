package com.example.springboot.templates;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 基本返回工具类
 */
public class BaseResultResponseUtil {

    public static BaseResult fail() {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(false);
        baseResult.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        baseResult.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        return baseResult;
    }

    public static BaseResult success() {
        BaseResult baseResult = new BaseResult();
        baseResult.setSuccess(true);
        baseResult.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        baseResult.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        return baseResult;
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setSuccess(true);
        baseResult.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        baseResult.setData(data);
        baseResult.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        return baseResult;
    }

    public static <T> BaseResult<T> fail(T data) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setSuccess(false);
        baseResult.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        baseResult.setData(data);
        baseResult.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        return baseResult;
    }

    /**
     * 分页返回
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> pageSuccess(IPage<T> body) {
        PagingResult result = new PagingResult();
        result.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        result.setSuccess(true);
        result.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        result.setPageNum((int) body.getCurrent());
        result.setPageSize((int) body.getSize());
        result.setTotal(body.getTotal());
        result.setData(body.getRecords());
        return result;
    }

    /**
     * 分页返回
     *
     * @param body
     * @param <T>
     * @return
     */
    public static <T> PagingResult<T> pageSuccess(List<T> body, long pageIndex, long pageSize, long total) {
        PagingResult result = new PagingResult();
        result.setRespCode(ResultCodeEnum.SUCCESS.getCode());
        result.setSuccess(true);
        result.setRespMsg(ResultCodeEnum.SUCCESS.getMessage());
        result.setPageNum((int) pageIndex);
        result.setPageSize((int) pageSize);
        result.setTotal(total);
        result.setData(body);
        return result;
    }

}
