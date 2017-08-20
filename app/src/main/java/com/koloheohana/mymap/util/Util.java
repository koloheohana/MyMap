package com.koloheohana.mymap.util;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by User on 2017/08/20.
 */

public class Util {

    public static InputFilter getEnglishFiliter(){
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if(source.toString().matches("^[0-9a-zA-Z@¥.¥_¥¥-]+$")){
                    return source;
                }else{
                    return "";
                }
            }
        };
        return inputFilter;
    }
    public static InputFilter[] getEnglishFilters(){
        InputFilter[] filters = new InputFilter[]{getEnglishFiliter()};
        return filters;
    }
}
