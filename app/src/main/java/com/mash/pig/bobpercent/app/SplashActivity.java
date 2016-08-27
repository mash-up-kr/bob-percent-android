package com.mash.pig.bobpercent.app;

import android.app.Notification;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;

import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        // get
        //int userId = BobPercentApplication.getInstance().getUserId();





            // SPLASH_DISPLAY_LENGTH 뒤에 메인 액티비티를 실행시키고 종료한다.
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 메인 액티비티를 실행하고 로딩화면을 죽인다.
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    SplashActivity.this.startActivity(intent);
                    SplashActivity.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }
    }


