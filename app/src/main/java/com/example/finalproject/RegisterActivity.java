package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    Connection connection;
    private Button registerButton, registerCancelButton;
    TextView status;
    EditText email, password, name, phone, address;
    //EditText  id;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.editEmailReg);
        password = (EditText) findViewById(R.id.editPasswordReg);
        registerButton = findViewById(R.id.buttonFinishReg);
        name = (EditText) findViewById(R.id.editTextRegName);
        phone = (EditText) findViewById(R.id.editTextPhone);
        address = (EditText) findViewById(R.id.editTextRegAddress);
        registerCancelButton = findViewById(R.id.cancelRegiButton);

        String statementCount = "select * from users";
        int count = 0;

        ConSQL c = new ConSQL();
        connection = c.conclass();
        try {
            Statement countStmt = connection.createStatement();
            ResultSet rs = countStmt.executeQuery(statementCount);
            while (rs.next()) {
                count++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        int finalCount = count;
        registerButton.setOnClickListener(v -> {

            try {
                int myId = finalCount + 1;
                String myname = name.getText().toString();
                String myemail = email.getText().toString();
                String myphone = phone.getText().toString();
                String mypassword = password.getText().toString();
                String myaddress = address.getText().toString();
                String statement = "insert into users(userID," +
                        " name," +
                        " phone, " +
                        "address," +
                        "email," +
                        "password" +
                        ")" +
                        " values " +
                        "(?,?,?,?,?,?)";

                PreparedStatement stmt = connection.prepareStatement(statement);
                stmt.setInt(1, myId);
                stmt.setString(2, myname);
                stmt.setString(3, myphone);
                stmt.setString(4, myaddress);
                stmt.setString(5, myemail);
                stmt.setString(6, mypassword);
                stmt.executeUpdate();
                connection.close();
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("error: ", e.getMessage(), e);
            }
        });
    }
}