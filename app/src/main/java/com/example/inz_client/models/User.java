package com.example.inz_client.models;

import androidx.annotation.Nullable;

import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.models.Auth.Token;

public class User {
    String email;
    String password;
    @Nullable
    String first_name;
    @Nullable
    String last_name;
    @Nullable //nie trzeba do tworzenia
    String token;

    public User(String email, String password, @Nullable String first_name, @Nullable String last_name) {
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.token = null;
    }
    public static User loadLoginCredentails(LoginCredentials credentials){
        User user = new User(credentials.getEmail(),credentials.getPassword(),null,null);
        return user;
    }

    public void attachToken(String t){
        token = t;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(@Nullable String first_name) {
        this.first_name = first_name;
    }

    @Nullable
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(@Nullable String last_name) {
        this.last_name = last_name;
    }

    @Nullable
    public String getToken() {
        return token;
    }

    public void setToken(@Nullable String token) {
        this.token = token;
    }
}
