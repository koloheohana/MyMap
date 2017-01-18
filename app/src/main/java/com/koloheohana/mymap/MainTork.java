package com.koloheohana.mymap;

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

import org.w3c.dom.Text;

import java.util.zip.Inflater;

/**
 * Created by User on 2016/07/02.
 */
public class MainTork extends AppCompatActivity {
    public static String TORK_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tork_activity);

        TextView tv = (TextView)findViewById(R.id.nowGroupText);
        tv.setText(TORK_NAME);
    }
    public StringBuffer sb = new StringBuffer();

    public void edit_tork(View view){
        createTork((EditText)findViewById(R.id.tork_text));

        EditText tork_text = (EditText)findViewById(R.id.tork_text);
        tork_text.setText(null);
    }
    float x;
    float y;
    private void createTork(EditText ed){
        LinearLayout layout = (LinearLayout)findViewById(R.id.tork_layout);
        LinearLayout tork_layout = getLayout(R.layout.tork_layout);
        TextView tork_tv = (TextView)tork_layout.findViewById(R.id.tork_text_box);
        TextView name_tv = (TextView)tork_layout.findViewById(R.id.tork_name);
        tork_tv.setText(ed.getText().toString());
        name_tv.setText(TORK_NAME);
        layout.addView(tork_layout,LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
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

}
