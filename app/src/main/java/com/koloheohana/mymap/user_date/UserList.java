package com.koloheohana.mymap.user_date;

import android.content.Context;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;

import java.util.ArrayList;

/**
 * Created by User on 2017/04/23.
 */
public class UserList {
    public static ArrayList<User> ALL_USER_LIST = new ArrayList<>();
    public static boolean add(User user){
        if(ALL_USER_LIST.contains(user)){
            return false;
        }
        ALL_USER_LIST.add(user);
        return true;
    }
    public static User getUserById(int id){
        for(User user:ALL_USER_LIST){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public static User getDbUserById(Context context,long id){
        return new User(context,OrmaOperator.getDBUser(MainActivity.ME).idEq(id).get(0));
    }
    public static User getDbUserById(Context context,int id){
        return new User(context,OrmaOperator.getDBUser(MainActivity.ME).idEq(id).get(0));
    }
}
