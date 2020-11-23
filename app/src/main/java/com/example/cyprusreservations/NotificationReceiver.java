package com.example.cyprusreservations;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"1");
        NotificationChannel channel = null;
        channel = new NotificationChannel(
                "1",
                "channel1",
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        //default ringtone uri
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context.getApplicationContext(), "1")
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle("AEK vs DOKSA 5/12")
                .setContentText("Make your reservation now at Confuzio Cafe!")
                .setSound(uri)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.ic_launcher_foreground, "View", pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notifyAdmin = NotificationManagerCompat.from(context);
        notifyAdmin.notify(1, notification.build());

    }
}
