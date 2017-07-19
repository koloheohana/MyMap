package com.koloheohana.mymap.util;

import java.util.Random;

/**
 * Created by User on 2017/07/17.
 */

public class Patisuro {


    int max = 65536;
    int big_number = 4;
    int reg_number = 4;
    int bit_medal = 98304;
    double big_count =0;
    double reg_count = 0;
    int reg_medal = 98304;
    int one_play_medal = 3;
    double play_medal_count = 0;
    double get_medal_count = 0;
    double my_medal = 0;
    double count = 0;
    public void start1(){

    }
    public void start(){
        System.out.println("パチスロ台を開始します");
        while(count != 1000000){
            count++;
            my_medal -= one_play_medal;
            play_medal_count += one_play_medal;
            int random = new Random().nextInt(max)+1;
            int count_number = big_number;
            if(count_number >= random){
                my_medal += bit_medal;
                big_count++;
                get_medal_count += bit_medal;
                continue;
            }else{
                count_number += big_number;
            }
            if(count_number >= random){
                my_medal += reg_medal;
                reg_count++;
                get_medal_count += reg_medal;
                continue;
            }else{
                count_number += reg_number;
            }
        }
        stop();
    }
    public void stop(){
        System.out.println("ビッグ回数:"+big_count);
        System.out.println("レグ回数:"+reg_count);
        double gousei = (big_count+reg_count)/count;
        System.out.println("合成確立:"+(1/gousei)+"分の１");
        System.out.println("持ちメダル:"+my_medal+"枚");
        System.out.println("払出メダル:"+get_medal_count+"枚");
        System.out.println("プレイメダル:"+play_medal_count+"枚");
        double last = my_medal / play_medal_count;
        System.out.println("機械割:"+(100+(last*100))+"%");
    }
}
