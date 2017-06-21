package com.koloheohana.mymap.map;

import android.provider.Settings;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.date.SaveDateController;
import com.koloheohana.mymap.dialog.ShopDialog;
import com.koloheohana.mymap.user_date.MyBookmark;
import com.koloheohana.mymap.user_date.ReadDate;
import com.koloheohana.mymap.util.GetScreenShot;

import java.util.ArrayList;

/**
 * Created by User on 2017/01/19.
 */
public class ShopSearch {
    public static MapsActivity ME = MapsActivity.MAP_ME;
    public static int POSITION = 0;
    private static double KM[] = {3, 1, 0.5, 0.1};

    public static void setAdapter() {
        setCategorySpinner();
        setSearchRange();
    }

    public static void searchShop(View view) {
        String str;
        EditText et = (EditText) ME.findViewById(R.id.search_map);
        str = et.getText().toString();
        searchShop(str, view);
    }

    private static void searchShop(String shop_name, View view) {
        ArrayList<ShopDate> list = ShopSearch.getSearchShopList(shop_name);
        if (list.isEmpty()) {
            return;
        }

        PopupMenu pm = new PopupMenu(MapsActivity.MAP_ME.getApplicationContext(), view);
        int count = 0;
        for (ShopDate sd : list) {
            pm.getMenu().add(1, count, count, sd.getShopName()).setTitleCondensed(sd.getADDRRES());
            count++;
        }
        pm.show();
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ShopDate S_D = ShopSearch.getShopDate(item.getTitle().toString(),item.getTitleCondensed().toString());
                MapsActivity.MAP_ME.setMarker(S_D, true);
                ShopDialog sd = new ShopDialog(S_D);
                sd.show(MapsActivity.MAP_ME.getSupportFragmentManager(), S_D.getShopName());
                return false;
            }

        });
    }

    public static ShopDate getShopDate(String name, String addrres) {
        for (ShopDate sd : ShopList.ALLLIST) {
            if (sd.getADDRRES().equals(addrres)||sd.getShopName().equals(name)) {
                return sd;
            }
        }
        return null;
    }

    public static ShopDate getShopDate(int ID) {
        return getShopDate(1);
    }

    public static ArrayList<ShopDate> getSearchShopList(String str) {
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for (ShopDate sd : ShopList.ALLLIST) {
            String name = sd.getShopName();
            if (name.startsWith(str)) {
                list.add(sd);
            }
        }
        return list;
    }

    private static void setSearchRange() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ME, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter.add("範囲選択");

        for (double d : KM) {
            adapter.add(String.valueOf(d) + "㎞");
        }
        Spinner spinner = (Spinner) ME.findViewById(R.id.spinner2);
        // アダプターを設定します
        spinner.setAdapter(adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                POSITION = position;
                if (POSITION == 0) {
                    return;
                }
                // 選択されたアイテムを取得します
                ArrayList<ShopDate> list = getSearchRange(position - 1);
                MapsActivity.MAP_ME.setMarker(list, true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }
    public static ArrayList<ShopDate> getSearchRange(int position) {
        LatLng lan = MapsActivity.MAP_ME.getLatLngNow();
        Double[] RANGE = Calculation.rangeCal(lan, KM[position]);
        ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
        for (LatLng _lat : ShopList.SHOP_MAP_LATLNG.keySet()) {
            if (RANGE[0] <= _lat.latitude && RANGE[1] >= _lat.latitude &&
                    RANGE[2] <= _lat.longitude && RANGE[3] >= _lat.longitude) {
                shop_list.add(ShopList.SHOP_MAP_LATLNG.get(_lat));
            }
        }
        return shop_list;
    }
    public static void setMarker(int kiro){
        LatLng lan = MapsActivity.MAP_ME.getLatLngNow();
        Double[] RANGE = Calculation.rangeCal(lan, kiro);
        ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
        for (LatLng _lat : ShopList.SHOP_MAP_LATLNG.keySet()) {
            if (RANGE[0] <= _lat.latitude && RANGE[1] >= _lat.latitude &&
                    RANGE[2] <= _lat.longitude && RANGE[3] >= _lat.longitude) {
                shop_list.add(ShopList.SHOP_MAP_LATLNG.get(_lat));
            }
        }
        MapsActivity.MAP_ME.setMarker(shop_list,true);
    }
    public static ArrayList<ShopDate> getSearchRangeKiro(int kiro) {
        LatLng lan = MapsActivity.MAP_ME.getLatLngNow();
        Double[] RANGE = Calculation.rangeCal(lan, kiro);
        ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
        for (LatLng _lat : ShopList.SHOP_MAP_LATLNG.keySet()) {
            if (RANGE[0] <= _lat.latitude && RANGE[1] >= _lat.latitude &&
                    RANGE[2] <= _lat.longitude && RANGE[3] >= _lat.longitude) {
                shop_list.add(ShopList.SHOP_MAP_LATLNG.get(_lat));
            }
        }
        return shop_list;
    }

    /*    private static void setBookmarkSpinner(){
            ReadDate.read();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ME, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            // アイテムを追加します
            adapter.add("お気に入り選択");
            for(ShopDate sd: MyBookmark.getList()){
                adapter.add(sd.getShopName());
            }
            Spinner spinner = (Spinner) ME.findViewById(R.id.spinner3);
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
                    ME.setMarker(MyBookmark.getList().get(position-1),true,true);
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                }
            });
        }*/
    private static void setCategorySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ME, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        System.out.println("test");
        // カテゴリアイテムを追加します
        adapter.add("ジャンル選択");
        for (String str : ShopDate.SHOP_CATEGORY.getCategoryNameList()) {
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
                if (POSITION == 0) {
                    return;
                }
                // 選択されたアイテムを取得します
                String item = (String) spinner.getSelectedItem();
                ME.setMarker(getCategoryList(getSearchRange(0), item), true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

    }

    public static ArrayList<ShopDate> getCategoryList(ArrayList<ShopDate> _list, String str) {
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for (int i = 0; i < _list.size(); i++) {
            ShopDate _sd = _list.get(i);
            for (String _cate_name : _sd.CATEGORY) {
                if (_cate_name.equals(str)) {
                    list.add(_list.get(i));
                    break;
                }
            }
        }
        return list;
    }

    public static ArrayList<ShopDate> getsearchList(ArrayList<ShopDate> _list, String str) {
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for (int i = 0; i < _list.size(); i++) {
            ShopDate _sd = _list.get(i);
            if (_sd.getShopName().equals(str)) {
                list.add(_sd);
            }
        }
        return list;
    }
}
