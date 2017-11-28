package com.koloheohana.mymap.user_date;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.koloheohana.mymap.LoadingActivity;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaTork;
import com.koloheohana.mymap.data_base.OrmaUser;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.util.BitmapReader;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class User {
    private long id;
    private String server_id;
    private Bitmap icon;
    private String name;
    private String mutter;
    private String comment;
    private int age;
    private String addrres;
    private String tel;
    public ArrayList<OneTork> TORK = new ArrayList<OneTork>();
    public User(long _id ,String _icon,String name ,String mutter){
        System.out.println(_icon);
        id = _id;
        if(_id == -1){
            System.out.println("アイコン:"+_icon);
        }
        if(_icon.startsWith("file")) {
            setIcon(BitmapReader.getBitmap(LoadingActivity.ME,SaveDateController.getUri(_icon),false));
        }else {
            setIcon(BitmapFactory.decodeResource(LoadingActivity.ME.getResources(), Integer.valueOf(_icon)));
        }
        setComment("コメント");
        setName(name);
        setLoc(mutter);
        readTorkFile(LoadingActivity.ME);
    }
    public User(Context context,OrmaUser ormaUser){
        id = ormaUser.id;
        setIcon(null);
        setName(ormaUser.user_name);
        setLoc(ormaUser.addrres);
        setComment("コメント");
        readTorkFile(context);

    }
    public String getAddrres(){
        return addrres;
    }
    public void setComment(String _comment){
        comment = _comment;
    }
    public void readTorkFile(Context context){
        for(OrmaTork ot: OrmaOperator.getOrmaTorkSelector(LoadingActivity.ME).user_idEq(id).orderByIdAsc()){
            TORK.add(new OneTork(context,ot));
        }
    }
    public String getComment(){
        return comment;
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
    public void setIcon(Context context,Uri uri){
        setIcon(BitmapReader.getBitmap(context,uri,false));
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