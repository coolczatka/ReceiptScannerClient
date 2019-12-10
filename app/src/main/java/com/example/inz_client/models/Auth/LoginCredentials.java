package com.example.inz_client.models.Auth;

public class LoginCredentials {
    String username;
    String password;

    public String getEmail() {
        return username;
    }

    public void setEmail(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginCredentials(String email, String password) {
        this.username = email;
        this.password = password;
    }
}
