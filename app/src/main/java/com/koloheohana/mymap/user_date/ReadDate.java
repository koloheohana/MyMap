package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.sns.ReadFileSns;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    public static void read(){
        MyBookmark.read();
        ShopMemo.read();
        ReadFileSns.read();
    }
}
