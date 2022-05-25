package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity {
    Connection connection;
    private RecyclerView recyclerView;
    private int lid;
    Bundle bundle;
    private ArrayList<UserProduct> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_items);
        recyclerView = findViewById(R.id.recyclerViewPast);
        list = new ArrayList<>();
        bundle = getIntent().getExtras();
        lid = bundle.getInt("listid");
        System.out.println("LIST ID: " + lid);
        ConSQL c = new ConSQL();
        connection = c.conclass();
        try {
            setItemsInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setAdapter();
    }

    private void setItemsInfo() throws SQLException {
        String query = "select item_id, name, amount, itemDesc from user_list_items join items on user_list_items.item_id=items.itemID\n" +
                " where list_id=?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, lid);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String pName = rs.getString("name");
            int pID = rs.getInt("item_id");
            String pDesc = rs.getString("itemDesc");
            int pAmount = rs.getInt("amount");
            UserProduct up = new UserProduct(pName, pDesc, pID, 0, pAmount);
            list.add(up);
        }
    }

    private void setAdapter() {
        PastItemRecyclerAdapter adatper = new PastItemRecyclerAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adatper);
    }
}