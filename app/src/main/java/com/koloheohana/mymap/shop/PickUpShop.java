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
    public static ArrayList<ShopDate> new_list = new ArrayList<ShopDate>();
    public static ArrayList<String> string_list = new ArrayList<String>();
    public static void fileLoad(Context context){
        string_list  = SaveDateController.readCsv(context,R.raw.news);
        for(String shop:string_list){
            String[] str = shop.split(",");
            if(str[0].matches("新着")){
                ShopDate sd = new ShopDate(50000,str[4],"468-0011",str[2],str[3],"08069180544",Double.valueOf(str[5]),Double.valueOf(str[6]));
                new_list.add(sd);
            }else if (str[0].matches("更新")){

            }else{
                continue;
            }
        }
        new_shops_value = new_list.size();
    }
}
