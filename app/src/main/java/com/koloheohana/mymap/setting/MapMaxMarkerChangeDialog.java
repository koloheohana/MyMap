package com.koloheohana.mymap.setting;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.util.MyConfig;

import static android.text.InputType.TYPE_CLASS_NUMBER;
import static android.text.InputType.TYPE_NUMBER_VARIATION_NORMAL;

/**
 * Created by User on 2017/08/20.
 */

public class MapMaxMarkerChangeDialog extends DialogFragment {
    public static Context CONTEXT;
    public MapMaxMarkerChangeDialog(Context context){
        CONTEXT = context;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.setting_marker_dialog);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView textView = (TextView)dialog.findViewById(R.id.now_comment);
        textView.setText("マーカー表示数");
        final TextView textView1 = (TextView)dialog.findViewById(R.id.now_value);
        textView1.setText(String.valueOf(MyConfig.map_marker_max));
        //エディットテキスト
        final EditText edit = (EditText)dialog.findViewById(R.id.comment_edit);
        edit.setHint("10~500");
        edit.setInputType(InputType.TYPE_CLASS_NUMBER|TYPE_NUMBER_VARIATION_NORMAL);
        edit.setWidth(400);
        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.positive_button);
        button.setText("変更");
        dialog.findViewById(R.id.positive_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String change = edit.getText().toString();
                int value = Integer.valueOf(change);
                if(value >= 10 && value <= 500){
                    MyConfig.map_marker_max = value;
                    OrmaOperator.setConfig(CONTEXT,value,false,"","",false);

                    dismiss();
                }else{
                    edit.setText(null);
                    edit.setError("入力数値は不正な値です");
                }
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
