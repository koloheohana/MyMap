package com.koloheohana.mymap.data;

import io.realm.Realm;
import io.realm.RealmMigration;

/**
 * Created by User on 2017/06/08.
 */

public class Migration implements RealmMigration {

    @Override
    public long execute(Realm realm, long version) {
        return 0;
    }
}
