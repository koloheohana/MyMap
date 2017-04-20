package com.koloheohana.mymap.user_date;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.map.Memo;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;

import java.util.ArrayList;

/**
 * Created by User on 2017/04/14.
 */
public class ShopMemo {
    public static String FILE_NAME = SaveFile.SHOP_MEMO;
    public static ArrayList<Memo> MEMO_LIST = new ArrayList<Memo>();
    public static ArrayList<String[]> MEMO_LIST_STRING = new ArrayList<String[]>();
    public static void read(){
        MEMO_LIST_STRING = SaveDateController.MemoRead();
        /**
         * 要検討
         */
        for(String[] memos:MEMO_LIST_STRING){
            ShopDate sd = ShopList.getShopDate(memos[0],memos[1]);
            if(sd == null){
                System.out.println("存在しません");
            }else{
                Memo memo = new Memo(memos);
                MEMO_LIST.add(memo);
                sd.addMemo(memo);
            }
        }
    }
    public static void write(ShopDate SD,String memo){
        if(memo.isEmpty()){
            return;
        }
        String time = new Clocks(MapsActivity.MAP_ME).getStringSandTheString(",");
        System.out.println(time);
        String str = SD.getShopName()+","+SD.getADDRRES()+","+memo+","+time;
        System.out.println(str);
        SaveDateController.write(FILE_NAME,str);

    }
}
