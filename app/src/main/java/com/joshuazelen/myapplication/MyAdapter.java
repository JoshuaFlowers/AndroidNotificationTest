package com.joshuazelen.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
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

import static android.content.Context.NOTIFICATION_SERVICE;
import static java.security.AccessController.getContext;

/**
 * Created by Joshua on 2/27/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;
    private ArrayList<LinearLayout> mRows;
    private static NotificationManager mNotificationManager;
    private Context mContext;


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

    public MyAdapter(ArrayList<LinearLayout> myRows, Context context, MyOnClickListener cl){
        mRows = myRows;
        mContext = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LinearLayout vi = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.listitems, parent, false);
        ViewHolder vh = new ViewHolder(vi);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mEditText.setId(position);
        holder.fab.setOnClickListener(new MyOnClickListener(position, mContext, holder.mEditText));
    }

    @Override
    public int getItemCount() {
        return mRows.size();
    }

    public LinearLayout[] getRows(){ return (LinearLayout[]) mRows.toArray();}


}