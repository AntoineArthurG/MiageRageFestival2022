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

        recyclerView = findViewById(R.id.rv_favoris);

        SharedPreferences sp = getSharedPreferences("mesFavoris", 0);

        // La méthode getAll() renvoie une Map de type <String, ?> or nous avons besoin d'une liste afin d'alimenter le recyclerView des favoris
        Map<String, ?> mapGroupeFavoris = sp.getAll();

        for (Map.Entry<String, ?> entry : mapGroupeFavoris.entrySet()) {
            String res = entry.getValue().toString();
            listeGroupeFavoris.add(res);

        }

        // On alimente ici le recycler view avec tout les groupes favoris présent dans les sharedPreferences
        for (int i = 0; i < listeGroupeFavoris.size(); i++) {
            rvAdapter = new FavorisViewAdapter(FavorisActivity.this, listeGroupeFavoris);
            recyclerView.setLayoutManager(new LinearLayoutManager(FavorisActivity.this));
            recyclerView.setAdapter(rvAdapter);
        }


    }
}
