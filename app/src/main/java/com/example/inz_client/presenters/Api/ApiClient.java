package com.example.inz_client.presenters.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Retrofit.*;

public class ApiClient {
    public static String BASE_URL = "http://192.168.1.20:8000/inz/";
    private static Retrofit retrofit = null;

    public static Retrofit getClinet(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
