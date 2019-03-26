package com.bkz.demo.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 签名
 */
public class SignUtils {

    /**
     * 除去数组中的空值和签名参数
     *
     * @param params 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> params) {

        Map<String, String> result = new HashMap<String, String>();

        if (params == null || params.size() <= 0) {
            return result;
        }

        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value == null || value.equals("")
                    || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {
        Map<String, String> filterParams = paraFilter(params);
        List<String> keys = new ArrayList<String>(filterParams.keySet());
        //排序
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = filterParams.get(key);
            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    /**
     * 获得签名
     *
     * @param token
     * @param params
     * @return
     */
    public static String getSign(String token, Map<String, String> params) {
        String createLinkString = createLinkString(params);
        String strLink = createLinkString + token;
        String result = MD5Util.MD5(strLink);
        return result;
    }

    /**
     * 获得签名
     *
     * @param token
     * @param params
     * @return
     */
    public static String getSignWithoutKey(String token, Map<String, String> params) {
        Map<String, String> filterParams = paraFilter(params);
        List<String> keys = new ArrayList<String>(filterParams.keySet());
        //排序
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = filterParams.get(key);
            prestr.append(value);
        }
        prestr.append(token);
        String strLink = prestr.toString();
        return MD5Util.MD5(strLink);
    }

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
     * 区块链共享设备专用
     * <p>
     * 获取签名————适用内部通信接口
     */
    public static String getSign(Map<String, String> params, @Nullable String token) {
        try {
            if (params != null) {
                params = sortMapByKey(params);//排序
                StringBuilder signUrl = new StringBuilder();
                Log.e("singUtils-------- ", "" + params.entrySet());
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (TextUtils.isEmpty(entry.getValue())//排除空值
                            || entry.getKey().equals("serialVersionUID")//排除serialVersionUID
                            || entry.getKey().equals("requestHead"))//排除requestHead
                        continue;
                    signUrl.append(entry.getValue());
                    //.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
                if (!TextUtils.isEmpty(token)) {
                    signUrl.append(token);
                }
                return MD5Util.MD5(signUrl.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static class MapKeyComparatorUtil implements Comparator<String> {
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
