package com.example.miageragefestival2022;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    @GET("liste")
    Call<JsonObject> getListeGroupes();

    /*
        L'annotation @Url permet de concaténer l'URL de base avec l'URL dynamique
     */
    @GET()
    Call<Groupe> getDetailGroupe(@Url String url);


}
