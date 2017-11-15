package com.koloheohana.mymap.shop;

import android.content.Context;
import android.text.TextUtils;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDate;

import java.util.ArrayList;

/**
 * Created by User on 2017/11/14.
 */

public class PickUpShop {
    public static int new_shops_value = 0;
    public static ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
    public static ArrayList<ShopDate> new_list = new ArrayList<ShopDate>();
    public static ArrayList<String> string_list = new ArrayList<String>();
    public static void addList(ShopDate sd){
        shop_list.add(sd);
    }
    public static int getShopListSize(){
        return shop_list.size();
    }
    public static void fileLoad(Context context){
        string_list  = SaveDateController.readCsv(context,R.raw.news);
        System.out.println("読込テスト"+string_list.size());
        for(String shop:string_list){
            String[] str = shop.split(",");
            if(str[0].matches("新規")){
                ShopDate sd = new ShopDate(50000,str[3],str[4],str[2],str[3],str[5],Double.valueOf(str[6]),Double.valueOf(str[7]));
                sd.setPlusData(Integer.valueOf(str[9]),str[11],str[8],str[12],str[13],str[10]);
                new_list.add(sd);
                addList(sd);
            }else if (str[0].matches("更新")){

            }else{
                continue;
            }
        }
        new_shops_value = new_list.size();
    }
}
