package com.joshuazelen.myapplication;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

/**
 * Created by Joshua on 2/27/18.
 */

public class MyOnClickListener implements View.OnClickListener {

    MyAdapter myAdapter;
    Context context;
    EditText mEditText;
    private int id;
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

    public MyOnClickListener(Context context, EditText mEditText){
        super();
        this.id = new Random(System.currentTimeMillis()).nextInt();
        this.context = context;
        this.mEditText = mEditText;
    }

    public MyOnClickListener(Context context, EditText mEditText, int myId){
        super();
        this.id = myId;
        this.context = context;
        this.mEditText = mEditText;
    }

    public MyOnClickListener(){
        super();
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public void onClick(View v) {
        NewCustomNotification.notify(context, mEditText, id);
    }
}
