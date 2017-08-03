package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.setting.Setting;

/**
 * Created by User on 2017/08/03.
 */

public class SettingActivity extends AppCompatActivity{
    public static SettingActivity ME;
    public static String Scene;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        Scene = intent.getStringExtra(SettingSelectActivity.INTENT_KEY);
        ME = this;
        int layout = 0;
        switch(Scene){
            case SettingSelectActivity.MyProfSetting:
                layout = R.layout.activity_setting_prof;
                break;
            case SettingSelectActivity.FriendSetting:
                layout = R.layout.activity_setting;
                break;
            case SettingSelectActivity.MapSetting:
                layout = R.layout.activity_setting;
                break;
            case SettingSelectActivity.UtilSetting:
                layout = R.layout.activity_setting;
                break;
        }
        setContentView(layout);
        setLayout();
    }

    public void setLayout(){
        switch(Scene){
            case SettingSelectActivity.MyProfSetting:
                Setting.setProfListView(this);
                break;
            case SettingSelectActivity.FriendSetting:
                break;
            case SettingSelectActivity.MapSetting:
                break;
            case SettingSelectActivity.UtilSetting:
                break;
        }
    }




}
