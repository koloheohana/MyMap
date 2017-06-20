package com.koloheohana.mymap.util;

import android.provider.Settings;

/**
 * Created by User on 2017/06/20.
 */

public class TimeStopper {
    public static long start_time;
    public static long stop_time;
    public static void start(){
        start_time = System.currentTimeMillis();
    }
    public static void stop(){
        stop_time = System.currentTimeMillis();
        time();
    }
    public static void time(){
        System.out.println("計測タイムは:"+(stop_time - start_time));
    }
}
