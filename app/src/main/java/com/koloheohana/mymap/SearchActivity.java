package com.koloheohana.mymap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.koloheohana.mymap.adapter.SearchAdapter;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.map.ShopDate;
import com.koloheohana.mymap.map.ShopSearch;
import com.koloheohana.mymap.user_date.UserAdapter;
import com.koloheohana.mymap.user_date.UserList;
import com.koloheohana.mymap.util.Scene;
import com.koloheohana.mymap.util.Util;

import java.util.ArrayList;

/**
 * Created by User on 2017/12/27.
 */

public class SearchActivity extends AppCompatActivity {
    private SearchView mSearchView;
    public static SearchActivity ME;

    @Override
    public void onCreate(Bundle save) {
        super.onCreate(save);
        Scene.set(this);
        setContentView(R.layout.activity_search);
        ME = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.search_item);
        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.search).getActionView();
        Menu menu = toolbar.getMenu();
        MenuItem item = menu.add("検索");
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        item.setActionView(mSearchView);
        mSearchView.setIconified(false);

        mSearchView.setQueryHint("店舗名を入力して下さい");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.isEmpty()) {
                    return false;
                }
                LIST.clear();
                changeSearchResultText(s, LIST.size());
                System.out.println("test:" + s);
                ArrayList<ShopDate> list = search(s);
                System.out.println();
                for (ShopDate sd : list) {
                    LIST.add(sd);
                }
                changeSearchResultText(s, LIST.size());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
/*                if (s.length() <= 1) {
                    return false;
                }
                System.out.println("test:" + s);
                search(s);*/
                return false;
            }
        });
        ListView myList = (ListView) findViewById(R.id.myListView);
        SearchAdapter adapter = new SearchAdapter(SearchActivity.ME, 0, LIST);
        myList.setAdapter(adapter);

    }

    public void changeSearchResultText(String search_text, int value) {
        TextView tv = (TextView) findViewById(R.id.search_text);
        tv.setText(search_text + "の検索結果数は" + value + "件です");
    }

    public ArrayList<ShopDate> LIST = new ArrayList<ShopDate>();

    public ArrayList<ShopDate> search(String str, ArrayList<ShopDate> list) {
        return ShopSearch.getSearchShopList(this, str);
    }

    public ArrayList<ShopDate> search(String str) {
        char[] c = str.toCharArray();
        String[] search_text = str.split("");
        ArrayList<ShopDate> list = new ArrayList<ShopDate>();
        ArrayList<ShopDate> list2 = new ArrayList<ShopDate>();
        boolean first = true;
        for (char c1 : c) {
            int count = countUp(c,c1);
            int target_count = 0;
            if(String.valueOf(c1).isEmpty()){
                continue;
            }
            ArrayList<ShopDate> _list = new ArrayList<ShopDate>();
            ArrayList<ShopDate> _list2 = new ArrayList<ShopDate>();
            if(first){
                _list = ShopSearch.getSearchShopList(this, String.valueOf(c1));
                if (Util.isHiragana(c1)) {
                    _list2 = ShopSearch.getSearchShopList(this, Util.zenkakuHiraganaToZenkakuKatakana(String.valueOf(c1)));
                } else if (Util.isKatanaka(c1)) {
                    _list2 = ShopSearch.getSearchShopList(this, Util.katakanaToHiragana(String.valueOf(c1)));
                }
                first = false;
            }else{
                _list = ShopSearch.search( String.valueOf(c1),list);
                if (Util.isHiragana(c1)) {
                    _list2 = ShopSearch.search(Util.zenkakuHiraganaToZenkakuKatakana(String.valueOf(c1)),list);
                } else if (Util.isKatanaka(c1)) {
                    _list2 = ShopSearch.search( Util.katakanaToHiragana(String.valueOf(c1)),list);
                }
            }
            list.clear();
            for(ShopDate sd : _list){
                target_count += countUp(sd.getShopName().toCharArray(),c1);
                System.out.println("元カウント"+count+"ターゲットカウント"+target_count);
                if(!list.contains(sd)) {
                    if(count <= target_count) {
                        list.add(sd);
                    }
                }
                target_count = 0;
            }
            for(ShopDate sd : _list2){
                target_count += countUp(sd.getShopName().toCharArray(),c1);
                System.out.println("元カウント"+count+"ターゲットカウント"+target_count);

                if(!list.contains(sd)) {
                    if(count <= target_count) {
                        list.add(sd);
                    }
                }
                target_count = 0;
            }
            System.out.println("ショップのサイズ"+list.size());
        }
        System.out.println("ショップのサイズ２：　" + list.size());

        if (list.isEmpty()) {
            return list;
        }
        return list;
    }
    public int countUp(char c[],char target){
        int i = 0;
        for(char c1:c){
            if(c1 == target){
                i++;
            }else if(c1 == Util.changeHiraKana(target)){
                i++;
            }

        }
        return i;
    }
    public ArrayList<ShopDate> testSearch(String str,ArrayList<ShopDate> list,char[] c){
        boolean first = false;
        for(char _c : c){
            if(!first){
                first = true;
                continue;
            }
            for(ShopDate sd:list){

            }
        }
        return list;
    }
}
