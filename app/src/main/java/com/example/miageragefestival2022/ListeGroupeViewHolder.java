package com.example.miageragefestival2022;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListeGroupeViewHolder extends RecyclerView.ViewHolder {

    private Button nomGroupe;
    private ImageButton addToFavorite;

    public ListeGroupeViewHolder(View itemView){
        super(itemView);
        nomGroupe = (Button) itemView.findViewById(R.id.btn_listeGroupe);
        addToFavorite = itemView.findViewById(R.id.btn_addToFavorite);


        /*
            Bouton qui permet de sélectionner un groupe dans le MainActivity et afficher les détails de celui-ci
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
            Bouton qui permet d'ajouter un groupe du MainActivity a la liste de groupe favoris
         */
        addToFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = view.getContext().getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();


                String nomGroupe = getButtonNomGroupe().getText().toString();

                editor.putString(nomGroupe, nomGroupe);
                editor.commit();

                // On identifie le groupe comme favoris si il est ajouté aux favoris en changeant l'image
                addToFavorite.setBackgroundResource(R.drawable.ic_favorite_purple);


                Toast.makeText(view.getContext(), nomGroupe+" ajouter aux favoris", Toast.LENGTH_LONG).show();
            }
        });
    }


    public Button getButtonNomGroupe() {
        return nomGroupe;
    }

    public ImageButton getImgButton () { return addToFavorite; }

    public void setNomGroupe(Button nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
