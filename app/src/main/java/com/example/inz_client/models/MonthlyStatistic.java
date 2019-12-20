package com.example.inz_client.models;

public class MonthlyStatistic {

    String month;
    float sum;

    public MonthlyStatistic(String month, float sum) {
        this.month = month;
        this.sum = sum;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
