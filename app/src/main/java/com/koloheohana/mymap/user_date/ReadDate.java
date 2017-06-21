package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.sns.ReadFileSns;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    static boolean tester = false;
    static boolean clear = false;
    public static void read(){
/*
        OrmaOperator.remove(MainActivity.ME);
*/
        if(tester) {
            final CsvReader read = new CsvReader();
            read.execute();
            ShopMemo.read();
/*
            OrmaOperator.remove(MainActivity.ME,1);
*/
            OrmaOperator.createShopData(MainActivity.ME);
/*
            OrmaOperator.setBookMark();
*/
        }else{
            OrmaOperator.setShopList();

        }

        if(clear){
            ReadFileSns.fileClear();
            ReadFileSns.testCreate();
        }
        ReadFileSns.read();
        ReadFileSns.readTorkFile();

    }
}
