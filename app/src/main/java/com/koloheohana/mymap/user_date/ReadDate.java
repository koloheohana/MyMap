package com.koloheohana.mymap.user_date;

import android.content.Context;

import com.koloheohana.mymap.LoadingActivity;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.shop.PickUpShop;
import com.koloheohana.mymap.sns.ReadFileSns;

/**
 * Created by User on 2017/04/07.
 */
public class ReadDate {
    static boolean user = true;
    static boolean clear = true;
    static boolean map = true;

    public static void read(final Context context) {
        PickUpShop.fileLoad(context);
        if (clear) {
            LoadingActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    LoadingActivity.ME.progressDialog.setMessage("初期化中");
                }
            });
            ReadFileSns.fileClear();
            ReadFileSns.testCreate();
        }
        if (user) {
            LoadingActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    LoadingActivity.ME.progressDialog.setMessage("データベース初期化中");
                }
            });
            OrmaOperator.remove(context, OrmaOperator.TORK_NUMBER);
            OrmaOperator.remove(context, OrmaOperator.SHOP_NUMBER);
            OrmaOperator.remove(context, 2);
            OrmaOperator.remove(context, 3);
/*
            OrmaOperator.remove(context, 4);
*/
            //クリエイト
        }
        if (map) {
            LoadingActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    LoadingActivity.ME.progressDialog.setMessage("地図データ作成中");
                }
            });

            final CsvReader read = new CsvReader();
            read.execute();
        }
        if (user) {
            LoadingActivity.ME.handler_2.post(new Runnable() {
                @Override
                public void run() {
                    LoadingActivity.ME.progressDialog.setMessage("ユーザーデータベース作成中");
                }
            });
/*
            OrmaOperator.createMyData(context);
*/
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
        LoadingActivity.ME.handler_2.post(new Runnable() {
            @Override
            public void run() {
                LoadingActivity.ME.progressDialog.setMessage("アプリ準備中");
            }
        });

        //データベースからの読み込み、セット
        OrmaOperator.read(context);
        //サーバーデータを作成
        ServerOperator.setServer(context);
/*        OrmaMyData omd = OrmaOperator.getMyData(context);
        ServerOperator.imageUploadAndPush(context, omd.user_icon, MyUser.ME.getIcon(),1);*/
    }
}
