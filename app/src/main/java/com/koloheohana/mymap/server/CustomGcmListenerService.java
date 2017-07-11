package com.koloheohana.mymap.server;

import android.app.NotificationManager;
import android.os.Bundle;
import android.util.Log;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.util.MyNotification;
import com.nifty.cloud.mb.core.NCMBGcmListenerService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * Created by User on 2017/07/11.
 */

public class CustomGcmListenerService extends NCMBGcmListenerService{

    @Override
    public void onMessageReceived(String from, Bundle data) {

        //ペイロードデータの取得
        if (data.containsKey("com.nifty.Data")) {
            try {
                JSONObject json = new JSONObject(data.getString("com.nifty.Data"));
                System.out.println("user_id:"+json.getString("user_id"));
                ServerOperator.getServerBitmap(json.getString(ServerOperator.IMAGE_URL));
                OrmaOperator.addServerTork(MainActivity.ME,data.getString("message"),json.getLong(ServerOperator.SENT_USER_ID),json.getString(ServerOperator.IMAGE_URL),json.getInt(ServerOperator.SHOP_ID));
            } catch (JSONException e) {
                //エラー処理
            }
        }

        //デフォルトの通知が必要なければコメントアウトする
        super.onMessageReceived(from, data);
    }

}
