package com.koloheohana.mymap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by User on 2016/07/02.
 */
public class MainSetting extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup containter, Bundle saveInstanceState){
        return inflater.inflate(R.layout.activity_setting,null);
    }

}
