package ua.gram.munhauzen;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;




import en.munchausen.fingertipsandcompany.full.R;

import static android.support.v4.app.NotificationCompat.PRIORITY_DEFAULT;


public class NotificationHelper {

    private static final String CHANNEL_ID = "simplified_coding";
    private static final String CHANNEL_NAME = "simplified_coding";
    private static final String CHANNEL_DESC = "Simplified_coding Notifications";


    public static void displayNotification(Context context, String title, String body){
        Intent intent = new Intent(context, AndroidLauncher.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher_foreground)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(PRIORITY_DEFAULT);

        NotificationManagerCompat mNotificationMgr = NotificationManagerCompat.from(context);

        mNotificationMgr.notify(1, mBuilder.build());

    }
}
