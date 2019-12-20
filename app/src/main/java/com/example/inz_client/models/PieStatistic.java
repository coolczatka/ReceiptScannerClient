package com.example.inz_client.models;

public class PieStatistic {
    String category;
    float percent;

    public PieStatistic(String category, float percent) {
        this.category = category;
        this.percent = percent;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }
}
