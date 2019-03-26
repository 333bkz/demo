package com.bkz.demo.http.base;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;

public class BaseLiveData<T> extends LiveData<T> {
    public static final String TAG = "BaseLiveData";

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
    }

    @Override
    public void removeObserver(@NonNull Observer<T> observer) {
        super.removeObserver(observer);
    }

    @Override
    public void removeObservers(@NonNull LifecycleOwner owner) {
        super.removeObservers(owner);
    }
}
