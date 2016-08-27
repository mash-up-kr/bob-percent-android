package com.mash.pig.bobpercent.rest.game;

import com.mash.pig.bobpercent.model.FoodModel;
import com.mash.pig.bobpercent.model.GameModel;
import com.mash.pig.bobpercent.model.GameResultModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by bigstark on 2016. 8. 28..
 */

public interface GameService {

    @POST("/game/join/{userId}")
    Call<GameModel> join(@Path("userId") int userId);


    @POST("/game/{gameId}/user/{userId}")
    Call<ResponseBody> saveResult(@Path("gameId") int gameId, @Path("userId") int userId, @Body List<FoodModel> selectItems);


    @GET("/game/{gameId}/user/{userId}")
    Call<GameResultModel> getResult(@Path("gameId") int gameId, @Path("userId") int userId);
}
