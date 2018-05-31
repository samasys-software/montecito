package com.matriot.cbin;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.matriot.cbin.ui.Home;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("FireBase Message", "From: " + remoteMessage.getFrom());
        if(remoteMessage.getData().size()>0) {
            Log.d("FireBase Message", "Notification Message Body: " + remoteMessage.getData());
            Notification mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.logo_icon)
                            .setContentTitle("Cbin").build();

            mBuilder.contentIntent=  PendingIntent.getActivity(this, 0,
                    new Intent(this, Home.class), PendingIntent.FLAG_UPDATE_CURRENT);
        }



    }

}
