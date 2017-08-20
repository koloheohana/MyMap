package com.koloheohana.mymap.me;

import android.provider.Settings;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.data_base.OrmaMyData;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/08/24.
 */
public class MyUser extends User{
    public static MyUser ME;
    public MyUser(OrmaMyData omd) {
        super(-1,omd.user_icon,omd.user_name,omd.user_addrres);
        ME = this;
    }
    public ArrayList<User> getListUser(){
        ArrayList<User> list = new ArrayList<User>();
        list.add(this);
        return list;
    }
}
