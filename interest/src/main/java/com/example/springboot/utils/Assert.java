package com.example.springboot.utils;

import com.example.springboot.model.enums.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;
import java.util.Objects;

/**
 * @author chen
 * @create 2020-12-01
 **/
public class Assert {

    public static void isTrue(boolean express, ResultCodeEnum resultCodeEnum) {
        if (express) {
            resultCodeEnum.throwIt();
        }
    }

    public static void notTrue(boolean express, ResultCodeEnum resultCodeEnum) {
        if (!express) {
            resultCodeEnum.throwIt();
        }
    }

    public static void isNull(Object object, ResultCodeEnum resultCodeEnum) {
        if (object == null) {
            resultCodeEnum.throwIt();
        }
    }

    public static void notNull(Object object, ResultCodeEnum resultCodeEnum) {
        if (object != null) {
            resultCodeEnum.throwIt();
        }
    }

    public static void isEquals(Object one, Object two, ResultCodeEnum resultCodeEnum) {
        if (Objects.equals(one, two)) {
            resultCodeEnum.throwIt();
        }
    }

    public static void notEquals(Object one, Object two, ResultCodeEnum resultCodeEnum) {
        if (!Objects.equals(one, two)) {
            resultCodeEnum.throwIt();
        }
    }

    public static void notEmpty(String object, ResultCodeEnum resultCodeEnum) {
        if (StringUtils.isNotEmpty(object)) {
            resultCodeEnum.throwIt();
        }
    }

    public static void isEmpty(String object, ResultCodeEnum resultCodeEnum) {
        if (StringUtils.isEmpty(object)) {
            resultCodeEnum.throwIt();
        }
    }
}
