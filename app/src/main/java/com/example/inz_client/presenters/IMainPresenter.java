package com.example.inz_client.presenters;

import android.net.Uri;

import java.io.File;

public interface IMainPresenter {
    public void takeData(String token);
    public void uploadImage(String token, File image);
}
