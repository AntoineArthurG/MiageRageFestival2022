package com.example.miageragefestival2022;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("liste")
    Call<JsonObject> getGroupes();
}
