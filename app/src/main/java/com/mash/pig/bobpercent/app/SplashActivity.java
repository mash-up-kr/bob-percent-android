package com.mash.pig.bobpercent.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        // get
        int userId = BobPercentApplication.getInstance().getUserId();
    }

}
