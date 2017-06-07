package com.koloheohana.mymap.user_date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.sns.OneTork;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private android.view.LayoutInflater LayoutInflater;
    User USER;
    public UserAdapter(Context c, int i, ArrayList<User> list) {
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
        USER = (User) getItem(pos);

        vh.iv.setImageBitmap(USER.getIcon());
        vh.name.setText(USER.getName());
        vh.loc.setText(setLastTork());
        return view;
    }
    public int max_size = 10;
    public String setLastTork(){
        String tork = null;
        if(USER.TORK.isEmpty()){
            return tork;
        }
        OneTork oneTork = USER.TORK.get(USER.TORK.size()-1);
        tork = oneTork.getTork();
        if(tork.length() >= max_size){
            tork.substring(0,max_size);
        }
        return tork;
    }
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


