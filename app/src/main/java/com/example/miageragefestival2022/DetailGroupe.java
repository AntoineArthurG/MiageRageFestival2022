package com.example.miageragefestival2022;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailGroupe extends AppCompatActivity {

    private TextView tv_titreGroupe ;
    private TextView tv_date;
    private TextView tv_heure;
    private TextView tv_description;
    private TextView tv_scene;
    private ImageView tv_web;
    private ImageView iv_facebook;
    private ImageView iv_image;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_groupe);
        Context context = getApplicationContext();

        tv_titreGroupe = findViewById(R.id.tv_nom_groupe);
        tv_date= findViewById(R.id.tv_date);
        tv_heure = findViewById(R.id.tv_heure);
        tv_description = findViewById(R.id.tv_description);
        tv_scene = findViewById(R.id.tv_scene);
        tv_web = (ImageView) findViewById(R.id.tv_web);
        iv_facebook = (ImageView) findViewById(R.id.facebook);
        iv_image = (ImageView) findViewById(R.id.iv_image);

        Intent intent = getIntent();

        String titreGroupe = intent.getStringExtra("titreGroupe");

        // On récupère l'image et on l'affiche dans la description
        Glide.with(context)
                .load("https://daviddurand.info/D228/festival/illustrations/" + titreGroupe + "/image.jpg")
                .override(500,400)
                .fitCenter()
                .into(iv_image);

        getDetailGroupe("info/"+titreGroupe);


        tv_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = iv_facebook.getContentDescription().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        iv_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = iv_facebook.getContentDescription().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
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
                tv_date.setText(groupe.getData().getJour());
                tv_heure.setText(groupe.getData().getHeure());
                tv_description.setText(groupe.getData().getTexte());
                tv_web.setContentDescription(groupe.getData().getWeb());
                tv_scene.setText(groupe.getData().getScene());
                iv_facebook.setContentDescription(groupe.getData().getWeb());

            }

            @Override
            public void onFailure(Call<Groupe> call, Throwable t) {
                Log.d("TAG","Response = " + t.toString());
            }
        });
    }
}