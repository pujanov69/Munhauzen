package ua.gram.munhauzen;

import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.annotation.NonNull;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getNotification() != null){
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            Uri uri = remoteMessage.getNotification().getLink();

            System.out.println("Uri---->" + uri);

            NotificationHelper.displayNotification(getApplicationContext(), title,body);
        }
    }
}
