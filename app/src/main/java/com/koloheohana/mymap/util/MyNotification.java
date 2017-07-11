package com.koloheohana.mymap.util;

import android.app.Fragment;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.R;

/**
 * Created by User on 2017/07/11.
 */

public class MyNotification {
    public static void setNotification(Context context,String title){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(title);
        NotificationManager manager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
