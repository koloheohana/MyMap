package com.koloheohana.mymap.map;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by User on 2016/12/07.
 */
public class ShopList {
    public static ArrayList<ShopDate> ALLLIST = new ArrayList<ShopDate>();
    public static HashMap<LatLng,ShopDate> SHOP_MAP_LATLNG = new HashMap<LatLng, ShopDate>();
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
    public static ShopDate getShopDate(String shop_name,String shop_addrres){
        for(ShopDate sd:ALLLIST){
            if(sd.getADDRRES().matches(shop_addrres)&&sd.getShopName().matches(shop_name)){
                return sd;
            }
        }
        return null;
    }
}
