package com.koloheohana.mymap.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.View;

import java.lang.reflect.Method;

/**
 * Created by User on 2017/05/31.
 */
public class Window {
    public static Point WINDOW_SIZE = new Point();
    public static Display DISPLAY;

    public static void setWindowSize(Activity activity){
        DISPLAY = activity.getWindowManager().getDefaultDisplay();
        DISPLAY.getSize(WINDOW_SIZE);
    }
    /**
     * Get a Real Size(Hardware Size)
     * @param activity
     * @return
     */
    @SuppressLint("NewApi")
    public static Point getRealSize(Activity activity) {

        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point(0, 0);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // Android 4.2~
            display.getRealSize(point);
            return point;

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            // Android 3.2~
            try {
                Method getRawWidth = Display.class.getMethod("getRawWidth");
                Method getRawHeight = Display.class.getMethod("getRawHeight");
                int width = (Integer) getRawWidth.invoke(display);
                int height = (Integer) getRawHeight.invoke(display);
                point.set(width, height);
                return point;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return point;
    }

    /**
     * Get a view size. if display view size, after onWindowFocusChanged of method
     * @param View
     * @return
     */
    public static Point getViewSize(View View){
        Point point = new Point(0, 0);
        point.set(View.getWidth(), View.getHeight());

        return point;
    }
}
