package com.bkz.demo.http;

import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.model.login.LoginData;
import com.bkz.demo.http.model.software.SoftwareVersion;
import com.bkz.demo.http.model.url.UrlData;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @POST
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded
    Flowable<BaseData<LoginData>> login(@Url String url, @FieldMap Map<String, String> map);

    @GET
    Flowable<BaseData<List<UrlData>>> getUrls(@Url String url, @QueryMap Map<String, String> map);

    @GET
    Flowable<BaseData<SoftwareVersion>> getSoftwareVersion(@Url String url, @QueryMap Map<String, String> map);
}
