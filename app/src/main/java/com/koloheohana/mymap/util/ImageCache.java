package com.koloheohana.mymap.util;

import android.graphics.Bitmap;
import android.net.Uri;

import com.koloheohana.mymap.user_date.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by User on 2017/05/28.
 */
public class ImageCache {
    //保持するbitmapキャッシュの数
    public static int max_value = 20;
    public static LinkedHashMap<Uri, Bitmap> map = new LinkedHashMap<Uri, Bitmap>() {
        @Override
        protected boolean removeEldestEntry(Map.Entry<Uri, Bitmap> map) {
            return size() > max_value;
        }
        //要素の繰り上がり
        public Bitmap get(Object key) {
            Bitmap value = super.get(key);
            if (value != null) {
                remove(key);
                put((Uri) key, value);
            }
            return value;
        }
    };
    public static Bitmap get(Uri uri){
        return map.get(uri);
    }

    public static void set(Uri uri, Bitmap image) {
        if (!map.containsKey(uri)) {
            map.put(uri, image);
        }
    }


    public static void clear() {
        map.clear();
    }

    public static boolean isKey(Uri uri) {
        return map.containsKey(uri);
    }

}
