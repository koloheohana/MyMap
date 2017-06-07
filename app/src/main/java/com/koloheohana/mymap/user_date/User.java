package com.koloheohana.mymap.user_date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.koloheohana.mymap.MainActivity;
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
        readTorkFile();

    }
    public void readTorkFile(){

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
    public int getUserId(){
        return this.id;
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