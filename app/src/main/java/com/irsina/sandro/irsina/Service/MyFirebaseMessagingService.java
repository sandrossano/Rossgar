package com.irsina.sandro.irsina.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.irsina.sandro.irsina.Fuori;
import com.irsina.sandro.irsina.MainActivity;
import com.irsina.sandro.irsina.R;
import com.irsina.sandro.irsina.SponsorSingolo;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent intent = new Intent(this,MyFirebaseMessagingService.class);
        sendBroadcast(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent i= new Intent(this,MyFirebaseMessagingService.class);
        startService(i);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage.getData() !=null){
             sendNotification(remoteMessage);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void sendNotification(RemoteMessage remoteMessage) {

        Map<String,String> data=remoteMessage.getData();
        String  title= data.get("title");
        String content= data.get("content");

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String  NOTIFICATION_CHANNEL_ID="irsina";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel= new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Irsina Notification",
                    NotificationManager.IMPORTANCE_HIGH);

            notificationChannel.setDescription("Test Notify");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[] {0,100,500,1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder nofificationBuilder= new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

// Create an Intent for the activity you want to start
        Intent resultIntent = new Intent(this, SponsorSingolo.class);
        resultIntent.putExtra("nome", "Ducale");
        resultIntent.putExtra("descrizione", "<b>Caffè, pizzeria e ristorante tipico, a Irsina</b><br><br>" +
                "Il Ducale è caffè, ristorante e pizzeria a Irsina, in provincia di Matera. Situato sui resti della vecchia torre normanna del feudo di Montepeloso, in Piazza Garibaldi 1, è il luogo ideale per una semplice pausa caffè o anche per una sosta a pranzo o a cena dopo una visita al centro storico di Irsina.<br>Il Ducale unisce al locale confortevole e innovativo una ricca offerta di piatti della tradizione contadina locale, regalandovi un viaggio nei sapori della terra lucana.<br>Per la pizza, utilizza solo ingredienti di prima qualità, lievito madre e farine nobili: di farro, di grano duro Senatore Cappelli, di grano arso e ai cinque cereali...per una gustosa digeribilità. A vostra disposizione, il servizio Wi-Fi gratuito e un'ampia terrazza panoramica.");
        resultIntent.putExtra("numero", "0835 628190");
        resultIntent.putExtra("email", "giovannigiorgio@live.it");
        resultIntent.putExtra("indirizzo", "Piazza Garibaldi, 1\n75022 Irsina MT");
        resultIntent.putExtra("sito", "https://www.ilducalepizzeriaristorante-irsina.it/");
        resultIntent.putExtra("orariolun", "CHIUSO");
        resultIntent.putExtra("orariomar", "7:30 - 2:00");
        resultIntent.putExtra("orariomer", "7:30 - 2:00");
        resultIntent.putExtra("orariogio", "7:30 - 2:00");
        resultIntent.putExtra("orarioven", "7:30 - 2:00");
        resultIntent.putExtra("orariosab", "7:30 - 2:00");
        resultIntent.putExtra("orariodom", "7:30 - 2:00");

        resultIntent.putExtra("luogo", "Ducale");

// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        nofificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.logo)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.logo))
                .setTicker("Hearty365")
                .setContentTitle(title)
                .setContentText(content)
                .setContentInfo("info")
                .setContentIntent(resultPendingIntent);

        notificationManager.notify(0,nofificationBuilder.build());

    }
}
