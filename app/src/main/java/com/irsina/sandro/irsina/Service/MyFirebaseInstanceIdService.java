package com.irsina.sandro.irsina.Service;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        sendNewTokenToServer(FirebaseInstanceId.getInstance().getToken());
    }
    @Override
    public void onTaskRemoved(Intent rootIntent) {

        Intent intent = new Intent(this,MyFirebaseInstanceIdService.class);
        sendBroadcast(intent);
        super.onTaskRemoved(rootIntent);
    }
    @Override
    public void onDestroy() {

        Intent i= new Intent(this,MyFirebaseInstanceIdService.class);
        startService(i);
        super.onDestroy();
    }

    private void sendNewTokenToServer(String token) {
        FirebaseMessaging.getInstance().subscribeToTopic("irsina");

        Log.d("TOKEN",String.valueOf(token));
    }
}
