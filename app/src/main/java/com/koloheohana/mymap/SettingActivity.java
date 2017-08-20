package com.koloheohana.mymap;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.setting.Setting;
import com.koloheohana.mymap.util.BitmapReader;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.util.Scene;

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
        com.koloheohana.mymap.util.Scene.set(this);

        Scene = intent.getStringExtra(SettingSelectActivity.INTENT_KEY);
        ME = this;
        int layout = 0;
        layout = R.layout.activity_setting;
/*        switch(Scene){
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
        }*/
        setContentView(layout);
        setLayout();
    }

    public void setLayout(){
        switch(Scene){
            case SettingSelectActivity.MyProfSetting:
                Setting.setProfListView(this);
                break;
            case SettingSelectActivity.FriendSetting:
                Setting.setFriendListView(this);
                break;
            case SettingSelectActivity.MapSetting:
                Setting.setMapListView(this);
                break;
            case SettingSelectActivity.UtilSetting:
                Setting.setUtilListView(this);
                break;
        }
    }
    private static final int REQUEST_GALLERY = 0;

    public void getPicture(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent resultData) {
        if(resultData == null){
            return;
        }
        Bitmap loadBitmap = BitmapReader.rotateAndResize(this,resultData.getData());
        Uri uri = SaveDateController.saveBitmapFile(this, loadBitmap,"tork"+String.valueOf(MyUser.ME.getId())+new Clocks(this).getStringAllTime());
        OrmaOperator.setMyData("",uri.toString(),"","",SettingActivity.ME);

    }


}
