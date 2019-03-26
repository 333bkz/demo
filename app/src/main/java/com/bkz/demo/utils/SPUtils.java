package com.bkz.demo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.Map;

@SuppressWarnings("unused")
public class SPUtils {
    private static SPUtils sInstance;
    private final SharedPreferences sp;

    public static void init(String spName, Context context) {
        if (sInstance == null) {
            synchronized (SPUtils.class) {
                if (sInstance == null) {
                    sInstance = new SPUtils(context.getApplicationContext(), spName);
                }
            }
        }
    }

    private SPUtils(Context context, String spName) {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static SPUtils getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("The object is not initialized");
        }
        return sInstance;
    }

    public void put(@NonNull String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    public String getString(@NonNull String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public void put(@NonNull String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public int getInt(@NonNull String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public void put(@NonNull String key, long value) {
        sp.edit().putLong(key, value).apply();
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    public void put(@NonNull String key, float value) {
        sp.edit().putFloat(key, value).apply();
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public void put(@NonNull String key, boolean value) {
        sp.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    public boolean contains(@NonNull String key) {
        return sp.contains(key);
    }

    public void remove(@NonNull String key) {
        sp.edit().remove(key).apply();
    }

    public void clear() {
        sp.edit().clear().apply();
    }
}