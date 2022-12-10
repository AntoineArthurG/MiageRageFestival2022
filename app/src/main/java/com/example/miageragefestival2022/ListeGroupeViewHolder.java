package com.example.miageragefestival2022;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListeGroupeViewHolder extends RecyclerView.ViewHolder {

    //private TextView nomGroupe;
    private Button nomGroupe;
    private Button addToFavorite;

    public ListeGroupeViewHolder(View itemView){
        super(itemView);
        nomGroupe = (Button) itemView.findViewById(R.id.btn_listeGroupe);
        addToFavorite = itemView.findViewById(R.id.btn_addToFavorite);

        /*
            Bouton qui permet de sélectionner un groupe et afficher les détails de celui-ci
         */
        nomGroupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailGroupeIntent = new Intent(view.getContext() ,DetailGroupe.class);
                detailGroupeIntent.putExtra("titreGroupe", nomGroupe.getText().toString());
                view.getContext().startActivity(detailGroupeIntent);
            }
        });

        /*
            Bouton qui permet d'ajouter un groupe a la liste de groupe favoris
         */
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = view.getContext().getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                String nomGroupe = getButtonNomGroupe().getText().toString();

                editor.putString("nom_groupe", nomGroupe);
                editor.commit();
            }
        });
    }

    public Button getButtonNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(Button nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
