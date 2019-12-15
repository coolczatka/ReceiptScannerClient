package com.example.inz_client.presenters;

import com.example.inz_client.models.Receipt;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.IMainView;

import java.util.LinkedList;
import java.util.List;

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
    public double calculateMounthlyExcome() {
        return 0;
    }

    @Override
    public double calculateForCategory() {
        return 0;
    }
}
