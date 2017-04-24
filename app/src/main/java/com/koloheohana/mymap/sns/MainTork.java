package com.koloheohana.mymap.sns;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.adapter.TorkAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by User on 2016/07/02.
 */
public class MainTork extends AppCompatActivity {
    //インテントで移動する時に誰のトークを開くか指定している　stringじゃなくオブジェクト指定にする
    public static int ID;
    public static User user;
    //+ID+.txt
    private static String file_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tork_activity);

        TextView tv = (TextView)findViewById(R.id.nowGroupText);
        user = UserList.getUserById(ID);
        tv.setText(user.getName());
        file_name = SaveFile.TORK_ID+ID+SaveFile.FORMAT;
        File file = new File(file_name);
        testCreateTork("s");
/*        if (file.exists()){
            SaveDateController.newFile(file_name,"");
            System.out.println("ファイルは存在します");
        }else{
            System.out.println("ファイルは存在しません");
        }
        read();*/
    }
    public StringBuffer sb = new StringBuffer();

    /**
     * onClick
     * @param view
     */
    public void edit_tork(View view){
        EditText ed =(EditText)findViewById(R.id.tork_text);
        testCreateTork(ed.getText().toString());
        EditText tork_text = (EditText)findViewById(R.id.tork_text);
        tork_text.setText(null);
    }
    float x;
    float y;

    /**
     * トーク書き込み
     * @param tork トーク内容
     */
    private void createTork(String tork){
/*
        LinearLayout layout = (LinearLayout)findViewById(R.id.tork_layout);
*/
/*
        LinearLayout tork_layout = getLayout(R.layout.tork_layout);
        TextView tork_tv = (TextView)tork_layout.findViewById(R.id.tork_text_box);
        TextView name_tv = (TextView)tork_layout.findViewById(R.id.tork_name);
        tork_tv.setText(tork);
        name_tv.setText(user.getName());
        layout.addView(tork_layout,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        final ScrollView sv = (ScrollView)findViewById(R.id.scroll_tork);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
*/

    }
    private void testCreateTork(String tork){

        ListView list = (ListView)findViewById(R.id.tork_list_view);
        TorkAdapter ta = new TorkAdapter(this,0,user.TORK);
        list.setAdapter(ta);
/*
        LinearLayout layout = (LinearLayout)findViewById(R.id.tork_layout);
        LinearLayout tork_layout = getLayout(R.layout.tork_layout);
        TextView tork_tv = (TextView)tork_layout.findViewById(R.id.tork_text_box);
        TextView name_tv = (TextView)tork_layout.findViewById(R.id.tork_name);
        tork_tv.setText(tork);
        name_tv.setText(user.getName());
        layout.addView(tork_layout,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
*/
        final ScrollView sv = (ScrollView)findViewById(R.id.scroll_tork);
        sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
    public LinearLayout getLayout(int select){
        LayoutInflater inflater = this.getLayoutInflater();
        LinearLayout view = (LinearLayout)inflater.inflate(select,null);
        return view;
    }
    public void myMap(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);
    }
    public void read(){
        ArrayList<String> list = SaveDateController.read(file_name);
        for(String str:list){

        }
    }

}
