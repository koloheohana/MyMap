package com.koloheohana.mymap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.awesomebar.ActionItem;
import com.github.florent37.awesomebar.AwesomeBar;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.koloheohana.mymap.adapter.MainFragmentPagerAdapter;
import com.koloheohana.mymap.data_base.OrmaMyData;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.CsvReader;
import com.koloheohana.mymap.map.ShopList;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.sns.ReadFileSns;
import com.koloheohana.mymap.user_date.ReadDate;
import com.koloheohana.mymap.util.AppData;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Window;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;



public class MainActivity extends AppCompatActivity /*implements Runnable*/{
    public ViewPager mPager;
    public static MainActivity ME = new MainActivity();
    public MainActivity me;
    public ProgressDialog progressDialog;
    public Handler handler_2 = new Handler();
    private Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scene.set(ME);
        ME = this;
/*        progressDialog = new ProgressDialog(ME);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("各種データ読み込み中...");
        progressDialog.show();
        if(!AppData.StartUp_data){
            //データ読み込みスレッド開始
            thread = new Thread(this);
            thread.start();
        }*/
        setter();

    }

    public void setter(){
        Window.setWindowSize(this);
        final MainFragmentPagerAdapter mfp = new MainFragmentPagerAdapter(getSupportFragmentManager(),ME);
        mPager = (ViewPager) findViewById(R.id.viewpager);
        mPager.setAdapter(mfp);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
/*                TextView tv = (TextView)findViewById(R.id.nowGroupText);
                tv.setText(mfp.getTitle(position));*/

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // 上部にタブをセットする
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mPager);


        //左テストメニューバー
        //drawerLayouteer
        AwesomeBar bar = (AwesomeBar)findViewById(R.id.bar);
        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
/*        bar.addAction(R.drawable.awsb_ic_edit_animated,"test");
        bar.setActionItemClickListener(new AwesomeBar.ActionItemClickListener() {
            @Override
            public void onActionItemClicked(int position, ActionItem actionItem) {
                Toast.makeText(getBaseContext(),actionItem.getText()+"clicks",Toast.LENGTH_LONG).show();
            }
        });*/
        bar.setOnMenuClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        final ArcNavigationView navigationView = (ArcNavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Intent intent;
                switch (item.getItemId()){
                    case R.id.menu_home:
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.new_post:
                        intent = new Intent(MainActivity.ME,PickUpShopActivity.class);
                        MainActivity.ME.startActivity(intent);
                        return true;
                    case R.id.menu_bookmark:
                        intent = new Intent(MainActivity.ME,PickUpShopActivity.class);
                        MainActivity.ME.startActivity(intent);
                        return true;
                    case R.id.menu_search:
                        intent = new Intent(MainActivity.ME,SearchActivity.class);
                        MainActivity.ME.startActivity(intent);
                        return  true;
                    case R.id.app_news:
                        return true;
                    case R.id.this_app:
                        return true;
                    case R.id.menu_setting:
                        intent = new Intent(MainActivity.ME,SettingSelectActivity.class);
                        MainActivity.ME.startActivity(intent);
                        return true;
                }
                return false;
            }
        });
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
/*    private Handler handler = new Handler(){
        public void handleMessage(Message message){
            MainActivity.ME.setter();
        }
    };
    @Override
    public void run() {
        ReadDate.read(ME);
        this.handler.sendEmptyMessage(0);
        progressDialog.dismiss();
    }*/
    public void openQRreader(){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        String code = intentResult.getContents();
        System.out.println("QRコード読み取り結果:"+code);
    }
}