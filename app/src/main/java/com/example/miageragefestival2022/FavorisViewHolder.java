package com.example.miageragefestival2022;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FavorisViewHolder extends RecyclerView.ViewHolder {

    private TextView nomGroupe;
    private Button detail;
    private Button supprGroupeFromFavoris;
    private FavorisViewAdapter adapter;

    public FavorisViewHolder(@NonNull View itemView) {
        super(itemView);
        nomGroupe = itemView.findViewById(R.id.tv_nomGroupeFavoris);
        detail = itemView.findViewById(R.id.btn_go2Groupe);
        supprGroupeFromFavoris = itemView.findViewById(R.id.btn_Suppr_favoris);


        /*
            Ce bouton permet d'aller voir le détail du groupe selectionné par l'utilisateur
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
         */
        supprGroupeFromFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // On récupère les groupes favoris stockés dans les SharedPreferences
                SharedPreferences sp = view.getContext().getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();

                // On supprime le groupe selectionné par l'utilisateur des sharedPreferences
                editor.remove(nomGroupe.getText().toString()).commit();

                // On supprime le groupe du recyclerView
                adapter.listeFavorisGroupe.remove(getAbsoluteAdapterPosition());

                // On notifie l'utilisateur de la suppression du groupe
                Toast.makeText(view.getContext(), nomGroupe.getText().toString()+" supprimé", Toast.LENGTH_LONG).show();

                // On notifie de même l'adapter du recyclerView afin de le mettre à jour
                adapter.notifyItemRemoved(getAbsoluteAdapterPosition());


            }
        });
    }

    public TextView getNomGroupe (){
        return this.nomGroupe;
    }

    public FavorisViewHolder linkAdapter (FavorisViewAdapter adapter) {
        this.adapter = adapter;
        return this;
    }
}
