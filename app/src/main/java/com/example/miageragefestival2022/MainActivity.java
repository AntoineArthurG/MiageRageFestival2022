package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.miageragefestival2022.databinding.ActivityMainBinding;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends DrawerBaseActivity {

    ActivityMainBinding activityMainBinding;

    private RecyclerView recyclerView;
    private RecyclerViewAdapater rvAdapter;
    public List<String> listeGroupe ;
    private SharedPrefHelper sharedPrefListeGroupe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("Accueil");

        recyclerView = findViewById(R.id.rv_ListeGroupe);

        // On ouvre les shared preferences
        sharedPrefListeGroupe = new SharedPrefHelper(this.getApplicationContext(),"listeDesGroupes");
        listeGroupe = sharedPrefListeGroupe.getListeGroupesSharedPref();

        // Si les shared preferences sont vides alors on récupère la liste des groupe depuis l'api et on les enregistre dans les shared preferences prévu a cet effet
        if (sharedPrefListeGroupe.getListeGroupesSharedPref().isEmpty()) {
            getListeGroupesAPI(sharedPrefListeGroupe);
            finish();
            startActivity(getIntent());

            sharedPrefListeGroupe = new SharedPrefHelper(this, "listeDesGroupes");
            listeGroupe = sharedPrefListeGroupe.getListeGroupesSharedPref();
            sauvegarderGroupe(listeGroupe);
            afficherGroupes(listeGroupe,recyclerView);

        }
        else {
            afficherGroupes(listeGroupe, recyclerView);
            //sharedPrefListeGroupe.clear();
        }



    }

    /*
          GET Request through Retrofit2
    */
    private void getListeGroupesAPI (SharedPrefHelper sharedPreferences) {

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
                    sharedPreferences.saveListeGroupesToSharedPref(listeGroupe);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }

    /*
        Alimente le recycler view de l'activité
     */
    public void afficherGroupes (List<String> listeGroupe, RecyclerView recyclerView) {
        rvAdapter = new RecyclerViewAdapater(MainActivity.this, listeGroupe);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(rvAdapter);
    }

    public void sauvegarderGroupe (List<String> listeGroupe) {

        for (int i =0; i < listeGroupe.size(); i++) {
            SharedPrefHelper sharedPrefGroupe = new SharedPrefHelper(MainActivity.this, listeGroupe.get(i));

            // On récupère les details du groupe et on les enregistres dans un shared preferences dédié
            getGroupeDetail(listeGroupe.get(i), sharedPrefGroupe);
            System.out.println(listeGroupe.get(i) + " sauvegarder");
        }
    }

    // TODO: trouver un moyen pour que la méthode renvoie le groupe passé en paramêtre ofin de sortir la méthode saveGroupeToSharedPref()
    public void getGroupeDetail (String groupe, SharedPrefHelper sharedPreferences) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://daviddurand.info/D228/festival/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Groupe> call = api.getDetailGroupe("info/" + groupe);
        call.enqueue(new Callback<Groupe>() {
            @Override
            public void onResponse(Call<Groupe> call, Response<Groupe> response) {
                if(response.isSuccessful()) {

                    //On stock la réponse du GET dans un objet de type Groupe
                    Groupe groupe = new Groupe(
                            response.body().getCode(),
                            response.body().getMessage(),
                            response.body().getData()
                    );

                    // On enregistre les détails du groupe en question
                    sharedPreferences.saveGroupeToSharedPref(groupe);
                }
            }

            @Override
            public void onFailure(Call<Groupe> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }

    //Aller à la page Favoris
    public void favorisPage(View view){
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
    }


}