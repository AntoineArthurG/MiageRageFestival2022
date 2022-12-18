package com.example.miageragefestival2022;

import android.content.Intent;
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
            TODO: Implementer l'action du bouton pour supprimer le groupe des favoris
         */
        supprGroupeFromFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public TextView getNomGroupe (){
        return this.nomGroupe;
    }
}
