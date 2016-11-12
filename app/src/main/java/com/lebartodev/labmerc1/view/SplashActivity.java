package com.lebartodev.labmerc1.view;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lebartodev.labmerc1.R;

import org.androidannotations.annotations.EActivity;


@EActivity(R.layout.activity_splash)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null)
            delayedActivityStart();

    }

    void delayedActivityStart() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ColorListActivity_.intent(SplashActivity.this).start();
                SplashActivity.this.finish();
            }
        }, 5000);

    }
}
