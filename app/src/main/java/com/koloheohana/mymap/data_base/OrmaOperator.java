package com.koloheohana.mymap.data_base;


import android.content.Context;
import android.os.TransactionTooLargeException;
import android.support.annotation.NonNull;

import com.github.gfx.android.orma.AccessThreadConstraint;
import com.github.gfx.android.orma.Inserter;
import com.github.gfx.android.orma.annotation.OnConflict;
import com.github.gfx.android.orma.migration.BuildConfig;
import com.github.gfx.android.orma.migration.ManualStepMigration;
import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopList;
import com.koloheohana.mymap.menutab.Tork;
import com.koloheohana.mymap.sns.OneTork;
import com.koloheohana.mymap.user_date.MyBookmark;
import com.koloheohana.mymap.user_date.ShopMemo;
import com.koloheohana.mymap.user_date.User;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.TimeStopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import dagger.Provides;

public class OrmaOperator {
    int version_3 = 4;
    public static void test(){
        OrmaDatabase orma = getOrmaDataBase(Scene.CONTEXT, "OrmaShopData");
        OrmaShopData_Selector selector = orma.selectFromOrmaShopData().bookmarkEq(true).orderByIdDesc();
        System.out.println("ステップ1:ブックマークの数は"+selector.count());
        orma.updateOrmaShopData().idEq(100).bookmark(true).execute();
        OrmaShopData_Selector selector1 = orma.selectFromOrmaShopData().bookmarkEq(true).orderByIdDesc();
        System.out.println("ステップ2:ブックマークの数は"+selector1.count());
        OrmaShopData_Selector selector2 = orma.selectFromOrmaShopData().idEq(101).orderByAddrresDesc();

    }
    public static OrmaDatabase getOrmaDataBase(Context context, String name) {
        OrmaDatabase ormaDatabase = null;
        OrmaDatabase.Builder db = OrmaDatabase.builder(context).name(name);
        ormaDatabase = db.readOnMainThread(AccessThreadConstraint.NONE).writeOnMainThread(BuildConfig.DEBUG ? AccessThreadConstraint.WARNING : AccessThreadConstraint.NONE).build();
        return ormaDatabase;
    }
    public static OrmaTork_Selector getOrmaTorkSelector(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaTork");
        return orma.selectFromOrmaTork();
    }
    public static ArrayList<String> sampleUser() {
        return null;
    }

    public static void setUser(Context context) {
        OrmaDatabase orma = getOrmaDataBase(context, "OrmaUser");
        Inserter<OrmaUser> inserter = orma.prepareInsertIntoOrmaUser();
        for (User user : UserList.ALL_USER_LIST) {
            OrmaUser ot = new OrmaUser(user.getId(), user.getName(), 19000101, "null", "愛知県");
            inserter.execute(ot);
        }
    }
    public static void createUserData(Context context){
        final ArrayList<OrmaUser> list = new ArrayList<OrmaUser>();
        long id = 1;
        for(User user:UserList.ALL_USER_LIST){
            OrmaUser ou = new OrmaUser(id,user.getName(),20,user.getIcon().toString(),user.getLoc());
            list.add(ou);
            id++;
        }
        final OrmaDatabase orma = getOrmaDataBase(context, "OrmaUser");
        final Inserter<OrmaUser> inserter = orma.prepareInsertIntoOrmaUser();

        orma.transactionSync(new Runnable() {
            @Override
            public void run() {
                inserter.executeAll(list);
                TimeStopper.stop();
                OrmaUser_Selector selector1 = orma.selectFromOrmaUser();
                System.out.println("要素数：" + selector1.count());
            }
        });
    }
    public static void createShopData(Context context) {
        final ArrayList<OrmaShopData> list = new ArrayList<OrmaShopData>();

        long id = 1;
        for (ShopDate data : ShopList.ALLLIST) {
            List<String> category_list = data.getCategoryList();
            OrmaShopData osd = new OrmaShopData(id, data.getShopName(), category_list, data.getTEL(), "HP", "ICON", data.getPOSTAL(), data.getADDRRES(), data.getX(), data.getY(), false);
            list.add(osd);
            id++;
        }
        createShopData(context,list);
        ShopList.ALLLIST.clear();
    }
    public static void createShopData(Context context,final ArrayList<OrmaShopData> list){
        final OrmaDatabase orma = getOrmaDataBase(context, "OrmaShopData");
        final Inserter<OrmaShopData> inserter = orma.prepareInsertIntoOrmaShopData();

        orma.transactionSync(new Runnable() {
            @Override
            public void run() {
                inserter.executeAll(list);
                TimeStopper.stop();
                OrmaShopData_Selector selector1 = orma.selectFromOrmaShopData();
                System.out.println("要素数：" + selector1.count());
            }
        });
    }
    public static void addTork(Context context, OneTork tork) {
        OrmaDatabase orma = getOrmaDataBase(context, "OrmaTork");
        Inserter<OrmaTork> inserter = orma.prepareInsertIntoOrmaTork();
        int id = tork.getID();
        String Clock = tork.getClock().getStringSandTheString("/");
        int shop_data;
        if(tork.getShopData() == null){
            shop_data = 0;
        }else{
            shop_data = tork.getShopData().ID;
        }
        OrmaTork ormaTork = new OrmaTork(id,tork.getTork(),1,"null",tork.isImage(),Clock,tork.isCamera(),"",shop_data);
        inserter.execute(ormaTork);
    }

