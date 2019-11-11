package com.example.inz_client.presenters;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;
import com.example.inz_client.models.User;
import com.example.inz_client.presenters.Api.ApiClient;
import com.example.inz_client.presenters.Api.IAuthApi;
import com.example.inz_client.views.ILoginView;
import com.example.inz_client.views.IRegisterView;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthPresenter implements IAuthPresenter {

    IAuthApi client;
    ILoginView loginView;
    IRegisterView registerView;
    User loggedUser;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public AuthPresenter(@Nullable ILoginView lView, @Nullable IRegisterView rView) {
        loginView = lView;
        registerView = rView;
        loggedUser = null;
        client = ApiClient.getClinet().create(IAuthApi.class);
    }

    @Override
    public void performLogin(final LoginCredentials credentials) {
        //musi być w osobnym wątku
        Call<Token> tokenCall = client.login(credentials);
        try {
            Response<Token> response = tokenCall.execute();
            String token = response.body().getToken();
            if(token != null) {
                loggedUser = User.loadLoginCredentails(credentials);
                loggedUser.attachToken(token);
                loginView.loginSuccess();
            }
            else{
                loginView.loginError("cos zle");
            }
        }
        catch (IOException e){
            Log.e("Auth",e.getMessage());
        }
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

    }
}
