package com.koloheohana.mymap.data_base;

import com.koloheohana.mymap.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by User on 2017/06/11.
 */
@Singleton
@Component(modules = {DataBaseModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
