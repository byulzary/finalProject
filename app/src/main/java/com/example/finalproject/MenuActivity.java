package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button pastListsButton, newListButton, aboutButton,settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.newListButton:
                startActivity(new Intent(this, NewListActivity.class));
                break;
            case R.id.pastListsButton:
                startActivity(new Intent(this, PastListsActivity.class));
                break;
            case R.id.aboutButton:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.settingsButton:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.logoutButton:
                logout();
                break;
        }
    }

    private void logout() {
        startActivity(new Intent(this, MainActivity.class));
    }
}