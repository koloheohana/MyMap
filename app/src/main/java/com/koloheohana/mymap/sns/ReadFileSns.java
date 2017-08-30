package com.koloheohana.mymap.sns;

import android.content.Context;
import android.util.Log;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaUser;
import com.koloheohana.mymap.data_base.OrmaUser_Selector;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;

import java.util.ArrayList;

/**
 * Created by User on 2017/04/23.
 */
public class ReadFileSns {
    public static String FILE_NAME = SaveFile.FRIEND_DATE;

    public static void read() {
        ArrayList<String> list = SaveDateController.read(FILE_NAME);
        for (String str : list) {
            try {
                String[] users = str.split("＼");
                int id = Integer.valueOf(users[0].split("∥")[1]);
                String name = users[1].split("∥")[1];
                String mutter = users[2].split("∥")[1];
                int icon = Integer.valueOf(users[3].split("∥")[1]);
                UserList.add(new User(id, String.valueOf(icon), name, mutter));
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("tork_data", "ユーザーデータに不正な処理が入っている為飛ばします");
            }
        }
    }

    //後に改定
    static String[] NAMES = {"社長", "田中", "瀬戸", "近Ｍ", "近Ｓ", "山田", "池田", "松尾", "安井", "永井",
            "鵜飼", "馬", "竹", "竹", "竹", "竹", "竹"};
    static String[] LOCS = {"名古屋", "名古屋", "インド", "名古屋", "名古屋",
            "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋", "名古屋"};
    static int[] ICONS = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.drawable.ohana, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher
    };

    /*    private static void friend(){
            for (int i = 0; i < ICONS.length; i++) {
                User user = new User(ICONS[i],NAMES[i],LOCS[i]);
                UserList.add(user);
            }
        }*/
    public static void testCreate() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < NAMES.length; i++) {
            sb.append("ID∥" + i + "＼");
            sb.append("名前∥" + NAMES[i] + "＼");
            sb.append("場所∥" + LOCS[i] + "＼");
            sb.append("画像∥" + String.valueOf(ICONS[i]) + "\n");
        }
        SaveDateController.newFile(FILE_NAME, sb.toString());
    }

    public static void fileClear() {
        for (User user : UserList.ALL_USER_LIST) {
            int user_id = (int) user.getId();
            String file_name = SaveFile.TORK_ID + user_id + SaveFile.FORMAT;
            SaveDateController.newFile(file_name, "");
        }
    }
    public static void readTorkFile(Context context,long user_id){

    }
    public static void readTorkFile() {

        for (User user : UserList.ALL_USER_LIST) {
            int user_id = (int) user.getId();
            String file_name = SaveFile.TORK_ID + user_id + SaveFile.FORMAT;
            ArrayList<String> tork_list = SaveDateController.read(file_name);
            if (tork_list.isEmpty()) {
                continue;
            }
            try {
                for (String str : tork_list) {
                    String[] torks = str.split("＼");
                    String[] time = torks[2].split("∥");
                    String TORK = torks[3].split("∥")[1];
                    Clocks clock = new Clocks(time);
                    OneTork one_tork = new OneTork(TORK, clock, user, null, null, null);
                    user.addTork(one_tork);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.v("tork_data", "id" + user.getId() + "のトークデータに不正な入力が有りますので飛ばします");
                continue;
            }
        }
    }

    public static void writeTorkFile(int user_id, OneTork tork) {
        String file_name = SaveFile.TORK_ID + user_id + SaveFile.FORMAT;
        StringBuffer sb = new StringBuffer();
        sb.append(tork.getStringFileConverter());
        SaveDateController.write(file_name, sb.toString());
    }

    public static void removeTorkFile(int user_id, OneTork tork) {
        String file_name = SaveFile.TORK_ID + user_id + SaveFile.FORMAT;
        StringBuffer sb = new StringBuffer();
        sb.append(tork.getStringFileConverter());
        SaveDateController.removeLine(file_name, sb.toString());
    }
}
