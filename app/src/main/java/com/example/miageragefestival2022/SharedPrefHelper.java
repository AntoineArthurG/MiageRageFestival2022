package com.example.miageragefestival2022;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class SharedPrefHelper {

    public String groupe;
    public String nom_shared_pref;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper (Context context, String nom_shared_pref) {
        this.sharedPreferences = context.getSharedPreferences(nom_shared_pref, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void saveListeGroupesToSharedPref (List<String> listeGroupe) {
        for (int i = 0; i < listeGroupe.size(); i++) {
            editor.putString(listeGroupe.get(i), listeGroupe.get(i));
        }
        editor.commit();
    }


}
