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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.map.ShopDate;

/**
 * Created by User on 2016/08/24.
 */
public class ShopDialog extends DialogFragment{
    private final ShopDate SD;
    public ShopDialog(ShopDate sd){
        SD = sd;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_shop_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text = (TextView)dialog.findViewById(R.id.title_dialog);
        text.setText(SD.getShopName());
        //テキストの設置
        LinearLayout ll = (LinearLayout)dialog.findViewById(R.id.shop_date_text);
        TextView category = new TextView(MapsActivity.MAP_ME);
        category.setText(SD.getCATEGORY());
        ll.addView(category);
        TextView postal = new TextView(MapsActivity.MAP_ME);
        postal.setText(SD.getPOSTAL());
        ll.addView(postal);
        TextView adrres = new TextView(MapsActivity.MAP_ME);
        adrres.setText(SD.getADDRRES());
        ll.addView(adrres);
        if(!SD.getTEL().isEmpty()){
            TextView tel = new TextView(MapsActivity.MAP_ME);
            tel.setText(SD.getTEL());
            ll.addView(tel);
        }
        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.positive_button);
        button.setEnabled(true);
        button.setBackgroundColor(0xaa808080);
        button.setText("HPへ飛ぶ");
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("飛ぶ");
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
    public void setLayout(View view){
        view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
    }
}
