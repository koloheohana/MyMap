package com.koloheohana.mymap.map;

import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import java.io.Serializable;

/**
 * Created by User on 2017/04/27.
 */
public class ShopDataIntent implements Serializable {
    public String SHOP_NAME;
    public String SHOP_ADDRRES;
    public int USER_ID;
    public String file_name;
    public ShopDataIntent(String str, String shop_addrres, int user_id,String file) {
        SHOP_NAME = str;
        SHOP_ADDRRES = shop_addrres;
        USER_ID = user_id;
        file_name = file;
    }
}
