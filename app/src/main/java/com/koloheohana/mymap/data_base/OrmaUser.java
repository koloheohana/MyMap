package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

import java.util.List;

@Table
public class OrmaUser {
    @PrimaryKey(auto = false)
    public long id;
    @Column(indexed = true,defaultExpr = "1")
    public String device_id;
    @Column(indexed = true)
    public String user_name;
    @Column(indexed = true)
    public int user_age;
    @Column(indexed = true)
    public String user_icon;
    @Column(defaultExpr = "1",indexed = true)
    public String addrres;
    @Column(defaultExpr = "comment")
    public String comment;
    @Setter
    public OrmaUser(long id,String device_id,String user_name,int user_age,String user_icon,String addrres,String comment){
        this.id = id;
        this.device_id = device_id;
        this.user_name = user_name;
        this.user_age = user_age;
        this.user_icon = user_icon;
        this.addrres = addrres;
        this.comment = comment;
    }
}
