package com.example.inz_client.presenters;

import com.example.inz_client.models.Product;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.IReceiptView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReceiptPresenter {
    IReceiptView receiptView;
    String token;
    IApi apiClient;
    public static double total = -1;

    public ReceiptPresenter(IReceiptView receiptView, String token) {
        this.receiptView = receiptView;
        this.token = token;
        apiClient = ApiClient.getClinet().create(IApi.class);
    }

    public void takeData(){
        apiClient.getProducts(token).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                double total = 0;
                for(Product i : response.body())
                    total+= i.getAmount()*i.getPrice();
                ReceiptPresenter.total = total;
                receiptView.showData(response.body());
                receiptView.showTotalPrice();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        });
    }
}
