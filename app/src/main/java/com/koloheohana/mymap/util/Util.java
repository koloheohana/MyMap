package com.koloheohana.mymap.util;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by User on 2017/08/20.
 */

public class Util {

    public static InputFilter getEnglishFiliter() {
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.toString().matches("^[0-9a-zA-Z@¥.¥_¥¥-]+$")) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        return inputFilter;
    }

    public static InputFilter[] getEnglishFilters() {
        InputFilter[] filters = new InputFilter[]{getEnglishFiliter()};
        return filters;
    }

    public static String zenkakuHiraganaToZenkakuKatakana(String s) {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ぁ' && c <= 'ん') {
                sb.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
            }
        }
        return sb.toString();
    }

    public static String katakanaToHiragana(String str) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char code = str.charAt(i);
            if ((code >= 0x30a1) && (code <= 0x30f3)) {
                buf.append((char) (code - 0x60));
            } else {
                buf.append(code);
            }
        }
        return buf.toString();
    }

    public static boolean isKatanaka(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA;
    }

    public static boolean isHiragana(char c) {
        return Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA;
    }

    public static char changeHiraKana(char c){
        if(isKatanaka(c)){
            return katakanaToHiragana(String.valueOf(c)).toCharArray()[0];
        }
        if(isHiragana(c)){
            return zenkakuHiraganaToZenkakuKatakana(String.valueOf(c)).toCharArray()[0];
        }
        return c;


    }
}
