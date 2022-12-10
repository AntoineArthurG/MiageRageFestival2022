package com.example.miageragefestival2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class favorisActivity extends AppCompatActivity {

    TextView tv_nomGroupe;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        SharedPreferences sp = getSharedPreferences("mesFavoris", 0);
        String nomGroupe = sp.getString("nom_groupe", "oups!");

        tv_nomGroupe = findViewById(R.id.tv_favoris);
        tv_nomGroupe.setText(nomGroupe);

    }
}
