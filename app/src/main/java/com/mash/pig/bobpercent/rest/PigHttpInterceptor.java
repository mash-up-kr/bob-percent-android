package com.mash.pig.bobpercent.rest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by bigstark on 2016. 8. 27..
 */

public class PigHttpInterceptor implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .header("Accept", "application/json")
                .method(chain.request().method(), chain.request().body())
                .build();

        return chain.proceed(request);
    }

}
