package com.example.miageragefestival2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavorisViewAdapter extends RecyclerView.Adapter<FavorisViewHolder> {

    private Context context;
    public List<String> listeFavorisGroupe;


    public FavorisViewAdapter(Context ct, List<String> listeGroupe){
       context = ct;
       this.listeFavorisGroupe = listeGroupe;
    }


    @NonNull
    @Override
    public FavorisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.favoris_groupe_card, parent, false);
        return new FavorisViewHolder(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorisViewHolder holder, int position) {
        holder.getGroupeButton().setText(listeFavorisGroupe.get(position));

    }

    @Override
    public int getItemCount() {
        return listeFavorisGroupe.size();
    }
}
