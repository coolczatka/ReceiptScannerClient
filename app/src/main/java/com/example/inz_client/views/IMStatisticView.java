package com.example.inz_client.views;

import com.example.inz_client.models.MonthlyStatistic;

import java.util.List;

public interface IMStatisticView {
    void showdata(List<MonthlyStatistic> list,String year);
}
