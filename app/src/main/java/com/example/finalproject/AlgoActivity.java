package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlgoActivity extends AppCompatActivity {
    Connection connection;
    Bundle bundle;
    int listId;
    ArrayList<NaviProduct> naviProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        naviProducts = new ArrayList<>();
        bundle = new Bundle();
        bundle = getIntent().getExtras();
        listId = bundle.getInt("listId");
        System.out.println("listId:" + listId);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);
        ConSQL c = new ConSQL();
        connection = c.conclass();
        try {
            createProductArray(naviProducts, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void createProductArray(ArrayList<NaviProduct> naviProducts, Connection connection) throws SQLException {
        String query = " select item_id, name, amount, " +
                "loc_x, loc_y,loc_z, itemDesc " +
                "from user_list_items join items" +
                " on user_list_items.item_id=items.itemID" +
                " where list_id='39'";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
            int loc_x = rs.getInt("loc_x");
            int loc_y = rs.getInt("loc_y");
            int loc_z = rs.getInt("loc_z");
            int amount = rs.getInt("amount");
            int id = rs.getInt("item_id");
            NaviProduct np = new NaviProduct(name, loc_x, loc_y, loc_z, id,
                    amount);
            naviProducts.add(np);
        }
        System.out.println("Products:");
        for (int i = 0; i < naviProducts.size(); i++) {
            System.out.println(naviProducts.get(i).id);
        }
    }
}