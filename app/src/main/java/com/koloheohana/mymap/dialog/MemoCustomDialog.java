package com.koloheohana.mymap.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.adapter.MemoAdapter;
import com.koloheohana.mymap.adapter.MyBookMarkAdapter;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.MyBookmark;
import com.koloheohana.mymap.user_date.ShopMemo;

/**
 * Created by User on 2017/04/21.
 */
public class MemoCustomDialog extends AlertDialog{
    public MemoCustomDialog(final Context context){
        super(context);
        MemoAdapter adapter = new MemoAdapter(context.getApplicationContext(), 0, ShopMemo.MEMO_SHOP_LIST);
        final ListView listView = new ListView(context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShopDate sd = (ShopDate)listView.getItemAtPosition(position);
                new AlertDialog.Builder(context)
                        .setTitle("メモ")
                        .setMessage(sd.getMemoString(context))
/*                        .setPositiveButton("", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed
                            }
                        })*/
                        .setNegativeButton("閉じる", null)
                        .show();
                dismiss();
            }

        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final ShopDate sd = (ShopDate) listView.getItemAtPosition(position);
                new AlertDialog.Builder(context)
                        .setTitle("メモを削除しますか？")
                        .setMessage(sd.getShopName())
                        .setPositiveButton("削除", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed
                            }
                        })
                        .setNegativeButton("キャンセル", null)
                        .show();
                return true;
            }
        });
        setTitle("メモ一覧");
        setView(listView);
    }}
