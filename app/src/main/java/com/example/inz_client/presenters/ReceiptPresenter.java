package com.example.inz_client.presenters;

import android.util.Log;

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
    String receipt_id;
    IApi apiClient;
    public static double total = -1;

    public ReceiptPresenter(IReceiptView receiptView, String token, String receipt_id) {
        this.receiptView = receiptView;
        this.token = token;
        this.receipt_id = receipt_id;
        apiClient = ApiClient.getClinet().create(IApi.class);
    }

    public void takeData(){
        Log.d("produkty","Token: "+token);
        Log.d("produkty","Receipt_id: "+receipt_id);
        apiClient.getProducts(token,receipt_id).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                Log.d("produkty","Response");
                double total = 0;
                for(int i=0;i<response.body().size();i++) {
                    Log.d("produkty","Petla"+i);
                    total += response.body().get(i).getAmount() * response.body().get(i).getPrice();
                }
                total = Math.round(total*100.0)/100.0;
                ReceiptPresenter.total = total;
                receiptView.showData(response.body());
                receiptView.showTotalPrice();
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("produkty","Fajlure");
            }
        });
    }
}
