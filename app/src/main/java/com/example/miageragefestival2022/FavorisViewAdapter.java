package com.example.miageragefestival2022;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FavorisViewAdapter extends RecyclerView.Adapter<FavorisViewHolder> {

    private Context context;
    public List<String> listeFavorisString;


    public FavorisViewAdapter(Context ct, List<String> listeGroupe){
       context = ct;
       this.listeFavorisString = listeGroupe;
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
        holder.getGroupeButton().setText(listeFavorisString.get(position));
        Glide.with(context).load("https://daviddurand.info/D228/festival/illustrations/" + listeFavorisString.get(position) + "image.jpg")
                .override(300,270)
                .into(holder.getImgViewGroupe());

    }

    @Override
    public int getItemCount() {
        return listeFavorisString.size();
    }
}
