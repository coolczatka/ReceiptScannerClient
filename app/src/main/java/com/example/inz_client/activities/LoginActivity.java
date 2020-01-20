package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inz_client.R;
import com.example.inz_client.models.Auth.LoginCredentials;
import com.example.inz_client.presenters.AuthPresenter;
import com.example.inz_client.presenters.IAuthPresenter;
import com.example.inz_client.views.ILoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    IAuthPresenter authPresenter;
    EditText emailField;
    EditText passwordField;
    Button login;
    TextView error;
    TextView register_link;

    public void start_register_activity(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        authPresenter = new AuthPresenter(this,null);
        setContentView(R.layout.activity_login);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        error = findViewById(R.id.error);
        error.setTextColor(Color.RED);
    }

    public void log_in(View view){
        if(validateLogin()){
            LoginCredentials lc = new LoginCredentials(emailField.getText().toString(),
                    passwordField.getText().toString());
            authPresenter.performLogin(lc);
        }
    }

    @Override
    public boolean validateLogin() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = emailPattern.matcher(email);
        boolean result = true;
        if(!matcher.matches()) {
            error.setText("Wpisz poprawny email");
            result = false;
        }
        if(password.equals("")){
            error.setText("Podaj hasło");
            result = false;
        }
        return result;
    }

    @Override
    public void loginSuccess() {
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("token", authPresenter.getLoggedUserToken());
        startActivity(i);
        finish();
        error.setText("");
    }

    @Override
    public void loginError(String message) {
        error.setText(message);
    }
}
