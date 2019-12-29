package com.example.inz_client.presenters.Api;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;
import com.example.inz_client.models.ImageResponse;
import com.example.inz_client.models.MonthlyStatistic;
import com.example.inz_client.models.PieStatistic;
import com.example.inz_client.models.Product;
import com.example.inz_client.models.Receipt;
import com.example.inz_client.models.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    @POST("receipts/")
    Call<Receipt> postReceipt(@Header("Authorization") String token, @Body Receipt receipt);
    @POST("products/{receipt_id}/")
    Call<Product> postProduct(@Header("Authorization") String token, @Path("receipt_id") String receipt_id,@Body Product product);
    //upload
    @Multipart
    @POST("pictures/")
    Call<ImageResponse> uploadPhoto(@Header("Authorization") String token, @Part MultipartBody.Part picture);

    //statistics
    @GET("sum")
    Call<List<MonthlyStatistic>> getMS(@Header("Authorization") String token, @Query("year") int year);
    @GET("pie")
    Call<List<PieStatistic>> getPie(@Header("Authorization") String token, @Query("year") int year);
}
