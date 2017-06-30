package com.koloheohana.mymap.map;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaShopData;
import com.koloheohana.mymap.data_base.OrmaShopData_Selector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 2016/12/07.
 */
public class ShopList {
    public static ArrayList<ShopDate> ALLLIST = new ArrayList<ShopDate>();
    private static HashMap<LatLng,ShopDate> SHOP_MAP_LATLNG = new HashMap<LatLng, ShopDate>();
    public static void setShopList(ShopDate sd){
        if(SHOP_MAP_LATLNG.containsKey(sd.getLATLNG())){
            ShopDate _sd = SHOP_MAP_LATLNG.get(sd.getLATLNG());
            if(_sd.CATEGORY.indexOf(sd.FIRST_CATEGORY) == -1){
                _sd.CATEGORY.add(sd.FIRST_CATEGORY);
            }
            return;
        }
        ALLLIST.add(sd);
        SHOP_MAP_LATLNG.put(sd.getLATLNG(),sd);
    }
    public static ShopDate getShopDate(Context context,long id){
        return new ShopDate(OrmaOperator.getOrmaShopData(context,id));
    }
    public static ShopDate getShopDate(String shop_name,String shop_addrres){
        for(OrmaShopData sd:OrmaOperator.getShopDataSelector()){
            if(sd.addrres.matches(shop_addrres)&&sd.shop_name.matches(shop_name)){
                return new ShopDate(sd);
            }
        }
        return null;
    }
}
