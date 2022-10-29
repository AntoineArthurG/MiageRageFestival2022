package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailGroupe extends AppCompatActivity {

    private TextView tv_titreGroupe ;
    private TextView tv_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_groupe);

        tv_titreGroupe = findViewById(R.id.tv_nom_groupe);
        tv_description = findViewById(R.id.tv_description_groupe);
        Intent intent = getIntent();

        String titreGroupe = intent.getStringExtra("titreGroupe");

        getDetailGroupe("info/"+titreGroupe);
    }

    /**
        Méthode pour récuperer les informations concernant le groupe selectionné par l'utilisateur
        avec la librarie Retrofit2.
        @param url : l'url dynamique. Par ex : "/info/redveil"
     */
    public void getDetailGroupe(String url) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://daviddurand.info/D228/festival/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<Groupe> call = api.getDetailGroupe(url);
        call.enqueue(new Callback<Groupe>() {
            @Override
            public void onResponse(Call<Groupe> call, Response<Groupe> response) {

                Groupe groupe = new Groupe(
                        response.body().getCode(),
                        response.body().getMessage(),
                        response.body().getData()
                );

                tv_titreGroupe.setText(groupe.getData().getArtiste());
                tv_description.setText(groupe.getData().getTexte());

            }

            @Override
            public void onFailure(Call<Groupe> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }
}