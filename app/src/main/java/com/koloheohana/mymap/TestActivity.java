package com.koloheohana.mymap;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.SearchView;
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
    protected void onCreate(Bundle save) {
        super.onCreate(save);
        Scene.set(this);
        setContentView(R.layout.activity_test);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        System.out.println("test");
        // Set Menu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_item, menu);

        MenuItem menuItem = menu.findItem(R.id.toolbar_menu_search);

        mSearchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        // whether display Magnifying Glass Icon at first
        mSearchView.setIconifiedByDefault(true);

        // whether display Submit Button
        mSearchView.setSubmitButtonEnabled(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
