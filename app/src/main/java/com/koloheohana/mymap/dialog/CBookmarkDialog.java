package com.koloheohana.mymap.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.adapter.MyBookMarkAdapter;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.MyBookmark;

/**
 * Created by User on 2017/04/14.
 */
public class CBookmarkDialog extends AlertDialog{
    public CBookmarkDialog(final Context context){
        super(context);
        MyBookMarkAdapter adapter = new MyBookMarkAdapter(context.getApplicationContext(), 0, MyBookmark.getList());
        final ListView listView = new ListView(context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopDate sd = (ShopDate)listView.getItemAtPosition(position);
                MapsActivity.MAP_ME.setMarker(sd,true);
                dismiss();
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ShopDate sd = (ShopDate) listView.getItemAtPosition(position);
                new AlertDialog.Builder(context)
                        .setTitle("お気に入りを解除しますか？")
                        .setMessage(sd.getShopName())
                        .setPositiveButton("解除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed
                                MyBookmark.release(sd);
                                dismiss();
                            }
                        })
                        .setNegativeButton("キャンセル", null)
                        .show();
                return true;
            }
        });
        setTitle("お気に入り一覧");
        setView(listView);
    }
}
