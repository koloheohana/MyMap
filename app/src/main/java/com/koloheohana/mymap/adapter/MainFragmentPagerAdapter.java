package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.menutab.MainGroup;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.menutab.StartMenuTab;

/**
 * Created by User on 2016/08/03.
 */
public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"メイン", "グループ", "トーク"};
    private Context context;

    public MainFragmentPagerAdapter(FragmentManager fm, Context context) {
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
    public static MainGroup group = new MainGroup();
    public static StartMenuTab start_menu = new StartMenuTab();
    public static Tork tork = new Tork();
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return start_menu;
            case 1:
                return group;
            case 2:
                return  tork;
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
