package com.koloheohana.mymap.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.koloheohana.mymap.MainActivity;

/**
 * Created by User on 2017/06/04.
 */

public class TestClass {
    public void test(){
        Todo todo = new Todo(MainActivity.ME);
        final SQLiteDatabase db = todo.getWritableDatabase();
        String name = "名前";
        String age = "10";
        ContentValues in = new ContentValues();
        in.put("name",name);
        in.put("age",age);
        in.put("loc","テスト");
        long id = db.insert("person",name,in);
    }
    public void open(){
        Todo todo = new Todo(MainActivity.ME);
        SQLiteDatabase db = todo.getReadableDatabase();
        Cursor c = db.query("person", new String[] { "name", "age" }, null,
        null, null, null, null);

        boolean mov = c.moveToNext();
        while(mov){
            System.out.println("データベース:"+c.getString(0)+":"+c.getInt(1));
            mov = c.moveToNext();
        }
    }
}
