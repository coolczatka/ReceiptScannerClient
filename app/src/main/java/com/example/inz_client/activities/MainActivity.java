package com.example.inz_client.activities;

import android.os.Bundle;

import com.example.inz_client.models.Receipt;
import com.example.inz_client.presenters.IMainPresenter;
import com.example.inz_client.presenters.MainPresenter;
import com.example.inz_client.views.IMainView;
import com.example.inz_client.views.ReceiptsRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.example.inz_client.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainView {

    IMainPresenter presenter;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rvReceipts);

        presenter = new MainPresenter(this);
        token = this.getIntent().getStringExtra("token");
        presenter.takeData(token);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void showData(List<Receipt> list) {
        adapter = new ReceiptsRecyclerViewAdapter(list,this,token);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
