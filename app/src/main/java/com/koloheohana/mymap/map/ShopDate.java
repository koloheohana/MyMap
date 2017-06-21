package com.koloheohana.mymap.map;

import android.media.Image;
import android.media.MediaRouter;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.data_base.OrmaShopData;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/09/11.
 */
public class ShopDate implements Serializable{

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
            for(String str:CATEGORY){
                if(str.equals(CATEGORY_NUMBER[i])){
                    return (count+1) * 10;
                }
            }
        }
        return 0;
    }
    int ID;
    String ADDRRES;
    String NAME;
    String TEL;
    String URL;
    ArrayList<String> CATEGORY = new ArrayList<String>();
    String FIRST_CATEGORY;

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
    ArrayList<Memo> memo_list = new ArrayList<Memo>();
    public ArrayList<Memo> getMemo(){
        return memo_list;
    }
    public String getMemoString(){
        StringBuffer sb = new StringBuffer();
        for(Memo memo: memo_list){
            sb.append(memo.MEMO+"\n");
            sb.append(memo.convertJapanese()+"\n");
        }
        return sb.toString();
    }
    public void addMemo(Memo memo){
        memo_list.add(memo);
    }
    Image[] IMAGE;
    public ShopDate(int _id,String _addrres,String postal,String _name,String category,String _tel,double _COORDINATE_X,double _COORDINATE_Y){
        ID = _id;
        ADDRRES = _addrres;
        POSTAL = postal;
        NAME = _name;
        TEL = _tel;
        CATEGORY.add(category);
        FIRST_CATEGORY = category;
        COORDINATE_X = _COORDINATE_X;
        COORDINATE_Y = _COORDINATE_Y;
        LATLNG = new LatLng(COORDINATE_X,COORDINATE_Y);
    }
    public ShopDate(long _id,String _addrres,String postal,String _name,List<String> category,String _tel,double _COORDINATE_X,double _COORDINATE_Y){
        ID = (int)_id;
        ADDRRES = _addrres;
        POSTAL = postal;
        NAME = _name;
        TEL = _tel;
        for(String cate:category) {
            CATEGORY.add(cate);
        }
        FIRST_CATEGORY =category.get(0);
        COORDINATE_X = _COORDINATE_X;
        COORDINATE_Y = _COORDINATE_Y;
        LATLNG = new LatLng(COORDINATE_X,COORDINATE_Y);
    }
    public ShopDate(long _id,String _addrres,String postal,String _name,List<String> category,String _tel,double _COORDINATE_X,double _COORDINATE_Y,boolean bookmark){
        ID = (int)_id;
        ADDRRES = _addrres;
        POSTAL = postal;
        NAME = _name;
        TEL = _tel;
        for(String cate:category) {
            CATEGORY.add(cate);
        }
        FIRST_CATEGORY =category.get(0);
        COORDINATE_X = _COORDINATE_X;
        COORDINATE_Y = _COORDINATE_Y;
        LATLNG = new LatLng(COORDINATE_X,COORDINATE_Y);
        BOOKMARK = bookmark;
    }
    public boolean BOOKMARK = false;
    public Double getX(){
        return COORDINATE_X;
    }
    public Double getY(){
        return COORDINATE_Y;
    }
    public ArrayList<String> getCATEGORY(){
        return CATEGORY;
    }
    public String getCategoryNames(){
        StringBuffer sb = new StringBuffer();
        for(String _name : CATEGORY){
            if(sb.length()!=0){
                sb.append("|");
            }
            sb.append(_name);
        }
        return sb.toString();
    }
    public List<String> getCategoryList(){
        List<String> list = new ArrayList<String>();
        for(String category:CATEGORY){
            list.add(category);
        }
        return list;
    }
    public String getEncodeAddrres(){
        String encode;
        try {
            encode = URLEncoder.encode(ADDRRES,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        return encode;
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
