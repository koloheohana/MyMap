package com.koloheohana.mymap.user_date;

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
}
