package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by User on 2017/06/30.
 */
@Table
public class OrmaConfig {
    @Column(defaultExpr = "1",indexed = true)
    public long this_id;
    @Column(indexed = true)
    public int max_shop_search;
    @Column(indexed = true)
    public boolean isMemberRegist;
    @Column(defaultExpr = "name",indexed = true)
    public String user_name;
    @Column(defaultExpr =  "pass",indexed = true)
    public String user_pass;
    @Setter
    public OrmaConfig(long this_id,int max_shop_search,boolean isMemberRegist,String user_name,String user_pass){
        this.this_id = this_id;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.max_shop_search = max_shop_search;
        this.isMemberRegist = isMemberRegist;
    }
}
