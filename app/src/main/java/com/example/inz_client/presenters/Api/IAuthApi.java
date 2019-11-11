package com.example.inz_client.presenters.Api;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;
import com.example.inz_client.models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IAuthApi {

    @POST("login")
    Call<Token> login(@Body LoginCredentials loginCredentials);

    @POST("register")
    Call<User> register(@Body User user);
}
