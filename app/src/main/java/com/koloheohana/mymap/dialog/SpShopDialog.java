package com.koloheohana.mymap.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Line;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.qrCode.QrCord;

/**
 * Created by User on 2016/08/24.
 */
public class SpShopDialog extends DialogFragment{
    public ShopDate SD;
    public SpShopDialog(ShopDate sd){
        SD = sd;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.pick_shop_dialog);
        setShopData(dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // アイコンを設定する
        ImageView imageView = (ImageView)dialog.findViewById(R.id.icon);
        imageView.setImageBitmap(QrCord.getQRCode("test"));
        TextView text = (TextView)dialog.findViewById(R.id.title_dialog);
        text.setText(SD.getShopName());
/*        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.positive_button);
        button.setText("testButton");
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });*/
        // Close ボタンのリスナ
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }
    private void setShopData(Dialog dialog){
        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.construct);
        TextView textView = new TextView(dialog.getContext());
        textView.setText("testText");
        layout.addView(textView);
    }
}
