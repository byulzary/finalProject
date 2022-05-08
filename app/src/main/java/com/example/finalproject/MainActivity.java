package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button registerButton, loginButton;
    EditText emailLogin, passwordLogin;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.layout.activity_menu);
        registerButton = findViewById(R.layout.activity_register);
        emailLogin = findViewById(R.id.editEmail);
        passwordLogin = findViewById(R.id.editPassword);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonRegister:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.buttonLogin:
                int status = loginTry(emailLogin, passwordLogin);
                startActivity(new Intent(this, MenuActivity.class));
                break;
        }
    }

    private int loginTry(EditText emailLogin, EditText passwordLogin) {

        return 0;
    }
}