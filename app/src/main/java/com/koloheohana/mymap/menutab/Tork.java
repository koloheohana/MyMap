package com.koloheohana.mymap.menutab;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MainSetting;
import com.koloheohana.mymap.MainTork;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.dialog.GroupDialog;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 2016/08/03.
 */
public class Tork extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public  int Ic = 0;
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
    private void openTork(ListView _lv){
        _lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int pos , long id){
                MainTork.TORK_NAME = NAMES[pos];
                Intent intent = new Intent(MainActivity.ME, MainTork.class);
                startActivity(intent);
            }
        });

    }


    public int getI(){
        return Ic;
    }

    /**
     * データベース項目
     */
    static String[] NAMES = {"社長", "田中", "瀬戸", "近藤", "近藤２", "山田", "池田", "松尾", "安井", "永井",
            "鵜飼", "馬", "竹"};
    /**
     * トーク内容
     */
    static String[] LOCS = {"今日の予定は・・・", "どうしよう", "インドに出張します", "ここが", "トークの内容を軽く表示します",
            "トークトーク", "トークトーク", "トークトーク", "トークトーク", "トークトーク", "トークトーク", "トークトーク", "トークトーク"};
    static int[] ICONS = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.drawable.ohana, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher,
    };
    //データベースから取得する項目　ここまで
    public static ListView create(View view) {
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayList<User> user_list = new ArrayList<User>();
        for (int i = 0; i < ICONS.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(MainActivity.ME.getResources(), ICONS[i]));
            user.setName(NAMES[i]);
            user.setLoc(LOCS[i]);
            user_list.add(user);
        }
        UserAdapter adapter = new UserAdapter(MainActivity.ME, 0, user_list);

        myList.setAdapter(adapter);


        return myList;
    }




    public void println(String str){
        System.out.println(str);
    }

}
