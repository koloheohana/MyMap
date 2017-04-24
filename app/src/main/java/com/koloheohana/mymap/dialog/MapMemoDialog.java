package com.koloheohana.mymap.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.ShopMemo;

/**
 * Created by User on 2017/04/14.
 */
public class MapMemoDialog extends DialogFragment{
    private ShopDate SD;
    public MapMemoDialog(ShopDate sd){
        SD = sd;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_shop_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));        dialog.setContentView(R.layout.custom_memo_dialog);
        TextView tv = (TextView)dialog.findViewById(R.id.title_dialog);
        tv.setText(SD.getShopName());
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et = (EditText)dialog.findViewById(R.id.edit_memo);
                String st = et.getText().toString();
                ShopMemo.write(SD,st);
                dismiss();
            }
        });
        // Close ボタンのリスナ
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }
}
