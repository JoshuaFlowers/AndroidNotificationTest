package com.joshuazelen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static int NOTIFICATION_ID=0;
    public static final String ACTION_NOTIFY="com.joshuazelen.constantnotification";
    public static NotificationManager mNotificationManager;
    public static PendingIntent contentPendingIntent;
    Context context;
    EditText mEdit1;
    Object[] listItems;
    ListView list;
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
        listItems=new Object[]{new EditText(context), "apples", "Momo", "booty", 5};
        //list=(ListView)findViewById(R.id.NotifList);


        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent contentIntent = new Intent(context,MainActivity.class);
        contentPendingIntent = PendingIntent.getActivity(context,NOTIFICATION_ID,contentIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        mEdit1 = (EditText) findViewById(R.id.Notification_Name);


        String[] myDataset = {"aples", "Big Bob", "Bunny"};
        rowList = new ArrayList<>();


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        setRecyclerViewData();
        //Notification note = buildNotification(context, mEdit1);

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

        //mAdapter = new MyAdapter(myDataset);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                //ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listItems);
                //list.setAdapter(adapter);
                rowList.add((LinearLayout) LayoutInflater.from(MainActivity.this).inflate(R.layout.listitems,null, false));
                mAdapter.notifyItemInserted(rowList.size());

            }
        });
        //**********************


        //deliverNotification(context, mEdit1);

        //((LinearLayout) findViewById(mRecyclerView.getAdapter().getItemId(1))).;
/*        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.AddNotifButton1);
        fab1.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view){
                deliverNotification(context, mEdit1);

           }
        });*/
    }

    private void setRecyclerViewData(){
        for(int i=0;i<2;i++){
            rowList.add((LinearLayout) LayoutInflater.from(this).inflate(R.layout.listitems,null, false));
        }

    }

    public static void deliverNotification(Context context, EditText editor){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setContentIntent(contentPendingIntent);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setAutoCancel(false);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        //builder.
        mNotificationManager.notify(++NOTIFICATION_ID,builder.build());

    }

    public static Notification buildNotification(Context context, EditText editor){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.ic_strikethrough_s);
        builder.setContentTitle(editor.getText().toString());
        builder.setContentText(context.getString(R.string.notification_text));
        builder.setContentIntent(contentPendingIntent);
        builder.setPriority(Notification.PRIORITY_MAX);
        builder.setAutoCancel(true);
        builder.setDefaults(NotificationCompat.DEFAULT_ALL);
        //builder.
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
