package com.mash.pig.bobpercent.app.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.app.FoodOrHisActivity;
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
 * Created by bigstark on 2016. 8. 28..
 */

public class InputCodeActivity extends AppCompatActivity {

    @BindView(R.id.et_pending_code) EditText etCode;

    @OnClick(R.id.btn_pending_next)
    void onNextClicked() {
        String code = etCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            // ERROR
            Toast.makeText(InputCodeActivity.this, "코드를 채워주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        int userId = BobPercentApplication.getInstance().getUserId();
        UserService userService = PigClient.getService(UserService.class);
        Call<UserModel> call = userService.matchUser(userId, code);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200 && !response.body().isPending()) {

                    UserModel user = response.body();
                    BobPercentApplication.getInstance().storeUser(user.getUserId(), user.getCode(), user.isPending());

                    Intent intent = new Intent(InputCodeActivity.this, FoodOrHisActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                // ERROR
                Toast.makeText(InputCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // ERROR
                Toast.makeText(InputCodeActivity.this, "에러가 발생하였습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_code_main);
        ButterKnife.bind(this);
    }
}
