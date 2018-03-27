package com.joshuazelen.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Joshua on 2/27/18.
 */

public class MyOnClickListener implements View.OnClickListener {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    Context context;
    EditText mEditText;
    private int id;


    public MyOnClickListener(Context context, EditText mEditText, int myId){
        super();
        this.id = myId;
        this.context = context;
        this.mEditText = mEditText;
        sharedPreferences = context.getSharedPreferences("NiftyNotiPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void onClick(View v) {
        NewCustomNotification.notify(context, mEditText, id);
        LinkedHashSet<String> notifSet = new LinkedHashSet<String>(sharedPreferences.getStringSet("notifIds", new LinkedHashSet<String>(0)));
        notifSet.add(String.valueOf(id));
        editor.putStringSet("notifIds", notifSet);
        editor.putString(String.valueOf(id), mEditText.getText().toString());
        editor.commit();

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }
}
