package com.koloheohana.mymap.sns;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.adapter.TorkAdapter;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.map.ShopDataIntent;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;

import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.BitmapReader;
import com.koloheohana.mymap.util.CustomListView;
import com.koloheohana.mymap.util.MyClipboard;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/07/02.
 */
public class MainTork extends AppCompatActivity {
    //インテントで移動する時に誰のトークを開くか指定している　stringじゃなくオブジェクト指定にする
    public static long ID;
    public static User user;
    public static MainTork ME;
    private TorkAdapter TA;
    private InputMethodManager inputMethodManager;
    private LinearLayout mainLayout;
    public float viewWidth;

    //+ID+.txt
    private static String file_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tork_activity);
        ME = this;
        mainLayout = (LinearLayout) findViewById(R.id.tork_main_layout);
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        // ウィンドウマネージャのインスタンス取得
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        // ディスプレイのインスタンス生成
        Display disp = wm.getDefaultDisplay();
        viewWidth = disp.getWidth();

        Intent intent = getIntent();
        ShopDataIntent sd = (ShopDataIntent) intent.getSerializableExtra("ShopData");
        TextView tv = (TextView) findViewById(R.id.nowGroupText);
        if (sd != null) {
            user = UserList.getUserById(sd.USER_ID);
            ID = sd.USER_ID;
            createTork();
            ShopDate _sd = ShopList.getShopDate(ME,ID);
            Uri uri = Uri.fromFile(new File(sd.file_name));
            addTork(_sd, null, uri);

        } else {
            user = UserList.getDbUserById(ME,ID);
            createTork();
        }
        tv.setText(user.getName());
    }

    //URLリンクに移動する
    public void createMapTork(ShopDate sd) {
        String _URL = "https://www.google.co.jp/#q=" + sd.getShopName();
        Uri uri = Uri.parse(_URL);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(i);
    }

    public StringBuffer sb = new StringBuffer();

    /**
     * onClick
     *
     * @param view
     */
    public void edit_tork(View view) {
        EditText ed = (EditText) findViewById(R.id.tork_text);
        String tork = ed.getText().toString();
        if (tork.isEmpty()) {
            return;
        }
        addTork(tork);
        EditText tork_text = (EditText) findViewById(R.id.tork_text);
        tork_text.setText(null);
    }


    /**
     * トーク書き込み
     *
     * @param _tork トーク内容
     */
    private void addTork(String _tork) {
        OneTork tork = new OneTork(_tork, new Clocks(this), user, null, null, null);
        addTork(tork);
    }

    private void addTork(OneTork tork) {
        OrmaOperator.addTork(ME,tork);
        user.addTork(tork);
/*
        ReadFileSns.writeTorkFile(user.getId(), tork);
*/
        CustomListView list = (CustomListView) findViewById(R.id.tork_list_view);
        int last = list.getCount();
        list.setSelection(last - 1);
        list.deferNotifyDataSetChanged();
        TA.notifyDataSetChanged();
    }

    private void addTork(ShopDate sd, Uri camera, Uri map) {
        OneTork tork = new OneTork(null, new Clocks(this), user, camera, sd, map);
        addTork(tork);
    }

    public void removeTork(OneTork ot) {
        user.removeTork(ot);
/*
        ReadFileSns.removeTorkFile(user.getId(), ot);
*/
        CustomListView list = (CustomListView) findViewById(R.id.tork_list_view);
        int last = list.getCount();
        list.setSelection(last - 1);
        list.deferNotifyDataSetChanged();
        TA.notifyDataSetChanged();
    }

    private void createTork() {
        CustomListView list = (CustomListView) findViewById(R.id.tork_list_view);
        TA = new TorkAdapter(this, R.layout.tork_list_item, user);
/*
        TorkAdapter ta = new TorkAdapter(this,0,user);
*/

        list.setAdapter(TA);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CustomListView clv = (CustomListView) parent;
                OneTork one = (OneTork) clv.getItemAtPosition(position);
                SampleDialogFragment sdf = new SampleDialogFragment(ME, one);
                sdf.show();
            }
        });

        int last = list.getCount();
        list.setSelection(last - 1);
    }

    public void storageTork() {
        StringBuffer sb = new StringBuffer();
        ;
        for (OneTork otk : user.TORK) {
            sb.append(otk.getStringFileConverter());
        }
        ShareCompat.IntentBuilder builder = ShareCompat.IntentBuilder.from(MapsActivity.MAP_ME);
        builder.setChooserTitle("トークを保存します");
        builder.setSubject("保存方法を選んで下さい");
        builder.setText(sb);
        builder.setType("text/plain");
        builder.startChooser();
    }

    public void openGallery(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_GALLERY);
    }

    private static final int REQUEST_GALLERY = 0;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent resultData) {
        Bitmap loadBitmap = BitmapReader.rotateAndResize(this,resultData.getData());
        Uri uri = SaveDateController.saveBitmapFile(this, loadBitmap,"tork"+String.valueOf(user.getId())+new Clocks(this).getStringAllTime());
        loadBitmap = null;
        addTork(null,uri, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        mainLayout.requestFocus();
        return false;
    }
}

class SampleDialogFragment extends AlertDialog {
    OneTork OT;

    public SampleDialogFragment(final Context context, OneTork ot) {
        super(context);
        OT = ot;
        ArrayList<String> list = new ArrayList<>();
        TorkDialogAdapter adapter = new TorkDialogAdapter(context.getApplicationContext(), 0, list);
        final ListView listView = new ListView(context);
        listView.setAdapter(adapter);
        if(OT.isImage()){
            if(OT.MAP_URI != null) {
                list.add("この場所までのナビを開く");
                list.add("他の人に共有する");
                list.add("削除する");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                Uri gmUri = Uri.parse("google.navigation:q="+"座標"+","+"座標"+"&mode=w");
                                Intent gmIntent = new Intent(Intent.ACTION_VIEW, gmUri);
                                gmIntent.setPackage("com.google.android.apps.maps");
                                MainTork.ME.startActivity(gmIntent);
                                break;
                            case 1:
                                break;
                            case 2:
                                MainTork.ME.removeTork(OT);
                                break;
                        }
                        dismiss();
                    }
                });
                setTitle(OT.getTork());
            }
        }else {
            list.add("このトークを削除する");
            list.add("このトークをコピーする");
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                        case 0:
                            MainTork.ME.removeTork(OT);
                            break;
                        case 1:
                            MyClipboard.set(MainTork.ME, OT.getTork());
                            break;
                    }
                    dismiss();
                }
            });
            setTitle(OT.getTork());
        }
        setView(listView);
    }

    class TorkDialogAdapter extends ArrayAdapter<String> {
        private LayoutInflater inflater;

        public TorkDialogAdapter(Context context, int resource, List<String> objects) {
            super(context, resource, objects);
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            String item = (String) getItem(position);
            if (null == v) v = inflater.inflate(R.layout.util_list_item, null);

            TextView intTextView = (TextView) v.findViewById(R.id.util_text);
            intTextView.setText(item);

            return v;
        }
    }
}
