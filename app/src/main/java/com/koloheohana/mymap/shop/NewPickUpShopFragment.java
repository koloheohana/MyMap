package com.koloheohana.mymap.shop;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.LoginActivity;
import com.koloheohana.mymap.PickUpShopActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.adapter.PicturePagerAdapter;
import com.koloheohana.mymap.dialog.SpShopDialog;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.setting.AddrresChangeDialog;
import com.koloheohana.mymap.setting.CommentChangeDialog;
import com.koloheohana.mymap.setting.FriendSettingFragment;
import com.koloheohana.mymap.setting.MapSettingFragment;
import com.koloheohana.mymap.setting.NameChangeDialog;
import com.koloheohana.mymap.setting.PictureChangeDialog;
import com.koloheohana.mymap.setting.ProfSettingFragment;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by User on 2017/11/14.
 */

public class NewPickUpShopFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_page_list, container, false);
        ListView lv = create(view);
        /**
         * コンテキストメニュー作成
         */
        registerForContextMenu(lv);
        return view;
    }

    public static ListView create(View view) {
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickUpShopActivity.ME, R.layout.list);
        for (ShopDate _sd : PickUpShop.new_list) {
            adapter.add(_sd.getShopName());
        }

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final SpShopDialog dialog =
                        new SpShopDialog(PickUpShop.new_list.get(position), PickUpShopActivity.ME);

                dialog.show(PickUpShopActivity.ME.getSupportFragmentManager(), "PICK_UP");
            }
        });

        myList.setAdapter(adapter);

        return myList;
    }
}
