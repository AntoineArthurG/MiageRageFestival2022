package com.example.miageragefestival2022;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SharedPrefHelper {

    public String groupe;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper (Context context, String nom_shared_pref) {
        this.sharedPreferences = context.getSharedPreferences(nom_shared_pref, Activity.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    /*
        Sauvegarde la liste passée en paramêtre dans les shared preferences
     */
    public void saveListeGroupesToSharedPref (List<String> listeGroupe) {
        for (int i = 0; i < listeGroupe.size(); i++) {
            editor.putString(listeGroupe.get(i), listeGroupe.get(i)).commit();
        }
    }

    /*
        Récupère les groupes depuis les shared preferences
     */
    public List<String> getListeGroupesSharedPref () {
        List<String> listeGroupe = new ArrayList<>();

        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            String groupe = entry.getValue().toString();
            listeGroupe.add(groupe);
        }

        // On retourne la liste obtenu des shared preferences
        return listeGroupe;
    }


    public void saveGroupe (Groupe groupe) {
            editor.putString("artiste", groupe.getData().getArtiste());
            editor.putString("texte", groupe.getData().getTexte());
            editor.putString("web", groupe.getData().getWeb());
            editor.putString("image", groupe.getData().getImage());
            editor.putString("scene", groupe.getData().getScene());
            editor.putString("jour", groupe.getData().getJour());
            editor.putString("heure", groupe.getData().getHeure());
            editor.putInt("time", groupe.getData().getTime());

            editor.commit();
    }


    public Groupe getGroupe() {
        Map<String, ?> groupe = sharedPreferences.getAll();

        Groupe.Data data = new Groupe.Data(
                groupe.get("artiste").toString(),
                groupe.get("texte").toString(),
                groupe.get("web").toString(),
                groupe.get("image").toString(),
                groupe.get("scene").toString(),
                groupe.get("jour").toString(),
                groupe.get("heure").toString(),
                (Integer) groupe.get("time")
        );
        Groupe res = new Groupe();
        res.setCode("");
        res.setMessage("");
        res.setData(data);

        return res;
    }

    public void removeFromFavoris (String nomGroupe) {
        editor.remove(nomGroupe).apply();
    }

    public void ajouterFavoris (String nomGroupe) {
        editor.putString(nomGroupe,nomGroupe).apply();
    }



    public void clear() {
        editor.clear().commit();
    }

}
