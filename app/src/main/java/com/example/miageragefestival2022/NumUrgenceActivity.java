package com.example.miageragefestival2022;

import android.os.Bundle;

import com.example.miageragefestival2022.databinding.ActivityNumUrgenceBinding;
import com.google.android.material.navigation.NavigationView;

public class NumUrgenceActivity extends DrawerBaseActivity {

    ActivityNumUrgenceBinding activityNumUrgenceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumUrgenceBinding = ActivityNumUrgenceBinding.inflate(getLayoutInflater());
        setContentView(activityNumUrgenceBinding.getRoot());
        allocateActivityTitle("Numéros d'urgence");

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
    }
}