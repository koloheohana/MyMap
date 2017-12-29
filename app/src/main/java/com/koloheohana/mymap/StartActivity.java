package com.koloheohana.mymap;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.koloheohana.mymap.data_base.OrmaDatabase;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.util.Scene;
import com.nifty.cloud.mb.core.NCMB;

import junit.framework.Test;

/**
 * Created by User on 2017/07/20.
 */

public class StartActivity extends AppCompatActivity {
    private boolean test_page =false;
    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);
        Scene.set(this);
        setContentView(R.layout.loading_activity);
        if(test_page){
            Intent intent = new Intent(this, TestActivity.class);
            this.startActivity(intent);
            return;
        }
        OrmaOperator.setFirstConfig(this);
        ServerOperator.setServer(this);
        if(ServerOperator.setPush(this)){
            if(!OrmaOperator.getConfig(this).isMemberRegist){
                mainStartActivity(this);
            }else{
                ServerOperator.rogin(this);
            }
        }else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        //サーバーデータを作成
    }
    public static void mainStartActivity(Context context){
        Intent intent = new Intent(context, LoadingActivity.class);
        context.startActivity(intent);
/*
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
*/
    }
}
