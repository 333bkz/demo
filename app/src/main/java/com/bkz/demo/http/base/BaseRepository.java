package com.bkz.demo.http.base;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class BaseRepository<T> {
    private BaseSubscriber<T> subscribe;
    private Flowable<BaseData<T>> flowable;

    public BaseSubscriber<T> send() {
        flowable.subscribeOn(Schedulers.io())
                .doOnCancel(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribe);
        return subscribe;
    }

    public BaseRepository() {
        subscribe = new BaseSubscriber<>();
    }

    public BaseRepository<T> request(Flowable<BaseData<T>> flowable) {
        this.flowable = flowable;
        return this;
    }
}
