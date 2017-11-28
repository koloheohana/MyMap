package com.koloheohana.mymap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.koloheohana.mymap.adapter.MainFragmentPagerAdapter;
import com.koloheohana.mymap.user_date.ReadDate;
import com.koloheohana.mymap.util.AppData;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Window;

/**
 * Created by User on 2017/11/22.
 */

public class LoadingActivity extends AppCompatActivity implements Runnable{
    public static LoadingActivity ME;
    public ProgressDialog progressDialog;
    public Handler handler_2 = new Handler();
    private Thread thread;
    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);
        Scene.set(this);
        setContentView(R.layout.loading_activity);
        ME = this;
        progressDialog = new ProgressDialog(ME);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("各種データ読み込み中...");
        progressDialog.show();
        if(!AppData.StartUp_data){
            //データ読み込みスレッド開始
            thread = new Thread(this);
            thread.start();
        }
    }



    private Handler handler = new Handler(){
        public void handleMessage(Message message){
/*
            MainActivity.ME.setter();
*/
            Intent intent = new Intent(ME,MainActivity.class);
            ME.startActivity(intent);
        }
    };
    @Override
    public void run() {
        ReadDate.read(ME);
        progressDialog.dismiss();
        this.handler.sendEmptyMessage(0);
    }}
