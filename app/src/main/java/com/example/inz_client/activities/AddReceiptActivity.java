package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.inz_client.R;

public class AddReceiptActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button submit;
    Button addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receipt);
        addProduct = findViewById(R.id.addProduct);
        submit = findViewById(R.id.submitBtn);
        recyclerView = findViewById(R.id.rvProductForms);

    }
}
