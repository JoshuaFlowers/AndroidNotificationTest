package com.joshuazelen.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DismissNotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.getAction();
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
