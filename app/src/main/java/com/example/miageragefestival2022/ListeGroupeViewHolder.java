package com.example.miageragefestival2022;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListeGroupeViewHolder extends RecyclerView.ViewHolder {

    private Button nomGroupe;
    private ImageButton addToFavorite;
    private ImageView iv_groupe;

    public ListeGroupeViewHolder(View itemView){
        super(itemView);
        nomGroupe = (Button) itemView.findViewById(R.id.btn_listeGroupe);
        addToFavorite = itemView.findViewById(R.id.btn_addToFavorite);
        iv_groupe = itemView.findViewById(R.id.iv_groupe);


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
                // On récupère les groupes favoris de l'utilisateur que l'on transforme en Liste
                SharedPrefHelper sp = new SharedPrefHelper(view.getContext(), "mesFavoris");
                List<String> listeGroupeFavoris = sp.getListeGroupesSharedPref();

                String nomGroupe = getButtonNomGroupe().getText().toString();

                // Si le groupe existe dans les favoris alors il est supprimé de la liste des favoris
                if (listeGroupeFavoris.contains(nomGroupe)) {
                    sp.removeFromFavoris(nomGroupe);

                    // On change l'image d'identification d'un groupe favoris en conséquence
                    addToFavorite.setBackgroundResource(R.drawable.star_icon_clair);
                    Toast.makeText(view.getContext(), nomGroupe+" supprimer des favoris", Toast.LENGTH_SHORT).show();
                }
                // Sinon il est ajouté a la liste des favoris de l'utilisateur
                else {
                    sp.ajouterFavoris(nomGroupe);
                    SharedPrefHelper spGroupe = new SharedPrefHelper(view.getContext(), nomGroupe);

                    // On change l'image d'identification d'un groupe favoris en conséquence
                    addToFavorite.setBackgroundResource(R.drawable.ic_favorite_purple);
                    Toast.makeText(view.getContext(), nomGroupe + " ajouter aux favoris", Toast.LENGTH_SHORT).show();

                    // set Notif
                    Intent intent = new Intent(view.getContext(), ReminderBoradcast.class);
                    intent.putExtra("NOM", nomGroupe);
                    intent.putExtra("SCENE", spGroupe.getGroupe().getData().getScene());
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(view.getContext(), 0, intent,PendingIntent.FLAG_IMMUTABLE);

                    AlarmManager alarmManager = (AlarmManager) view.getContext().getSystemService(Context.ALARM_SERVICE);
                    Integer time = spGroupe.getGroupe().getData().getTime() * 10;
                    Long currentTime = System.currentTimeMillis();

                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                            currentTime+ time,
                            pendingIntent);
                }

            }
        });
    }


    public Button getButtonNomGroupe() {
        return nomGroupe;
    }

    public ImageButton getImgButton () { return addToFavorite; }

    public ImageView getImgViewGroupe () {
        return iv_groupe;
    }


}
