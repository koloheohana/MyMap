package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;

import java.util.List;

/**
 * Created by User on 2017/04/12.
 */
public class MyBookMarkAdapter extends ArrayAdapter<ShopDate> {
    private LayoutInflater inflater;

    public MyBookMarkAdapter(Context context, int resource, List<ShopDate> objects) {
        super(context, resource, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ShopDate item = (ShopDate) getItem(position);
        if (null == v) v = inflater.inflate(R.layout.custom_listview, null);

        TextView intTextView = (TextView)v.findViewById(R.id.int_item);
        intTextView.setText(item.getShopName());

        TextView stringTextView = (TextView)v.findViewById(R.id.string_item);
        stringTextView.setText(item.getADDRRES());
        return v;
    }
}
