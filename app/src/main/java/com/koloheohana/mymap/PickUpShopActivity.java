package com.koloheohana.mymap;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.koloheohana.mymap.adapter.PickUpShopFragAdapter;
import com.koloheohana.mymap.adapter.SettingFragAdapter;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Window;

/**
 * Created by User on 2017/11/14.
 */

public class PickUpShopActivity extends AppCompatActivity{
    public static PickUpShopActivity ME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ME = this;
        Scene.set(this);

        setContentView(R.layout.activity_shop);
        setter();
    }
    public ViewPager mPager;

    public void setter(){
        Window.setWindowSize(this);
        final PickUpShopFragAdapter mfp = new PickUpShopFragAdapter(getSupportFragmentManager(),ME);
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
}
