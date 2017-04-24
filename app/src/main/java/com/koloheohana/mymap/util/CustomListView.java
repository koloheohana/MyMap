package com.koloheohana.mymap.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;



/**
 * Created by User on 2017/04/24.
 */
public class CustomListView extends ListView {

    public CustomListView(Context context) {
        super(context);
    }

    //このコンストラクタが無いとXMLからインフレートできないので注意
    public  CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public  CustomListView(Context context, AttributeSet attrs,
                           int defStyle) {
        super(context, attrs, defStyle);
    }


    //Viewのサイズが変化した時に呼ばれるメソッド
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);

        //キーボード出現時（Viewのサイズが小さくなった場合）のみ
        if(h < oldh){
            //インターフェースを実装したリスナー（Activity、Fragment）のメソッドを呼ぶ
            setSelection(getCount()-1);
        }
    }
}
