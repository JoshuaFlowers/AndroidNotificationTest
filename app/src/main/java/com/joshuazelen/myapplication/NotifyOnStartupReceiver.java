package com.joshuazelen.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import java.util.LinkedHashSet;

/**
 * Created by Joshua on 3/27/18.
 */

public class NotifyOnStartupReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){

            //Get Shared Preferences named "NiftyNotiPrefs"
            SharedPreferences sharedPreferences = context.getSharedPreferences("NiftyNotiPrefs", Context.MODE_PRIVATE);

            if(sharedPreferences.getBoolean("startup_notifications", true)) {

                //Get list of notification Ids from shared preferences
                LinkedHashSet<String> notificationIds = new LinkedHashSet<String>(sharedPreferences.getStringSet("notifIds", new LinkedHashSet<String>(0)));

                //for each notification Id, get the corresponding notification text and notify the user
                for (String id : notificationIds) {
                    String notifText = sharedPreferences.getString(id, "");
                    NewCustomNotification.notify(context, notifText, Integer.parseInt(id));
                }
            }
        }
    }
}
