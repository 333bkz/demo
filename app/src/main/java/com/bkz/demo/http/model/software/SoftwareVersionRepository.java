package com.bkz.demo.http.model.software;

import android.arch.lifecycle.LiveData;

import com.bkz.demo.http.Api;
import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.base.BaseRepository;

public class SoftwareVersionRepository extends BaseRepository<SoftwareVersion> {

    public SoftwareVersionRepository() {
        super();
    }

    public LiveData<BaseData<SoftwareVersion>> getSoftwareVersion() {
        return request(Api.getSoftwareVersion()).send().getLiveData();
    }
}