package com.example.miageragefestival2022;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListeGroupeViewHolder extends RecyclerView.ViewHolder {

    //private TextView nomGroupe;
    private Button nomGroupe;

    public ListeGroupeViewHolder(View itemView){
        super(itemView);
        nomGroupe = (Button) itemView.findViewById(R.id.btn_listeGroupe);
        nomGroupe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailGroupeIntent = new Intent(view.getContext() ,DetailGroupe.class);
                detailGroupeIntent.putExtra("titreGroupe", nomGroupe.getText().toString());
                view.getContext().startActivity(detailGroupeIntent);
            }
        });
    }

    public TextView getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(Button nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
