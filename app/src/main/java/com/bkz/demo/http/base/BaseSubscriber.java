package com.bkz.demo.http.base;

import android.app.Dialog;
import android.arch.lifecycle.LiveData;

import com.bkz.demo.http.exception.ApiException;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class BaseSubscriber<T> implements Subscriber<BaseData<T>> {

    public static final int SUCCESS_CODE = 0;

    private Dialog dialog;

    private BaseLiveData<BaseData<T>> data;

    public BaseSubscriber() {
        data = new BaseLiveData<BaseData<T>>();
    }

    public LiveData<BaseData<T>> getLiveData() {
        return data;
    }

    public void set(BaseData<T> t) {
        this.data.setValue(t);
    }

    public void post(BaseData<T> t) {
        this.data.postValue(t);
    }

    public void onFinish(BaseData<T> t) {
        set(t);
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(1);
        if (dialog != null
                && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onNext(BaseData<T> t) {
        if (t != null) {
            if (t.getCode() == SUCCESS_CODE) {
                onFinish(t);
            } else {
                ApiException ex = ApiException.handleException(t.getCode(), t.getMessage());
                onError(ex);
            }
        } else {
            ApiException ex = ApiException.handleException(-1, "");
            onError(ex);
        }
    }

    @Override
    public void onError(Throwable t) {
        BaseData<T> baseData = new BaseData<T>();
        if (t instanceof ApiException) {
            baseData.setCode(((ApiException) t).getCode());
            baseData.setMessage(t.getMessage());
        } else {
            baseData.setCode(-1);
            baseData.setMessage(null);
        }
        onFinish(baseData);
    }

    @Override
    public void onComplete() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
