package com.koloheohana.mymap.sns;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.adapter.MyBookMarkAdapter;
import com.koloheohana.mymap.adapter.TorkAdapter;
import com.koloheohana.mymap.adapter.TorkShareAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.map.ShopDataIntent;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.user_date.MyBookmark;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.CustomListView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by User on 2016/07/02.
 */
public class MainTork extends AppCompatActivity {
    //インテントで移動する時に誰のトークを開くか指定している　stringじゃなくオブジェクト指定にする
    public static int ID;
    public static User user;
    public static MainTork ME;
    //+ID+.txt
    private static String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tork_activity);
        ME = this;
        TextView tv = (TextView)findViewById(R.id.nowGroupText);
        user = UserList.getUserById(ID);
        createTork();
        tv.setText(user.getName());
        Intent intent = getIntent();
        ShopDataIntent sd = (ShopDataIntent)intent.getSerializableExtra("ShopData");
        if(sd != null) {
            addTork(sd.SHOP_NAME);
        }
    }
    public StringBuffer sb = new StringBuffer();

    /**
     * onClick
     * @param view
     */
    public void edit_tork(View view){
        EditText ed =(EditText)findViewById(R.id.tork_text);
        addTork(ed.getText().toString());
        EditText tork_text = (EditText)findViewById(R.id.tork_text);
        tork_text.setText(null);
    }


    /**
     * トーク書き込み
     * @param _tork トーク内容
     */
    private void addTork(String _tork){
        OneTork tork =  new OneTork(_tork,new Clocks(this),MyUser.ME);
        user.addTork(tork);
        CustomListView list = (CustomListView)findViewById(R.id.tork_list_view);
        int last = list.getCount();
        list.setSelection(last-1);
        list.deferNotifyDataSetChanged();
    }

    private void createTork(){

        CustomListView list = (CustomListView)findViewById(R.id.tork_list_view);
        TorkAdapter ta = new TorkAdapter(this,0,user);
        list.setAdapter(ta);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomListView clv = (CustomListView)parent;
                OneTork one = (OneTork)clv.getItemAtPosition(position);
                TorkAdapter TA = (TorkAdapter)clv.getAdapter();
                SampleDialogFragment sdf = new SampleDialogFragment(ME,one);
                sdf.show();
            }
        });

        int last = list.getCount();
        list.setSelection(last-1);
    }

    public void storageTork(){
        StringBuffer sb = new StringBuffer();;
        for(OneTork otk:user.TORK){
            sb.append(otk.getStringFileConverter());
        }
        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(MapsActivity.MAP_ME);
        builder.setChooserTitle("トークを保存します");
        builder.setSubject("保存方法を選んで下さい");
        builder.setText(sb);
        builder.setType("text/plain");
        builder.startChooser();
    }
}
class SampleDialogFragment extends AlertDialog {
    OneTork OT;
    public SampleDialogFragment(final Context context,OneTork ot){
        super(context);
        OT = ot;
        TorkShareAdapter adapter = new TorkShareAdapter(context.getApplicationContext(), 0, UserList.ALL_USER_LIST);
        final ListView listView = new ListView(context);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User sd = (User)listView.getItemAtPosition(position);
                System.out.println(sd.getName());
                System.out.println(OT.getTork());

                dismiss();
            }

        });

        setTitle("共有先一覧");
        setView(listView);
    }
}