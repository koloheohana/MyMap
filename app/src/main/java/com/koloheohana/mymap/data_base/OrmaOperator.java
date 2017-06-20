package com.koloheohana.mymap.data_base;


import android.content.Context;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;

import com.github.gfx.android.orma.AccessThreadConstraint;
import com.github.gfx.android.orma.Inserter;
import com.github.gfx.android.orma.migration.BuildConfig;
import com.github.gfx.android.orma.migration.ManualStepMigration;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.TimeStopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Provides;

public class OrmaOperator {
    int version_3 = 4;
    public static OrmaDatabase getOrmaDataBase(Context context,String name){
        OrmaDatabase ormaDatabase = null;
        OrmaDatabase.Builder db =OrmaDatabase.builder(context).name(name);
        ormaDatabase = db.readOnMainThread(AccessThreadConstraint.NONE).writeOnMainThread(BuildConfig.DEBUG ? AccessThreadConstraint.WARNING : AccessThreadConstraint.NONE).build();
        return ormaDatabase;
    }
    public static ArrayList<String> sampleUser(){
        return null;
    }
    public static void setUser(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaUser");
        Inserter<OrmaUser> inserter = orma.prepareInsertIntoOrmaUser();
        for(User user: UserList.ALL_USER_LIST){
            OrmaUser ot = new OrmaUser(user.getId(),user.getName(),19000101,"null","愛知県");
            inserter.execute(ot);
        }
    }
    public static void setShopData(Context context){
/*
        System.out.println("ショッ.ALLLIST.size());
*/
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopData");
        orma.deleteFromOrmaShopData().execute();
        TimeStopper.start();
        Inserter<OrmaShopData> inserter = orma.prepareInsertIntoOrmaShopData();
        ArrayList<OrmaShopData> list = new ArrayList<OrmaShopData>();
        long id = 1;
        for(ShopDate data: ShopList.ALLLIST){
            List<String> category_list =  data.getCategoryList();
            OrmaShopData osd = new OrmaShopData(id,data.getShopName(),category_list,data.getTEL(),"HP","ICON",data.getPOSTAL(),data.getADDRRES(),data.getX().longValue(),data.getY().longValue());
            list.add(osd);
            id++;
        }
        inserter.executeAll(list);
        TimeStopper.stop();

    }
    public static void setTork(Context context,OneTork tork){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaTork");
        Inserter<OrmaTork> inserter = orma.prepareInsertIntoOrmaTork();
        String sentence = tork.getTork();
        int id = tork.getID();
        String Clock = tork.getClock().getStringAllTime();
        String shop_data = tork.getShopData().getShopName();

    }
    public static void remove(Context context){
        OrmaDatabase orma1 = getOrmaDataBase(context,"OrmaShopData");
        orma1.deleteFromOrmaShopData().execute();
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaUser");
        orma.deleteFromOrmaUser().execute();

    }
    public static void read(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaUser");
        OrmaUser_Selector selector = orma.selectFromOrmaUser()
                .orderByIdDesc();
        for(OrmaUser ot:selector){
            System.out.println(ot.user_name);
        }
        OrmaDatabase orma1 = getOrmaDataBase(context,"OrmaShopData");
        OrmaShopData_Selector selector1 = orma1.selectFromOrmaShopData();
        for(OrmaShopData sd:selector1){
            System.out.println(sd.shop_name);
            System.out.println(selector1.count());
        }
    }
    public static void setShopList(){
        OrmaDatabase orma1 = getOrmaDataBase(MainActivity.ME,"OrmaShopData");
        OrmaShopData_Selector selector1 = orma1.selectFromOrmaShopData();
        for(OrmaShopData sd:selector1){
            ShopList.setShopList(new ShopDate(sd.id,sd.addrres,sd.shop_postal,sd.shop_name,sd.shop_category_list,sd.shop_tel,sd.coordinate_x,sd.coordinate_y));
        }
    }
    public static OrmaUser_Selector getDBUser(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaUser");
        orma.deleteFromOrmaUser();
        OrmaUser_Selector selector = orma.selectFromOrmaUser()
                .orderByIdDesc();
        return selector;
    }
}
