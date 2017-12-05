package com.koloheohana.mymap.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.vision.text.Line;
import com.google.android.gms.vision.text.Text;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.adapter.PagePagerAdapter;
import com.koloheohana.mymap.adapter.PicturePagerAdapter;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.qrCode.QrCord;

/**
 * Created by User on 2016/08/24.
 */
public class SpShopDialog extends DialogFragment {
    public ShopDate SD;
    public Context THIS;
    public SpShopDialog(ShopDate sd,Context context) {
        SD = sd;
        THIS = context;
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
/*        ImageView imageView = (ImageView) dialog.findViewById(R.id.shop_image_icon);
        imageView.setImageBitmap(QrCord.getQRCode("test"));*/
        TextView text = (TextView) dialog.findViewById(R.id.title_dialog);
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

    private void setShopData(Dialog dialog) {
        ViewPager pager2 = (ViewPager)dialog.findViewById(R.id.view_pager2);
        PagePagerAdapter adapter2 = new PagePagerAdapter(THIS);
        LinearLayout layout = new LinearLayout(THIS);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(15,10,0,0);
        layout.setBackgroundResource(R.drawable.bg_dialog);
        TextView textView = new TextView(THIS);
        textView.setText("test1");
        layout.addView(textView);
        LinearLayout _layout2 = new LinearLayout(THIS);
        _layout2.setOrientation(LinearLayout.VERTICAL);
        _layout2.setBackgroundResource(R.drawable.bg_dialog);

        TextView textView1 = new TextView(THIS);
        textView1.setText("test2");
        _layout2.addView(textView1);
        adapter2.add(layout);
        adapter2.add(_layout2);
        pager2.setPageMargin(getResources().getDisplayMetrics().widthPixels / 10);

        pager2.setAdapter(adapter2);



        PicturePagerAdapter adapter = new PicturePagerAdapter(THIS);
        adapter.add(R.mipmap.ic_launcher);
        adapter.add(R.mipmap.ic_launcher);
        adapter.add(R.mipmap.ic_launcher);
        System.out.println("ADAPTER0:"+R.mipmap.ic_launcher);
        ViewPager pager = (ViewPager)dialog.findViewById(R.id.view_pager);
        pager.setAdapter(adapter);
        pager.setPageMargin(getResources().getDisplayMetrics().widthPixels / -10);
        pager.setOffscreenPageLimit(3);
        if (!SD.BUDGET.isEmpty()) {
            TextView _text = new TextView(dialog.getContext());
            _text.setText("予算：" + SD.BUDGET);
            layout.addView(_text);
        }
        if (SD.SEAT != 0) {
            TextView _text1 = new TextView(dialog.getContext());
            _text1.setText("席数:" + SD.SEAT + "席");
            layout.addView(_text1);
        }
        if (!SD.BUSINESS_HOUR.isEmpty()) {
            TextView _text2 = new TextView(dialog.getContext());
            _text2.setText("営業時間:" + SD.BUSINESS_HOUR);
            layout.addView(_text2);
        }
        if (SD.SMOOKING_SPACE.matches("可")) {
            TextView _smooking = new TextView(dialog.getContext());
            _smooking.setText("喫煙:" + SD.SMOOKING_SPACE);
            layout.addView(_smooking);
        }
        if (SD.CHARGE.matches("可")) {
            TextView _charge = new TextView(dialog.getContext());
            _charge.setText("充電:" + SD.CHARGE);
            layout.addView(_charge);
        }

        if (!SD.HOMEPAGE_ADDRRES.isEmpty()) {
            LinearLayout ll = new LinearLayout(dialog.getContext());
            ll.setHorizontalGravity(LinearLayout.HORIZONTAL);
            TextView _text3 = new TextView(dialog.getContext());
            _text3.setText("ホームページ:");
            TextView _text4 = new TextView(dialog.getContext());
            _text4.setText("HPを見る");
            _text4.setTextColor(Color.parseColor("#1111cc"));
            _text4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(SD.HOMEPAGE_ADDRRES);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });



            layout.addView(ll);

            layout.addView(_text3);
            layout.addView(_text4);
        }

        TextView _text1 = new TextView(dialog.getContext());
        _text1.setText("住所:"+SD.getPOSTAL() +"\n"+ SD.getADDRRES());
        layout.addView(_text1);
        TextView _text2 = new TextView(dialog.getContext());
        _text2.setText("カテゴリー:" + SD.getCategoryNames());
        layout.addView(_text2);
        TextView _text3 = new TextView(dialog.getContext());
        _text3.setText("TEL:" + SD.getTEL());
        layout.addView(_text3);
    }
}
