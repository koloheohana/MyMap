package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.sns.MainTork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.util.BitmapReader;
import com.koloheohana.mymap.util.Clocks;
import com.koloheohana.mymap.util.ImageCache;

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
        System.out.println("TORK"+ot.getTork());
        System.out.print("FILE"+ot.getStringUri());
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



