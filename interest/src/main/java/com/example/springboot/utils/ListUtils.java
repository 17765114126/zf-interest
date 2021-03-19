package com.example.springboot.utils;


import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author <a href="mailto:clf@dora.com">clf</a>
 * @Description: ListUtils
 * @Date Create on 2019-06-17 13:56
 * @since version1.0
 */
public class ListUtils {

    public static <T> List<List<T>> batchList(List<T> sourceList, int batchCount) {
        if (CollectionUtils.isEmpty(sourceList) || batchCount <= 0) {
            return null;
        }
        List<List<T>> returnList = new ArrayList<>(sourceList.size() / batchCount + 1);
        // 从第0个索引开始
        int startIndex = 0;
        int endIndex;
        while (startIndex < sourceList.size()) {
            if (sourceList.size() - batchCount < startIndex) {
                endIndex = sourceList.size();
            } else {
                endIndex = startIndex + batchCount;
            }
            returnList.add(sourceList.subList(startIndex, endIndex));
            // 下一批
            startIndex = startIndex + batchCount;
        }
        return returnList;
    }

    /**
     * 对象list 根据属性去重
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>(16);
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

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

     * 循环截取某页列表进行分页

     * @param dataList 分页数据

     * @param pageSize  页面大小

     * @param currentPage   当前页面

     */

    public static List<String> page(List<String> dataList, int pageSize,int currentPage) {

        List<String> currentPageList = new ArrayList<>();

        if (dataList != null && dataList.size() > 0) {

            int currIdx = (currentPage > 1 ? (currentPage - 1) * pageSize : 0);

            for (int i = 0; i < pageSize && i < dataList.size() - currIdx; i++) {

                String data = dataList.get(currIdx + i);

                currentPageList.add(data);

            }

        }

        return currentPageList;

    }
}
