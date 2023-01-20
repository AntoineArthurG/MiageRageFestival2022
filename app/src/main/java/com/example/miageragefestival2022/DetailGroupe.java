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

import java.util.Map;

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
        iv_image = (ImageView) findViewById(R.id.iv_image);

        // On récupère le nom du groupe sélectionner par l'utilisateur
        Intent intent = getIntent();
        String titreGroupe = intent.getStringExtra("titreGroupe");

        // On récupère l'image et on l'affiche dans la description
        getImageGroupe(context, titreGroupe);

        //On récupère les informations du groupe
        //getDetailGroupe("info/" + titreGroupe);
        afficherDetail(titreGroupe);

        // On met en place le lien permettant d'accéder à la page web du groupe
        tv_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = tv_web.getContentDescription().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    public void afficherDetail(String nomGroupe) {
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(DetailGroupe.this, nomGroupe);
        Groupe groupe = sharedPrefHelper.getGroupe();

        tv_titreGroupe.setText(groupe.getData().getArtiste());
        tv_date.setText(groupe.getData().getJour());
        tv_heure.setText(groupe.getData().getHeure());
        tv_description.setText(groupe.getData().getTexte());
        tv_web.setContentDescription(groupe.getData().getWeb());
        tv_scene.setText(groupe.getData().getScene());
    }

    /*
        Récupère l'image du groupe, la transforme et l'affiche dans la page de description du groupe
     */
    public void getImageGroupe (Context context, String titreGroupe) {
        Glide.with(context)
                .load("https://daviddurand.info/D228/festival/illustrations/" + titreGroupe + "/image.jpg")
                .override(700,700)
                .fitCenter()
                .into(iv_image);
    }
}