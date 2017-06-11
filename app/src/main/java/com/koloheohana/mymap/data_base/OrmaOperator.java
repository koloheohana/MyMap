package com.koloheohana.mymap.data_base;


import com.koloheohana.mymap.MainActivity;

import javax.inject.Singleton;

import dagger.Provides;

public class OrmaOperator {

    public static void set(){
        OrmaDatabase orma = OrmaDatabase.builder(MainActivity.ME).build();
        for(int i = 0;i <= 10;i++) {
            OrmaUser ot = new OrmaUser(i,"名前"+i,1988,null);
            orma.insertIntoOrmaUser(ot);
        }


    }
    public static void read(){
        OrmaDatabase orma = OrmaDatabase.builder(MainActivity.ME).build();
        OrmaUser_Selector selector = orma.selectFromOrmaUser()
                .orderByIdDesc();

        for(OrmaUser ot:selector){
            System.out.println(ot.user_name);
        }
    }
}
