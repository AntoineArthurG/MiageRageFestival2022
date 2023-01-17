package com.example.miageragefestival2022;

import android.os.Bundle;

import com.example.miageragefestival2022.databinding.ActivityAboutBinding;
import com.example.miageragefestival2022.databinding.ActivityAppUtilesBinding;
import com.google.android.material.navigation.NavigationView;

public class AboutActivity extends DrawerBaseActivity {

    ActivityAboutBinding activityAboutsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutsBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutsBinding.getRoot());
        allocateActivityTitle("A Propos");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
    }
}