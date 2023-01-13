package com.example.miageragefestival2022;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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

    /*
        On override le bouton de retour arrière du system pour recharger le MainActivity et éviter d'avoir
        des groupes supprimés des favoris toujours signalés comme tel dans le MainActivity.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this,MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}
