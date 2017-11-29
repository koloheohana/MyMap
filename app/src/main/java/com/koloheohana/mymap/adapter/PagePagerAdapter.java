package com.koloheohana.mymap.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by User on 2017/11/29.
 */

public class PagePagerAdapter extends PagerAdapter{
    /**
     * コンテキスト.
     */
    private Context mContext;

    /**
     * リスト.
     */
    private ArrayList<LinearLayout> mList;

    /**
     * コンストラクタ.
     */
    public PagePagerAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<LinearLayout>();
    }

    /**
     * リストにアイテムを追加する.
     *
     * @param item アイテム
     */
    public void add(LinearLayout item) {
        mList.add(item);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // リストから取得
        LinearLayout item = mList.get(position);
        // コンテナに追加
        container.addView(item);

        return item;
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
        return view == (LinearLayout) object;
    }
}
