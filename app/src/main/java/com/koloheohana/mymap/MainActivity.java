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
                System.out.println("セレクト");
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("チェンジ");
            }
        });
        // 上部にタブをセットする
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);
    }

    public void file_save(){
        try {
            OutputStream outStream = openFileOutput("test.txt", MODE_APPEND);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outStream,"UTF-8"));
            writer.append("testeeees");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        file_open();
/*        try {
            out = openFileOutput("text.txt",MODE_PRIVATE);
            out.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
    public void file_open(){
        try {
            FileInputStream in = openFileInput("test.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                System.out.println(tmp);
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
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
        file_save();
//        TextView tv = (TextView)MainActivity.ME.findViewById(R.id.flags);
//        if(tv == null) {
//            System.out.println("nnnnnnnnnnnnnuuuuuuuuuuuulllllllaaaaaaaaaaaaaaaaal");
//            System.out.println(tv);
//        }else{
//            System.out.println("cheeeeeeeeeeeeeeeeeeeeeeeck");
//            System.out.println(tv);
//        }
    }

    public void println(String str){
        System.out.println(str);
    }
}