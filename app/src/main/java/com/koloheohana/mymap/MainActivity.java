package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.koloheohana.mymap.adapter.MainFragmentPagerAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.map.ShopList;
import com.koloheohana.mymap.sns.ReadFileSns;
import com.koloheohana.mymap.user_date.ReadDate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    public ViewPager mPager;
    public static MainActivity ME = new MainActivity();
    public MainActivity me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ME = this;

        final MainFragmentPagerAdapter mfp = new MainFragmentPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(mfp);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                TextView tv = (TextView)findViewById(R.id.nowGroupText);
                tv.setText(mfp.getTitle(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // 上部にタブをセットする
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
        if(ShopList.ALLLIST.isEmpty()) {
/*            final CsvReader read = new CsvReader();
            read.execute();*/
            ReadDate.read();
        }
    }


    public void myMap(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }

    public void mySetting(View view) {

        Intent intent = new Intent(this,MainSetting.class);
        startActivity(intent);

    }

    public void myFriend(View view) {

    }

    public void println(String str){
        System.out.println(str);
    }
}