package com.koloheohana.mymap.server;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.dialog.TorkShareDialog;
import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.FetchFileCallback;
import com.nifty.cloud.mb.core.FindCallback;
import com.nifty.cloud.mb.core.NCMB;
import com.nifty.cloud.mb.core.NCMBAcl;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBFile;
import com.nifty.cloud.mb.core.NCMBInstallation;
import com.nifty.cloud.mb.core.NCMBObject;
import com.nifty.cloud.mb.core.NCMBPush;
import com.nifty.cloud.mb.core.NCMBQuery;
import com.nifty.cloud.mb.core.NCMBUser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by User on 2017/07/01.
 */

public class ServerOperator {
    public static String APP_KEY = "760f1e53c4c219a642f2bcbdff34f77509ac07c45d37b3b2242a69ca68f4ecb1";
    public static String CLIENT_KEY = "6fee8348c87aba566b616672818c33fa9143f598118cfacf44506ba131e7f356";
    public static String SENDER_KEY = "385724188713";
    public static void setServer(Context context){
        NCMB.initialize(context,APP_KEY,CLIENT_KEY);
        rogin();
    }

    public static Activity activity;


    public static void createClass(){
        NCMBObject ncmbObject = new NCMBObject("TestClass");
        ncmbObject.put("message","Hello,NCMB!");
        ncmbObject.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if(e != null){
                    System.out.println("CreateClass:保存失敗");
                }else{
                    System.out.println("CreateClass:保存成功");
                }
            }
        });
    }
    public static void sendPush(){
        NCMBPush push = new NCMBPush();
        push.setTitle("test title");
        push.setMessage("test Message");
        try {
            push.setTarget(new JSONArray("[android]"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        push.setDialog(true);
        push.sendInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if(e != null){
                    e.printStackTrace();
                }else{
                    System.out.println("test");
                }
            }
        });
    }
    //プッシュ通知設定
    public static void setPush(){

        final NCMBInstallation installation = NCMBInstallation.getCurrentInstallation();

        //NCM設定
        installation.getRegistrationIdInBackground("385724188713", new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if(e == null){
                    installation.saveInBackground(new DoneCallback() {
                        @Override
                        public void done(NCMBException e) {
                            if(e == null){
                                System.out.println("保存成功");
                            }else if(NCMBException.DUPLICATE_VALUE.equals(e.getCode())){
                                System.out.println("保存失敗:registrationID重複");
                                updateInstallation(installation);
                            }else{
                                System.out.println("保存失敗：そのた");
                                e.printStackTrace();
                            }
                        }
                    });
                }else{
                    //ID取得失敗
                    System.out.println("失敗：ID取得");
                }
            }
        });

    }
    public static void updateInstallation(final NCMBInstallation installation){
        //installationクラスを検索するクエリの作成
        NCMBQuery<NCMBInstallation> query = NCMBInstallation.getQuery();

        //同じRegistration IDをdeviceTokenフィールドに持つ端末情報を検索する
        query.whereEqualTo("deviceToken", installation.getDeviceToken());

        //データストアの検索を実行
        query.findInBackground(new FindCallback<NCMBInstallation>() {
            @Override
            public void done(List<NCMBInstallation> results, NCMBException e) {

                //検索された端末情報のobjectIdを設定
                installation.setObjectId(results.get(0).getObjectId());

                //端末情報を更新する
                installation.saveInBackground();
            }
        });
    }
    //ログインダイアログ
    public static void rogin(){
        SignUpDialog dialog = new SignUpDialog();
        dialog.show(MainActivity.ME.getFragmentManager(),"サインアップ");
    }
    //ログイン
    public static void signup(Activity _activity,String user_name,String user_pass){
        activity = _activity;
        NCMBUser user = new NCMBUser();

        final ProgressDialog dialog = new ProgressDialog(MainActivity.ME);
        dialog.setIndeterminate(true);
        dialog.setMessage("アカウントを作成します");
        dialog.show();

        user.setPassword(user_name);
        user.setUserName(user_name);
        //サーバー登録
        user.signUpInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if(e != null){
                    e.printStackTrace();
                }else{
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    activity.finish();
                                    dialog.dismiss();
                                }
                            },3000);
                }
            }
        });
    }
    public static Bitmap TestBitmap = null;
    public static void imageUpload(Bitmap bitmap){
        //画像準備
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,output);
        byte[] data = output.toByteArray();
        NCMBAcl acl = new NCMBAcl();
        //読み書き許可
        acl.setPublicReadAccess(true);
        acl.setPublicWriteAccess(true);
        //通信実施
        final NCMBFile file = new NCMBFile("testBitmap.png",data,acl);
        file.saveInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                String result;
                if(e != null){
                    System.out.println("保存失敗");
                }else{
                    NCMBFile file = new NCMBFile("testBitmap.png");
                    file.fetchInBackground(new FetchFileCallback() {
                        @Override
                        public void done(byte[] bytes, NCMBException e) {
                            if(e != null){
                                System.out.println("保存失敗");
                            }else{
                                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                                TestBitmap = bitmap;
                            }
                        }
                    });
                }
            }
        });
    }
}
class SignUpDialog extends DialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getActivity());
        // タイトル非表示
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        // フルスクリーン
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        dialog.setContentView(R.layout.custom_dialog_yes_no);
        // 背景を透明にする
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView text = (TextView)dialog.findViewById(R.id.title_dialog);
        // テキスト設定
        text.setText("");
        // OK ボタンのリスナ
        Button button = (Button)dialog.findViewById(R.id.share_nomitomo_button);
        button.setText("新規登録する");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServerOperator.signup(getActivity(),"田中","12341234");
                dismiss();
            }
        });
        Button button2 = (Button)dialog.findViewById(R.id.share_ather_button);
        button2.setText("ログインする");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        // Close ボタンのリスナ
        Button button1 = (Button)dialog.findViewById(R.id.close_button);
        dialog.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }

}
