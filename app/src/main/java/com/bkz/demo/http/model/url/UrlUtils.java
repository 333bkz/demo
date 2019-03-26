package com.bkz.demo.http.model.url;

import android.text.TextUtils;

import com.bkz.demo.http.Api;
import com.bkz.demo.http.base.BaseData;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;

public class UrlUtils {
    private static UrlUtils sInstance;

    private List<UrlData> urlDataList;

    private UrlUtils() {
    }

    public static UrlUtils getInstance() {
        if (sInstance == null) {
            sInstance = new UrlUtils();
        }
        return sInstance;
    }

    public Flowable<String> getUrl(final String key) {
        String url = queryLocalUrl(key);
        if (TextUtils.isEmpty(url)) {
            return Api.getUrls()
                    .observeOn(AndroidSchedulers.mainThread())//在主线程更新和查询urls
                    .map(new Function<BaseData<List<UrlData>>, String>() {
                        @Override
                        public String apply(BaseData<List<UrlData>> data) throws Exception {
                            if (data != null
                                    && data.isSuccess()
                                    && data.getData() != null
                                    && !data.getData().isEmpty()) {
                                saveUlrs(data.getData());
                                return queryLocalUrl(key);
                            }
                            return null;
                        }
                    });
        }
        return Flowable.just(url);
    }

    private void saveUlrs(List<UrlData> data) {
        urlDataList = data;
    }

    private String queryLocalUrl(String key) {
        if (urlDataList != null) {
            for (UrlData urlData : urlDataList) {
                if (urlData.name.equals(key)) {
                    return urlData.url;
                }
            }
        }
        return null;
    }
}
