package com.koloheohana.mymap.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.koloheohana.mymap.setting.AddrresChangeDialog;
import com.koloheohana.mymap.setting.CommentChangeDialog;
import com.koloheohana.mymap.setting.NameChangeDialog;
import com.koloheohana.mymap.setting.PictureChangeDialog;

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
        ListView lv= create(view);
        /**
         * コンテキストメニュー作成
         */
        registerForContextMenu(lv);
        return view;
    }

    public static final String MyName = "名前";
    public static final String MyPicture = "写真";
    public static final String MyAddrres = "所在地";
    public static final String MyComment = "コメント";
    public static final String MyRingTone = "通知音変更";
    public static final String Login ="ログインor会員登録";
    public static ListView create(View view) {
        final AppCompatActivity activity = SettingSelectActivity.ME;
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PickUpShopActivity.ME,R.layout.list);
        adapter.add(MyName);
        adapter.add(MyPicture);
        adapter.add(MyAddrres);
        adapter.add(MyComment);
        adapter.add(MyRingTone);
        adapter.add(Login);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MyName:
                        NameChangeDialog dialog = new NameChangeDialog(activity);
                        dialog.show(activity.getSupportFragmentManager(),"name");
                        break;
                    case MyPicture:
                        PictureChangeDialog dialog1 = new PictureChangeDialog(activity);
                        dialog1.show(activity.getFragmentManager(),"MyPicture");
                        break;
                    case MyAddrres:
                        AddrresChangeDialog dialog2 = new AddrresChangeDialog(activity);
                        dialog2.show(activity.getSupportFragmentManager(),"MyAddrres");
                        break;
                    case MyComment:
                        CommentChangeDialog dialog3 = new CommentChangeDialog(activity);
                        dialog3.show(activity.getFragmentManager(),"MyComment");
                        break;
                    case MyRingTone:
                        break;
                    case Login:
                        Intent intent = new Intent(activity, LoginActivity.class);
                        activity.startActivity(intent);
                        break;
                }
            }
        });

        myList.setAdapter(adapter);

        return myList;
    }
}
