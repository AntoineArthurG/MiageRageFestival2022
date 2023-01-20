package com.example.miageragefestival2022;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBoradcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String nomGroupe = intent.getStringExtra("NOM");
        String scene = intent.getStringExtra("SCENE");
        String msg = nomGroupe + " est sur le point de commencer à jouer sur la scène " + scene + ". Amuse toi bien !";

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "myCh")
                .setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentTitle("MR Festival")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        notificationManagerCompat.notify(1, builder.build());
    }
}
