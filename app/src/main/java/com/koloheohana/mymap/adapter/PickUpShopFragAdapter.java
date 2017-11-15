package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.koloheohana.mymap.setting.FriendSettingFragment;
import com.koloheohana.mymap.setting.MapSettingFragment;
import com.koloheohana.mymap.setting.ProfSettingFragment;
import com.koloheohana.mymap.shop.NewPickUpShopFragment;
import com.koloheohana.mymap.shop.PickUpShop;
import com.koloheohana.mymap.shop.PickUpShopFragment;

/**
 * Created by User on 2017/11/14.
 */

public class PickUpShopFragAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"新着情報"+" (" +PickUpShop.new_shops_value+")", "店舗"+" ("+PickUpShop.getShopListSize()+")"};
    private Context context;

    public PickUpShopFragAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;


    }
    public String getTitle(int i){
        return tabTitles[i];
    }
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    public static NewPickUpShopFragment NEW = new NewPickUpShopFragment();
    public static PickUpShopFragment PICK = new PickUpShopFragment();
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return NEW;
            case 1:
                return PICK;

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
    public class TabAdapter{
        public android.app.Fragment fragment;
        public String tab_title;
        public TabAdapter(android.app.Fragment _fragment , String _title){
            fragment = _fragment;
            tab_title = _title;
        }
        public android.app.Fragment getFragment(){
            return fragment;
        }
        public String getTab_title(){
            return tab_title;
        }
    }
}
