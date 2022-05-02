package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class NewListActivity extends AppCompatActivity {

    private ArrayList<Product> productsList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);
        productsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerViewProducts);

        setProductInfo();
        setAdapter();

    }

    private void setAdapter() {
        RecyclerAdapter adatper = new RecyclerAdapter(productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adatper);
    }

    private void setProductInfo() {
        productsList.add(new Product("banana"));
        productsList.add(new Product("apple"));
        productsList.add(new Product("nuts"));
    }
}