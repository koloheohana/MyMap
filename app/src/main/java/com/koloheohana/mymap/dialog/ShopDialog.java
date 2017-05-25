package com.koloheohana.mymap.dialog;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.ShareCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.MyBookmark;
import com.koloheohana.mymap.user_date.ShopMemo;
import com.koloheohana.mymap.util.GetScreenShot;

/**
 * Created by User on 2016/08/24.
 */
public class ShopDialog extends DialogFragment{
    private final ShopDate SD;
    private final boolean TEST_SWITCH = false;
    private int ENABLE_FALSE_COLOR = 0xaa808080;
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
        category.setText(SD.getCategoryNames());
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

        String bookmark ="お気に入り登録";
        if(MyBookmark.getList().contains(SD)){
            bookmark = "お気に入りを解除する";
        }
        button.setText(bookmark);
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyBookmark.getList().contains(SD)){
                    MyBookmark.release(SD);
                    Toast toast = Toast.makeText(MapsActivity.MAP_ME,"お気に入りを解除しました",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                    MyBookmark.set(SD);
                    Toast toast = Toast.makeText(MapsActivity.MAP_ME,"お気に入り登録しました",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        Button button2 = (Button)dialog.findViewById(R.id.tel_button);
        button2.setEnabled(TEST_SWITCH);
        button2.setBackgroundColor(0xaa808080);
        dialog.findViewById(R.id.tel_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tel = SD.getTEL();
                if(tel.isEmpty()){
                    return;
                }
                String[] TEL = tel.split("-");
                Intent intentcall = new Intent();
                intentcall.setAction(Intent.ACTION_CALL);
                StringBuffer sb = new StringBuffer();
                for(String str:TEL){
                    sb.append(str);
                }
                intentcall.setData(Uri.parse("tel:" + sb.toString()));
                startActivity(intentcall);
            }
        });
        Button button3 = (Button)dialog.findViewById(R.id.memo_button);
        if(!SD.getMemo().isEmpty()){
            button3.setText("メモを読む");
        }
        dialog.findViewById(R.id.memo_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                MapMemoDialog mmd = new MapMemoDialog(SD);
                mmd.show(getFragmentManager(),"");
            }
        });
        Button button4 = (Button)dialog.findViewById(R.id.share_button);
        dialog.findViewById(R.id.share_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareDialog shareDialog = new ShareDialog(SD);
                shareDialog.show(MapsActivity.MAP_ME.getSupportFragmentManager(),"共有");


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
