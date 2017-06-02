package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.sns.ReadFileSns;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    static boolean tester = true;
    static boolean clear = false      ;
    public static void read(){
        if(tester) {
            final CsvReader read = new CsvReader();
            read.execute();
            MyBookmark.read();
            ShopMemo.read();
        }
        ReadFileSns.read();
        if(clear){
            ReadFileSns.fileClear();
        }
        ReadFileSns.readTorkFile();
    }
}
