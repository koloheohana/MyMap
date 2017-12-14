package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.github.florent37.awesomebar.AwesomeBar;
import com.koloheohana.mymap.adapter.PickUpShopFragAdapter;
import com.koloheohana.mymap.adapter.SettingFragAdapter;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Window;
import com.rom4ek.arcnavigationview.ArcNavigationView;

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



        final DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        AwesomeBar bar = (AwesomeBar)findViewById(R.id.bar);
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
                        intent = new Intent(PickUpShopActivity.ME,MainActivity.class);
                        PickUpShopActivity.ME.startActivity(intent);
                        return true;
                    case R.id.new_post:
                        intent = new Intent(PickUpShopActivity.ME,PickUpShopActivity.class);
                        PickUpShopActivity.ME.startActivity(intent);
                        return true;
                    case R.id.menu_bookmark:
                        intent = new Intent(PickUpShopActivity.ME,PickUpShopActivity.class);
                        PickUpShopActivity.ME.startActivity(intent);
                        return true;
                    case R.id.menu_search
                            :return  true;
                    case R.id.app_news:
                        return true;
                    case R.id.this_app:
                        return true;
                    case R.id.menu_setting:
                        intent = new Intent(PickUpShopActivity.ME,SettingSelectActivity.class);
                        PickUpShopActivity.ME.startActivity(intent);
                        return true;
                }
                return false;
            }
        });
    }
}