    public static void remove(Context context, int number) {
        switch (number) {
            case 1:
                OrmaDatabase orma1 = getOrmaDataBase(context, "OrmaShopData");
                orma1.deleteFromOrmaShopData().execute();
                break;
            case 2:
                OrmaDatabase orma = getOrmaDataBase(context, "OrmaUser");
                orma.deleteFromOrmaUser().execute();
                break;
        }
    }

    public static void read(Context context) {
        //データベースからユーザーデータ読み込みおよび、トークデータ読み込み
        OrmaDatabase orma = getOrmaDataBase(context, "OrmaUser");
        OrmaUser_Selector selector = orma.selectFromOrmaUser()
                .orderByIdAsc();
        for (OrmaUser ot : selector) {
            UserList.add(new User(context,ot));
        }
        //ブックマークデータの読み込み
        setBookMark(context);
        //メモデータの読み込み
        ShopMemo.read(context);

    }
    public static void readTorkData(long id){

    }
    public static void deleteShopMemo(Context context,long id){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopMemo");
        orma.deleteFromOrmaShopMemo().idEq(id).execute();
    }
    public static void writeShopMemo(Context context,long id,String memo,String time){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopMemo");

        if(!orma.selectFromOrmaShopMemo().shop_idEq(id).isEmpty()){
            System.out.println("追加します");
            OrmaShopMemo orma_memo = orma.selectFromOrmaShopMemo().idEq(id).get(0);
            orma_memo.memo_list.add(memo);
            orma.updateOrmaShopMemo().shop_idEq(id).memo_list(orma_memo.memo_list).time(time).execute();
        }else {
            System.out.println("新規作成します");
            List<String> list = new ArrayList<String>();
            list.add(memo);
            OrmaShopMemo orma_memo = new OrmaShopMemo(id, list, id, time);
            Inserter<OrmaShopMemo> inserter = orma.prepareInsertIntoOrmaShopMemo();
            inserter.execute(orma_memo);
        }

    }
    public static OrmaTork_Selector readTork(Context context,long id){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaTork");
        OrmaTork_Selector selector = orma.selectFromOrmaTork().user_idEq(id).orderByIdDesc();
        return selector;
    }
    public static void setBookMark(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopData");
        OrmaShopData_Selector selector = orma.selectFromOrmaShopData().bookmarkEq(true).orderByIdDesc();
        for(OrmaShopData sd:selector){
            MyBookmark.set(context,new ShopDate(sd));
        }
    }
    public static void setShopList() {
        OrmaDatabase orma1 = getOrmaDataBase(MainActivity.ME, "OrmaShopData");
        OrmaShopData_Selector selector1 = orma1.selectFromOrmaShopData();

        TimeStopper.start();
        for (OrmaShopData sd : selector1) {
            ShopList.setShopList(new ShopDate(sd));
        }
        TimeStopper.stop();
        System.out.println("ショップインスタンス生成タイム");
    }
    public static OrmaShopData_Selector getShopDataSelector(){
        OrmaDatabase orma1 = getOrmaDataBase(MainActivity.ME, "OrmaShopData");
        OrmaShopData_Selector selector1 = orma1.selectFromOrmaShopData();
        return selector1;
    }
    public static boolean setBookmark(Context context,int shop_id,boolean b){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopData");
        OrmaShopData_Selector selector = orma.selectFromOrmaShopData().idEq(shop_id);
        OrmaShopData osd = selector.get(0);
        if(osd.bookmark){
            return false;
        }
        orma.updateOrmaShopData().idEq(shop_id).bookmark(b).execute();
        return true;
    }
    public static OrmaShopData_Selector getSelectorShopNameInclude(Context context, String name, String table){
        OrmaDatabase orma = getOrmaDataBase(context,table);
        OrmaShopData_Selector osd = orma.selectFromOrmaShopData().where("shop_name LIKE ?","%"+name).orderByShop_nameDesc();

        return osd;
    }
    public static OrmaShopData getOrmaShopData(Context context,long shop_id){
        return getOrmaDataBase(context,"OrmaShopData").selectFromOrmaShopData().idEq(shop_id).orderByIdDesc().get(0);
    }
    public static OrmaShopData getOrmaShopData(Context context,String shop_name,String addrres){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopData");
        OrmaShopData_Selector osd = orma.selectFromOrmaShopData().shop_nameEq(shop_name).addrresEq(addrres).orderByShop_nameDesc();
        return osd.get(0);
    }
    public static OrmaShopData_Selector getShopSelector(Context context){
        OrmaDatabase orma = getOrmaDataBase(context,"OrmaShopData");
        return orma.selectFromOrmaShopData();
    }

    public static OrmaShopMemo_Selector getShopMemoSelector(Context context){
        OrmaDatabase odb = getOrmaDataBase(context,"OrmaShopMemo");
        OrmaShopMemo_Selector selector = odb.selectFromOrmaShopMemo();
        return selector;
    }
    public static OrmaUser_Selector getDBUser(Context context) {
        OrmaDatabase orma = getOrmaDataBase(context, "OrmaUser");
        orma.deleteFromOrmaUser();
        OrmaUser_Selector selector = orma.selectFromOrmaUser()
                .orderByIdDesc();
        return selector;
    }
}
