package com.koloheohana.mymap.user_date;

import android.content.Context;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaShopMemo;
import com.koloheohana.mymap.data_base.OrmaShopMemo_Selector;
import com.koloheohana.mymap.util.Clocks;
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
/*    public static ArrayList<Memo> MEMO_LIST = new ArrayList<Memo>();
    public static ArrayList<String[]> MEMO_LIST_STRING = new ArrayList<String[]>();*/
    public static ArrayList<ShopDate> MEMO_SHOP_LIST = new ArrayList<ShopDate>();
    public static void read(Context context){
        OrmaShopMemo_Selector osm = OrmaOperator.getShopMemoSelector(context);
        System.out.println("メモデータの読み込みをします");
        for(OrmaShopMemo sm:osm){
            System.out.println("店舗ID:"+sm.shop_id);
            MEMO_SHOP_LIST.add(new ShopDate(OrmaOperator.getOrmaShopData(context,sm.shop_id)));
        }

        /*        MEMO_LIST_STRING = SaveDateController.MemoRead();
        *//**
         * 要検討
         *//*
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
        }*/
    }
    public static void memoDelete(Context context,long id){
        OrmaOperator.deleteShopMemo(context,id);
/*        StringBuffer sb = new StringBuffer();
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
        sd.getMemo().remove(memo);*/
    }
    public static void write(Context context,ShopDate SD,String memo){
        OrmaOperator.writeShopMemo(context,SD.ID,memo,new Clocks(context).getStringSandTheString("/"));
        MEMO_SHOP_LIST.add(SD);

/*        if(memo.isEmpty()){
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
        SaveDateController.write(FILE_NAME,str);*/

    }
}
