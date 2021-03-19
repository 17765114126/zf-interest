package com.example.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date: 2019/8/27
 * @Author: zhaofu
 * @Description: CopyUtil bean对象拷贝
 **/
public class CopyUtil {

    /**
     * 定义日志对象
     */
    private static final Logger logger = LoggerFactory.getLogger(CopyUtil.class);

    private static final ConcurrentHashMap<String, BeanCopier> CACHE_COPIER_MAP = new ConcurrentHashMap<>();

    /**
     * 集合复制
     *
     * @param sList
     * @param tClass
     * @return
     */
    public static <S, T> List<T> newCopyList(List<S> sList, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        if (CollectionUtils.isEmpty(sList)) {
            return list;
        }
        try {
            for (S s : sList) {
                T t = tClass.newInstance();
                BeanUtils.copyProperties(s, t);
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", tClass));
        }
    }

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     * @param converter converter转换器
     */
    public static <T> T copyObject(Object srcObj, T targetObj, Converter converter) {
        if (null == srcObj) {
            logger.error("复制对象时源对象为null!");
            return null;
        }

        if (null == targetObj) {
            logger.error("复制对象时目标对象为null!");
            return null;
        }
        BeanCopier bc = getBeanCopierInstance(srcObj.getClass(), targetObj.getClass(), converter);
        bc.copy(srcObj, targetObj, converter);
        return targetObj;
    }

    @Deprecated
    public static <T> T copy(Object srcObj, T targetObj, Converter converter) {
        if (null == srcObj) {
            logger.error("复制对象时源对象为null!");
            return null;
        }

        if (null == targetObj) {
            logger.error("复制对象时目标对象为null!");
            return null;
        }
        BeanCopier bc = BeanCopier.create(srcObj.getClass(), targetObj.getClass(), converter != null);
        bc.copy(srcObj, targetObj, converter);
        return targetObj;
    }

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     */
    public static <T> void copyObject(Object srcObj, T targetObj) {
        copyObject(srcObj, targetObj, null);
    }

    /**
     * 复制对象属性
     *
     * @param srcObj    源对象
     * @param targetObj 目标对象
     */
    public static <T> T copyObject(Object srcObj, Class<T> targetObj) {
        try {
            T t = targetObj.newInstance();
            copyObject(srcObj, t, null);
            return t;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetObj));
        }
    }


    /**
     * 复制列表中所有元素到新列表中.
     *
     * @param srcList            源列表
     * @param targetElementClass 目标列表元素class
     * @param <S>                源列表元素类型
     * @param <T>                目标列表元素类型
     * @return 目标列表
     */
    public static <S, T> List<T> copyList(List<S> srcList, Class<T> targetElementClass) {
        List<T> targetList = new ArrayList<T>();
        if (CollectionUtils.isEmpty(srcList)) {
            return targetList;
        }
        for (S src : srcList) {
            try {
                T target = targetElementClass.newInstance();
                CopyUtil.copyObject(src, target);
                targetList.add(target);
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format("Cannot instantiate an object of %s.", targetElementClass));
            }
        }
        return targetList;
    }

    private static <S, T> BeanCopier getBeanCopierInstance(Class<S> sourceClass, Class<T> targetClass, Converter converter) {
        String key = sourceClass.getName() + "#" + targetClass.getName();
        BeanCopier bc = CACHE_COPIER_MAP.get(key);
        if (null == bc) {
            bc = BeanCopier.create(sourceClass, targetClass, converter != null);
            CACHE_COPIER_MAP.put(key, bc);
        }
        return bc;
    }
}
