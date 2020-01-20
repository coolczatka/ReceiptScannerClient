package com.example.inz_client.presenters;

import android.net.Uri;
import android.os.FileUtils;
import android.util.Log;

import com.example.inz_client.models.ImageResponse;
import com.example.inz_client.models.Receipt;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.IMainView;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {
    IApi client;
    IMainView view;

    public MainPresenter(IMainView view) {
        this.view = view;
        client = ApiClient.getClinet().create(IApi.class);
    }

    @Override
    public void takeData(String token) {
        client.getReceipts(token).enqueue(new Callback<List<Receipt>>() {
            @Override
            public void onResponse(Call<List<Receipt>> call, Response<List<Receipt>> response) {
                view.showData(response.body());
            }

            @Override
            public void onFailure(Call<List<Receipt>> call, Throwable t) {

            }
        });
    }

    @Override
    public void uploadImage(String token, File image) {
        try {
            String path = image.getPath();
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), image);
            MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("picture",
                    image.getName(), requestFile);
            client.uploadPhoto(token, multipartBody).enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    Log.d("tagito",response.body().getDate());
                    view.startProductFormActivity(response.body());
                }
                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {
                    Log.d("tagito","nie wyszlo");
                }
            });
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
