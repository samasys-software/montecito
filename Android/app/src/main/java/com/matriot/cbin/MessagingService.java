package com.matriot.cbin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("FireBase Message", "From: " + remoteMessage.getFrom());
        Log.d("FireBase Message", "Notification Message Body: " + remoteMessage.getNotification().getBody());

    }

}
