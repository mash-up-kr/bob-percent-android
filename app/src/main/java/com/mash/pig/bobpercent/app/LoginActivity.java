package com.mash.pig.bobpercent.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.mash.pig.bobpercent.BobPercentApplication;
import com.mash.pig.bobpercent.R;
import com.mash.pig.bobpercent.model.UserModel;
import com.mash.pig.bobpercent.rest.PigClient;
import com.mash.pig.bobpercent.rest.user.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jimin on 2016-08-27.
 */
public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }



    private void login(String name, String email, String password) {
        UserModel user = new UserModel();
        user.setName(name);
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

                    // TODO start pending activity
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


    public void onClickSignUp(View v)
    {
        switch(v.getId()){
            case R.id.btnSignUp:
                Intent intent1 = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent1);
                break;

        }



        }
}
