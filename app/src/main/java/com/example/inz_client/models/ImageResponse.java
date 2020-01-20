package com.example.inz_client.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ImageResponse implements Parcelable {
    String shop;
    String date;
    List<Product> products;

    public ImageResponse(String shop, String date, List<Product> products) {
        this.shop = shop;
        this.date = date;
        this.products = products;
    }

    protected ImageResponse(Parcel in) {
        shop = in.readString();
        date = in.readString();
        products = in.createTypedArrayList(Product.CREATOR);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(shop);
        parcel.writeString(date);
        parcel.writeTypedList(products);
    }

    public static final Creator<ImageResponse> CREATOR = new Creator<ImageResponse>() {
        @Override
        public ImageResponse createFromParcel(Parcel in) {
            return new ImageResponse(in);
        }

        @Override
        public ImageResponse[] newArray(int size) {
            return new ImageResponse[size];
        }
    };

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
