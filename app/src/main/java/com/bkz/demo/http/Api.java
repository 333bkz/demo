package com.bkz.demo.http;

import android.text.TextUtils;

import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.model.RequestHead;
import com.bkz.demo.http.model.login.LoginData;
import com.bkz.demo.http.model.software.SoftwareVersion;
import com.bkz.demo.http.model.url.UrlData;
import com.bkz.demo.http.model.url.UrlUtils;
import com.bkz.demo.utils.JsonUtils;
import com.bkz.demo.utils.MD5Util;
import com.bkz.demo.utils.RestCheckUtil;
import com.bkz.demo.utils.SignUtils;

import org.reactivestreams.Publisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class Api {
    public static final String TAG = "Api";

    /**
     * 区块链接口参数url拼接
     * url + "/" +"参数值" + "/" + "参数值" + ....
     */
    protected static String stitchUrl(String url, Map<String, String> params) {
        Map<String, String> map = SignUtils.sortMapByKey(params);//通过key排序
        StringBuilder urlSb = new StringBuilder(url);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (TextUtils.isEmpty(entry.getValue())//排除空值
                    || entry.getKey().equals("serialVersionUID")//排除serialVersionUID
//                    || entry.getKey().equals("sign")//排除sign
                    )
                continue;
            urlSb.append("/")
                    .append(entry.getValue());
//                        .append(URLEncoder.encode(value, "UTF-8"));//转码
        }
        return urlSb.toString().replace(" ", "%20");
    }


    private static String appendParams(String url, Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("?");
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                sb.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private static RequestHead getRequestHead(String restKey) {
        RequestHead requestHead = new RequestHead();
        requestHead.appID = "346b81a32e7007eccadf60252bb599f0";
        requestHead.lanCode = "zh-cn";
        requestHead.osVersion = 1;
        requestHead.loginID = 100;
        requestHead.sessionID = "";
        requestHead.softwareVersion = "4.0.0";
        requestHead.imeiNum = "";
        requestHead.restKey = restKey;
        requestHead.platfromType = 0;
        return requestHead;
    }

    private static String getSign(RequestHead requestHead) {
        Map<String, String> map = requestHead.convertMap();
        return RestCheckUtil.getSign(map);
    }

    public static Flowable<BaseData<LoginData>> login() {
        HashMap<String, String> params = new HashMap<>();
        params.put("username", "qqqqqq");
        final String dateTime = "2018-09-29 18:13:22";
        params.put("password", MD5Util.MD5(MD5Util.MD5("qqqqqq") + dateTime));
        params.put("dateTime", dateTime);
//        params.put("serialNo", "982792481500");
        params.put("serialNo", "983290018400");
//        return Flowable.create(new FlowableOnSubscribe<BaseData<LoginData>>() {
//            @Override
//            public void subscribe(FlowableEmitter<BaseData<LoginData>> emitter) throws Exception {
//                NBLog.e(TAG, Thread.currentThread().getName());
//                Thread.sleep(5000);
//                emitter.onNext(null);
//            }
//        }, BackpressureStrategy.ERROR);
        return Http.getApiService().login(
                stitchUrl("http://192.168.93.231:9080/app/user/login", params),
                params);
    }

    public static Flowable<BaseData<List<UrlData>>> getUrls() {
        String url = "https://app.hesvit.com/rest/client/queryClientServiceUrl.shtml";
        Map<String, String> params = new HashMap<>();
        RequestHead requestHead = getRequestHead("h1_client_query_service_urls");
        String sign = getSign(requestHead);
        String requestHeadJson = JsonUtils.parseObj2Json(requestHead);
        String requestBodyJson = "{}";
        params.put("requestHead", requestHeadJson);
        params.put("requestBody", requestBodyJson);
        params.put("sign", sign);
        return Http.getApiService().getUrls(url, params);
    }

    public static Flowable<BaseData<SoftwareVersion>> getSoftwareVersion() {
        return UrlUtils.getInstance().getUrl("h1_client_get_software_version")
                .observeOn(Schedulers.io())
                //指定 io 线程 请求网络
                //可以多次指定订阅者接收线程 每调用一次 observerOn()下游的线程就会切换一次
                .flatMap(new Function<String, Publisher<BaseData<SoftwareVersion>>>() {
                    @Override
                    public Publisher<BaseData<SoftwareVersion>> apply(String url) throws Exception {
                        if (!TextUtils.isEmpty(url)) {
                            Map<String, String> params = new HashMap<>();
                            RequestHead requestHead = getRequestHead("h1_client_get_software_version");
                            String sign = getSign(requestHead);
                            String requestHeadJson = JsonUtils.parseObj2Json(requestHead);
                            String requestBodyJson = "";
                            params.put("requestHead", requestHeadJson);
                            params.put("requestBody", requestBodyJson);
                            params.put("sign", sign);
                            return Http.getApiService().getSoftwareVersion(url, params);
                        }
                        return null;
                    }
                });
    }
}