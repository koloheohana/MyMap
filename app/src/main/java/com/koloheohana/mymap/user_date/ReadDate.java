package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.sns.ReadFileSns;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    static boolean tester = true;
    static boolean clear = true;
    public static void read(){
        if(clear){
            ReadFileSns.fileClear();
            ReadFileSns.testCreate();
        }
        if(tester) {
            OrmaOperator.remove(MainActivity.ME,OrmaOperator.TORK_NUMBER);
            OrmaOperator.remove(MainActivity.ME,OrmaOperator.SHOP_NUMBER);
            OrmaOperator.remove(MainActivity.ME,2);
            OrmaOperator.remove(MainActivity.ME,3);
            //クリエイト
            OrmaOperator.remove(MainActivity.ME,4);
            OrmaOperator.createMyData(MainActivity.ME);
            final CsvReader read = new CsvReader();
            read.execute();
/*
            OrmaOperator.remove(MainActivity.ME,1);
*/
            OrmaOperator.createShopData(MainActivity.ME);
            ReadFileSns.read();
            ReadFileSns.readTorkFile();
            OrmaOperator.createUserData(MainActivity.ME);
/*
            OrmaOperator.setBookMark();
*/
        }
/*
        else{
            OrmaOperator.setShopList();

        }
*/


        //データベースからの読み込み、セット
        OrmaOperator.read(MainActivity.ME);
        //サーバーデータを作成
        ServerOperator.setServer(MainActivity.ME);


    }
}
