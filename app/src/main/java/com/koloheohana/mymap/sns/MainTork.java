package com.koloheohana.mymap.sns;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.adapter.TorkAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.date.SaveFile;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.menutab.Tork;
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
    //+ID+.txt
    private static String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tork_activity);

        TextView tv = (TextView)findViewById(R.id.nowGroupText);
        user = UserList.getUserById(ID);
        createTork();
        tv.setText(user.getName());
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
                SampleDialogFragment sdf  = new SampleDialogFragment(TA,one);
                sdf.show(getFragmentManager(),"");

            }
        });

        int last = list.getCount();
        list.setSelection(last-1);

    }

}
class SampleDialogFragment extends DialogFragment {
    TorkAdapter TA;
    OneTork OT;
    public SampleDialogFragment(TorkAdapter ta,OneTork ot){
        TA = ta;
        OT = ot;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle("このトークを削除しますか？")
                .setMessage(OT.getTork())
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // OK button pressed
                        TA.remove(OT);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onPause() {
        super.onPause();

        // onPause でダイアログを閉じる場合
        dismiss();
    }
}