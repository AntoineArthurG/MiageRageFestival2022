package com.example.miageragefestival2022;

import android.os.Bundle;

import com.example.miageragefestival2022.databinding.ActivityNumUrgenceBinding;

public class NumUrgenceActivity extends DrawerBaseActivity {

    ActivityNumUrgenceBinding activityNumUrgenceBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNumUrgenceBinding = ActivityNumUrgenceBinding.inflate(getLayoutInflater());
        setContentView(activityNumUrgenceBinding.getRoot());
        allocateActivityTitle("Numéros d'urgence");
    }
}