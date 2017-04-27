package com.koloheohana.mymap.map;

import java.io.Serializable;

/**
 * Created by User on 2017/04/27.
 */
public class ShopDataIntent implements Serializable {
    public String SHOP_NAME;
    public ShopDate sd;
    public ShopDataIntent(String str) {
        SHOP_NAME = str;
    }
}
