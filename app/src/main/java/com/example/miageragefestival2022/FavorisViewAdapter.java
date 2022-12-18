package com.example.miageragefestival2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FavorisViewAdapter extends RecyclerView.Adapter<FavorisViewHolder> {

    private Context context;
    private String nomGroupe;

    public FavorisViewAdapter(Context ct, String groupe){
       context = ct;
       this.nomGroupe = groupe;
    }


    @NonNull
    @Override
    public FavorisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.favoris_groupe_card, parent, false);
        return new FavorisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorisViewHolder holder, int position) {
        holder.getNomGroupe().setText(nomGroupe);

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
