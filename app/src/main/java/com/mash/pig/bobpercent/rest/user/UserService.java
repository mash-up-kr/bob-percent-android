package com.mash.pig.bobpercent.rest.user;

import com.mash.pig.bobpercent.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by bigstark on 2016. 8. 27..
 */

public interface UserService {

    @POST("/user")
    Call<UserModel> signUp(@Body UserModel user);


    @POST("/user/login")
    Call<UserModel> login(@Body UserModel user);


    @POST("/user/gen/{userId}")
    Call<UserModel> genCode(@Path("userId") int userId);


    @POST("/user/match/{userId}/{code}")
    Call<UserModel> matchUser(@Path("userId") int userId, @Path("code") String code);
}
