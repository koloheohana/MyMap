package com.koloheohana.mymap.user_date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaTork;
import com.koloheohana.mymap.data_base.OrmaUser;
import com.koloheohana.mymap.sns.OneTork;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class User {
    private long id;
    private Bitmap icon;
    private String name;
    private String mutter;
    private int age;
    private String addrres;
    private String tel;
    public ArrayList<OneTork> TORK = new ArrayList<OneTork>();
    public User(long _id ,int _icon,String name ,String mutter){
        id = _id;
        setIcon(BitmapFactory.decodeResource(MainActivity.ME.getResources(), _icon));
        setName(name);
        setLoc(mutter);
        readTorkFile(MainActivity.ME);
    }
    public User(Context context,OrmaUser ormaUser){
        id = ormaUser.id;
        setIcon(null);
        setName(ormaUser.user_name);
        setLoc(ormaUser.addrres);
        readTorkFile(context);

    }
    public void readTorkFile(Context context){
        for(OrmaTork ot: OrmaOperator.getOrmaTorkSelector(MainActivity.ME).user_idEq(id).orderByIdAsc()){
            TORK.add(new OneTork(context,ot));
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
    public long getUserId(){
        return this.id;
    }
    public long getId(){
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