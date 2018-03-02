package com.joshuazelen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int NOTIFICATION_ID=0;
    public static final String ACTION_NOTIFY="com.joshuazelen.constantnotification";
    public static NotificationManager mNotificationManager;
    public static PendingIntent contentPendingIntent;
    Context context;
    EditText mEdit1;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<LinearLayout> rowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //*********************
        super.onCreate(savedInstanceState);
        context=getApplicationContext();
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       /* mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent(context,MainActivity.class);
        contentPendingIntent = PendingIntent.getActivity(context,NOTIFICATION_ID,contentIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mEdit1 = (EditText) findViewById(R.id.Notification_Name);*/

        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mEdit1 = (EditText) findViewById(R.id.Notification_Name);


        rowList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        setRecyclerViewData();

        mAdapter = new MyAdapter(rowList, context, new MyOnClickListener() {

            @Override
            public void onClick(View v) {
                EditText mEdit = (EditText) findViewById(this.getId());
                deliverNotification(context, mEdit);
            }

        });
        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rowList.add((LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.listitems,null, false));
                mAdapter.notifyItemInserted(rowList.size());

            }
        });
    }

    private void setRecyclerViewData(){
        rowList.add((LinearLayout) LayoutInflater.from(this).inflate(R.layout.listitems,null, false));

    }

    public static void deliverNotification(Context context, EditText editor){

        /*Intent dismissNotificationIntent = new Intent(context, Notification.class);
        dismissNotificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        dismissNotificationIntent.putExtra(NOTIFICATION_ID, notificationId);*/

        Intent contentIntent = new Intent(context,NotificationActivity.class);
        //contentIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        contentPendingIntent = NotificationActivity.getDismissIntent(NOTIFICATION_ID,context);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setContentIntent(contentPendingIntent);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setOngoing(true);
        builder.setAutoCancel(false);
        builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", contentPendingIntent);
        mNotificationManager.notify(NOTIFICATION_ID++,builder.build());

    }

    public static Notification buildNotification(Context context, EditText editor){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setContentIntent(contentPendingIntent);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setAutoCancel(false);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        builder.setOngoing(true);
        //builder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "Dismiss", )
        return builder.build();

    }


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
