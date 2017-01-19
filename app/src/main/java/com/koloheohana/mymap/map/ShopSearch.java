package com.koloheohana.mymap.map;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;

import java.util.ArrayList;

/**
 * Created by User on 2017/01/19.
 */
public class ShopSearch {
    public static MapsActivity ME = MapsActivity.MAP_ME;
    public static int POSITION = 0;
    public static void setAdapter(){
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
                ME.clearMarker();
                ME.setMarker(getCategoryList(item));
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public static ArrayList<ShopDate> getCategoryList(String str){
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for(int i = 0; i < ShopList.ALLLIST.size();i++){
            ShopDate _sd = ShopList.ALLLIST.get(i);
            if(_sd.getCATEGORY().equals(str)){
                list.add(ShopList.ALLLIST.get(i));
            }else{
                continue;
            }
        }
        return list;
    }
}
