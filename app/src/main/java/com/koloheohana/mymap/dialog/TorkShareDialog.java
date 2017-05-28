package com.koloheohana.mymap.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.adapter.TorkShareAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDataIntent;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.GetScreenShot;

import java.io.Serializable;

/**
 * Created by User on 2017/04/27.
 */
public class TorkShareDialog extends AlertDialog {
    ShopDate SD;
    public TorkShareDialog(final Context context, final ShopDate sd){
        super(context);
        SD = sd;
        final TorkShareAdapter adapter = new TorkShareAdapter(context.getApplicationContext(), 0, UserList.ALL_USER_LIST);
        final ListView listView = new ListView(context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user = adapter.getItem(position);
                MapsActivity.MAP_ME.getSnapShot(SD,user);
                dismiss();
            }

        });

        setTitle("共有先一覧");
        setView(listView);
    }
}
