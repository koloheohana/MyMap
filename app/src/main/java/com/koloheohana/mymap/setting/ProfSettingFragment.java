package com.koloheohana.mymap.setting;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingActivity;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserAdapter;
import com.koloheohana.mymap.user_date.UserList;

import java.util.ArrayList;

/**
 * Created by User on 2017/10/04.
 */

public class ProfSettingFragment extends Fragment{

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
        openTork(lv);
        return view;
    }
    private void openTork(final ListView _lv){
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int pos , long id){
                User user = (User)_lv.getItemAtPosition(pos);
                MainTork.ID = user.getId();
                Intent intent = new Intent(MainActivity.ME, MainTork.class);
                startActivity(intent);
            }
        });
    }
    public static final String MyName = "名前";
    public static final String MyPicture = "写真";
    public static final String MyAddrres = "所在地";
    public static final String MyComment = "コメント";
    public static ListView create(View view) {

        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingSelectActivity.ME,R.layout.list);
        adapter.add(MyName);
        adapter.add(MyPicture);
        adapter.add(MyAddrres);
        adapter.add(MyComment);
        myList.setAdapter(adapter);
        return myList;
    }
}
