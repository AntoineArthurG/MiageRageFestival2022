package com.example.miageragefestival2022;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SearchView;

import com.example.miageragefestival2022.databinding.ActivityMainBinding;

import com.google.android.material.navigation.NavigationView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends DrawerBaseActivity {

    ActivityMainBinding activityMainBinding;

    private RecyclerView recyclerView;
    private RecyclerViewAdapater rvAdapter;
    public String[] jour;
    public String[] scene;
    public ArrayAdapter<String> jourArrayAdapter;
    public ArrayAdapter<String> sceneArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        allocateActivityTitle("Accueil");

        // On alimente le drop down menu du filtre par jour
        jour = getResources().getStringArray(R.array.Jour);
        jourArrayAdapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, jour);
        activityMainBinding.jour.setAdapter(jourArrayAdapter);

        // On alimente le drop down menu du filtre par scene
        scene = getResources().getStringArray(R.array.scene);
        sceneArrayAdapter = new ArrayAdapter<>(this, com.google.android.material.R.layout.support_simple_spinner_dropdown_item,scene);
        activityMainBinding.scene.setAdapter(sceneArrayAdapter);

        recyclerView = findViewById(R.id.rv_ListeGroupe);

        // On ouvre les shared preferences
        SharedPrefHelper sharedPrefListeGroupe = new SharedPrefHelper(this.getApplicationContext(),"listeDesGroupes");

        // Si les shared preferences sont vides
        if (sharedPrefListeGroupe.getListeGroupesSharedPref().isEmpty()) {

            // On récupère la liste des groupes depuis l'api et on les enregistrent dans les shared preferences prévu a cet effet
            getListeGroupesAPI(sharedPrefListeGroupe);

            // On notifie les shared preferences que leur contenu a changé
            // TODO: il existe une autre manière plus propre pour cette action en passant par un listener
            finish();
            startActivity(getIntent());
        }

        List<Groupe> listeGroupe = getListeGroupe(sharedPrefListeGroupe.getListeGroupesSharedPref());
        afficherGroupes(listeGroupe, recyclerView);


        // Filtre les groupes par jour grace à une liste déroulante
        AutoCompleteTextView jour = findViewById(R.id.jour);
        jour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texte = jour.getText().toString().toLowerCase();
                rvAdapter.getFilter().filter(texte);
            }
        });

        // Filtre les groupes par scene grace à une liste déroulante
        AutoCompleteTextView scene = findViewById(R.id.scene);
        scene.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texte = scene.getText().toString().toLowerCase();
                rvAdapter.getFilter().filter(texte);
            }
        });


        // Filtre les groupes grace à une recherche
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                rvAdapter.getFilter().filter(newText);
                return false;
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

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
                    sauvegarderGroupe(listeGroupe);
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
    public void afficherGroupes (List<Groupe> listeGroupe, RecyclerView recyclerView) {
        rvAdapter = new RecyclerViewAdapater(MainActivity.this, listeGroupe);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(rvAdapter);
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
                    sharedPreferences.saveGroupe(groupe);
                }
            }

            @Override
            public void onFailure(Call<Groupe> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }

    /*
        Sauvegarde chaque groupe avec ses détails dans un shared preference dédié
     */
    public void sauvegarderGroupe (List<String> listeGroupe) {

        for (int i =0; i < listeGroupe.size(); i++) {
            SharedPrefHelper sharedPrefGroupe = new SharedPrefHelper(MainActivity.this, listeGroupe.get(i));

            // On récupère les details du groupe et on les enregistres dans un shared preferences dédié
            getGroupeDetail(listeGroupe.get(i), sharedPrefGroupe);

        }
    }

    /*
        Renvoit une liste de Groupe utiliser pour peupler le recycler view
     */
    public List<Groupe> getListeGroupe (List<String> listeGroupe) {
        List<Groupe> res = new ArrayList<>();

        for (int i = 0; i < listeGroupe.size(); i++) {
            SharedPrefHelper sp = new SharedPrefHelper(this, listeGroupe.get(i));

            Groupe groupe = new Groupe("","",new Groupe.Data(
                    listeGroupe.get(i),
                    sp.getGroupe().getData().getTexte(),
                    sp.getGroupe().getData().getWeb(),
                    sp.getGroupe().getData().getImage(),
                    sp.getGroupe().getData().getScene(),
                    sp.getGroupe().getData().getJour(),
                    sp.getGroupe().getData().getHeure(),
                    sp.getGroupe().getData().getTime()
            ));

            res.add(groupe);
        }
        return res;
    }

    //Aller à la page Favoris
    public void favorisPage(View view){
        Intent intent = new Intent(this, FavorisActivity.class);
        startActivity(intent);
    }


}