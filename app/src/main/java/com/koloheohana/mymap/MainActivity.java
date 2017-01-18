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
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.map.MapActivity;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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
        /*
        viewpager操作時アクション
         */
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
        /*
        ファイルオープンテスト
         */
        /*CsvReader.parse(this);*/
        final CsvReader read = new CsvReader();
        read.execute();
    }
    public void file_save(StringBuffer sb ,String file_name){
        String message = "";
        String fileName = "test.txt";
        String inputText = sb.toString();

        try {
            FileOutputStream outStream = openFileOutput("data/data/test.txt", MODE_PRIVATE);
            OutputStreamWriter writer = new OutputStreamWriter(outStream);
            writer.write(inputText);
            writer.flush();
            writer.close();

            message = "File saved.";
        } catch (FileNotFoundException e) {
            message = e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            message = e.getMessage();
            e.printStackTrace();
        }

        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
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
            String str = "";
            String tmp;
            while ((tmp = reader.readLine()) != null) {
                str = str + tmp + "\n";
            }
            System.out.println("file"+str);
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