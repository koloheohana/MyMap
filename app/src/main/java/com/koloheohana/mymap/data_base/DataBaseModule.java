package com.koloheohana.mymap.data_base;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by User on 2017/06/11.
 */
@Module
public class DataBaseModule {
    @Singleton
    @Provides
    public OrmaDatabase DataBaseModule(Context context){
        return OrmaDatabase.builder(context).build();
    }
}
