package com.koloheohana.mymap.data_base;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 2017/06/11.
 */
@Module
public class DataBaseModule {
    @Inject
    public OrmaDatabase ormaDatabase;
    public DataBaseModule(Context context){
        ormaDatabase = OrmaDatabase.builder(context).build();
    }
    @Singleton
    @Provides
    public OrmaDatabase DataBaseModule(Context context){
        return ormaDatabase;
    }
}
