package com.koloheohana.mymap.sns;

import com.koloheohana.mymap.Clocks;
import com.koloheohana.mymap.user_date.User;

/**
 * Created by User on 2017/04/24.
 */
public class OneTork {
    String TORK;
    User USER;
    int ID;
    Clocks CLOCK;
    public OneTork(String tork, Clocks clocks,User user){
        TORK = tork;
        CLOCK = clocks;
        USER = user;
        ID = user.getId();
    }
    public String getStringFileConverter(){
        StringBuffer sb = new StringBuffer();
        sb.append("ID:"+ID+"＼"+"CLOCK:"+CLOCK+"＼"+"TORK:"+TORK+"\n");
        return sb.toString();
    }

    public String getTork(){
        return TORK;
    }
}
