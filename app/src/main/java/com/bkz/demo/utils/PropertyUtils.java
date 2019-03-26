package com.bkz.demo.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {

    /**
     * 根据key获取assets/config.properties里面的值
     */
    public static String getPropertyValue(Context context, String key){
        try {
            Properties props = new Properties();
            InputStream input = context.getAssets().open("config.properties");
            props.load(input);
            return props.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
