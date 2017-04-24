package com.koloheohana.mymap.sns;

import com.koloheohana.mymap.Clocks;

/**
 * Created by User on 2017/04/24.
 */
public class OneTork {
    String TORK;
    Clocks CLOCKS;
    public OneTork(String tork, Clocks clocks){
        TORK = tork;
        CLOCKS = clocks;
    }
    public OneTork(String tork){
        TORK = tork;
    }
    public String getTork(){
        return TORK;
    }
}
