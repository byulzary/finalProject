package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PastListsActivity extends AppCompatActivity {

    private ArrayList<Product> pastProductsList;
    private RecyclerView recyclerViewPast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_lists);
        pastProductsList = new ArrayList<>();
        recyclerViewPast = findViewById(R.id.recyclerViewPast);

        setProductInfo();
        setAdapter();

    }

    private void setAdapter() {
        PastRecyclerAdapter adatper = new PastRecyclerAdapter(pastProductsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPast.setLayoutManager(layoutManager);
        recyclerViewPast.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPast.setAdapter(adatper);
    }

    private void setProductInfo() {
        pastProductsList.add(new Product("banana"));
        pastProductsList.add(new Product("apple"));
        pastProductsList.add(new Product("nuts"));
        pastProductsList.add(new Product("ketchup"));
        pastProductsList.add(new Product("mayo"));
    }
}