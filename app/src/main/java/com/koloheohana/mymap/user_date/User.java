package com.koloheohana.mymap.user_date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.sns.OneTork;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class User {
    private int id;
    private Bitmap icon;

    private String name;
    private String mutter;
    public ArrayList<OneTork> TORK = new ArrayList<OneTork>();
    public User(int _id ,int _icon,String name ,String mutter){
        id = _id;
        setIcon(BitmapFactory.decodeResource(MainActivity.ME.getResources(), _icon));
        setName(name);
        setLoc(mutter);
        for(int i = 0 ; i <= 20;i++) {
            if(i == 10){
                TORK.add(new OneTork(String.valueOf(i)+"\ncreate",new Clocks(MainActivity.ME),this));
            }
            TORK.add(new OneTork(String.valueOf(i),new Clocks(MainActivity.ME),this));
        }
    }
    public void addTork(OneTork tork){
        TORK.add(tork);
    }
    public void removeTork(OneTork tork){
        TORK.remove(tork);
    }
    public User getUser(){
        return this;
    }
    public String getLoc() {
        return mutter;
    }

    public void setLoc(String loc) {
        this.mutter = loc;
    }
    public int getId(){
        return this.id;
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