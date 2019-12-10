package com.example.inz_client.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.inz_client.R;
import com.example.inz_client.models.User;
import com.example.inz_client.presenters.AuthPresenter;
import com.example.inz_client.presenters.IAuthPresenter;
import com.example.inz_client.views.IRegisterView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    EditText firstNameField;
    EditText lastNameField;
    EditText emailField;
    EditText passwordField;
    EditText confirmPasswordField;
    TextView errorText;

    IAuthPresenter authPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        authPresenter = new AuthPresenter(null,this);
        firstNameField = findViewById(R.id.first_name);
        lastNameField = findViewById(R.id.last_name);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.password);
        confirmPasswordField = findViewById(R.id.confirm_password);
        errorText = findViewById(R.id.error);
    }

    public void register_button(View view){
        if(validateRegister()) {
            String first_name = firstNameField.getText().toString();
            String last_name = lastNameField.getText().toString();
            String email = emailField.getText().toString();
            String password = passwordField.getText().toString();

            User u = new User(email,password,first_name,last_name);
            authPresenter.register(u);
        }
    }

    @Override
    public boolean validateRegister() {
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        String confirmedPassword = passwordField.getText().toString();

        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = emailPattern.matcher(email);

        boolean result = true;
        if(!matcher.matches()) {
            errorText.setText("Wpisz poprawny email");
            result = false;
        }
        if(!password.equals(confirmedPassword) && !password.equals("")){
            errorText.setText("Hasła nie są takie same");
            result = false;
        }

        return result;
    }

    @Override
    public void registerSuccess() {
        finish();

    }

    @Override
    public void registerError() {
        errorText.setText("Coś poszło nie tak");
    }
}
