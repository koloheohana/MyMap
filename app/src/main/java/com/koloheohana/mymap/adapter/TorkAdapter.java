package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class TorkAdapter extends ArrayAdapter<OneTork> {
    private android.view.LayoutInflater LayoutInflater;
    OneTork TORK;
    public TorkAdapter(Context c, int i, ArrayList<OneTork> list) {
        super(c, i, list);
        this.LayoutInflater = (android.view.LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int pos, View view, ViewGroup vp) {

        aViewHolder vh = new aViewHolder();
        if (view == null) {
            view = LayoutInflater.inflate(R.layout.tork_list_item, vp, false);
            vh.set((TextView)view.findViewById(R.id.tork_name));
            vh.setText((TextView)view.findViewById(R.id.tork_text_box));
            view.setTag(vh);
        } else {
            vh = (aViewHolder) view.getTag();
        }

        TORK = (OneTork)getItem(pos);
        System.out.println("check"+pos);
        vh.name.setText("test");
        vh.text.setText(TORK.getTork());
        return view;
    }
}
class aViewHolder {
    TextView name;
    TextView text;
    public void setText(TextView _text){
        this.text = _text;
    }

    public void set(TextView _name) {
        this.name = _name;
    }
}


