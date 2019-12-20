package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.inz_client.R;
import com.example.inz_client.models.MonthlyStatistic;
import com.example.inz_client.presenters.StatisticsPresenter;
import com.example.inz_client.views.IMStatisticView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class MonthlyStatisticActivity extends AppCompatActivity implements IMStatisticView {

    BarChart chart;
    StatisticsPresenter presenter;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_statistic);
        chart = findViewById(R.id.barchart);
        presenter = new StatisticsPresenter(null,this);
        token = getIntent().getStringExtra("token");
        presenter.getMonthly(token);
    }

    @Override
    public void showdata(List<MonthlyStatistic> list, String year) {
        ArrayList values = new ArrayList();
        ArrayList labels = new ArrayList();
        for(int i=0;i<list.size();i++){
            values.add(new BarEntry(i,list.get(i).getSum()));
            labels.add(list.get(i).getMonth());
        }
        BarDataSet dataSet = new BarDataSet(values,"Zestawienie sumy miesięcznych wydatkow z roku "+year);
        BarData data = new BarData(dataSet);
        chart.setData(data);
        XAxis xaxis = chart.getXAxis();
        xaxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateXY(3000, 3000);
    }
}
