package com.koloheohana.mymap.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.util.BitmapReader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by User on 2016/08/24.
 */
public class TorkAdapter extends ArrayAdapter<OneTork> {
    private android.view.LayoutInflater LayoutInflater;
    OneTork TORK;
    User USER;
    Context CONTEXT;

    public TorkAdapter(Context c, int i, User user) {
        super(c, i, user.TORK);
        CONTEXT = c;
        USER = user;
        this.LayoutInflater = (android.view.LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    View list_view;

    @Override
    public View getView(int pos, View view, ViewGroup vp) {
        list_view = view;
        TORK = USER.TORK.get(pos);
        OneTork ot = (OneTork) getItem(pos);
        aViewHolder vh = new aViewHolder();
        view = LayoutInflater.inflate(R.layout.tork_list_item, vp, false);
        vh.set((TextView) view.findViewById(R.id.tork_name));
        vh.setText((TextView) view.findViewById(R.id.tork_text_box));
        vh.setIvTork((ImageView) view.findViewById(R.id.tork_image));
        vh.name.setText(USER.getName());

        //画像を表示する場合
        if (ot.getUri() != null) {
            vh.text.setVisibility(View.INVISIBLE);
            BitmapReader.setSizeAndDirection(MainTork.ME,vh.iv_tork, null, ot.getUri());
            return view;
        }
        vh.text.setText(ot.getTork());
        return view;
    }



    class aViewHolder {
        TextView name;
        TextView text;
        ImageView iv_tork;

        public void setIvTork(ImageView view) {
            iv_tork = view;
        }

        public void setText(TextView _text) {
            this.text = _text;
        }

        public void set(TextView _name) {
            this.name = _name;
        }
    }
}



