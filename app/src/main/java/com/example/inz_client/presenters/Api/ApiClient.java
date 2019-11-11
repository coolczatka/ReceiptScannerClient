package com.example.inz_client.presenters.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Retrofit.*;

public class ApiClient {
    public static String BASE_URL = "http://127.0.0.1/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClinet(){
        if(retrofit==null){
            //retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();

        Builder builder = new Builder();
        builder = builder.baseUrl(BASE_URL);
        retrofit = builder.build();
        }
        return retrofit;
    }
}
