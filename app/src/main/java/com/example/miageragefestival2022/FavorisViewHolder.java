package com.example.miageragefestival2022;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavorisViewHolder extends RecyclerView.ViewHolder {

    private TextView nomGroupe;
    private Button detail;
    private Button supprGroupeFromFavoris;

    public FavorisViewHolder(@NonNull View itemView) {
        super(itemView);
        nomGroupe = itemView.findViewById(R.id.tv_nomGroupeFavoris);
        detail = itemView.findViewById(R.id.btn_go2Groupe);
        supprGroupeFromFavoris = itemView.findViewById(R.id.btn_Suppr_favoris);

        /*
            Ce bouton permet d'aller voir le détail du groupe
         */
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailGroupeIntent = new Intent(view.getContext(), DetailGroupe.class);
                detailGroupeIntent.putExtra("titreGroupe", nomGroupe.getText().toString());
                view.getContext().startActivity(detailGroupeIntent);
            }
        });

        /*
            Supprime le groupe sélectionner des favoris
            TODO: rafaichir le recyclerView afin d'enlever le groupe supprimer en temps réel
         */
        supprGroupeFromFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = view.getContext().getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                editor.remove(nomGroupe.getText().toString()).commit();

            }
        });
    }

    public TextView getNomGroupe (){
        return this.nomGroupe;
    }
}
