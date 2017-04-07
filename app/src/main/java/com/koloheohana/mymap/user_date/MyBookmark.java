package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;

import java.util.ArrayList;

/**
 * Created by User on 2017/04/05.
 */
public class MyBookmark {
    private static ArrayList<ShopDate> bookmark_list = new ArrayList<>();
    public static boolean READ_ON =false;
    public static void set(ShopDate sd){
        if(!READ_ON){
            read();
        }
        if(isSetBookmark(sd)){
           return;
        }
        bookmark_list.add(sd);
        SaveDateController.bookmarkWriter(sd);
    }
    public static void read(){
        ArrayList<String[]> list = SaveDateController.bookmarkReader();
        for(ShopDate sd:ShopList.ALLLIST){
            for(String[] str:list){
                if(sd.getADDRRES().equals(str[1])){
                    if(sd.getShopName().equals(str[0])){
                        bookmark_list.add(sd);
                    }
                }
            }
        }
    }
    public static ArrayList<ShopDate> getList(){
        return bookmark_list;
    }
    public static boolean isSetBookmark(ShopDate sd){
        if(bookmark_list.contains(sd)){
            return true;
        }

        return false;
    }
}
