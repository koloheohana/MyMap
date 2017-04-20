package com.koloheohana.mymap.date;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.user_date.MyBookmark;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by User on 2017/04/04.
 */
public class SaveDateController {
    public static void write(String file_name,String str){
        try {
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
    public static String read(String file_name){
        try{
            InputStream in = MainActivity.ME.openFileInput(file_name);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
            String s;
            while((s = reader.readLine()) != null){
                System.out.println(s);
            }
            reader.close();
            return null;
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
            StringBuffer sb = new StringBuffer();
            while((s = reader.readLine()) != null){
                if(str.matches(str)){
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

}
