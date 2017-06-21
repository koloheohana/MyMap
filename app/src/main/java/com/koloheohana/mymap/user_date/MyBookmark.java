package com.koloheohana.mymap.user_date;

import android.content.Context;
import android.widget.PopupMenu;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaShopData;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;

import java.util.ArrayList;

/**
 * Created by User on 2017/04/05.
 */
public class MyBookmark {
    private static ArrayList<ShopDate> bookmark_list = new ArrayList<>();
    public static void set(ShopDate sd){
        if(OrmaOperator.setBookmark(MainActivity.ME,sd.SHOP_ID,true)){
            return;
        }
        bookmark_list.add(sd);

    }
    public static void release(ShopDate sd){
        bookmark_list.remove(sd);
        OrmaOperator.setBookmark(MainActivity.ME,sd.SHOP_ID,false);
    }

    public static ArrayList<ShopDate> getList(){
        return bookmark_list;
    }

}
