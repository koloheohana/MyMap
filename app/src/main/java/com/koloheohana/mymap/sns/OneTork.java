package com.koloheohana.mymap.sns;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaTork;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.User;

import java.io.Serializable;

/**
 * Created by User on 2017/04/24.
 */
public class OneTork implements Serializable{
    String TORK;
    long USER_ID;
    User USER;
    long ID;
    Clocks CLOCK;
    ShopDate SHOP_DATA;
    Uri GALLARY_URI;
    Uri MAP_URI;
    String FILE_NAME;
    public OneTork(Context context,OrmaTork ormaTork){
        if(ormaTork.image_switch){
            if(!ormaTork.camera_picture){
                GALLARY_URI = SaveDateController.getUri(ormaTork.image_uri);
                TORK = ormaTork.image_uri;
            }else{
                MAP_URI = SaveDateController.getUri(ormaTork.image_uri);
                TORK = ormaTork.image_uri;
                SHOP_DATA = new ShopDate(OrmaOperator.getOrmaShopData(context,ormaTork.shop_id));
            }
        }else {
            TORK = ormaTork.tork_sentence;
        }
        CLOCK = new Clocks(ormaTork.clock);
        USER_ID = ormaTork.user_id;
        ID = ormaTork.user_id;
    }
    public OneTork(String tork, Clocks clocks,User user,Uri gallaryUri,ShopDate sd,Uri mapUri){
        if(tork == null){
            if(gallaryUri != null){
                GALLARY_URI = gallaryUri;
                TORK = gallaryUri.toString();
            }else if(mapUri != null){
                MAP_URI = mapUri;
                TORK = mapUri.toString();
            }
        }else {
            TORK = tork;
        }
        CLOCK = clocks;
        USER = user;
        ID = user.TORK.size()+1;
        SHOP_DATA = sd;
    }
    public boolean isImage(){
        if(GALLARY_URI == null&& MAP_URI == null){
            return false;
        }
        return true;
    }
    public boolean isCamera(){
        return GALLARY_URI != null;
    }
    public int getID(){
        return (int) ID;
    }
    public String getFILE_NAME(){
        return FILE_NAME;
    }
    public ShopDate getShopData(){
        return SHOP_DATA;
    }
    public Uri getUri(){
        if(GALLARY_URI != null){
            return GALLARY_URI;
        }else{
            return MAP_URI;
        }
    }
    public String getStringUri(){
        String str = "";
        System.out.println("URI:"+getUri());
        if(isImage()){
            return getUri().toString();
        }
        return str;
    }
    public String getStringFileConverter(){
        StringBuffer sb = new StringBuffer();
        sb.append(ID+"＼"+"ID∥"+USER.getId()+"＼"+"CLOCK∥"+CLOCK.getStringSandTheString("∥")+"＼"+"TORK∥"+TORK+"\n");
        return sb.toString();
    }
    public String getTork(){
        return TORK;
    }
    public Clocks getClock(){
        return CLOCK;
    }
}
class TorkShareMap{
    Bitmap BIT;
    ShopDate SD;
    public TorkShareMap(Bitmap bit,ShopDate sd){
        BIT = bit;
        SD = sd;
    }
}
