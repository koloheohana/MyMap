package com.koloheohana.mymap.setting;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingActivity;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.me.MyUser;

/**
 * Created by User on 2017/08/08.
 */

public class PictureChangeDialog extends DialogFragment{
    public static Context CONTEXT;
    public PictureChangeDialog(Context context){
        CONTEXT = context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.setting_picture_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView image = (ImageView)dialog.findViewById(R.id.tork_image);
        image.setImageBitmap(MyUser.ME.getIcon());

        Button change_picture = (Button)dialog.findViewById(R.id.picture_select_button);
        change_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingSelectActivity.ME.getPicture();
                dismiss();
            }
        });

        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.positive_button);
        button.setText("変更");
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
