package com.mash.pig.bobpercent.app.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigstark.gunner.library.Bullet;
import com.bigstark.gunner.library.Gunner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.model.FoodCompModel;
import com.mash.pig.bobpercent.model.FoodModel;
import com.mash.pig.bobpercent.model.GameModel;
import com.mash.pig.bobpercent.rest.PigClient;
import com.mash.pig.bobpercent.rest.game.GameService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public class QuizActivity extends AppCompatActivity {

    @BindView(R.id.iv_quiz_top) SimpleDraweeView ivFoodTop;
    @BindView(R.id.iv_quiz_bottom) SimpleDraweeView ivFoodBottom;

    @BindView(R.id.rl_quiz_match_top) View viewMatchTop;
    @BindView(R.id.rl_quiz_match_bottom) View viewMatchBottom;

    @BindView(R.id.tv_quiz_count) TextView tvCount;
    @BindView(R.id.pb_quiz) ProgressBar pbQuiz;


    private GameModel game;
    private FoodCompModel currentComp;

    private List<FoodModel> selectedFoods = new ArrayList<>();


    @OnClick(R.id.iv_quiz_top)
    void onTopClicked() {
        viewMatchTop.setVisibility(View.VISIBLE);
        viewMatchBottom.setVisibility(View.GONE);

        selectedFoods.add(currentComp.getFirstFood());
        selectedFoods.remove(currentComp.getSecondFood());
    }


    @OnClick(R.id.iv_quiz_bottom)
    void onBottomClicked() {
        viewMatchTop.setVisibility(View.GONE);
        viewMatchBottom.setVisibility(View.VISIBLE);

        selectedFoods.add(currentComp.getSecondFood());
        selectedFoods.remove(currentComp.getFirstFood());
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_main);
        ButterKnife.bind(this);

        getQuiz();
    }

    private void getQuiz() {
        int userId = BobPercentApplication.getInstance().getUserId();

        GameService gameService = PigClient.getService(GameService.class);
        Call<GameModel> call = gameService.join(userId);
        call.enqueue(new Callback<GameModel>() {
            @Override
            public void onResponse(Call<GameModel> call, Response<GameModel> response) {
                if (response.code() == 200) {
                    startGame(response.body());
                    return;
                }

                Toast.makeText(QuizActivity.this, "퀴즈를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<GameModel> call, Throwable t) {
                Toast.makeText(QuizActivity.this, "퀴즈를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private int index = 0;


    private void startGame(GameModel game) {
        this.game = game;
        pbQuiz.setVisibility(View.GONE);
        setItem();
    }


    void setItem() {
        if (index == game.getComps().size()) {
            reportResult();
            return;
        }

        currentComp = game.getComps().get(index);

        FoodModel foodTop = currentComp.getFirstFood();
        FoodModel foodBottom = currentComp.getSecondFood();

        viewMatchTop.setVisibility(View.GONE);
        viewMatchBottom.setVisibility(View.GONE);

        ivFoodTop.setImageURI(foodTop.getImageLink());
        ivFoodBottom.setImageURI(foodBottom.getImageLink());

        tvCount.setText("");

        Gunner.shoot(this);
        index++;
    }



    @Bullet(sequence = 1, delay = 1000)
    private void count5() {
        tvCount.setText(String.valueOf(5));
    }


    @Bullet(sequence = 2, delay = 1000)
    private void count4() {
        tvCount.setText(String.valueOf(4));
    }


    @Bullet(sequence = 3, delay = 1000)
    private void count3() {
        tvCount.setText(String.valueOf(3));
    }


    @Bullet(sequence = 4, delay = 1000)
    private void count2() {
        tvCount.setText(String.valueOf(2));
    }


    @Bullet(sequence = 5, delay = 1000)
    private void count1() {
        tvCount.setText(String.valueOf(1));
    }


    @Bullet(sequence = 6, delay = 1000)
    private void callSetItem() {
        setItem();
    }


    private void reportResult() {
        int userId = BobPercentApplication.getInstance().getUserId();

        GameService gameService = PigClient.getService(GameService.class);
        Call<ResponseBody> call = gameService.saveResult(game.getGameId(), userId, selectedFoods);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() / 100 == 2) {
                    startResultActivity();
                    return;
                }

                //error
                Toast.makeText(QuizActivity.this, "결과를 불러오는데 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //error
                Toast.makeText(QuizActivity.this, "결과를 불러오는데 에러가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


    private void startResultActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("gameId", game.getGameId());
                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}
