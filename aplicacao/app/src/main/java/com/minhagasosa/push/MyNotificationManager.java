package com.minhagasosa.push;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.minhagasosa.R;
import com.minhagasosa.activites.maps.GasStationActivity;


public class MyNotificationManager {

    private Context context;

    public static final int NOTIFICATION_ID = 234;

    public MyNotificationManager(Context context) {
        this.context = context;
    }

    //public void showNotification(String from, String notification, Intent intent) {
    public void showNotification(String from, String notification, String id) {

        Intent intent = new Intent(context, GasStationActivity.class);
        intent.putExtra("gas", id);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Notification notificationFCM = builder
                .setSmallIcon(R.mipmap.ic_new_releases_white_24dp)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(from)
                .setContentText(notification)
                .build();

        notificationFCM.flags |= Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notificationFCM);
    }

}
