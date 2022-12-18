package com.example.miageragefestival2022;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavorisViewAdapter rvAdapter;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        recyclerView = findViewById(R.id.rv_favoris);
        //recyclerView.setAdapter(rvAdapter);

        SharedPreferences sp = getSharedPreferences("mesFavoris", 0);
        String nomGroupe = sp.getString("nom_groupe", "oups!");

        rvAdapter = new FavorisViewAdapter(FavorisActivity.this, nomGroupe);
        recyclerView.setLayoutManager(new LinearLayoutManager(FavorisActivity.this));
        recyclerView.setAdapter(rvAdapter);

    }
}
