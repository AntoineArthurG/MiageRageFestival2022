package com.example.miageragefestival2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;

import java.util.List;

public class RecyclerViewAdapater extends RecyclerView.Adapter<ListeGroupeViewHolder> {

    private List<String> listeGroupe;
    private Context context;

    public RecyclerViewAdapater(Context ct, List<String>  listeGroupe) {
        this.context = ct;
        this.listeGroupe = listeGroupe;
    }

    public void setListeGroupe(List<String>  listeGroupe) {
        this.listeGroupe = listeGroupe;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListeGroupeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.groupe_card, parent, false);
        return new ListeGroupeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeGroupeViewHolder holder, int position) {
        holder.getNomGroupe().setText(listeGroupe.get(position));
    }

    @Override
    public int getItemCount() {
        return listeGroupe.size();
    }
}