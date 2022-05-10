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

import com.example.finalproject.Connection.ConnectionClass;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {
    Connection connection;
    private Button registerButton;
    TextView status;
    EditText email, password, verPass, name, id, phone, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.editEmailReg);
        password = (EditText) findViewById(R.id.editPasswordReg);
        verPass = (EditText) findViewById(R.id.editPassowrdRegVer);
        registerButton = findViewById(R.id.buttonFinishReg);
        status = findViewById(R.id.statusTextView);
        name = (EditText) findViewById(R.id.editTextRegName);
        id = (EditText) findViewById(R.id.editIDReg);
        phone = (EditText) findViewById(R.id.editTextPhone);
        address = (EditText) findViewById(R.id.editTextRegAddress);

        registerButton.setOnClickListener(v -> {
            ConSQL c = new ConSQL();
            connection = c.conclass();
            try {
                int myId = Integer.parseInt(id.getText().toString());
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
                //String statement = "Select * from users";
                PreparedStatement stmt = connection.prepareStatement(statement);
                stmt.setInt(1, myId);
                stmt.setString(2, myname);
                stmt.setString(3, myphone);
                stmt.setString(4, myaddress);
                stmt.setString(5, myemail);
                stmt.setString(6, mypassword);
                //  ResultSet set = stmt.executeUpdate(statement);
                stmt.executeUpdate();
//                while (set.next()) {
//                    status.setText(set.getString(2));
//                    Log.d("myTag", set.getString(2));
//                }
                connection.close();
            } catch (Exception e) {
                Log.e("error: ", e.getMessage(), e);
            }
        });
    }
}