package com.koloheohana.mymap.me;

import android.provider.Settings;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class MyUser extends User{

    public MyUser() {
        super(-1,ICON,NAME,LOCS);
    }
    //設定ファイルからの読み込み
    private static String NAME = "マイネーム";
    private static int ICON = R.mipmap.ic_launcher;
    private static String LOCS ="平針";

    public static MyUser ME =new MyUser();

    /**
     * MainGroupの仕組みが変
     * @return 自身のみ
     */
    public ArrayList<User> getListUser(){
        ArrayList<User> list = new ArrayList<User>();
        list.add(ME);
        return list;
    }

}
