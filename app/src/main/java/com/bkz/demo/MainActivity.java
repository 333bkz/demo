package com.bkz.demo;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.bkz.demo.http.base.BaseData;
import com.bkz.demo.http.model.software.SoftwareVersion;
import com.bkz.demo.http.model.software.SoftwareVersionRepository;
import com.bkz.demo.utils.CyclePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CyclePagerAdapter adapter;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.jumpToNextPager();
            handler.sendEmptyMessageDelayed(0, 1_500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);

        List<Drawable> drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.drawable.image1));
        drawables.add(getResources().getDrawable(R.drawable.image2));
        drawables.add(getResources().getDrawable(R.drawable.image3));
        drawables.add(getResources().getDrawable(R.drawable.image4));

        adapter = new CyclePagerAdapter(this, drawables, viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 2_000);
                return false;
            }
        });

        if (adapter.getCount() > 1) {
            viewPager.setCurrentItem(1);
            startTimer();
        }
    }

    private void startTimer() {
        handler.sendEmptyMessageDelayed(0, 2_000);
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

    public void toRefreshView(View view) {
//        startActivity(new Intent(this, RefreshViewActivity.class));
    }
}
