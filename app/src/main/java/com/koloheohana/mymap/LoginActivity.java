package com.koloheohana.mymap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
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
import com.nifty.cloud.mb.core.LoginCallback;
import com.nifty.cloud.mb.core.NCMBException;
import com.nifty.cloud.mb.core.NCMBUser;

/**
 * Created by User on 2017/07/20.
 */

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public LoginActivity ME;
    EditText _nameText;
    EditText _passwordText;
    Button _loginButton;
    Button _noLoginButton;
    TextView _signupLink;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ME = this;
        Scene.set(this);
        setContentView(R.layout.activity_login);
        _noLoginButton = (Button)findViewById(R.id.btn_no_login);
        _nameText = (EditText)findViewById(R.id.input_name);
        _passwordText = (EditText)findViewById(R.id.input_password);
        _loginButton = (Button)findViewById(R.id.btn_login);
        _signupLink = (TextView)findViewById(R.id.link_signup);
        _noLoginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ME,MainActivity.class);
                ME.startActivity(intent);
            }
        });
        _loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SighupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        _nameText.setFilters(Util.getEnglishFilters());
        _passwordText.setFilters(Util.getEnglishFilters());
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Theme_AppCompat_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String name = _nameText.getText().toString();
        final String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        //ユーザ名とパスワードを指定してログインを実行
        try {
            System.out.println("ログイン処理を開始します");
            NCMBUser.loginInBackground(name, password, new LoginCallback() {
                @Override
                public void done(final NCMBUser user, NCMBException e) {
                    if (e != null) {
                        progressDialog.dismiss();
                        System.out.println("エラー"+e);
                        //エラー時の処理
                        onLoginFailed();
                    } else {
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        System.out.println("ログイン処理が完了しました");
                                        OrmaOperator.setConfig(ME,name,password,false);
                                        if(!ServerOperator.insta_id.isEmpty()){
                                            System.out.println("登録情報を更新を開始します");
                                            user.put("ID",ServerOperator.insta_id);
                                            user.saveInBackground(new DoneCallback() {
                                                @Override
                                                public void done(NCMBException e) {
                                                    System.out.println("登録情報を更新しました");
                                                }
                                            });
                                        }
                                        // On complete call either onLoginSuccess or onLoginFailed
                                        onLoginSuccess();
                                        // onLoginFailed();
                                        progressDialog.dismiss();
                                        StartActivity.mainStartActivity(ME);
                                    }
                                }, 3000);
                    }
                }
            });
        } catch (NCMBException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        _nameText.setError("名前かパスワードが間違っています");
        _passwordText.setError("名前かパスワードが間違っています");

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError("空欄です");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("４文字以上９文字以下で入力して下さい");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
