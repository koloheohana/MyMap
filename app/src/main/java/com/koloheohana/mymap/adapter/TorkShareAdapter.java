package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.User;

import java.util.List;

/**
 * Created by User on 2017/04/12.
 */
public class TorkShareAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;

    public TorkShareAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        User item = (User) getItem(position);
        if (null == v) v = inflater.inflate(R.layout.tork_selection_list_item, null);

        TextView intTextView = (TextView)v.findViewById(R.id.share_tork_user);
        intTextView.setText(item.getName());

        return v;
    }
}
