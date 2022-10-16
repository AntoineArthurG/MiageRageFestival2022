package com.example.miageragefestival2022;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ListeGroupeViewHolder extends RecyclerView.ViewHolder {

    private TextView nomGroupe;

    public ListeGroupeViewHolder(View itemView){
        super(itemView);
        nomGroupe = (TextView) itemView.findViewById(R.id.tv_nomGroupe);
    }

    public TextView getNomGroupe() {
        return nomGroupe;
    }

    public void setNomGroupe(TextView nomGroupe) {
        this.nomGroupe = nomGroupe;
    }
}
