package com.koloheohana.mymap;

import android.app.Notification;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.koloheohana.mymap.util.Scene;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 2017/11/28.
 */

public class TestActivity extends AppCompatActivity {
    private SearchView mSearchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Scene.set(this);
        setContentView(R.layout.activity_test);

        System.out.println(zenkakuHiraganaToZenkakuKatakana("テストてすと"));
        System.out.println(katakanaToHiragana("テストてすと"));
        System.out.println(Character.UnicodeBlock.of('あ') == Character.UnicodeBlock.HIRAGANA);
        System.out.println(Character.UnicodeBlock.of('あ') == Character.UnicodeBlock.KATAKANA);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.search_item);

        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.search).getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        //
        Menu menu = toolbar.getMenu();
        MenuItem item = menu.add("検索");
        item.setIcon(R.drawable.common_google_signin_btn_icon_dark);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setActionView(mSearchView);
    }
    public static String zenkakuHiraganaToZenkakuKatakana(String s) {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ぁ' && c <= 'ん') {
                sb.setCharAt(i, (char)(c - 'ぁ' + 'ァ'));
            }
        }
        return sb.toString();
    }
    public static String katakanaToHiragana(String str){
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char code = str.charAt(i);
            if ((code >= 0x30a1) && (code <= 0x30f3)) {
                buf.append((char) (code - 0x60));
            } else {
                buf.append(code);
            }
        }
        return buf.toString();
    }
}