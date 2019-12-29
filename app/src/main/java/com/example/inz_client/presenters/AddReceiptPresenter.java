package com.example.inz_client.presenters;

import android.util.Log;

import com.example.inz_client.models.Product;
import com.example.inz_client.models.Receipt;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.IAddReceiptView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddReceiptPresenter {
    String token;
    IAddReceiptView view;
    IApi client;

    public AddReceiptPresenter(String token, IAddReceiptView view) {
        this.token = token;
        this.view = view;
        client = ApiClient.getClinet().create(IApi.class);
    }

    public void sendData(String shop, String date, final List<Product> products){
        Receipt r = new Receipt(shop,date);
        client.postReceipt(token,r).enqueue(new Callback<Receipt>() {
            @Override
            public void onResponse(Call<Receipt> call, Response<Receipt> response) {
                Receipt resp = response.body();
                for(Product p:products){
                    client.postProduct(token,resp.getId(),p).enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            Log.d("products","product added");
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {
                            Log.d("products","zle produkt");
                            view.error();
                        }
                    });
                    view.success();
                }
            }

            @Override
            public void onFailure(Call<Receipt> call, Throwable t) {
                Log.d("products","zle paragon");
                view.error();
            }
        });
    }
}
