package com.mash.pig.bobpercent.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonLoader {

    private volatile static GsonLoader instance;

    public static GsonLoader getInstance() {
        if (instance == null) {
            synchronized (GsonLoader.class) {
                if (instance == null) {
                    instance = new GsonLoader();
                }
            }
        }

        return instance;
    }


    public static String toJson(Object src) {
        return getInstance().getGson().toJson(src);
    }


    public static <T> T fromJson(String json, Class<T> classType) {
        return getInstance().getGson().fromJson(json, classType);
    }

    private Gson gson;


    public GsonLoader() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
    }

    public Gson getGson() {
        return gson;
    }

}