package com.irsina.sandro.irsina.Service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.media.MediaBrowserCompat;

import com.irsina.sandro.irsina.R;
import com.irsina.sandro.irsina.SponsorSingolo;

public class YourService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        bindService(new Intent("com.google.firebase.messaging.FirebaseMessagingService;"), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                startService(new Intent(getApplicationContext(),MyFirebaseMessagingService.class));
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
        return null;
    }

    @Override
    public void onCreate() {
        startService(new Intent(getApplicationContext(),MyFirebaseMessagingService.class));
        super.onCreate();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

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

        Notification notification = new Notification.Builder(this)
                .setContentTitle("Visit Irsina App")
                .setTicker("Truiton Music Player")
                .setContentText("App Irsina")

                .setSmallIcon(R.drawable.maps_icon)
                .setLargeIcon(
                        Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(),
                                R.drawable.logo), 128, 128, false))
                .setContentIntent(resultPendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous,
                        "Previous", resultPendingIntent)
                .addAction(android.R.drawable.ic_media_play, "Play",
                        resultPendingIntent).build();

        startForeground(101,
                notification);
        startService(new Intent(getApplicationContext(),MyFirebaseMessagingService.class));
        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onTaskRemoved(Intent rootIntent) {

        super.onTaskRemoved(rootIntent);
        //startService(new Intent(this,YourService.class));

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        //startService(new Intent(this,YourService.class));
    }
}