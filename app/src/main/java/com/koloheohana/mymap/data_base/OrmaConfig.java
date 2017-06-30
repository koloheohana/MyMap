package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by User on 2017/06/30.
 */
@Table
public class OrmaConfig {
    @Column
    public int max_shop_search;
    @Setter
    public OrmaConfig(int max_shop_search){
        this.max_shop_search = max_shop_search;
    }
}
