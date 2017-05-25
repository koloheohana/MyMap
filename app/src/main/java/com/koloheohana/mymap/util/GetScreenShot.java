package com.koloheohana.mymap.util;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by User on 2017/05/01.
 */
public class GetScreenShot {
    public static Bitmap getScreenBitmap(View view){
        return getViewBitmap(view.getRootView());
    }
    public static Bitmap getViewBitmap(View view){
        view.setDrawingCacheEnabled(true);
        Bitmap cache = view.getDrawingCache();
        if(cache == null){
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(cache);
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }
}
