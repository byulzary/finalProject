package com.example.finalproject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

//public static String ip = "10.100.102.39";
//public static String un = "admin";
//public static String pass = "admin123456";
//public static String db = "AndroidProj";

public class ConSQL {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        String ip = "10.0.2.2", port = "1433", db = "AndroidProj", username = "sa", password = "PaSSw0rd!";

        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + username + ";" + "password=" + password + ";";
            con = DriverManager.getConnection(ConnectURL);
        } catch (Exception e) {
            Log.e("error: ", e.getMessage());
        }
        return con;
    }
}
