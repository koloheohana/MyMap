package com.koloheohana.mymap.map;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;

import java.util.ArrayList;

/**
 * Created by User on 2017/01/19.
 */
public class ShopSearch {
    public static MapsActivity ME = MapsActivity.MAP_ME;
    public static int POSITION = 0;
    private static double KM[] = {3,1,0.5,0.1};
    public static void setAdapter(){
        setCategorySpinner();
        setSearchRange();
    }
    private static void setSearchRange(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ME, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("範囲選択");
        for(double d:KM){
            adapter.add(String.valueOf(d)+"㎞");
        }
        Spinner spinner = (Spinner) ME.findViewById(R.id.spinner2);
        // アダプターを設定します
        spinner.setAdapter(adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                POSITION = position;
                if(POSITION == 0){
                    return;
                }
                // 選択されたアイテムを取得します
                ArrayList<ShopDate> list = getSearchRange(position-1);
                MapsActivity.MAP_ME.setMarker(list,true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public  static ArrayList<ShopDate> getSearchRange(int position){
        LatLng lan = MapsActivity.MAP_ME.getLatLngNow();
        Double[] RANGE = Calculation.rangeCal(lan,KM[position]);
        ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
        for(LatLng _lat:ShopList.SHOP_MAP_LATLNG.keySet()){
            if(RANGE[0] <= _lat.latitude&& RANGE[1] >=_lat.latitude&&
                    RANGE[2] <= _lat.longitude && RANGE[3] >= _lat.longitude){
                shop_list.add(ShopList.SHOP_MAP_LATLNG.get(_lat));
            }
        }
        return shop_list;
    }
    private static void setCategorySpinner(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ME, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // カテゴリアイテムを追加します
        adapter.add("ジャンル選択");
        for(String str:ShopDate.SHOP_CATEGORY.getCategoryNameList()){
            adapter.add(str);
        }
        Spinner spinner = (Spinner) ME.findViewById(R.id.spinner);
        // アダプターを設定します
        spinner.setAdapter(adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                POSITION = position;
                if(POSITION == 0){
                    return;
                }
                // 選択されたアイテムを取得します
                String item = (String) spinner.getSelectedItem();
                ME.setMarker(getCategoryList(getSearchRange(0),item),true);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }
    public static ArrayList<ShopDate> getCategoryList(ArrayList<ShopDate> _list,String str){
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for(int i = 0; i < _list.size();i++){
            ShopDate _sd = _list.get(i);
            for(String _cate_name:_sd.CATEGORY){
                if(_cate_name.equals(str)){
                    list.add(_list.get(i));
                    break;
                }
            }
        }
        return list;
    }
}
