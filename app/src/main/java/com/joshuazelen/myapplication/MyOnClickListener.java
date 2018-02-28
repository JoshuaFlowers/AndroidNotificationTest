package com.joshuazelen.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Joshua on 2/27/18.
 */

public class MyOnClickListener implements View.OnClickListener {

    MyAdapter myAdapter;
    Context context;
    EditText mEditText;
    public MyOnClickListener(MyAdapter adapter){
        super();
        this.myAdapter = adapter;
    }

    public MyOnClickListener(int id, Context context, EditText mEditText){
        super();
        this.id = id;
        this.context = context;
        this.mEditText = mEditText;
    }

    public MyOnClickListener(){
        super();
    }
    private int id;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public void onClick(View v) {
        MainActivity.deliverNotification(context, mEditText);
    }
}
