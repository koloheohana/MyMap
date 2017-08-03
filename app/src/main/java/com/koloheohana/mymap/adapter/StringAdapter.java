package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;

/**
 * Created by User on 2017/07/27.
 */

public class StringAdapter extends ArrayAdapter<String> {
    private android.view.LayoutInflater LayoutInflater;

    public StringAdapter(Context c, int i, ArrayList<String> list) {
        super(c, i, list);
        this.LayoutInflater = (android.view.LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View view, ViewGroup vp) {
        TextView textView;
        return view;
    }
}
