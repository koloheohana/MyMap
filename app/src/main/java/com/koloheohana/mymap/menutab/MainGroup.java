package com.koloheohana.mymap.menutab;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.koloheohana.mymap.HueAdapter;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.dialog.GroupDialog;
import com.koloheohana.mymap.dialog.ProfDialog;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserAdapter;
import com.koloheohana.mymap.user_date.UserDate;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/08/03.
 */
public class MainGroup extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view;
        /**
         * アクティビティセット
         */
        view = inflater.inflate(R.layout.grop_menu, container, false);

        GridView lv = create(view);
        createProf(view);
        /**
         * コンテキストメニュー作成
         */

        registerForContextMenu(lv);
        /**
         * ポップアップメニュー作成
         */
        setPopupWindow(lv);
        return view;
    }
    private void setPopupWindow(final ListView _lv) {
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                ProfDialog gd = new ProfDialog();
                TextView tv = (TextView) view.findViewById(R.id.name);
                gd.setUser(tv.getText().toString());
                gd.show(getFragmentManager(), "名前");

            }
        });
    }
    private void setPopupWindow(final GridView _lv) {
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                GroupDialog gd = new GroupDialog();
                TextView tv = (TextView) view.findViewById(R.id.name);
                gd.setUser(tv.getText().toString());
                gd.show(getFragmentManager(), "名前");

            }
        });
    }

    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo info) {
        super.onCreateContextMenu(menu, view, info);
        AdapterContextMenuInfo adapterInfo = (AdapterContextMenuInfo) info;
        GridView listView = (GridView) view;

        menu.setHeaderTitle("テスト");
        menu.add("削除");
        menu.add("削除");
    }




    //データベースから取得する項目　ここまで
    public GridView create(View view) {
        GridView myList = (GridView) view.findViewById(R.id.gridview);
        UserAdapter adapter = new UserAdapter(MainActivity.ME, 0, UserDate.user_list);

        myList.setAdapter(adapter);


        return myList;
    }
    private ListView createProf(View view){
        ListView listView = (ListView)view.findViewById(R.id.profList);
        UserAdapter adapter = new UserAdapter(MainActivity.ME,0, MyUser.getUser());
        listView.setAdapter(adapter);
        setPopupWindow(listView);
        return listView;
    }


}
