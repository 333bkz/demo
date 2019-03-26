package com.bkz.demo;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.model.software.SoftwareVersion;
import com.bkz.demo.http.model.software.SoftwareVersionRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getSoftwareVersion(View view) {
        new SoftwareVersionRepository()
                .getSoftwareVersion()
                .observe(this, new Observer<BaseData<SoftwareVersion>>() {
                    @Override
                    public void onChanged(@Nullable BaseData<SoftwareVersion> data) {
                        if (data != null) {
                            Log.e("MainActivity", data.toString());
                        } else {
                            Log.e("MainActivity", "请求失败");
                        }
                    }
                });
    }

    public void toWebView(View view) {
        startActivity(new Intent(this, WebActivity.class));
    }
}
