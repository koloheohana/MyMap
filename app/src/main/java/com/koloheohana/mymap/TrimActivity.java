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
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.util.BitmapReader;

/**
 * Created by User on 2017/08/30.
 */

public class TrimActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trim);

        final CropImageView cropImageView = (CropImageView)findViewById(R.id.cropImageView);
        Intent intent = getIntent();
        String str = intent.getStringExtra("uri");
        // トリミングしたい画像をセット
        Bitmap bitmap  = BitmapReader.getBitmap(this, SaveDateController.getUri(str),true);
        cropImageView.setImageBitmap(bitmap);

        Button cropButton = (Button)findViewById(R.id.crop_button);
        cropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 処理
            }
        });
    }
}
