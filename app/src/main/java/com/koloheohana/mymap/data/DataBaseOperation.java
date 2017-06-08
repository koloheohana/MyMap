package com.koloheohana.mymap.data;

import android.content.Context;
import android.util.Log;

import com.koloheohana.mymap.MainActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

import static com.koloheohana.mymap.adapter.MainFragmentPagerAdapter.tork;

/**
 * Created by User on 2017/06/08.
 */

public class DataBaseOperation {
    public static Context CONTEXT = MainActivity.ME;
    public static void createUserData(){
        //ディレクトリにrealm作成
        RealmConfiguration realmCondig = new RealmConfiguration.Builder(CONTEXT).build();
        Realm.setDefaultConfiguration(realmCondig);

        //realmインスタンス作成
        Realm realm = Realm.getDefaultInstance();
        //データ作成及びトランザクション開始
        for(int i = 0; i <= 10; i++) {
            DbTork tork = new DbTork();
            tork.setID(i);
            tork.setCLOCK("時刻");
            tork.setTork("トーク"+i);
            realm.beginTransaction();
            final DbTork _tork = realm.copyToRealm(tork);
            DbUser user = realm.createObject(DbUser.class);
            user.setID(i);
            user.setNAME("テストユーザー"+i);
            user.setCOMMENT("コメント");
            user.setICON_BINARY("テストバイナリ"+i);
            user.getTORK().add(_tork);
            realm.commitTransaction();
        }
        //トランザクション終了
        RealmQuery<DbUser> query = realm.where(DbUser.class);
        RealmResults<DbUser> result = query.findAll();
        for(DbUser rd:result){
            System.out.println("ID"+rd.getID());
            System.out.println("名前"+rd.getNAME());
            System.out.println("サイズ："+rd.getTORK().size());
        }
    }
    public static void create(Context _context){
        context = _context;
        //ディレクトリにrealm作成
        RealmConfiguration realmCondig = new RealmConfiguration.Builder(context).build();
        Realm.setDefaultConfiguration(realmCondig);
        //realmインスタンス作成
        Realm realm = Realm.getDefaultInstance();

        RealmDb realmDb = new RealmDb();
        realmDb.setId(1);
        realmDb.setName("テストネーム");
        realmDb.setComment("テストコメント");
        //トランザクション
        realm.beginTransaction();
        realm.copyToRealm(realmDb);
        realm.commitTransaction();

        RealmQuery<RealmDb> query = realm.where(RealmDb.class);
        RealmResults<RealmDb> result = query.findAll();
        for(RealmDb rd:result){
            System.out.println(rd.getId());
        }
    }


    static Context context;
    public static void on(Context _context){
        RealmResults<RealmDb> results = null;
        context = _context;

        addTestData();
        addTestdataBestVer(1,"test_name_1","テスト中です");
        addTestdataBestVer(1,"test_name_2","テスト中だろ");
        results = getTestData();

        for(final RealmDb db: getTestData()){
            Log.d("REALM_TESTER",String.format("ID: %s Name: %s COMMENT: %s",db.getId(),db.getName(),db.getComment()));
        }
        updateTestData(results);
        deleteTestData(results);
        for(final RealmDb db: getTestData()){
            Log.d("REALM_TESTER2",String.format("ID: %s Name: %s COMMENT: %s",db.getId(),db.getName(),db.getComment()));
        }
    }
    private static void deleteTestData(RealmResults<RealmDb> result) {

        Realm realm = Realm.getInstance(context, "test_realm");
        RealmDb model = result.get(0);

        realm.beginTransaction();
        result.remove(1);

        model.removeFromRealm();

        //        result.clear();
        realm.commitTransaction();
    }
    private static void updateTestData(RealmResults<RealmDb> result) {

        Realm realm = Realm.getInstance(context, "test_realm");
        RealmDb model = result.get(2);

        realm.beginTransaction();
        model.setName("UPDATE");
        realm.commitTransaction();
    }
    private static RealmResults<RealmDb> getTestData(){
        Realm realm = Realm.getInstance(context,"test_realm");
        RealmQuery<RealmDb> query = realm.where(RealmDb.class);
        return query.findAll();
    }
    private static void addTestData(){
        Realm realm = Realm.getInstance(context,"test_realm");
        RealmDb rd = null;
        realm.beginTransaction();
        rd = realm.createObject(RealmDb.class);
        rd.setId(1);
        rd.setName("test_name");
        rd.setComment("テスト中");
        realm.commitTransaction();
    }
    private static void addTestdataBestVer(int id, String name,String comment) {

        RealmDb model = new RealmDb();
        model.setId(id);
        model.setName(name);
        model.setComment(comment);
        addTestDataBestVer(model);
    }
    private static void addTestDataBestVer(RealmDb model){
        Realm realm = Realm.getInstance(context, "test_realm");
        realm.beginTransaction();
        realm.copyToRealm(model);
        realm.commitTransaction();
    }

}
