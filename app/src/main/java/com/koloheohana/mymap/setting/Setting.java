package com.koloheohana.mymap.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Picture;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.encoder.QRCode;
import com.koloheohana.mymap.LoginActivity;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.util.AppData;
import com.koloheohana.mymap.util.Util;

/**
 * Created by User on 2017/08/03.
 */

public class Setting {

    public static final String MyName = "名前";
    public static final String MyPicture = "写真";
    public static final String MyAddrres = "所在地";
    public static final String MyComment = "コメント";
    public static final String MyRingTone = "通知音変更";
    public static final String Login ="ログインor会員登録";
    public static final String MaxMapMarker = "マーカー最大表示数";
    public static final String DeleteBookmarkAndMemo = "データ初期化";
    public static final String QRReader = "QR読み取り";
    public static final String QRDisplay = "QR表示";
    public static final String IDDisplay ="ID:";
    public static final String IDReader ="ID登録";
    public static void setFriendListView(final AppCompatActivity activity){
        TextView textView = (TextView)activity.findViewById(R.id.nowGroupText);
        textView.setText("フレンド関係");
        ListView listView = (ListView)activity.findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(activity, R.layout.list);
        list.add(IDDisplay);
        list.add(IDReader);
        list.add(QRDisplay);
        list.add(QRReader);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case IDDisplay:
                        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(activity);
                        builder.setChooserTitle("飲み友ID:"+MyUser.ME.getId());
                        builder.setSubject("飲み友ID:"+MyUser.ME.getId());
                        builder.setText(String.valueOf(MyUser.ME.getId()));
                        builder.setType("text/plain");
                        builder.startChooser();

                        break;
                    case IDReader:
                        break;
                    case QRDisplay:
                        new QrcodeDisplayDialog().show(activity.getFragmentManager(),"QR");
                        break;
                    case QRReader:
                        IntentIntegrator integrator = new IntentIntegrator(activity);
                        integrator.initiateScan();
                        break;
                }
            }
        });
    }
    public static void setMapListView(final AppCompatActivity activity){
        TextView textView = (TextView)activity.findViewById(R.id.nowGroupText);
        textView.setText("地図設定");
        ListView listView = (ListView)activity.findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(activity, R.layout.list);
        list.add(MaxMapMarker);
        list.add(DeleteBookmarkAndMemo);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MaxMapMarker:
                        new MapMaxMarkerChangeDialog(activity).show(activity.getFragmentManager(),"マーカー設定");
                        break;
                    case DeleteBookmarkAndMemo:
                        OrmaOperator.removeBookmarkAndMemo(activity);
                        break;
                }
            }
        });

    }
    public static void setUtilListView(final AppCompatActivity activity){
        TextView textView = (TextView)activity.findViewById(R.id.nowGroupText);
        textView.setText("その他設定");
        ListView listView = (ListView)activity.findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(activity, R.layout.list);
        list.add(MyRingTone);
        list.add(Login);
        listView.setAdapter(list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MyRingTone:
                        break;
                    case Login:
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent);
                        break;
                }
            }
        });
    }
    public static void setProfListView(final AppCompatActivity activity) {
        TextView textView = (TextView)activity.findViewById(R.id.nowGroupText);
        textView.setText("プロフィール設定");
        ListView listView = (ListView)activity.findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(activity, R.layout.list);
        list.add(MyName);
        list.add(MyPicture);
        list.add(MyAddrres);
        list.add(MyComment);
        listView.setAdapter(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MyName:
                        NameChangeDialog dialog = new NameChangeDialog(activity);
                        dialog.show(activity.getSupportFragmentManager(),"name");
                        break;
                    case MyPicture:
                        PictureChangeDialog dialog1 = new PictureChangeDialog(activity);
                        dialog1.show(activity.getFragmentManager(),"MyPicture");
                        break;
                    case MyAddrres:
                        AddrresChangeDialog dialog2 = new AddrresChangeDialog(activity);
                        dialog2.show(activity.getSupportFragmentManager(),"MyAddrres");
                        break;
                    case MyComment:
                        CommentChangeDialog dialog3 = new CommentChangeDialog(activity);
                        dialog3.show(activity.getFragmentManager(),"MyComment");
                        break;
                }
            }
        });
    }

}
