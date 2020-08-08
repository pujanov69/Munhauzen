package ua.gram.munhauzen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import ua.gram.munhauzen.utils.Log;

import static com.facebook.FacebookSdk.getApplicationContext;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("AlertReceiver"," triggered");
        NotificationHelper.displayNotification(getApplicationContext(), "title","body");
    }
}
