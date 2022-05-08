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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    private Button registerButton;
    TextView status;
    EditText email, password, verPass;
    Connection con;
    Statement stmt;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = (EditText) findViewById(R.id.editEmailReg);
        password = (EditText) findViewById(R.id.editPasswordReg);
        verPass = (EditText) findViewById(R.id.editPassowrdRegVer);
        registerButton = findViewById(R.layout.activity_main);
        status = findViewById(R.id.statusTextView);

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.buttonFinishReg:
//                new registerUser.execute("");
//                break;
//
//        }
//    }

    @SuppressLint("NewAPI")
    public java.sql.Connection connectionClass(String email, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        java.sql.Connection connection = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server + "/" + database + ";email=" + email + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception e) {
            Log.e("sql conn error", e.getMessage());

        }
        return connection;
    }

    public class registerUser extends AsyncTask<String, String, String> {

        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("Sending to DB");
        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Successfully Registered");
            email.setText("");
            password.setText("");
            verPass.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                con = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
                if (con == null) {
                    z = "Check your internet connection";
                } else {
                    String sql = "INSERT INTO users (email, password) VALUES ('" + email.getText() + "','" + password.getText() + "')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }

            } catch (Exception e) {

            }

            return z;
        }
    }

}