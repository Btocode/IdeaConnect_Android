package com.afsan.ideaconnect_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    BottomNavigationView bottomNavView;
    NavigationBarView navBarView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navBarView = findViewById(R.id.bottom_nav_menu);
        navBarView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));

                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(),Notification.class));
                    case R.id.trending:
                        startActivity(new Intent(getApplicationContext(),trending.class));
                    case R.id.idea:
                        startActivity(new Intent(getApplicationContext(),NewIdea.class));
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                }

            }
        });

    }

}