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
import android.support.v7.widget.RecyclerView;
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
import com.koloheohana.mymap.util.ImageCache;
import com.koloheohana.mymap.util.Window;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.w3c.dom.Text;

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


/*
    View list_view;
*/
    @Override
    public View getView(int pos, View view, ViewGroup vp) {
/*
        list_view = view;
*/
        if(view != null){
            viewClear(view);
        }
        TORK = USER.TORK.get(pos);
        OneTork ot = (OneTork) getItem(pos);
        aViewHolder vh = new aViewHolder();
        view = LayoutInflater.inflate(R.layout.tork_list_item, vp, false);
        vh.set((TextView) view.findViewById(R.id.tork_name));
        vh.setTime((TextView)view.findViewById(R.id.tork_time));
        vh.setText((TextView) view.findViewById(R.id.tork_text_box));
        vh.setIvTork((ImageView) view.findViewById(R.id.tork_image));
        vh.name.setText(USER.getName());
        view.setTag(vh);
        //画像を表示する場合
        if (ot.getUri() != null) {
            vh.text.setVisibility(View.GONE);
            if(ImageCache.isKey(ot.getUri())){
                System.out.println("キャッシュからの呼び出し");
                vh.iv_tork.setImageBitmap(ImageCache.get(ot.getUri()));
            }else {
                System.out.println("ファイルからの呼び出し");
                Bitmap loadBitmap = BitmapReader.getBitmap(MainTork.ME, ot.getUri(), true);
                vh.iv_tork.setImageBitmap(loadBitmap);
                ImageCache.set(ot.getUri(),loadBitmap);
            }
        }else {
            vh.iv_tork.setVisibility(View.GONE);
            vh.text.setText(ot.getTork());
        }
        vh.time.setText(ot.getClock().getMonthAndDay());
        return view;
    }

    public void viewClear(View view){
        releaseImageView(((aViewHolder)view.getTag()).iv_tork);
        view = null;
    }
    public void releaseImageView(ImageView view){
        if(view != null){
            view.setImageBitmap(null);
            view.setImageDrawable(null);
        }
    }



    class aViewHolder {
        TextView name;
        TextView text;
        ImageView iv_tork;
        TextView time;
        public void setTime(TextView tv){
            time = tv;
        }
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



