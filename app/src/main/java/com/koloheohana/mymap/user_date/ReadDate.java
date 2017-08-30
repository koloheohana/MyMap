package com.koloheohana.mymap.user_date;

import android.app.ProgressDialog;
import android.content.Context;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.Sound.Sound;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.sns.ReadFileSns;
import com.koloheohana.mymap.util.AppData;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    static boolean tester = true;
    static boolean clear = true;
    static boolean map = false;

    public static void read(final Context context) {

        if (clear) {
            MainActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.ME.progressDialog.setMessage("初期化中");
                }
            });
            ReadFileSns.fileClear();
/*
            ReadFileSns.testCreate();
*/
        }
        if (tester) {
            MainActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.ME.progressDialog.setMessage("データベース初期化中");
                }
            });
            OrmaOperator.remove(context, OrmaOperator.TORK_NUMBER);
            OrmaOperator.remove(context, OrmaOperator.SHOP_NUMBER);
            OrmaOperator.remove(context, 2);
            OrmaOperator.remove(context, 3);
            OrmaOperator.remove(context, 4);
            //クリエイト
        }
        if (map) {
            MainActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.ME.progressDialog.setMessage("地図データ作成中");
                }
            });

            final CsvReader read = new CsvReader();
            read.execute();
        }
        if (tester) {
            MainActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    MainActivity.ME.progressDialog.setMessage("ユーザーデータベース作成中");
                }
            });
            OrmaOperator.createMyData(context);
            OrmaOperator.createShopData(context);
            ReadFileSns.read();
            ReadFileSns.readTorkFile();
            OrmaOperator.createUserData(context);
/*
            OrmaOperator.setBookMark();
*/
        }
/*
        else{
            OrmaOperator.setShopList();

        }
*/
        MainActivity.ME.handler_2.post(new Runnable() {
            @Override
            public void run() {
                MainActivity.ME.progressDialog.setMessage("アプリ準備中");
            }
        });

        //データベースからの読み込み、セット
        OrmaOperator.read(context);
        //サーバーデータを作成
        ServerOperator.setServer(context);

    }
}
