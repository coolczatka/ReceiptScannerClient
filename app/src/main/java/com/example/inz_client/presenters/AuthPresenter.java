package com.example.inz_client.presenters;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;
import com.example.inz_client.models.User;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IApi;
import com.example.inz_client.views.ILoginView;
import com.example.inz_client.views.IRegisterView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter implements IAuthPresenter {

    IApi client;
    ILoginView loginView;
    IRegisterView registerView;
    public static String token = null;

    public String getLoggedUserToken() {
        return token;
    }

    public AuthPresenter(@Nullable ILoginView lView, @Nullable IRegisterView rView) {
        loginView = lView;
        registerView = rView;
        client = ApiClient.getClinet().create(IApi.class);
    }

    @Override
    public void performLogin(final LoginCredentials credentials) {
        Call<Token> tokenCall = client.login(credentials);
        tokenCall.enqueue(new Callback<Token>() {

            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                try {
                    token = "Token "+response.body().getToken();
                    loginView.loginSuccess();
                }catch (NullPointerException e) {
                    loginView.loginError("Błędne dane logowania");
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("tag","blad");
                loginView.loginError("Błędne dane logowania");
            }
        });
    }

    @Override
    public void register(User user) {
        Call<User> registerCall = client.register(user);
        registerCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()!=201){
                    Log.e("AuthPresenter",String.valueOf(response.code()));
                    registerView.registerError();
                }
                else {
                    Log.i("AuthPresenter", "Created");
                    registerView.registerSuccess();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("AuthPresenter","Not Created");
                registerView.registerError();
            }
        });
    }

    @Override
    public void logout() {
        token = null;
    }
}
