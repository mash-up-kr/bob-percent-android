package com.mash.pig.bobpercent.app.sign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.app.FoodOrHisActivity;
import com.mash.pig.bobpercent.model.UserModel;
import com.mash.pig.bobpercent.rest.PigClient;
import com.mash.pig.bobpercent.rest.user.UserService;
import com.mash.pig.bobpercent.util.Defines;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jimin on 2016-08-27.
 */
public class LoginActivity extends AppCompatActivity {


    @BindView(R.id.et_login_email) EditText etEmail;
    @BindView(R.id.et_login_pw) EditText etPw;


    @OnClick(R.id.btn_login)
    void onLoginClicked() {
        String email = etEmail.getText().toString();
        String pw = etPw.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pw)) {
            Toast.makeText(this, "나머지를 입력해 주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        login(email, pw);
    }


    @OnClick(R.id.btn_login_sign_up)
    void onSignUpClicked() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivityForResult(intent, Defines.REQUEST_SIGN_UP);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        ButterKnife.bind(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Defines.REQUEST_SIGN_UP && resultCode == RESULT_OK) {
            startPendingActivity();
        }
    }

    private void login(String email, String password) {
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);

        UserService userService = PigClient.getService(UserService.class);
        Call<UserModel> call = userService.login(user);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if (response.code() == 200) {
                    // SUCCESS
                    UserModel user = response.body();
                    BobPercentApplication.getInstance().storeUser(user.getUserId(), user.isPending());

                    if (user.isPending()) {
                        startPendingActivity();
                    } else {
                        BobPercentApplication.getInstance().storeUser(user.getUserId(), user.getCode(), user.isPending());
                        Intent intent = new Intent(LoginActivity.this, FoodOrHisActivity.class);
                        startActivity(intent);
                    }


                    return;
                }

                // ERROR
                Toast.makeText(LoginActivity.this, "회원가입에 실패하셨습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                // ERROR
                Toast.makeText(LoginActivity.this, "회원가입에 실패하셨습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void startPendingActivity() {
        Intent intent = new Intent(this, PendingActivity.class);
        startActivity(intent);
        finish();
    }

}
