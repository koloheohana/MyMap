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
    public static ArrayList<ShopDate> MEMO_SHOP_LIST = new ArrayList<ShopDate>();
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
                if(!MEMO_SHOP_LIST.contains(sd)) {
                    MEMO_SHOP_LIST.add(sd);
                }
                MEMO_LIST.add(memo);
                sd.addMemo(memo);
            }
        }
    }
    public static void memoDelete(Memo memo,ShopDate sd){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < memo.MEMOS.length;i++){
            if(i != 0){
                sb.append(",");
            }
            sb.append(memo.MEMOS[i]);
        }
        SaveDateController.removeLine(FILE_NAME,sb.toString());
        MEMO_SHOP_LIST.remove(sd);
        MEMO_LIST_STRING.remove(memo.MEMOS);
        MEMO_LIST.remove(memo);
        sd.getMemo().remove(memo);
    }
    public static void write(ShopDate SD,String memo){
        if(memo.isEmpty()){
            return;
        }
        Clocks clocks =new Clocks(MapsActivity.MAP_ME);
        String time = clocks.getStringSandTheString(",");
        String str = SD.getShopName()+","+SD.getADDRRES()+","+memo+","+time+"\n";
        Memo MEMO = new Memo(SD.getShopName(),SD.getADDRRES(),memo,clocks.YEAR,clocks.MONTH,clocks.DAY,clocks.HOUR,clocks.MINUTE,clocks.SECOND);
        SD.addMemo(MEMO);
        if(!MEMO_SHOP_LIST.contains(SD)) {
            MEMO_SHOP_LIST.add(SD);
        }
        SaveDateController.write(FILE_NAME,str);

    }
}
