package com.bkz.demo.http;

import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.annotation.Nullable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Http {
    public static final String TAG = "Http";
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private ApiService apiService;

    private static class SingletonHolder {
        private static final Http INSTANCE = new Http();
    }

    public static Http getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public static ApiService getApiService() {
        if (SingletonHolder.INSTANCE.apiService == null) {
            throw new NullPointerException("First initialize this object");
        }
        return SingletonHolder.INSTANCE.apiService;
    }

    private Http() {
        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(@Nullable String message) {//访问网络请求，和服务端响应请求时。将数据拦截并输出
                        Log.e(TAG, message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(10_000, TimeUnit.SECONDS)
                .readTimeout(10_000, TimeUnit.SECONDS)
                .writeTimeout(10_000, TimeUnit.SECONDS)
                .addNetworkInterceptor(new StethoInterceptor())
//                .addInterceptor(new AddCookiesInterceptor()) //这部分
//                .addInterceptor(new ReceivedCookiesInterceptor()) //这部分
//                .hostnameVerifier(new SafeHostnameVerifier())
//                .sslSocketFactory(CcsApplication.getSslSocket(), new SafeTrustManager())
                //
                .build();
    }

    public void init(String base_url) {
        retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }
}
