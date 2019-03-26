package com.bkz.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonUtils {

    /**
     * json字符串通过Gson框架生成对象
     */
    public static <T> Object parseJson2Obj(String jsonData, Class<T> c) {
        if (null == jsonData) {
            return null;
        }
        Gson gson = new Gson();
        return gson.fromJson(jsonData, c);
    }

    /**
     * 将java对象转换成json对象
     */
    public static String parseObj2Json(Object obj) {

        if (null == obj) {
            return new JsonObject().toString();
        }
        Gson gson = new Gson();
        String objstr = gson.toJson(obj);
        return objstr;
    }
}
