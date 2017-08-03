package com.koloheohana.mymap.setting;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.R;

/**
 * Created by User on 2017/08/03.
 */

public class Setting {

    public static final String MyName = "名前";
    public static final String MyPicture = "写真";
    public static final String MyAddrres = "所在地";
    public static final String MyComment = "コメント";

    public static void setProfListView(final AppCompatActivity activity) {
        ListView listView = (ListView)activity.findViewById(R.id.settingListView);
        ArrayAdapter<String> list = new ArrayAdapter<String>(activity, R.layout.list);
        list.add(MyName);
        list.add(MyPicture);
        list.add(MyAddrres);
        list.add(MyComment);

        listView.setAdapter(list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case MyName:
                        NameChangeDialog dialog = new NameChangeDialog();
                        dialog.show(activity.getSupportFragmentManager(),"name");
                        break;
                    case MyPicture:
                        break;
                    case MyAddrres:
                        break;
                    case MyComment:
                        break;
                }
            }
        });
    }

}
