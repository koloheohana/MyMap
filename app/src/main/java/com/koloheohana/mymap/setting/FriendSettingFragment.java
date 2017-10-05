package com.koloheohana.mymap.setting;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.user_date.User;

/**
 * Created by User on 2017/10/04.
 */

public class FriendSettingFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_page_list, container, false);
        ListView lv = create(view);
        /**
         * コンテキストメニュー作成
         */
        registerForContextMenu(lv);
        return view;
    }


    public static final String QRReader = "QR読み取り";
    public static final String QRDisplay = "QR表示";
    public static final String IDDisplay = "ID:";
    public static final String IDReader = "ID登録";

    public static ListView create(View view) {
        final AppCompatActivity activity = SettingSelectActivity.ME;
        ListView myList = (ListView) view.findViewById(R.id.myListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingSelectActivity.ME, R.layout.list);
        myList.setAdapter(adapter);
        adapter.add(IDDisplay+MyUser.ME.getName());
        adapter.add(IDReader);
        adapter.add(QRDisplay);
        adapter.add(QRReader);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView) view.findViewById(R.id.setting_item);
                String item_name = tv.getText().toString();
                switch (item_name) {
                    case IDDisplay:
                        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(activity);
                        builder.setChooserTitle("飲み友ID:" + MyUser.ME.getId());
                        builder.setSubject("飲み友ID:" + MyUser.ME.getId());
                        builder.setText(String.valueOf(MyUser.ME.getId()));
                        builder.setType("text/plain");
                        builder.startChooser();

                        break;
                    case IDReader:
                        break;
                    case QRDisplay:
                        new QrcodeDisplayDialog().show(activity.getFragmentManager(), "QR");
                        break;
                    case QRReader:
                        IntentIntegrator integrator = new IntentIntegrator(activity);
                        integrator.initiateScan();
                        break;
                }
            }
        });
        myList.setAdapter(adapter);

        return myList;
    }
}
