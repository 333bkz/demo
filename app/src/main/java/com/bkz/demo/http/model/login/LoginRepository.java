package com.bkz.demo.http.model.login;


import android.arch.lifecycle.LiveData;

import com.bkz.demo.http.Api;
import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.base.BaseRepository;

public class LoginRepository extends BaseRepository<LoginData> {

    public LoginRepository() {
        super();
    }

    public LiveData<BaseData<LoginData>> login() {
        return request(Api.login()).send().getLiveData();
    }
}
