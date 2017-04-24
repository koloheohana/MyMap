package com.koloheohana.mymap.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ShareCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;

public class ShareDialog extends DialogFragment{
    ShopDate SD;
    public ShareDialog(ShopDate sd){
        SD = sd;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_yes_no);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text = (TextView)dialog.findViewById(R.id.title_dialog);
        // テキスト設定
        text.setText("");
        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.share_nomitomo_button);
        button.setText("誰かを誘う");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        Button button2 = (Button)dialog.findViewById(R.id.share_ather_button);
        button2.setText("他に共有する");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 共有ボタンの中身
                ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(MapsActivity.MAP_ME);
                builder.setChooserTitle("食事のお誘いです");
                builder.setSubject("飲み友アプリ");
/*
                builder.setText("通信テストです"+"\n"+SD.getShopName()+"\n"+SD.getADDRRES()+"\n"+SD.getTEL()+"\n"+"https://www.google.co.jp/maps?q="+SD.getEncodeAddrres());
*/
                builder.setText("通信テストです"+"\n"+SD.getShopName()+"\n"+SD.getADDRRES()+"\n"+SD.getTEL()+"\n"+"https://www.google.co.jp/maps?q="+SD.getLATLNG().latitude+","+SD.getLATLNG().longitude);
                builder.setType("text/plain");
                builder.startChooser();
                dismiss();
            }
        });
        // Close ボタンのリスナ
        Button button1 = (Button)dialog.findViewById(R.id.close_button);
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }


}
