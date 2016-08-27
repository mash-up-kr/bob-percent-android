package com.mash.pig.bobpercent.app.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mash.pig.bobpercent.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public class PendingActivity extends AppCompatActivity {


    @OnClick(R.id.btn_pending_input)
    void onInputClicked() {
        Intent intent = new Intent(PendingActivity.this, InputCodeActivity.class);
        startActivity(intent);
        finish();
    }


    @OnClick(R.id.btn_pending_notice)
    void onNoticeClicked() {
        Intent intent = new Intent(PendingActivity.this, GenCodeActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_main);
        ButterKnife.bind(this);
    }
}
