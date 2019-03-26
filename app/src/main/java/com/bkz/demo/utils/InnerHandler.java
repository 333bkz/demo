package com.bkz.demo.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

public abstract class InnerHandler<T> extends Handler {

    private WeakReference<T> weak;

    public InnerHandler(T t) {
        this.weak = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        T t = weak.get();
        if (t != null) {
            handleMessage(t, msg);
        }
    }

    public abstract void handleMessage(T t, Message msg);
}
