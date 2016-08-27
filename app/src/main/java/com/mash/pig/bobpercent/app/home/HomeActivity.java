package com.mash.pig.bobpercent.app.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.app.quiz.QuizActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Jimin on 2016-08-28.
 */
public class HomeActivity extends AppCompatActivity {

    @OnClick(R.id.rl_home_food_match)
    void onMatchClicked() {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.rl_home_view_history)
    void onHistoryClicked() {
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        ButterKnife.bind(this);
    }
}
