package com.koloheohana.mymap.user_date;

import android.graphics.BitmapFactory;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;

import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class UserDate {
    /**
     * データベース項目
     */
    static String[] NAMES = {"社長", "田中", "瀬戸", "近Ｍ", "近Ｓ", "山田", "池田", "松尾", "安井", "永井",
            "鵜飼", "馬", "竹","竹","竹","竹","竹"};
    static String[] LOCS = {"名古屋", "名古屋", "インド", "名古屋", "名古屋",
            "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋"};
    static int[] ICONS = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.drawable.ohana, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };
    //ここまで

    public static ArrayList<User> user_list = users();

    private static ArrayList<User> users() {
        ArrayList<User> user_list = new ArrayList<User>();
        for (int i = 0; i < ICONS.length; i++) {
            User user = new User();
            user.setIcon(BitmapFactory.decodeResource(MainActivity.ME.getResources(), ICONS[i]));
            user.setName(NAMES[i]);
            user.setLoc(LOCS[i]);
            user_list.add(user);
        }
        return user_list;
    }

}
