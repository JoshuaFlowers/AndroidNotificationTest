package com.joshuazelen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import static android.content.Context.NOTIFICATION_SERVICE;
import static java.security.AccessController.getContext;

/**
 * Created by Joshua on 2/27/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<String> mRowsData;
    private Context mContext;
    private SharedPreferences sharedPreferences;
    private int notifID;
    private int listPosition;
    LinkedHashSet<String> notifSet;


    /*
    Created in {@link com.joshuazelen.myapplication.MyAdapter MyAdapter.onCreateViewHolder} defined below
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mLinearLayout;
        public FloatingActionButton fab;
        public EditText mEditText;

        public ViewHolder(LinearLayout v){
            super(v);
            mLinearLayout = v;
            mEditText = (EditText) v.findViewById(R.id.Notification_Name);
            fab = (FloatingActionButton) v.findViewById(R.id.AddNotifButton1);
        }

    }

    public MyAdapter(ArrayList<String> myRowsData, Context context){

        mRowsData = myRowsData;
        mContext = context;
        sharedPreferences = context.getSharedPreferences("NiftyNotiPrefs", Context.MODE_PRIVATE);
        notifSet = new LinkedHashSet<String>(sharedPreferences.getStringSet("notifIds", new LinkedHashSet<String>(0)));
        listPosition = 0;

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LinearLayout vi;

        if ((listPosition < mRowsData.size())) {
            vi = populateRow(parent, mRowsData.get(listPosition));
            listPosition++;
        }

        else {
            vi = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems, parent, false);
            //((EditText) vi.findViewById(R.id.Notification_Name)).setText("Error: onCreateViewHolder");
        }

        ViewHolder vh = new ViewHolder(vi);

        return vh;
    }

    public LinearLayout populateRow(ViewGroup parent, String notification_Id){

        LinearLayout newRow = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems, parent, false);

        if(!notification_Id.isEmpty()) {

            EditText newRowEditText = newRow.findViewById(R.id.Notification_Name);
            FloatingActionButton newRowFab = (FloatingActionButton) newRow.findViewById(R.id.AddNotifButton1);

            newRowEditText.setText(sharedPreferences.getString(notification_Id, ""));

            int thisId = Integer.parseInt(notification_Id);

            newRowFab.setOnClickListener(new MyOnClickListener(mContext, newRowEditText, thisId));

        }
        return newRow;
    }


    public void addNewRow(){

        mRowsData.add("");
        this.notifyItemInserted(mRowsData.size());

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        if (!holder.fab.hasOnClickListeners()) {
            notifID = new Random(System.currentTimeMillis()).nextInt();
            holder.fab.setOnClickListener(new MyOnClickListener(mContext, holder.mEditText, notifID));
        }

        else {
            notifID = holder.fab.getId();
        }

    }

    @Override
    public int getItemCount() {
        return mRowsData.size();
    }


}