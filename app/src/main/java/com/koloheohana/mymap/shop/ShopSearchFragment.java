package com.koloheohana.mymap.shop;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SearchActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.dialog.GroupDialog;
import com.koloheohana.mymap.dialog.ProfDialog;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.user_date.UserAdapter;
import com.koloheohana.mymap.user_date.UserList;

/**
 * Created by User on 2017/12/29.
 */

public class ShopSearchFragment extends Fragment {
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
        createProf(view);
        setPopupWindow(lv,false);
        return view;
    }

    public static ListView create(View view) {
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        UserAdapter adapter = new UserAdapter(SearchActivity.ME, 0, UserList.ALL_USER_LIST);
        myList.setAdapter(adapter);
        return myList;
    }
    private void setPopupWindow(final ListView _lv,boolean player) {
        if(player) {
            _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    ProfDialog gd = new ProfDialog();
                    TextView tv = (TextView) view.findViewById(R.id.name);
                    gd.setUser(tv.getText().toString());
                }
            });
        }else{
            _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    GroupDialog gd = new GroupDialog();
                    TextView tv = (TextView) view.findViewById(R.id.name);
                    gd.setUser(tv.getText().toString());
                }
            });
        }
    }
    private ListView createProf(View view){
        ListView listView = (ListView)view.findViewById(R.id.profList);
        MyUser myUser = new MyUser(OrmaOperator.getMyData(MainActivity.ME));

        UserAdapter adapter = new UserAdapter(SearchActivity.ME,0, MyUser.ME.getListUser());
        listView.setAdapter(adapter);
        setPopupWindow(listView,true);
        return listView;
    }
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);

        menu.setHeaderTitle("テスト");
        menu.add("削除");
    }
}
