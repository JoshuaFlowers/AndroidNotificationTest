package com.joshuazelen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    public static NotificationManager mNotificationManager;
    Context context;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<LinearLayout> rowList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){

        }

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        context=getApplicationContext();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialize/get Shared Preference named "NiftyNotiPrefs" and preference editor
        sharedPreferences = context.getSharedPreferences("NiftyNotiPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        //Initialize notification manager
        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        rowList = new ArrayList<LinearLayout>();

        //Set recyclerview to custom recyclerview with Id "my_recycler_view". RecyclerView was created in a layout file with custom parameters
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(false);



        mLayoutManager = new LinearLayoutManager(getApplicationContext());

        //Add bar indicating separation of items in RecyclerView
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(mLayoutManager);

        //Get recycler view data from data previously saved in shared preferences
        ArrayList<String> rowData = new ArrayList<String>(sharedPreferences.getStringSet("notifIds", new LinkedHashSet<String>(0)));

        //Add new blank row to recyclerview data
        rowData.add("");

        //Creates new custom Adapter (MyAdapter) using the rowData obtained from shared preferences
        mAdapter = new MyAdapter(rowData, context);

        //Set recyclerview adapter to adapter created above
        mRecyclerView.setAdapter(mAdapter);



        //Set action for Floating Action Button on main screen to add new row when pressed
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAdapter.addNewRow();
            }
        });


        //make switch preference - TEST
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPref.getBoolean
                (SettingsActivity.KEY_PREF_EXAMPLE_SWITCH, false);
    }

    @Override
    public void onSaveInstanceState(Bundle state){
        super.onSaveInstanceState(state);
    }

    private void setRecyclerViewData(){
        if(!sharedPreferences.getStringSet("notifIds", new HashSet(0)).isEmpty()){
            LinkedHashSet<String> notifIds = new LinkedHashSet<String>(sharedPreferences.getStringSet("notifIds", new HashSet(0)));
            for (String notif: notifIds){
                //mAdapter.addRow(notif);
                LinearLayout newRow = (LinearLayout) LayoutInflater.from(mRecyclerView.getContext()).inflate(R.layout.listitems, null, false);
                EditText newRowEditText = newRow.findViewById(R.id.Notification_Name);
                FloatingActionButton newRowFab = (FloatingActionButton) newRow.findViewById(R.id.AddNotifButton1);

                newRowEditText.setText(sharedPreferences.getString(notif,""));

                int thisId = Integer.parseInt(notif);

                newRowFab.setOnClickListener(new MyOnClickListener(context, newRowEditText, thisId));


                rowList.add(newRow);
            }
        }
        rowList.add((LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.listitems, null, false));

    }


    //////////***************////////////



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

            return true;
        }*/

        if (id == R.id.action_reset_settings) {
            editor.clear().commit();

            return true;
        }



        return super.onOptionsItemSelected(item);
    }
}
