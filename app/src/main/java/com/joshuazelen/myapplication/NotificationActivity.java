package com.joshuazelen.myapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.LinkedHashSet;

/**
 * Created by Joshua on 3/1/18.
 */

public class NotificationActivity extends Activity {

    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        int notifId = getIntent().getIntExtra(NOTIFICATION_ID,-1);
        manager.cancel(notifId);
        SharedPreferences sharedPreferences = this.getSharedPreferences("NiftyNotiPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        LinkedHashSet<String> notifSet = new LinkedHashSet<String>(sharedPreferences.getStringSet("notifIds", new LinkedHashSet<String>(0)));
        notifSet.remove(String.valueOf(notifId));
        editor.putStringSet("notifIds", notifSet);
        editor.remove(String.valueOf(notifId));
        editor.commit();
        finish();
    }

    public static PendingIntent getDismissIntent(int notificationId, Context context){
        Intent intent = new Intent(context, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK );
        intent.putExtra(NOTIFICATION_ID, notificationId);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent dismissIntent = PendingIntent.getActivity(context, 0, intent,PendingIntent.FLAG_ONE_SHOT);
        return dismissIntent;
    }
}
