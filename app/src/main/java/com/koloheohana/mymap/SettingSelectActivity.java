package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.journeyapps.barcodescanner.Util;

import java.util.ArrayList;

/**
 * Created by User on 2017/07/27.
 */

public class SettingSelectActivity extends AppCompatActivity{
    public static SettingSelectActivity ME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ME = this;
        setContentView(R.layout.activity_setting);
        setListView();

    }
    public static final  String MyProfSetting = "MyName";
    public static final String MapSetting = "MyPicture";
    public static final String FriendSetting = "MyAddrres";
    public static final String UtilSetting = "MyComment";

    private void setListView(){
        ListView listView = (ListView)findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(this,R.layout.list);
        list.add(MyProfSetting);
        list.add(MapSetting);
        list.add(FriendSetting);
        list.add(UtilSetting);

        listView.setAdapter(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                Intent intent;
                switch (item_name){
                    case MyProfSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select",MyProfSetting);
                        startActivity(intent);
                        break;
                    case MapSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select",MapSetting);
                        startActivity(intent);
                        break;
                    case FriendSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select",FriendSetting);
                        startActivity(intent);                        break;
                    case UtilSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select",UtilSetting);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
