package com.example.inz_client.models.Auth;

public class Token {
    public Token(String token) {
        this.token = token;
    }

    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
