package com.joshuazelen.myapplication;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.widget.EditText;

import java.util.Random;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Helper class for showing and canceling new custom
 * notifications.
 * <p>
 * This class makes heavy use of the {@link NotificationCompat.Builder} helper
 * class to create notifications in a backward-compatible way.
 */
public class NewCustomNotification {
    /**
     * The unique identifier for this type of notification.
     */
    //private static final String NOTIFICATION_TAG = "ConstantNotification";


    public static void notify(Context context, EditText editor){

        int NOTIFICATION_ID = new Random(System.currentTimeMillis()).nextInt();
        PendingIntent contentPendingIntent = NotificationActivity.getDismissIntent(NOTIFICATION_ID,context);

        android.support.v7.app.NotificationCompat.Builder builder=new android.support.v7.app.NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(android.support.v7.app.NotificationCompat.DEFAULT_ALL);
        builder.setOngoing(true);
        builder.setAutoCancel(false);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", contentPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }

    public static void notify(Context context, EditText editor, int myId){

        int NOTIFICATION_ID = myId;
        PendingIntent contentPendingIntent = NotificationActivity.getDismissIntent(NOTIFICATION_ID,context);

        android.support.v7.app.NotificationCompat.Builder builder=new android.support.v7.app.NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(android.support.v7.app.NotificationCompat.DEFAULT_ALL);
        builder.setOngoing(true);
        builder.setAutoCancel(false);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", contentPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
