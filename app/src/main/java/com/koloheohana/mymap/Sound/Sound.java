package com.koloheohana.mymap.Sound;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;

import com.koloheohana.mymap.util.Scene;

/**
 * Created by User on 2017/08/20.
 */

public class Sound {
    public static Sound ME = new Sound(Scene.CONTEXT);
    public Ringtone ringtone;
    public Sound(Context context){
        ME = this;
        Uri uri  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        ringtone = RingtoneManager.getRingtone(context,uri);
    }
    public void playRingtone(){
        ringtone.play();
    }
    public void setRingtone(Uri uri,Context context){
        ringtone = RingtoneManager.getRingtone(context,uri);
    }
}
