package com.example.miageragefestival2022;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        LinearLayout appelEuro = findViewById(R.id.num_appel_euro);
        appelEuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_num = findViewById(R.id.num_euro);
                String num = tv_num.getText().toString();
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", num, null));
                startActivity(appel);
            }
        });

        LinearLayout samu = findViewById(R.id.ll_samu);
        samu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_samu = findViewById(R.id.tv_num_samu);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_samu.getText().toString(), null));
                startActivity(appel);
            }
        });

        LinearLayout police = findViewById(R.id.ll_police);
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_police = findViewById(R.id.tv_police);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_police.getText().toString(), null));
                startActivity(appel);
            }
        });

        LinearLayout pompier = findViewById(R.id.ll_pompier);
        pompier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_pompier = findViewById(R.id.tv_pompier);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_pompier.getText().toString(), null));
                startActivity(appel);
            }
        });

        LinearLayout maritime = findViewById(R.id.ll_maritime);
        maritime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_maritime = findViewById(R.id.tv_maritime);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_maritime.getText().toString(), null));
                startActivity(appel);
            }
        });

        LinearLayout aero = findViewById(R.id.ll_aero);
        aero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_aero = findViewById(R.id.tv_aero);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_aero.getText().toString(), null));
                startActivity(appel);
            }
        });

        LinearLayout attentat = findViewById(R.id.ll_attentat);
        attentat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv_attentat = findViewById(R.id.tv_attentat);
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", tv_attentat.getText().toString(), null));
                startActivity(appel);
            }
        });
    }
}