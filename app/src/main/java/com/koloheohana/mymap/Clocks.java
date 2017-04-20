package com.koloheohana.mymap;

import android.content.Context;

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
    public Clocks(Context context){
        CALENDER = Calendar.getInstance();
        DATE_FORMAT = android.text.format.DateFormat.getDateFormat(context.getApplicationContext());
        String str = DATE_FORMAT.format(CALENDER.getTime());
        String[] time = str.split("/");
        YEAR = Integer.valueOf(time[0]);
        MONTH = Integer.valueOf(time[1]);
        DAY = Integer.valueOf(time[2]);
            System.out.println(CALENDER.get(Calendar.HOUR));
        HOUR = CALENDER.get(Calendar.HOUR);
        MINUTE = CALENDER.get(Calendar.MINUTE);
        SECOND = CALENDER.get(Calendar.SECOND);

    }

    public String getStringSandTheString(String str){
        String time =YEAR+str+MONTH+str+DAY+str+HOUR+str+MINUTE+str+SECOND;
        return time;
    }
}