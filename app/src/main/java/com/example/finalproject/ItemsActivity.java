package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ItemsActivity extends AppCompatActivity {

    private int uid;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        bundle = getIntent().getExtras();

        uid = bundle.getInt("uid");
    }
}