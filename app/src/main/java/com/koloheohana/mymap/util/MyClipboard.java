package com.koloheohana.mymap.util;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.EditText;

/**
 * Created by User on 2017/06/02.
 */
public class MyClipboard {

    public static void set(Context context, String str){
        ClipData.Item item = new ClipData.Item(str);
        String[] mimeType = new String[1];
        mimeType[0] = ClipDescription.MIMETYPE_TEXT_URILIST;
        ClipData cd = new ClipData(new ClipDescription("text_data", mimeType), item);
        ClipboardManager cm = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(cd);
    }
    public static String get(Context context){
        //システムのクリップボードを取得
        ClipboardManager cm = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);

        //クリップボードからClipDataを取得
        ClipData cd = cm.getPrimaryClip();

        //クリップデータからItemを取得
        if(cd != null){
            ClipData.Item item = cd.getItemAt(0);
            return item.getText().toString();
        }
        return null;
    }
}
