package com.koloheohana.mymap.util;

import android.content.Context;

import com.koloheohana.mymap.MainActivity;

import java.text.DateFormat;
import java.util.Calendar;

/**
 * Created by User on 2017/04/14.
 */
public class Clocks {
    public final Calendar CALENDER;
    public final DateFormat DATE_FORMAT;
    public final int YEAR;
    public final int DAY;
    public final int MONTH;
    public final int HOUR;
    public final int MINUTE;
    public final int SECOND;
    public final int MILLSECOND;
    public static int getClock(Context context){
        Calendar CALENDER = Calendar.getInstance();
        DateFormat DATE_FORMAT = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
        String str = DATE_FORMAT.format(CALENDER.getTime());
        String[] time = str.split("/");

        return 1;
    }
    public Clocks(String str){
        CALENDER = null;
        DATE_FORMAT = null;
        String[] clock = str.split("/");
        YEAR = Integer.valueOf(clock[0]);
        MONTH = Integer.valueOf(clock[1]);
        DAY = Integer.valueOf(clock[2]);
        HOUR = Integer.valueOf(clock[3]);
        MINUTE = Integer.valueOf(clock[4]);
        SECOND = Integer.valueOf(clock[5]);
        MILLSECOND = Integer.valueOf(clock[6]);
    }
    public Clocks(Context context){
        CALENDER = Calendar.getInstance();
        DATE_FORMAT = android.text.format.DateFormat.getDateFormat(context);
        String str = DATE_FORMAT.format(CALENDER.getTime());
        String[] time = str.split("/");
        YEAR = Integer.valueOf(time[0]);
        MONTH = Integer.valueOf(time[1]);
        DAY = Integer.valueOf(time[2]);
        HOUR = CALENDER.get(Calendar.HOUR);
        MINUTE = CALENDER.get(Calendar.MINUTE);
        SECOND = CALENDER.get(Calendar.SECOND);
        MILLSECOND = CALENDER.get(Calendar.MILLISECOND);
    }
    public Clocks(String[] time){
        CALENDER = Calendar.getInstance();
        DATE_FORMAT = android.text.format.DateFormat.getDateFormat(MainActivity.ME.getApplicationContext());
        YEAR = Integer.valueOf(time[1]);
        MONTH = Integer.valueOf(time[2]);
        DAY = Integer.valueOf(time[3]);
        HOUR = Integer.valueOf(time[4]);
        MINUTE = Integer.valueOf(time[5]);
        SECOND = Integer.valueOf(time[6]);
        MILLSECOND = 0;
    }
    public String getStringAllTime(){
        StringBuffer sb = new StringBuffer();
        sb.append(YEAR);
        sb.append(MONTH);
        sb.append(DAY);
        sb.append(HOUR);
        sb.append(MINUTE);
        sb.append(SECOND);
        sb.append(MILLSECOND);
        return sb.toString();
    }
    public String getStringSandTheString(String str){
        String time =YEAR+str+MONTH+str+DAY+str+HOUR+str+MINUTE+str+SECOND+str+MILLSECOND;
        return time;
    }
    public String getMonthAndDay(){
        StringBuffer sb = new StringBuffer();
        sb.append(MONTH);
        sb.append("月");
        sb.append(DAY);
        sb.append("日");
        return sb.toString();
    }
}
