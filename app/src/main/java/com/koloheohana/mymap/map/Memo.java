package com.koloheohana.mymap.map;

/**
 * Created by User on 2017/04/20.
 */
public class Memo {
    public String[] MEMOS;
    public String SHOP_NAME;
    public String SHOP_ADDRRES;
    public String MEMO;
    public ShopDate SD;
    public  int YEAR;
    public  int DAY;
    public  int MONTH;
    public  int HOUR;
    public  int MINUTE;
    public  int SECOND;


    public Memo(String shop_name,String shop_addrres,String memo,int year,int day,int month,int hour,int minute,int seconde){
        SHOP_NAME = shop_name;
        SHOP_ADDRRES = shop_addrres;
        MEMO = memo;
        YEAR = year;
        DAY = day;
        MONTH = month;
        HOUR = hour;
        MINUTE = minute;
        SECOND = seconde;
    }
    public Memo(String[] str){

        MEMOS = str;
        SHOP_NAME = str[0];
        SHOP_ADDRRES = str[1];
        MEMO = str[2];
        YEAR = Integer.valueOf(str[3]);
        DAY = Integer.valueOf(str[4]);
        MONTH = Integer.valueOf(str[5]);
        HOUR = Integer.valueOf(str[6]);
        MINUTE = Integer.valueOf(str[7]);
        SECOND = Integer.valueOf(str[8]);
    }
    public String convertJapanese(){
        StringBuffer sb = new StringBuffer();
        return sb.toString();
    }
}
