package com.koloheohana.mymap.sns;

import android.graphics.Bitmap;
import android.net.Uri;

import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.User;

import java.io.Serializable;

/**
 * Created by User on 2017/04/24.
 */
public class OneTork implements Serializable{
    String TORK;
    User USER;
    int ID;
    Clocks CLOCK;
    ShopDate SHOP_DATA;
    Uri GALLARY_URI;
    Uri MAP_URI;
    String FILE_NAME;
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
