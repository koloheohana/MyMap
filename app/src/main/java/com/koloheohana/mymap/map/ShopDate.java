package com.koloheohana.mymap.map;

import android.media.Image;
import android.media.MediaRouter;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by User on 2016/09/11.
 */
public class ShopDate {
    public enum SHOP_CATEGORY{
        飲み屋,居酒屋,レストラン,和食,肉料理,喫茶店カフェ("喫茶店・カフェ"),そばうどん("そば・うどん"),スイーツ,魚料理,
        ラーメン,中華中国料理("中華・中国料理"),すし,イタリアンフレンチ("イタリアン・フレンチ"),たこ焼きお好み焼き("たこ焼き・お好み焼き"),ファーストフード,
        各国料理, 郷土料理店,カレー;
        public String CATEGORY_NAME = this.name();
        SHOP_CATEGORY(String name){
            CATEGORY_NAME = name;
        }
        SHOP_CATEGORY(){

        }
        public static ArrayList<String> getCategoryNameList(){
            ArrayList<String> list = new ArrayList<String>();
            for(SHOP_CATEGORY sg: values()){
                list.add(sg.CATEGORY_NAME);
            }
            return list;
        }
    }
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

    public String getPOSTAL() {
        return POSTAL;
    }

    String POSTAL;

    public LatLng getLATLNG() {
        return LATLNG;
    }

    LatLng LATLNG;
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
        LATLNG = new LatLng(COORDINATE_X,COORDINATE_Y);
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
    public String getTEL() {
        return TEL;
    }
}
