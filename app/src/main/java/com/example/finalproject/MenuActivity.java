package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button pastListsButton, newListButton, aboutButton, settingsButton;
    private int uid;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bundle = getIntent().getExtras();

        uid = bundle.getInt("uid");
        System.out.println(uid);

    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.newListButton:
                intent = new Intent(this, NewListActivity.class);
                bundle.putInt("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
//                startActivity(new Intent(this, NewListActivity.class));
//                break;
            case R.id.pastListsButton:
                intent = new Intent(this, PastListsActivity.class);
                bundle.putInt("uid", uid);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
//                startActivity(new Intent(this, PastListsActivity.class));
//                break;
            case R.id.aboutButton:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.settingsButton:
                intent = new Intent(this, AlgoActivity.class);
//                bundle.putInt("uid", uid);
//                intent.putExtras(bundle);
                startActivity(intent);
                break;
//                startActivity(new Intent(this, SettingsActivity.class));
//                break;
            case R.id.logoutButton:
                logout();
                break;
        }
    }

    private void logout() {
        startActivity(new Intent(this, MainActivity.class));
    }
}