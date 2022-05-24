package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button registerButton, loginButton;
    EditText emailLogin, passwordLogin;
    Connection connection;

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
                int id = loginTry(emailLogin, passwordLogin);

                if (id == -1) {
                    Context context = getApplicationContext();
                    CharSequence text = "Password does not match email!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else if (id == 0) {
                    Context context = getApplicationContext();
                    CharSequence text = "No user found!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    Intent intent = new Intent(this, MenuActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putInt("uid", id);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
//                    startActivity(new Intent(this, MenuActivity.class));


                }
        }
    }

    private int loginTry(EditText emailLogin, EditText passwordLogin) {
        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();
        String loginStmt = "select * from users";
        ConSQL c = new ConSQL();
        connection = c.conclass();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(loginStmt);
            while (rs.next()) {
                String uEmail = rs.getString("email");
                String uPassword = rs.getString("password");
                if (uEmail.compareTo(email) == 0) {
                    if (uPassword.compareTo(password) == 0) {
                        int id = rs.getInt("userID");
                        return id;
                    } else return -1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}