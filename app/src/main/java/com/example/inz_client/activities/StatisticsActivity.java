package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.inz_client.R;
import com.example.inz_client.models.MonthlyStatistic;

public class StatisticsActivity extends AppCompatActivity {

    Button mb;
    Button pb;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mb = findViewById(R.id.btnMB);
        pb = findViewById(R.id.btnPB);
        token = getIntent().getStringExtra("token");

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MonthlyStatisticActivity.class);
                i.putExtra("token",token);
                startActivity(i);
            }
        });
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CategoryStatisticActivity.class);
                i.putExtra("token",token);
                startActivity(i);
            }
        });
    }
}
