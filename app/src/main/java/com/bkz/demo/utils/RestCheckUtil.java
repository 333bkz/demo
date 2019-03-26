package com.bkz.demo.utils;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class RestCheckUtil {

    /**
     * 使用 Map按key进行排序
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(new MapKeyComparatorUtil());
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 获取签名————适用内部通信接口
     */
    public static String getSign(final Map<String, String> params) {
        try {
            Map<String, String> map = new HashMap<String, String>();
            if (params != null) {
                map.putAll(params);
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    // 去除空值参数，不加入签名
                    if (TextUtils.isEmpty(entry.getValue()))
                        map.remove(entry.getKey());
                }
            }
            map = RestCheckUtil.sortMapByKey(map);// 排序
            StringBuffer signUrl = new StringBuffer();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getKey().equals("serialVersionUID"))
                    continue;
                signUrl.append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            }
            return MD5Util.MD5(signUrl.substring(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
