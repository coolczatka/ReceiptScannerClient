package com.example.inz_client.presenters;

import com.example.inz_client.models.MonthlyStatistic;
import com.example.inz_client.models.PieStatistic;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.IMStatisticView;
import com.example.inz_client.views.IPStatisticView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticsPresenter {
    IApi client;
    IPStatisticView pStatisticView;
    IMStatisticView mStatisticView;

    public StatisticsPresenter(@Nullable IPStatisticView pStatisticView,@Nullable IMStatisticView mStatisticView) {
        this.client = ApiClient.getClinet().create(IApi.class);
        this.pStatisticView = pStatisticView;
        this.mStatisticView = mStatisticView;
    }

    public void getMonthly(String token){
        client.getMS(token, Calendar.getInstance().get(Calendar.YEAR)).enqueue(new Callback<List<MonthlyStatistic>>() {
            @Override
            public void onResponse(Call<List<MonthlyStatistic>> call, Response<List<MonthlyStatistic>> response) {
                mStatisticView.showdata(response.body(),String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
            }

            @Override
            public void onFailure(Call<List<MonthlyStatistic>> call, Throwable t) {

            }
        });
    }
    public void getPie(String token){
        client.getPie(token,Calendar.getInstance().get(Calendar.YEAR)).enqueue(new Callback<List<PieStatistic>>() {
            @Override
            public void onResponse(Call<List<PieStatistic>> call, Response<List<PieStatistic>> response) {
                pStatisticView.showData(response.body(),String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
            }

            @Override
            public void onFailure(Call<List<PieStatistic>> call, Throwable t) {

            }
        });
    }
}
