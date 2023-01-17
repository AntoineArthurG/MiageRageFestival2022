package com.example.miageragefestival2022;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class RecyclerViewAdapater extends RecyclerView.Adapter<ListeGroupeViewHolder> implements Filterable {

    private List<Groupe> listeGroupe;
    private List<Groupe> listeGroupeFull ;
    private Context context;

    public RecyclerViewAdapater(Context ct, List<Groupe>  listeGroupe) {
        this.context = ct;
        this.listeGroupe = listeGroupe;
        this.listeGroupeFull = new ArrayList<>(listeGroupe);
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
        holder.getButtonNomGroupe().setText(listeGroupe.get(position).getData().getArtiste());
        Glide.with(context).load("https://daviddurand.info/D228/festival/" + listeGroupe.get(position).getData().getImage())
                .override(300,270)
                .into(holder.getImgViewGroupe());

        /*
        Si le nom du groupe apparait dans la liste des favoris alors l'étoile des favoris se met
        en violet sinon elle se met en clair. Cela permet a l'utilisateur de savoir quel.s groupe.s
        sont dans ses favoris.
         */
        SharedPreferences sp = context.getSharedPreferences("mesFavoris", Context.MODE_PRIVATE);
        if(sp.getAll().containsValue(listeGroupe.get(position).getData().getArtiste())) {
            holder.getImgButton().setBackgroundResource(R.drawable.ic_favorite_purple);
        } else {
            holder.getImgButton().setBackgroundResource(R.drawable.star_icon_clair);
        }
    }

    @Override
    public int getItemCount() {
        return listeGroupe.size();
    }

    @Override
    public Filter getFilter() {
        return filtreListeGroupe;
    }

    private Filter filtreListeGroupe = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Groupe> listeFiltrer = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                listeFiltrer.addAll(listeGroupe);
            }
            else {
                String filtre = constraint.toString().toLowerCase().trim();

                for (Groupe groupe : listeGroupeFull) {
                    if (groupe.getData().getJour().toLowerCase().contains(filtre) || groupe.getData().getScene().toLowerCase().contains(filtre)) {
                        listeFiltrer.add(groupe);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values= listeFiltrer;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listeGroupe.clear();
            listeGroupe.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}