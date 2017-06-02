package com.koloheohana.mymap.menutab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserAdapter;
import com.koloheohana.mymap.user_date.UserList;

/**
 * Created by User on 2016/08/03.
 */
public class Tork extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.activity_group, container, false);
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

    public static ListView create(View view) {
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        UserAdapter adapter = new UserAdapter(MainActivity.ME, 0, UserList.ALL_USER_LIST);
        myList.setAdapter(adapter);


        return myList;
    }

}
