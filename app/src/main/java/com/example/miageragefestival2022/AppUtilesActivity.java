package com.example.miageragefestival2022;

import android.os.Bundle;

import com.example.miageragefestival2022.databinding.ActivityAppUtilesBinding;
import com.google.android.material.navigation.NavigationView;

public class AppUtilesActivity extends DrawerBaseActivity {

    ActivityAppUtilesBinding activityAppUtilesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAppUtilesBinding = ActivityAppUtilesBinding.inflate(getLayoutInflater());
        setContentView(activityAppUtilesBinding.getRoot());
        allocateActivityTitle("Applications Utiles");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
    }
}