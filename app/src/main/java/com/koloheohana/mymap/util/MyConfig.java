package com.koloheohana.mymap.util;

import android.content.Context;

import com.koloheohana.mymap.data_base.OrmaOperator;

/**
 * Created by User on 2017/06/27.
 */

public class MyConfig {
    public static int map_marker_max = 300;
    public static void setDate(Context context){
        map_marker_max = OrmaOperator.getConfig(context).max_shop_search;
        System.out.println("marker:"+map_marker_max);
    }
}
