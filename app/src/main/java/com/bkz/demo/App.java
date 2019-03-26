package com.bkz.demo;

import android.app.Application;

import com.bkz.demo.http.Http;
import com.bkz.demo.utils.PropertyUtils;

public class App extends Application {

    private boolean debug = false;

    @Override
    public void onCreate() {
        super.onCreate();
        debug = Boolean.parseBoolean(PropertyUtils.getPropertyValue(this, "debug"));

        Http.getInstance().init("http://192.168.93.231:9080/");
    }
}
