package com.koloheohana.mymap.map;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.data_base.OrmaDatabase;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.data_base.OrmaShopData;
import com.koloheohana.mymap.data_base.OrmaShopData_Selector;
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
    public static String search_category = ShopDate.SHOP_CATEGORY.飲み屋.name();
    public static int search_range = 0;

    public static void setAdapter() {

/*
        setCategorySpinner();
*/
/*
        setSearchRange();
*/
    }
    public static ArrayList<ShopDate> search(String s,ArrayList<ShopDate> list){
        ArrayList<ShopDate> _list = new ArrayList<ShopDate>();
        for(ShopDate sd : list){
            if(sd.getShopName().indexOf(s) != -1){
                _list.add(sd);
            }
        }
        return _list;
    }
    public static void searchShop(Context context,View view) {
        String str;
        EditText et = (EditText) ME.findViewById(R.id.search_map);
        str = et.getText().toString();
        searchShop(context,str, view);
    }

    private static void searchShop(Context context,String shop_name, View view) {
        ArrayList<ShopDate> list = ShopSearch.getSearchShopList(context,shop_name);
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
                ShopDate S_D = ShopSearch.getShopDate(item.getTitle().toString(), item.getTitleCondensed().toString());
                MapsActivity.MAP_ME.setMarker(S_D, true);
                ShopDialog sd = new ShopDialog(S_D,MapsActivity.MAP_ME);
                sd.show(MapsActivity.MAP_ME.getSupportFragmentManager(), S_D.getShopName());
                return false;
            }

        });
    }

    public static ShopDate getShopDate(String name, String addrres) {
        return new ShopDate(OrmaOperator.getOrmaShopData(MapsActivity.MAP_ME, name, addrres));
    }

    public static ShopDate getShopDate(int ID) {
        return getShopDate(1);
    }

    public static ArrayList<ShopDate> getSearchShopList(Context content, String str) {
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        OrmaShopData_Selector osd = OrmaOperator.getSelectorShopNameInclude(content, str, "OrmaShopData");
        int i = 0;
        for (OrmaShopData sd : osd) {
            list.add(new ShopDate(sd));
        }
        return list;
    }

    public static boolean[] category_is_check;
    public static Button CATEGORY_BUTTON;
    public static Button RANGE_BUTTON;
    public static void setFirst() {
        category_is_check = new boolean[ShopDate.SHOP_CATEGORY.getCategoryNameList().size()];
        for (int i = 0; i < ShopDate.SHOP_CATEGORY.getCategoryNameList().size(); i++) {
            category_is_check[i] = false;
        }
        category_is_check[0] = true;
        setButton();
        Button CATEGORY_BUTTON= (Button) MapsActivity.MAP_ME.findViewById(R.id.category_button);

        RANGE_BUTTON.setText(ranges[checking]+"キロ");
        CATEGORY_BUTTON.setText(getCategory().get(0));
    }
    private static void setButton(){
        Button CATEGORY_BUTTON= (Button) MapsActivity.MAP_ME.findViewById(R.id.category_button);
        RANGE_BUTTON = (Button) MapsActivity.MAP_ME.findViewById(R.id.range_button);
        CATEGORY_BUTTON = (Button) MapsActivity.MAP_ME.findViewById(R.id.category_button);
    }
    public static ArrayList<String> getCategory() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < category_is_check.length; i++) {
            if (category_is_check[i] == true) {
                list.add(ShopDate.SHOP_CATEGORY.getCategoryNameList().get(i));
            }
        }
        if(list.isEmpty()){
            list.add(ShopDate.SHOP_CATEGORY.getCategoryNameList().get(0));
        }
        return list;
    }

    public static void setCategory() {
        ArrayList<String> list = ShopDate.SHOP_CATEGORY.getCategoryNameList();
        final String[] category_item = list.toArray(new String[list.size()]);
        category_is_check = new boolean[list.size()];
        for (int i = 0; i < list.size(); i++) {
            category_is_check[i] = false;
        }
        new AlertDialog.Builder(MapsActivity.MAP_ME)
                .setTitle("ジャンル選択")
                .setMultiChoiceItems(category_item, category_is_check,
                        new DialogInterface.OnMultiChoiceClickListener() {

                            public void onClick(DialogInterface dialog, int which,
                                                boolean isChecked) {
                                search_category = category_item[which];
                                category_is_check[which] = isChecked;
                            }
                        })
                .setNegativeButton("検索", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ME.setMarker(getCategorysList(getSearchRange(search_range), getCategory()), true);
                        setButton();
                        CATEGORY_BUTTON.setText(getCategory().get(0));
                        dialog.cancel();
                    }
                })
                .show();

    }

    public static String[] ranges = {"3", "1", "0.5", "0.1"};
    public static int checking = 1;
    public static int checked = 0;

    public static void setRange() {
        new AlertDialog.Builder(MapsActivity.MAP_ME)
                .setTitle("範囲選択")
                .setSingleChoiceItems(ranges, checking, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        checking = item;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        checked = checking;
                        setButton();
                        RANGE_BUTTON.setText(String.valueOf(ranges[checked])+"キロ");
                        ME.setMarker(getCategorysList(getSearchRange(checking),getCategory()), true);

                    }
                })
                .setNegativeButton("キャンセル", null)
                .show();
    }

/*    private static void setSearchRange() {
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
    }*/

    public static ArrayList<ShopDate> getSearchRange(int position) {
        LatLng lan = MapsActivity.MAP_ME.getLatLngNow();
        Double[] RANGE = Calculation.rangeCal(lan, KM[position]);
        ArrayList<ShopDate> shop_list = new ArrayList<ShopDate>();
        OrmaShopData_Selector osd = OrmaOperator.getShopDataSelector();
        osd.coordinate_xGe(RANGE[0]);
        osd.coordinate_xLe(RANGE[1]);
        osd.coordinate_yGe(RANGE[2]);
        osd.coordinate_yLe(RANGE[3]);
        System.out.println("範囲内のショップ数" + osd.count());
        for (OrmaShopData sd : osd) {
            System.out.println(sd.shop_name);
            shop_list.add(new ShopDate(sd));
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

    public static ArrayList<ShopDate> getCategorysList(ArrayList<ShopDate> _list, ArrayList<String> str_list) {
        boolean check = false;
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        for (int i = 0; i < _list.size(); i++) {
            ShopDate _sd = _list.get(i);

            for (String _cate_name : _sd.CATEGORY) {
                if (str_list.contains(_cate_name)) {
                    check = true;
                } else {
                    check = false;
                }
                if (!check) {
                    break;
                }
            }
            if (check) {
                list.add(_sd);
            }
        }
        return list;
    }

    public static ArrayList<ShopDate> getSearchList(ArrayList<ShopDate> _list, String str) {
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
