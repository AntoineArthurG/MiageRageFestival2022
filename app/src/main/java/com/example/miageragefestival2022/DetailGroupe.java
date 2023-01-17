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
        iv_facebook = (ImageView) findViewById(R.id.facebook);
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

    public void afficherDetail(String nomGroupe) {
        SharedPrefHelper sharedPrefHelper = new SharedPrefHelper(DetailGroupe.this, nomGroupe);
        Map<String, ?> groupe = sharedPrefHelper.getGroupe(nomGroupe);

        tv_titreGroupe.setText(groupe.get("artiste").toString());
        tv_date.setText(groupe.get("jour").toString());
        tv_heure.setText(groupe.get("heure").toString());
        tv_description.setText(groupe.get("texte").toString());
        tv_web.setContentDescription(groupe.get("web").toString());
        tv_scene.setText(groupe.get("scene").toString());
        iv_facebook.setContentDescription(groupe.get("web").toString());
    }

    /*
        Récupère l'image du groupe, la transforme et l'affiche dans la page de description du groupe
     */
    public void getImageGroupe (Context context, String titreGroupe) {
        Glide.with(context)
                .load("https://daviddurand.info/D228/festival/illustrations/" + titreGroupe + "/image.jpg")
                .override(500,400)
                .fitCenter()
                .into(iv_image);
    }
}