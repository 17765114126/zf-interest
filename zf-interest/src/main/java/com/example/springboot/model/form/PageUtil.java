package com.example.springboot.model.form;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jzl
 * @create 2019-06-28
 **/
public class PageUtil {

    /**
     * page 类型转换
     *
     * @param page
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Page<R> convert(IPage<T> page, Function<T, R> function) {
        Page<R> result = new Page<>();

        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());

        List<R> list = page.getRecords().stream().map(function).collect(Collectors.toList());
        result.setRecords(list);

        return result;
    }

    /**
     * page 类型转换 (并行流方式, 要注意执行的是线程安全的操作)
     *
     * @param page
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> Page<R> convertParallel(IPage<T> page, Function<T, R> function) {
        Page<R> result = new Page<>();

        result.setCurrent(page.getCurrent());
        result.setSize(page.getSize());
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());

        List<R> list = page.getRecords().parallelStream().map(function).collect(Collectors.toList());
        result.setRecords(list);

        return result;
    }

    /**
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> LambdaQueryWrapper<T> getQueryWrapper(T entity) {
        LambdaQueryWrapper wrapper = new QueryWrapper<T>().lambda();
        wrapper.setEntity(entity);
        return wrapper;
    }

    /**
     * @param entity
     * @param <T>
     * @return
     */
    public static <T> LambdaUpdateWrapper<T> getUpdateWrapper(T entity) {
        LambdaUpdateWrapper wrapper = new UpdateWrapper<T>().lambda();
        wrapper.setEntity(entity);
        return wrapper;
    }

    public static <T> Page<T> toPage(IPage<T> iPage) {
        Page<T> page = new Page(iPage.getCurrent(), iPage.getSize(), iPage.getTotal());
        page.setRecords(iPage.getRecords());
        return page;
    }
}
