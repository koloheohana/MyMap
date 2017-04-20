package com.koloheohana.mymap.map;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.user_date.ShopMemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by User on 2016/09/10.
 */
public class CsvReader extends AsyncTask<Void, Void, Void> {
    public static StringBuffer sb = new StringBuffer();
    /**
     * ショップデータ
     */
    public static int[] shop_date = {R.raw.hokkaido_hokuriku,R.raw.kantou,R.raw.toukai,R.raw.sikoku_tyugoku,R.raw.kinki,R.raw.kyusyu_okinawa};
    /**
     * ファイル読み込み、リスト生成
     */
    public static void parse(Context context) {
        for(int i = 0; i < shop_date.length;i++){
            file_read(context,shop_date[i]);
        }
    }
    private static String category;
    public static void file_read(Context context,int file){
        Resources res = context.getResources();
        InputStream is = res.openRawResource(file);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer buf = new StringBuffer();
        String str;

        int i = 0;
        try {
            while ((str = reader.readLine()) != null) {
                i++;

                if(str == null||i <= 2){
                    continue;
                }
                sb.append(str);
                sb.append("\n");
                if (str.startsWith("一致")||str.startsWith("col")||str == null) {
                    continue;
                }
                if(str == null){
                    continue;
                }

                /**
                 *
                 * split
                 */
                String[] shop_date = str.split(",");
                int count = shop_date.length;
                if(count  <= 26){
                    continue;
                }
                if(!shop_date[1].isEmpty()){
                    category = shop_date[1];
                }
                count -=27;
                int zahyou_x = 23+count;
                int zahyou_y = 24+count;

                String[] shop_x = shop_date[zahyou_x].split("\"");
                String[] shop_y = shop_date[zahyou_y].split("\"");

                TestXY _coordinate = new TestXY(shop_x[1],shop_y[1]);
                ShopDate sd = new ShopDate(0,shop_date[6],shop_date[5],shop_date[4], category,shop_date[10],_coordinate.X,_coordinate.Y);
                ShopList.setShopList(sd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ShopMemo.read();
    }
    public static String split_double(String s){
        String[] str = s.split("\"");
        return str[0];
    }
    public static TestXY getXY(String str) {
        if (str == null) {
            return null;
        }
        String[] xy = new String[2];
        String[] sp;
        sp = str.split(",");
        if (sp.length <= 3) {
            return null;
        }

        return new TestXY(sp[2], sp[3]);
    }

    @Override
    protected Void doInBackground(Void... params) {

/*
        System.out.println(sb.length());
*/

        return null;
    }
    @Override
    protected  void onPreExecute(){
        parse(MainActivity.ME);
        super.onPreExecute();
    }

    /**
     * 座標
     */
    public static class TestXY {
        public Double X;
        public Double Y;
        public TestXY(String _X, String _Y) {
            Y = Double.parseDouble(_X);
            X = Double.parseDouble(_Y);
        }
    }
}

