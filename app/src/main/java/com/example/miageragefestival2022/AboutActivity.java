package com.example.miageragefestival2022;

import android.os.Bundle;

import com.example.miageragefestival2022.databinding.ActivityAboutBinding;
import com.example.miageragefestival2022.databinding.ActivityAppUtilesBinding;

public class AboutActivity extends DrawerBaseActivity {

    ActivityAboutBinding activityAboutsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAboutsBinding = ActivityAboutBinding.inflate(getLayoutInflater());
        setContentView(activityAboutsBinding.getRoot());
        allocateActivityTitle("A Propos");
    }
}