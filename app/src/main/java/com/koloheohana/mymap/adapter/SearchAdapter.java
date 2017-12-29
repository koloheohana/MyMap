package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SearchActivity;
import com.koloheohana.mymap.map.ShopDate;

import java.util.ArrayList;

/**
 * Created by User on 2017/12/29.
 */

public class SearchAdapter extends ArrayAdapter<ShopDate> {
    private android.view.LayoutInflater LayoutInflater;
    ShopDate user;

    public SearchAdapter(Context c, int i, ArrayList<ShopDate> list) {
        super(c, i, list);
        this.LayoutInflater = (android.view.LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View view, ViewGroup vp) {
        ViewHolder vh = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.inflate(R.layout.list_item, vp, false);
            vh.set(((ImageView) view.findViewById(R.id.icon)), ((TextView) view.findViewById(R.id.name)), ((TextView) view.findViewById(R.id.loc)));
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        user = (ShopDate) getItem(pos);

        vh.iv.setImageBitmap(BitmapFactory.decodeResource(SearchActivity.ME.getResources(),R.mipmap.hidari));
        vh.name.setText(user.getShopName());
        vh.loc.setText(user.getADDRRES());
        return view;
    }

    public int max_size = 10;

}

class ViewHolder {
    ImageView iv;
    TextView name;
    TextView loc;

    public void setIv(ImageView iv) {
        this.iv = iv;
    }

    public void set(ImageView _iv, TextView _name, TextView _loc) {
        this.iv = _iv;
        this.name = _name;
        this.loc = _loc;
    }
}
