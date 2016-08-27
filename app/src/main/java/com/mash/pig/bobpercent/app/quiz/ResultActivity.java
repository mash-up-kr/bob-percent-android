package com.mash.pig.bobpercent.app.quiz;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.model.GameResultModel;
import com.mash.pig.bobpercent.rest.PigClient;
import com.mash.pig.bobpercent.rest.game.GameService;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public class ResultActivity extends AppCompatActivity {

    private int gameId;


    @BindView(R.id.tv_result_percent) TextView tvPercent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_amin);
        ButterKnife.bind(this);

        gameId = getIntent().getIntExtra("gameId", 0);
        tvPercent.setText("72%");
    }


    private void getResult() {
        int userId = BobPercentApplication.getInstance().getUserId();

        GameService gameService = PigClient.getService(GameService.class);
        Call<GameResultModel> call = gameService.getResult(gameId, userId);
        call.enqueue(new Callback<GameResultModel>() {
            @Override
            public void onResponse(Call<GameResultModel> call, Response<GameResultModel> response) {
                if (response.code() == 200) {
                    GameResultModel gameResultModel = response.body();
                    setResult(gameResultModel);
                    return;
                }

                // error
                Toast.makeText(ResultActivity.this, "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<GameResultModel> call, Throwable t) {
                // error
                Toast.makeText(ResultActivity.this, "에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private void setResult(GameResultModel result) {
        tvPercent.setText(result.getPercent() + "%");
    }
}
