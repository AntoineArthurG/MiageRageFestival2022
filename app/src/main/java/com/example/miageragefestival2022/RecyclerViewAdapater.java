package com.example.miageragefestival2022;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class RecyclerViewAdapater extends RecyclerView.Adapter<ListeGroupeViewHolder> {

    private List<String> listeGroupe;
    private Context context;

    public RecyclerViewAdapater(Context ct, List<String>  listeGroupe) {
        this.context = ct;
        this.listeGroupe = listeGroupe;
    }

//    public void setListeGroupe(List<String>  listeGroupe) {
//        this.listeGroupe = listeGroupe;
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public ListeGroupeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.groupe_card, parent, false);
        return new ListeGroupeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListeGroupeViewHolder holder, int position) {
        holder.getButtonNomGroupe().setText(listeGroupe.get(position));

        /*
        Si le nom du groupe apparait dans la liste des favoris alors l'étoile des favoris se met
        en violet sinon elle se met en clair. Cela permet a l'utilisateur de savoir quel.s groupe.s
        sont dans ses favoris.
         */
        SharedPreferences sp = context.getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
        if(sp.getAll().containsValue(listeGroupe.get(position))) {
            holder.getImgButton().setBackgroundResource(R.drawable.ic_favorite_purple);
        } else {
            holder.getImgButton().setBackgroundResource(R.drawable.star_icon_clair);
        }
    }

    @Override
    public int getItemCount() {
        return listeGroupe.size();
    }
}