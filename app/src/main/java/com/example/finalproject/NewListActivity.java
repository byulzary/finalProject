package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {
    Connection connection;

    private ArrayList<Product> productsList;
    public ArrayList<UserProduct> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    public Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        productsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);
        ConSQL c = new ConSQL();
        connection = c.conclass();
        saveBtn = findViewById(R.id.saveListButton);

        try {
            setProductInfo(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setAdapter();
        findViewById(R.id.saveListButton).setOnClickListener(v -> {
            try {
                saveListToDb(connection);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }

    private void saveListToDb(Connection connection) throws SQLException {
        for (int i = 0; i < RecyclerAdapter.userList.size(); i++) {
            String query =
                    "insert into user_list_items(id, list_id, item_id) values('1'," + "'1'," +
                            RecyclerAdapter.userList.get(0).getId() +
                            ")";
            System.out.println(RecyclerAdapter.userList.get(i).getId() + "|" + RecyclerAdapter.userList.get(i).getAmount());
            Statement st = connection.createStatement();
            st.executeUpdate(query);

        }
    }

    private void setAdapter() {
        RecyclerAdapter adatper = new RecyclerAdapter(productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adatper);
    }

    private void setProductInfo(Connection connection) throws SQLException {
        String statement = "select it.name, item_id, it.price, it.itemDesc from " +
                "(supermarket super inner join supermarket_items s_i on super.id=s_i.super_id inner join items as it on s_i.item_id=it.itemID) where super.id='1'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(statement);
        while (rs.next()) {
            String pName = rs.getString("name");
            int pID = rs.getInt("item_id");
            String pDesc = rs.getString("itemDesc");
            float pPrice = rs.getFloat("price");
            Product p = new Product(pName, pDesc, pID, pPrice);
            productsList.add(p);
        }
    }
}