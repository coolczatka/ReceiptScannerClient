package com.example.inz_client.views;

public interface ILoginView {
    boolean validateLogin();
    void loginSuccess();
    void loginError(String message);

}
