package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

import java.util.List;

/**
 * Created by User on 2017/06/28.
 */
@Table
public class OrmaShopMemo {
    @PrimaryKey(auto = false)
    public long id;
    @Column(indexed = true)
    public List<String> memo_list;
    @Column(indexed = true)
    public long shop_id;
    @Column(defaultExpr = "time",indexed = true)
    public String time;
    @Setter
    public OrmaShopMemo(long id ,List<String> memo_list,long shop_id,String time){
        this.id = id;
        this.memo_list = memo_list;
        this. shop_id = shop_id;
        this.time = time;
    }
}
