package com.koloheohana.mymap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.util.BitmapReader;

/**
 * Created by User on 2017/08/30.
 */

public class TrimActivity extends AppCompatActivity {
    public static TrimActivity ME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trim);
        ME = this;
        final CropImageView cropImageView = (CropImageView)findViewById(R.id.cropImageView);
        cropImageView.setMinFrameSizeInDp(300);
        cropImageView.setMinFrameSizeInPx(300);
        cropImageView.setOutputWidth(300);

        Intent intent = getIntent();
        String str = intent.getStringExtra("uri");
        // トリミングしたい画像をセット
        final Uri uri = SaveDateController.getUri(str);
        Bitmap bitmap  = BitmapReader.getBitmap(this, uri,true);
        cropImageView.setImageBitmap(bitmap);
        Button cropButton = (Button)findViewById(R.id.crop_button);
        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 処理
                Bitmap bitmap = BitmapReader.resize(cropImageView.getCroppedBitmap(),300,300);
                MyUser.ME.setIcon( BitmapReader.resize(cropImageView.getCroppedBitmap(),300,300));
                Uri _uri = SaveDateController.saveBitmapFile(ME,bitmap,"user_picture");
                OrmaOperator.setMyData("",_uri.toString(),"","",ME);
                ServerOperator.imageUploadAndPush(ME,_uri.toString(),bitmap,ServerOperator.SERVER_FILE_CATEGORY_MY_PICTURE);
                finish();
            }
        });
    }
}
