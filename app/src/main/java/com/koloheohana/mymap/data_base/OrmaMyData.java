package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;
import com.koloheohana.mymap.R;

/**
 * Created by User on 2017/06/30.
 */
@Table
public class OrmaMyData {
    @PrimaryKey(auto = false)
    public long id;
    @Column
    public String user_name;
    @Column
    public String user_tel;
    @Column
    public String user_addrres;
    @Column(defaultExpr = "1")
    public int user_icon;
    @Setter
    public OrmaMyData(long id,String user_name,String user_tel,String user_addrres,int user_icon){
        this.id = id;
        this.user_name = user_name;
        this. user_tel = user_tel;
        this.user_addrres = user_addrres;
        this.user_icon = user_icon;
    }
}
