package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PastListsActivity extends AppCompatActivity {
    Connection connection;

    private ArrayList<PastLists> pastLists;
    private RecyclerView recyclerViewPast;
    Bundle bundle;
    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        uid = bundle.getInt("uid");
        System.out.println("uid past lists: " + uid);
        setContentView(R.layout.activity_past_lists);
        pastLists = new ArrayList<>();
        recyclerViewPast = findViewById(R.id.recyclerViewPast);
        ConSQL c = new ConSQL();
        connection = c.conclass();
        try {
            setListsInfo(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        setAdapter();

    }

    private void setListsInfo(Connection connection) throws SQLException {
        String stmt = "select ul.name, ul.list_id from user_lists as ul left outer join " +
                "user_lists_rel as ulr on(ul.list_id=ulr.list_id) where ulr.user_id=" + uid;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(stmt);
        while (rs.next()) {
            String plName = rs.getString("name");
            int plId = rs.getInt("list_id");
            PastLists pl = new PastLists(plName, plId);
            pastLists.add(pl);

        }

    }

    private void setAdapter() {
        PastRecyclerAdapter adatper = new PastRecyclerAdapter(pastLists,
                new ItemListClickListener() {
                    @Override
                    public void onViewItemListClick(int listId) {
                        Bundle bundleListId = new Bundle();
                        bundleListId.putInt("listid", listId);

                        Intent intent = new Intent(PastListsActivity.this,
                                ItemsActivity.class);
                        intent.putExtras(bundleListId);
                        startActivity(intent);
                    }
                });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPast.setLayoutManager(layoutManager);
        recyclerViewPast.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPast.setAdapter(adatper);
    }
}