package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inz_client.R;
import com.example.inz_client.models.PieStatistic;
import com.example.inz_client.presenters.StatisticsPresenter;
import com.example.inz_client.views.IPStatisticView;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;


import java.util.ArrayList;
import java.util.List;


public class CategoryStatisticActivity extends AppCompatActivity implements IPStatisticView {

    PieChart chart;
    StatisticsPresenter presenter;
    String token;
    @Override
    public void showData(List<PieStatistic> list, String year) {
        ArrayList<PieEntry> values = new ArrayList<PieEntry>();
        for(int i=0;i<list.size();i++){
            values.add(new PieEntry(list.get(i).getPercent(),list.get(i).getCategory()));
        }
        PieDataSet dataSet = new PieDataSet(values,"Rozkład wydatków w ciągu roku "+year);
        PieData data = new PieData(dataSet);
        chart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateXY(3000, 3000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_statistic);
        chart = findViewById(R.id.piechart);
        presenter = new StatisticsPresenter(this,null);
        token = getIntent().getStringExtra("token");
        presenter.getPie(token);
    }
}
