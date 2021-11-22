package com.example.springboot.utils;

import com.example.springboot.utils.date.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author jzl
 * @create 2019-07-03
 **/
public class GsonSingle {

    private GsonSingle() {
    }

    private static class generalGson {
        private static final Gson GSON = new Gson();
    }

    private static class dateGson {
        private static final Gson GSON = new GsonBuilder().setDateFormat(DateUtil.DATE_TIME_FORMAT_T).create();
    }

    public static Gson getGson() {
        return generalGson.GSON;
    }

    public static Gson getDateGson() {
        return dateGson.GSON;
    }
}
