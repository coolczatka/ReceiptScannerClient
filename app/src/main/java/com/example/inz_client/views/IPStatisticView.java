package com.example.inz_client.views;

import com.example.inz_client.models.PieStatistic;

import java.util.List;

public interface IPStatisticView {
    void showData(List<PieStatistic> list,String year);
}
