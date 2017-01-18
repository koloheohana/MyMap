package com.koloheohana.mymap.map;

import android.media.Image;
import android.media.MediaRouter;

/**
 * Created by User on 2016/09/11.
 */
public class ShopDate {
    public static int SHOP_ID;
    /**
     * 仮
     */
    int COLORS[] = {10,30,50,70,90,110,130,150,170};
    String CATEGORY_NUMBER[] = {"飲み屋","居酒屋","レストラン","和食","肉料理","喫茶店・カフェ","そば・うどん","スイーツ","魚料理","ラーメン","中華・中国料理","すし","イタリアン・フレンチ","たこ焼き・お好み焼き","ファーストフード","各国料理",
    "郷土料理店","カレー"};
    public int testMarker(){
        int count= 0;
        for(int i = 0;i <= CATEGORY_NUMBER.length;i++) {
            count++;
            if(CATEGORY.equals(CATEGORY_NUMBER[i])){
                System.out.println(CATEGORY+"と"+CATEGORY_NUMBER[i]);
                return (count+1) * 10;
            }
        }
        return 0;
    }
    int ID;
    String ADDRRES;
    String NAME;
    String TEL;
    String URL;
    String CATEGORY;
    String POSTAL;
    Double COORDINATE_X;
    Double COORDINATE_Y;
    Image[] IMAGE;

    public ShopDate(int _id,String _addrres,String postal,String _name,String category,String _tel,double _COORDINATE_X,double _COORDINATE_Y){
        ID = _id;
        SHOP_ID++;
        ADDRRES = _addrres;
        POSTAL = postal;
        NAME = _name;
        TEL = _tel;
        CATEGORY = category;
        COORDINATE_X = _COORDINATE_X;
        COORDINATE_Y = _COORDINATE_Y;
/*
        check();
*/


    }
    public void check(){
        System.out.println(0+NAME+""+TEL+""+CATEGORY+""+COORDINATE_Y+"と"+COORDINATE_X);
    }
    public ShopDate(int _id,String _addrres,String _name,String _url){
        ID = _id;
        ADDRRES = _addrres;
        NAME = _name;
        URL = _url;
    }
    public Double getX(){
        return COORDINATE_X;
    }
    public Double getY(){
        return COORDINATE_Y;
    }
    public String getCATEGORY(){
        return CATEGORY;
    }
    public String getShopName(){
        return NAME;
    }
    public String getADDRRES(){
        return ADDRRES;
    }
}
