package com.koloheohana.mymap.setting;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.me.MyUser;

/**
 * Created by User on 2017/08/08.
 */

public class AddrresChangeDialog extends DialogFragment {
    public static Context CONTEXT;
    public AddrresChangeDialog(Context context){
        CONTEXT = context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.setting_addrres_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView textView = (TextView)dialog.findViewById(R.id.now_addrres);
        textView.setText(MyUser.ME.getName());
        //エディットテキスト
        final EditText edit = (EditText)dialog.findViewById(R.id.addrres_edit);
        edit.setWidth(400);
        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.positive_button);
        button.setText("変更");
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String change = edit.getText().toString();
                OrmaOperator.setMyData("","",change,"",CONTEXT);
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

