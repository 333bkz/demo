package com.bkz.demo.utils;

import android.content.Context;
import android.support.annotation.NonNull;

import com.tencent.mmkv.MMKV;

import java.util.Map;

public class MMKVUtils {
    private static MMKVUtils sInstance;
    private final MMKV mmkv;

    public static void init(String spName, Context context) {
        if (sInstance == null) {
            synchronized (SPUtils.class) {
                if (sInstance == null) {
                    sInstance = new MMKVUtils(context.getApplicationContext(), spName);
                }
            }
        }
    }

    private MMKVUtils(Context context, String name) {
        mmkv = MMKV.defaultMMKV();
        MMKV.initialize(context);
    }

    public static MMKVUtils getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("The object is not initialized");
        }
        return sInstance;
    }

    public void put(@NonNull String key, String value) {
        mmkv.encode(key, value);
    }

    public String getString(@NonNull String key, String defaultValue) {
        return mmkv.decodeString(key, defaultValue);
    }

    public String getString(@NonNull String key) {
        return mmkv.decodeString(key);
    }

    public void put(@NonNull String key, int value) {
        mmkv.encode(key, value);
    }

    public int getInt(@NonNull String key, int defaultValue) {
        return mmkv.decodeInt(key, defaultValue);
    }

    public int getInt(@NonNull String key) {
        return mmkv.decodeInt(key);
    }

    public void put(@NonNull String key, long value) {
        mmkv.encode(key, value);
    }

    public long getLong(@NonNull String key, long defaultValue) {
        return mmkv.decodeLong(key, defaultValue);
    }

    public long getLong(@NonNull String key) {
        return mmkv.decodeLong(key);
    }

    public void put(@NonNull String key, float value) {
        mmkv.encode(key, value);
    }

    public float getFloat(@NonNull String key, float defaultValue) {
        return mmkv.decodeFloat(key, defaultValue);
    }

    public float getFloat(@NonNull String key) {
        return mmkv.decodeFloat(key);
    }

    public void put(@NonNull String key, boolean value) {
        mmkv.encode(key, value);
    }

    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return mmkv.decodeBool(key, defaultValue);
    }

    public boolean getBoolean(@NonNull String key) {
        return mmkv.decodeBool(key);
    }

    public Map<String, ?> getAll() {
        return mmkv.getAll();
    }

    public boolean contains(@NonNull String key) {
        return mmkv.containsKey(key);
    }

    public void remove(@NonNull String key) {
        mmkv.removeValueForKey(key);
    }

    public void clear() {
        mmkv.clearAll();
    }

    public void clearMemoryCache() {
        mmkv.clearMemoryCache();
    }

    public void onExit() {
        MMKV.onExit();
    }
}
