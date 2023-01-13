package com.example.miageragefestival2022;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavorisActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavorisViewAdapter rvAdapter;
    private List<String> listeGroupeFavoris = new ArrayList<String>();


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoris);

        SharedPreferences sp = getSharedPreferences("mesFavoris", 0);

        // On récupère tout le contenu des sharedPreferences que l'on transforme en liste afin d'alimenter le recyclerView des favoris
        for (Map.Entry<String, ?> entry : sp.getAll().entrySet()) {
            String groupe = entry.getValue().toString();
            listeGroupeFavoris.add(groupe);
        }

        // On alimente ici le recycler view avec tout les groupes favoris présent dans les sharedPreferences
        recyclerView = findViewById(R.id.rv_favoris);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new FavorisViewAdapter(this,listeGroupeFavoris);
        recyclerView.setAdapter(rvAdapter);


    }
}
