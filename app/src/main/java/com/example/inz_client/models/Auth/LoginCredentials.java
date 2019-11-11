package com.example.inz_client.models.Auth;

public class LoginCredentials {
    String email;
    String password;

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

    public LoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
