package com.koloheohana.mymap.date;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.MyBookmark;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by User on 2017/04/04.
 */
public class SaveDateController {
    public static void write(String file_name,String str){
        try {
            System.out.println(file_name+":この内容で書き込みをします");
            System.out.println(str);

            OutputStream out = MainActivity.ME.openFileOutput(file_name,MainActivity.ME.MODE_APPEND);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
            writer.append(str);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void newFile(String file_name,String str){
        try {
            OutputStream out = MainActivity.ME.openFileOutput(file_name,MainActivity.ME.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
            writer.write(str);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<String> read(String file_name){
        try{
            InputStream in = MainActivity.ME.openFileInput(file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            ArrayList<String> list = new ArrayList<String>();
            while((s = reader.readLine()) != null){
                list.add(s);
            }
            reader.close();
            return list;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public static void bookmarkWriter(ShopDate sd){
        String str = sd.getShopName()+","+sd.getADDRRES()+"\n";
        write(SaveFile.BOOKMARK,str);
        read(SaveFile.BOOKMARK);
    }
    public static void bookmarkRemove(ShopDate sd){
        try{
            InputStream in = MainActivity.ME.openFileInput(SaveFile.BOOKMARK);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            StringBuffer sb = new StringBuffer();
            while((s = reader.readLine()) != null){
                String[] split = s.split(",");
                if(sd.getShopName().equals(split[0]) && sd.getADDRRES().equals(split[1])){
                    continue;
                }
                sb.append(s+"\n");
            }
            newFile(SaveFile.BOOKMARK,sb.toString());
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void removeFile(String file_name){
        MainActivity.ME.deleteFile(SaveFile.BOOKMARK);
    }
    public static void removeLine(String file_name,String str){
        try{
            InputStream in = MainActivity.ME.openFileInput(file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            String TORK_ID = str.split("＼")[2];
            String TORK_ID_S;
            StringBuffer sb = new StringBuffer();
            while((s = reader.readLine()) != null){
                TORK_ID_S = s.split("＼")[2];

                if(TORK_ID.matches(TORK_ID_S)){
                    continue;
                }
                sb.append(s).append("\n");
            }
            newFile(file_name,sb.toString());
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<String[]> bookmarkReader(){
        ArrayList<String[]> list = new ArrayList<>();
        try{
            InputStream in = MainActivity.ME.openFileInput(SaveFile.BOOKMARK);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            while((s = reader.readLine()) != null){
                String[] split = s.split(",");
                list.add(split);
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
    public static ArrayList<String[]> MemoRead(){
        ArrayList<String[]> list = new ArrayList<>();
        try{
            InputStream in = MainActivity.ME.openFileInput(SaveFile.SHOP_MEMO);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            while((s = reader.readLine()) != null){
                String[] split = s.split(",");
                list.add(split);
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
    public static void bitmapSave(Context context,Bitmap map){
        Bitmap b = map;//保存したいBitmap
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        String bitmapStr = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);

        SharedPreferences pref = context.getSharedPreferences("hoge",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("hoge", bitmapStr);
        editor.commit();
    }
    public static Uri saveBitmapFile(Context context, Bitmap image, String file_name){
        FileOutputStream out = null;
        Uri uri = null;
        try {
            // openFileOutputはContextのメソッドなのでActivity内ならばthisでOK
            out = context.openFileOutput(file_name+".png", Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.close();

            File file = new File(file_name+".png");
            uri = Uri.fromFile(file);
            FileInputStream in = context.openFileInput(file_name+".png");

            return uri;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uri;
    }
    public static Bitmap getBitMapPre(Context con){
        SharedPreferences pref = con.getSharedPreferences("hoge",Context.MODE_PRIVATE);
        String s = pref.getString("hoge","");
        byte[] b = Base64.decode(s, Base64.DEFAULT);
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
        return bmp;
    }
}
