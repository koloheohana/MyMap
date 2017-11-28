package com.koloheohana.mymap;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.koloheohana.mymap.adapter.PicturePagerAdapter;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.util.Scene;

/**
 * Created by User on 2017/11/28.
 */

public class TestActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);
        Scene.set(this);
        setContentView(R.layout.activity_test);
        PicturePagerAdapter adapter  = new PicturePagerAdapter(this);
        adapter.add(Color.BLACK);
        adapter.add(Color.RED);
        adapter.add(Color.BLUE);

        ViewPager pager  = (ViewPager)findViewById(R.id.view_pager);
        pager.setAdapter(adapter);
        // 戻るボタン
        findViewById(R.id.preview_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        // 進むボタン
        findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }
}
