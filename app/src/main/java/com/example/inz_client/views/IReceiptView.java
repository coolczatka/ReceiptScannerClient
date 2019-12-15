package com.example.inz_client.views;

import com.example.inz_client.models.Product;

import java.util.List;

public interface IReceiptView {
    void showData(List<Product> list);
    void showTotalPrice();
}
