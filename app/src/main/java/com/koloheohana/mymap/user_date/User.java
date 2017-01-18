package com.koloheohana.mymap.user_date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.koloheohana.mymap.MainActivity;

/**
 * Created by User on 2016/08/24.
 */
public class User {
    private Bitmap icon;
    private String name;
    private String loc;
    public User getUser(int _icon,String name ,String loc){
        setIcon(BitmapFactory.decodeResource(MainActivity.ME.getResources(), _icon));
        setName(name);
        setLoc(loc);
        return this;
    }
    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}