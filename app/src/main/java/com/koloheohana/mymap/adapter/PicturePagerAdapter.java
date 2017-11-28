package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.media.Image;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by User on 2017/11/28.
 */

public class PicturePagerAdapter extends PagerAdapter{
    /**
     * コンテキスト.
     */
    private Context mContext;

    /**
     * リスト.
     */
    private ArrayList<Integer> mList;

    /**
     * コンストラクタ.
     */
    public PicturePagerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<Integer>();
    }

    /**
     * リストにアイテムを追加する.
     *
     * @param item アイテム
     */
    public void add(Integer item) {
        mList.add(item);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // リストから取得
        Integer item = mList.get(position);

        // View を生成
        ImageView imageView = new ImageView(mContext);
        System.out.println("ADAPTER:"+item);
        imageView.setImageResource(item);
        // コンテナに追加
        container.addView(imageView);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // コンテナから View を削除
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // リストのアイテム数を返す
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        // Object 内に View が存在するか判定する
        return view == (ImageView) object;
    }
}
