package com.koloheohana.mymap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by User on 2017/08/03.
 */

public class SettingActivity extends AppCompatActivity{
    public static SettingActivity ME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_chose);
        ME = this;
    }

    public static final  String MyName = "MyName";
    public static final String MyPicture = "MyPicture";
    public static final String MyAddrres = "MyAddrres";
    public static final String MyComment = "MyComment";

    private void setListView(){
        ListView listView = (ListView)findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(this,R.layout.list);
        list.add(MyName);
        list.add(MyPicture);
        list.add(MyAddrres);
        list.add(MyComment);

        listView.setAdapter(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                Intent intent;
                switch (item_name){
                    case MyName:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select", MyName);
                        startActivity(intent);
                        break;
                    case MyPicture:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select", MyPicture);
                        startActivity(intent);
                        break;
                    case MyAddrres:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select", MyAddrres);
                        startActivity(intent);                        break;
                    case MyComment:
                        intent = new Intent(ME,SettingActivity.class);
                        intent.putExtra("select", MyComment);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
}
