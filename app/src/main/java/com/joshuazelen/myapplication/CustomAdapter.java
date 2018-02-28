package com.joshuazelen.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
//import com.telerik.widget.list;

/**
 * Created by Joshua on 2/24/18.


public class CustomAdapter extends ArrayAdapter {

    @Override
    public View getView(int position, View view, ViewGroup parent){

        ListViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ListViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.listitems, null, true);
            viewHolder.itmName = (TextView) view.findViewById(R.id.Item_name);
            viewHolder.itmPrice = (EditText) view.findViewById(R.id.Item_price);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ListViewHolder) view.getTag();
// loadSavedValues();
        }
        viewHolder.itmName.setText(itemNames[position]);
        viewHolder.itmPrice.setId(position);
        viewHolder.id = position;
        if (selItems != null && selItems.get(position) != null) {
            viewHolder.itmPrice.setText(selItems.get(position));
        } else {
            viewHolder.itmPrice.setText(null);
        }
// Add listener for edit text
        viewHolder.itmPrice
                .setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
/*
 * When focus is lost save the entered value for
 * later use

                        if (!hasFocus) {
                            int itemIndex = v.getId();
                            String enteredPrice = ((EditText) v).getText()
                                    .toString();
                            selItems.put(itemIndex, enteredPrice);
                        }
                    }
                });
        return view;
    }
}
*/