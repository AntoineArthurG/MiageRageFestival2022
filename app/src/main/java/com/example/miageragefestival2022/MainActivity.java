package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapater rvAdapter;
    public List<String> listeGroupe ;
    private SharedPrefHelper sharedPrefListeGroupe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_ListeGroupe);

        // On récupère la liste des groupes des shared preferences
        SharedPreferences sp = this.getSharedPreferences("listeDesGroupes", Context.MODE_PRIVATE);
        listeGroupe = getListeGroupesSharedPref(sp);

        // Si la liste est vide alors on requête l'api pour récupérer les groupes
        if (listeGroupe.isEmpty()) {
            // Les groupes sont automatiquement sauvegardés dans les shared preferences
            getListeGroupesAPI(sp);
            // Il n'y a plus qu'à alimenter la liste et la passer dans le recycler view
            listeGroupe = getListeGroupesSharedPref(sp);
            afficherGroupes(listeGroupe, recyclerView);
        }
        // Si la liste n'est pas vide alors on utilise la liste des shared preferences pour afficher les groupes
        else {
            afficherGroupes(listeGroupe, recyclerView);
        }
    }

    /*
          GET Request through Retrofit2
    */
    private void getListeGroupesAPI (SharedPreferences sharedPreferences) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://daviddurand.info/D228/festival/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call call = api.getListeGroupes();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    // On récupère la partie 'data' de la réponse sous format de String
                    JsonArray res = response.body().getAsJsonArray("data");
                    // On la transforme en une liste
                    List<String> listeGroupe = new Gson().fromJson(res, new TypeToken<List<String>>() {}.getType());

                    // On sauvegarde la liste des groupes dans les shared preferences si c'est la première ouverture de l'appli
                    saveListeGroupesToSharedPref(listeGroupe, sharedPreferences);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }

    /*
        Récupère les groupes depuis les shared preferences
     */
    public List<String> getListeGroupesSharedPref (SharedPreferences sharedPref) {
        List<String> listeGroupe = new ArrayList<>();

        // On transforme la map renvoyer par les shared preferences en liste
        for (Map.Entry<String, ?> entry : sharedPref.getAll().entrySet()) {
            String groupe = entry.getValue().toString();
            listeGroupe.add(groupe);
        }

        // On retourne la liste obtenu des shared preferences
        return listeGroupe;
    }

    /*
        Sauvegarde la liste passée en paramêtre dans les shared preferences
     */
    public void saveListeGroupesToSharedPref (List<String> listeGroupe, SharedPreferences sharedPref) {
        // On récupère les shared preferences
        SharedPreferences.Editor editor = sharedPref.edit();

        if (sharedPref.getAll().isEmpty()) {
            for (int i = 0; i < listeGroupe.size(); i++) {
                editor.putString(listeGroupe.get(i), listeGroupe.get(i));
            }
        }
        editor.apply();
    }

    /*
        Alimente le recycler view de l'activité
     */
    public void afficherGroupes (List<String> listeGroupe, RecyclerView recyclerView) {
        rvAdapter = new RecyclerViewAdapater(MainActivity.this, listeGroupe);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(rvAdapter);
    }

    //TODO
    public void saveGroupeToSharedPref (List<String> listeGroupe) {

        for (int i =0; i < listeGroupe.size(); i++) {
            // get groupe detail
            getGroupeDetail(listeGroupe.get(i));
        }
    }

    //TODO
    public Groupe getGroupeDetail (String groupe) {

        return null;
    }

    //Aller à la page Favoris
    public void favorisPage(View view){
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
    }


}