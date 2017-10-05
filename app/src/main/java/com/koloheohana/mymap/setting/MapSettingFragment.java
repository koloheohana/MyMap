package com.koloheohana.mymap.setting;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.user_date.User;

/**
 * Created by User on 2017/10/04.
 */

public class MapSettingFragment extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_page_list, container, false);
        ListView lv= create(view);
        /**
         * コンテキストメニュー作成
         */
        registerForContextMenu(lv);
        return view;
    }

    public static final String MaxMapMarker = "マーカー最大表示数";
    public static final String DeleteBookmarkAndMemo = "データ初期化";
    public static ListView create(View view) {
        final AppCompatActivity activity = SettingSelectActivity.ME;
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingSelectActivity.ME,R.layout.list);
        adapter.add(MaxMapMarker);
        adapter.add(DeleteBookmarkAndMemo);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MaxMapMarker:
                        new MapMaxMarkerChangeDialog(activity).show(activity.getFragmentManager(),"マーカー設定");
                        break;
                    case DeleteBookmarkAndMemo:
                        OrmaOperator.removeBookmarkAndMemo(activity);
                        break;
                }
            }
        });

        myList.setAdapter(adapter);
        return myList;
    }
}
