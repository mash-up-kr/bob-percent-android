package com.mash.pig.bobpercent.app.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.app.home.HomeActivity;
import com.mash.pig.bobpercent.model.UserModel;
import com.mash.pig.bobpercent.rest.PigClient;
import com.mash.pig.bobpercent.rest.user.UserService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jimin on 2016-08-27.
 */
public class GenCodeActivity extends AppCompatActivity {

    @BindView(R.id.tv_gen_code) TextView tvCode;


    @OnClick(R.id.btn_gen_next)
    void onNextClicked() {
        int userId = BobPercentApplication.getInstance().getUserId();
        UserService userService = PigClient.getService(UserService.class);
        Call<UserModel> call = userService.getUser(userId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200 && !response.body().isPending()) {

                    UserModel user = response.body();
                    BobPercentApplication.getInstance().storeUser(user.getUserId(), user.getCode(), user.isPending());

                    Intent intent = new Intent(GenCodeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                // ERROR
                Toast.makeText(GenCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // ERROR
                Toast.makeText(GenCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen_main);
        ButterKnife.bind(this);

        getCode();
    }


    private void getCode() {
        int userId = BobPercentApplication.getInstance().getUserId();
        UserService userService = PigClient.getService(UserService.class);
        Call<UserModel> call = userService.genCode(userId);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200) {
                    // SUCCESS, SET TEXT
                    UserModel user = response.body();
                    BobPercentApplication.getInstance().storeUser(user.getUserId(), user.getCode(), user.isPending());
                    tvCode.setText(user.getCode());
                    return;
                }

                // ERROR
                Toast.makeText(GenCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // ERROR
                Toast.makeText(GenCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
