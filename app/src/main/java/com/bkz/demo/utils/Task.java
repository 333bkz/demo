package com.bkz.demo.utils;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class Task<Param, Progress, Result> {
    public static final String TAG = "Task";
    public static final int SUCCESS = 0;
    public static final int ERROR = -1;

    protected Subscription subscription;

    private Consumer<Progress[]> progressConsumer;
    private Flowable<Progress[]> progressFlowable;
    private Disposable progressDisposable;

    @SafeVarargs
    public final void execute(final Param... params) {
        Flowable.create(new FlowableOnSubscribe<Result>() {
            @Override
            public void subscribe(FlowableEmitter<Result> emitter) throws Exception {
                emitter.onNext(doInBackground(params));
                emitter.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Result>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        subscription = s;
                        onPreExecute();
                        s.request(1);
                        //s.cancel();
                    }

                    @Override
                    public void onNext(Result result) {
                        onPostExecute(SUCCESS, result);
                    }

                    @Override
                    public void onError(Throwable t) {
                        onPostExecute(ERROR, null);
                    }

                    @Override
                    public void onComplete() {
                        Task.this.onComplete();
                    }
                });
    }

    @MainThread
    protected void onPreExecute() {

    }

    @WorkerThread
    protected abstract Result doInBackground(Param... params);

    @SafeVarargs
    @MainThread
    protected final void onProgressUpdate(Progress... values) {

    }

    @MainThread
    protected void onPostExecute(int resultCode, Result result) {

    }

    @MainThread
    protected void onComplete() {

    }

    public void cancel() {
        if (subscription != null) {
            subscription.cancel();
        }

        if (progressDisposable != null) {
            progressDisposable.dispose();
        }
    }

    @SafeVarargs
    protected final void publishProgress(final Progress... values) {
        if (progressConsumer == null) {
            progressConsumer = new Consumer<Progress[]>() {
                @Override
                public void accept(Progress[] progresses) throws Exception {
                    onProgressUpdate(progresses);
                }
            };
        }

        if (progressFlowable == null) {
            progressFlowable = Flowable.create(new FlowableOnSubscribe<Progress[]>() {
                @Override
                public void subscribe(FlowableEmitter<Progress[]> emitter) throws Exception {
                    emitter.onNext(values);
                }
            }, BackpressureStrategy.DROP).observeOn(AndroidSchedulers.mainThread());
        }

        progressDisposable = progressFlowable.subscribe(progressConsumer);
    }
}
