package com.koloheohana.mymap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Util;
import com.nifty.cloud.mb.core.DoneCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBInstallation;
import com.nifty.cloud.mb.core.NCMBUser;

/**
 * Created by User on 2017/07/20.
 */

public class SighupActivity extends AppCompatActivity{
    private static final String TAG = "SignupActivity";
    public static SighupActivity ME;
   EditText _nameText;
    EditText _passwordText;
  Button _signupButton;
   TextView _loginLink;
    Button _nologinButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ME = this;
        Scene.set(this);

        setContentView(R.layout.activity_sighup);
        _nameText = (EditText)findViewById(R.id.input_name);
        _passwordText = (EditText)findViewById(R.id.input_password);
        _signupButton = (Button)findViewById(R.id.btn_signup);
        _loginLink = (TextView)findViewById(R.id.link_login);
        _nologinButton = (Button)findViewById(R.id.btn_no_login);
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        _nologinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ME,MainActivity.class);
                ME.startActivity(intent);            }
        });
        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
        _nameText.setFilters(Util.getEnglishFilters());
        _passwordText.setFilters(Util.getEnglishFilters());
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SighupActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        //NCMBUserのインスタンスを作成
        final NCMBUser user = new NCMBUser();
        //ユーザ名を設定
        user.setUserName(name);
        //パスワードを設定
        user.setPassword(password);
        //設定したユーザ名とパスワードで会員登録を行う
        user.signUpInBackground(new DoneCallback() {
            @Override
            public void done(NCMBException e) {
                if (e != null) {
                    //会員登録時にエラーが発生した場合の処理
                    onSignupFailed();
                } else {
                    new android.os.Handler().postDelayed(
                            new Runnable() {
                                public void run() {
                                    OrmaOperator.setConfig(ME,name,password,false);
                                    if(!ServerOperator.insta_id.isEmpty()){
                                        user.put("ID",ServerOperator.insta_id);
                                        user.saveInBackground(new DoneCallback() {
                                            @Override
                                            public void done(NCMBException e) {
                                                final NCMBInstallation installation = NCMBInstallation.getCurrentInstallation();
                                                installation.put("menberID",name);
                                                installation.saveInBackground(new DoneCallback() {
                                                    @Override
                                                    public void done(NCMBException e) {
                                                        System.out.println("サーバー情報に紐付け完了");
                                                    }
                                                });
                                            }
                                        });
                                    }
                                    // On complete call either onSignupSuccess or onSignupFailed
                                    // depending on success
                                    onSignupSuccess();
                                    // onSignupFailed();
                                    progressDialog.dismiss();
                                    StartActivity.mainStartActivity(ME);
                                }
                            }, 3000);
                }
            }
        });
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("３文字以上で設定して下さい");
            valid = false;
        } else {
            _nameText.setError(null);
        }
        if(name.equals("user")){
            _nameText.setError("その名前は使えません");
            valid = false;
        }else{
            _nameText.setError(null);

        }
        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("４文字以上９文字以下で入力して下さい");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        if(password.equals("pass")){
            _passwordText.setError("そのパスワードは使えません");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}
