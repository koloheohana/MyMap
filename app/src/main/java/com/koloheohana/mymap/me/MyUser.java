package com.koloheohana.mymap.me;

import android.provider.Settings;

import com.koloheohana.mymap.R;
import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class MyUser {
    private static String NAME = "マイネーム";
    private static int ICON = R.mipmap.ic_launcher;
    private static String LOCS ="平針";
    public ArrayList<User> PROF = new ArrayList<User>();
    public String getMyName(){
        return NAME;
    }
    public User MY_USER;
    public int getMyIcon(){
        return ICON;
    }
    public String getMyLocs(){
        return LOCS;
    }
    public static ArrayList<User> getUser(){
        ArrayList<User> list = new ArrayList<User>();
        list.add(new User().getUser(ICON,NAME,LOCS));
        return list;
    }
    public MyUser(){
        MY_USER.getUser(ICON,NAME,LOCS);
        PROF.add(MY_USER);
        System.out.println(NAME);
        System.out.println(LOCS);
    }
}
