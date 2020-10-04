package com.food_on.app.SendNotification;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String title, message, typepage;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        title = remoteMessage.getData().get("Title");
        message = remoteMessage.getData().get("Message");
        typepage = remoteMessage.getData().get("Typepage");

        ShowNotification.ShowNotif(getApplicationContext(),title,message,typepage);
    }
}
