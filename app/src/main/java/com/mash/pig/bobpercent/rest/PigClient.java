package com.mash.pig.bobpercent.rest;

import com.mash.pig.bobpercent.util.GsonLoader;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bigstark on 2016. 8. 27..
 */

public class PigClient {

    private static final String BASE_URL = "http://192.168.15.167:4567";


    private volatile static PigClient instance;

    public static <T> T getService(Class<T> service) {
        if (instance == null) {
            synchronized (PigClient.class) {
                if (instance == null) {
                    instance = new PigClient();
                }
            }
        }

        return instance.getOrCreate(service);
    }

    private Retrofit retrofit;
    private Map<Object, Object> serviceHashMap = new HashMap<>();

    protected PigClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new PigHttpInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(GsonLoader.getInstance().getGson()))
                .build();
    }


    // get api service from hashmap.
    // if not exist, create from retrofit object and cache to hashmap
    <T> T getOrCreate(Class<T> service) {
        T apiService = (T) serviceHashMap.get(service);
        if (apiService != null) {
            return apiService;
        }

        apiService = retrofit.create(service);
        serviceHashMap.put(service, apiService);
        return apiService;
    }

}
