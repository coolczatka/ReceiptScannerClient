package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.inz_client.R;
import com.example.inz_client.models.Product;
import com.example.inz_client.presenters.ReceiptPresenter;
import com.example.inz_client.views.IReceiptView;
import com.example.inz_client.views.ProductsRecyclerViewAdapter;

import java.util.List;

public class ReceiptDetailsActivity extends AppCompatActivity implements IReceiptView {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ProductsRecyclerViewAdapter adapter;
    TextView sum;

    ReceiptPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_details);

        recyclerView = findViewById(R.id.productrv);
        sum = findViewById(R.id.sum);

        presenter = new ReceiptPresenter(this,getIntent().getStringExtra("token"),
                getIntent().getStringExtra("receipt_id"));
        presenter.takeData();

    }

    @Override
    public void showData(List<Product> list) {
        layoutManager = new LinearLayoutManager(this);
        adapter = new ProductsRecyclerViewAdapter(list);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showTotalPrice() {
        if(ReceiptPresenter.total == -1)
            sum.setText("Nie udało się policzyć sumy");
        else
            sum.setText("Razem: "+String.valueOf(ReceiptPresenter.total));
    }
}
