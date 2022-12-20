package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapater rvAdapter;
    public List<String> listeGroupe ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv_ListeGroupe);
        //recyclerView.setAdapter(rvAdapter);

        getListeGroupes();
    }

    /*
          GET Request through Retrofit2 + alimentation du recyclerView
          TODO : Voir si on peut récupérer la response.body().getAsJsonArray("data") directement en format de liste plutôt que d'un string
    */
    private void getListeGroupes () {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://daviddurand.info/D228/festival/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        Call call = api.getListeGroupes();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {

                    // Ici on récupère la partie 'data' de la réponse sous format de String
                    JsonArray res = response.body().getAsJsonArray("data");
                    // Puis on la transforme en une liste
                    listeGroupe = new Gson().fromJson(res, new TypeToken<List<String>>() {}.getType());

                    // Ici on alimente notre recyclerView adapter
                    for (int i = 0; i < listeGroupe.size(); i++) {
                        rvAdapter = new RecyclerViewAdapater(MainActivity.this, listeGroupe);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        recyclerView.setAdapter(rvAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
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