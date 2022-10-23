package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailGroupe extends AppCompatActivity {

    private TextView titreGroupe;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_groupe);

        titreGroupe = findViewById(R.id.tv_nom_groupe);
        description = findViewById(R.id.tv_description_groupe);

        Intent intent = getIntent();
        titreGroupe.setText(intent.getStringExtra("titreGroupe"));

    }
}