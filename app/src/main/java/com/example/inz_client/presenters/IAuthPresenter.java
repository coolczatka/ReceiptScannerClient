package com.example.inz_client.presenters;

import androidx.annotation.Nullable;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.User;

public interface IAuthPresenter {
    String token = "";

    void performLogin(LoginCredentials credentials);
    void register(User user);
    void logout();
    User getLoggedUser();
}
