package com.koloheohana.mymap.sns;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by User on 2017/04/23.
 */
public class ReadFileSns {
    public static String FILE_NAME = SaveFile.FRIEND_DATE;
    public static void read(){

        ArrayList<String> list = SaveDateController.read(FILE_NAME);
        for(String str:list) {
            System.out.println(str);
            String[] users = str.split("/");
            System.out.println(users[0]);
            int id = Integer.valueOf(users[0].split(":")[1]);
            String name = users[1].split(":")[1];
            String mutter = users[2].split(":")[1];
            int icon = Integer.valueOf(users[3].split(":")[1]);
            UserList.add(new User(id,icon,name,mutter));
        }
    }

    //後に改定
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

/*    private static void friend(){
        for (int i = 0; i < ICONS.length; i++) {
            User user = new User(ICONS[i],NAMES[i],LOCS[i]);
            UserList.add(user);
        }
    }*/
    public static void testCreate(){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < NAMES.length;i++){
            sb.append("ID:"+i+"/");
            sb.append("名前:"+NAMES[i]+"/");
            sb.append("場所:"+LOCS[i]+"/");
            sb.append("画像:"+String.valueOf(ICONS[i])+"\n");
        }
        System.out.println(sb.toString());
        SaveDateController.newFile(FILE_NAME,sb.toString());
    }

    public void readTorkFile(int user_id){
        String file_name = SaveFile.TORK_ID+user_id+SaveFile.FORMAT;
        File file = new File(file_name);
        if (file.exists()){
            SaveDateController.newFile(file_name,"");
            System.out.println("ファイルは存在します");
        }else{
            System.out.println("ファイルは存在しません");
        }
        ArrayList<String> tork_list = SaveDateController.read(file_name);
        for(String str:tork_list){
            int ID;
            String TORK;
            Clocks clock;
        }
    }
    public void writeTorkFile(int user_id){
        String file_name = SaveFile.TORK_ID+user_id+SaveFile.FORMAT;
        File file = new File(file_name);
        if (file.exists()){
            SaveDateController.newFile(file_name,"");
            System.out.println("ファイルは存在します");
        }else{
            System.out.println("ファイルは存在しません");
        }
        StringBuffer sb = new StringBuffer();
        User user = UserList.getUserById(user_id);
        for(OneTork otk:user.TORK){
            sb.append(otk.getStringFileConverter());
        }
        SaveDateController.write(file_name,sb.toString());
    }
}
