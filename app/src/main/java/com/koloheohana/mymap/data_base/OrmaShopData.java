package com.koloheohana.mymap.data_base;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

import java.util.List;

/**
 * Created by User on 2017/06/20.
 */
@Table
public class OrmaShopData {
    @PrimaryKey(auto = false)
    public long id;
    @Column(indexed = true)
    public String shop_name;
    @Column(indexed = true)
    public List<String> shop_category_list;
    @Nullable
    @Column(indexed = true)
    public String shop_tel;
    @Nullable
    @Column(indexed = true)
    public String shop_homepage;
    @Nullable
    @Column(indexed = true)
    public String shop_icon;
    @Column(defaultExpr = "1",indexed = true)
    public String addrres;
    @Column(defaultExpr = "2",indexed = true)
    public String shop_postal;
    @Column(indexed = true)
    public long coordinate_x;
    @Column(indexed = true)
    public long coordinate_y;
    @Column(defaultExpr = "1",indexed =  true)
    public boolean bookmark;

    @Setter
    public OrmaShopData(long id,String shop_name,List<String> shop_category_list,String shop_tel,String shop_homepage,String shop_icon,String shop_postal,String addrres,long coordinate_x,long coordinate_y,boolean bookmark){
        this.id = id;
        this.shop_name = shop_name;
        this.shop_category_list = shop_category_list;
        this.shop_tel = shop_tel;
        this.shop_homepage = shop_homepage;
        this.shop_icon = shop_icon;
        this.addrres = addrres;
        this.shop_postal = shop_postal;
        this.coordinate_x = coordinate_x;
        this.coordinate_y = coordinate_y;
        this.bookmark = bookmark;
    }

}
