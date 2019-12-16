package com.example.inz_client.presenters.Api;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;
import com.example.inz_client.models.Product;
import com.example.inz_client.models.Receipt;
import com.example.inz_client.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IApi {

    //auth
    @POST("login")
    Call<Token> login(@Body LoginCredentials loginCredentials);
    @POST("users/")
    Call<User> register(@Body User user);

    //receipts
    @GET("receipts/")
    Call<List<Receipt>> getReceipts(@Header("Authorization") String token);
    @GET("products/{receipt_id}/")
    Call<List<Product>> getProducts(@Header("Authorization") String token, @Path("receipt_id") String receipt_id);
}
