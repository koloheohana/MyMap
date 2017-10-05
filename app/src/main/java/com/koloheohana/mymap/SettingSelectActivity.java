package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.adapter.SettingFragAdapter;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Window;

/**
 * Created by User on 2017/07/27.
 */

public class SettingSelectActivity extends AppCompatActivity{
    public static SettingSelectActivity ME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ME = this;
        Scene.set(this);

        setContentView(R.layout.activity_setting);
        setter();
/*
        setListView();
*/

    }
    public static final  String MyProfSetting = "プロフィール設定";
    public static final String MapSetting = "地図設定";
    public static final String FriendSetting = "友達設定";
    public static final String UtilSetting = "その他";

    public static final String INTENT_KEY = "select";
    public ViewPager mPager;

    public void setter(){
        Window.setWindowSize(this);
        final SettingFragAdapter mfp = new SettingFragAdapter(getSupportFragmentManager(),ME);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(mfp);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // 上部にタブをセットする
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
    }
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
                        intent.putExtra(INTENT_KEY,MyProfSetting);
                        startActivity(intent);
                        break;
                    case MapSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra(INTENT_KEY,MapSetting);
                        startActivity(intent);
                        break;
                    case FriendSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra(INTENT_KEY,FriendSetting);
                        startActivity(intent);
                        break;
                    case UtilSetting:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra(INTENT_KEY,UtilSetting);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

}
