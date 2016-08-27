package com.mash.pig.bobpercent;

import android.app.Application;

/**
 * Created by bigstark on 2016. 8. 27..
 */

public class BobPercentApplication extends Application {

    private static BobPercentApplication instance;

    public static BobPercentApplication getInstance() {
        return instance;
    }


    public void storeUser(int userId, boolean pending) {
        getSharedPreferences("pig", 0).edit()
                .putInt("userId", userId)
                .putBoolean("pending", pending)
                .apply();
    }


    public void storeUser(int userId, String code, boolean pending) {
        getSharedPreferences("pig", 0).edit()
                .putInt("userId", userId)
                .putString("code", code)
                .putBoolean("pending", pending)
                .apply();
    }


    public int getUserId() {
        return getSharedPreferences("pig", 0).getInt("userId", 0);
    }


    public boolean isPending() {
        return getSharedPreferences("pig", 0).getBoolean("pending", true);
    }


    public String getCode() {
        return getSharedPreferences("pig", 0).getString("code", "");
    }



    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
