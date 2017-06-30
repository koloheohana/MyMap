package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;



@Table
public class OrmaTork{
    @PrimaryKey(autoincrement = true)
    public long id;
    @Column(indexed = true)
    public String tork_sentence;
    @Column(indexed = true)
    public long user_id;
    @Column(indexed = true)
    public String clock;
    @Column(indexed = true)
    public String image_binary;
    @Column(indexed = true)
    public boolean image_switch;
    @Column(indexed = true)
    public boolean camera_picture;
    @Column(defaultExpr = "null",indexed = true)
    public long shop_id;
    @Column(defaultExpr = "null",indexed = true)
    public String image_uri;

    @Setter
    public OrmaTork(long id,String tork_sentence,long user_id,String image_binary,boolean image_switch,String clock,boolean camera_picture,String image_uri,long shop_id){
        this.clock = clock;
        this.tork_sentence = tork_sentence;
        this.user_id = user_id;
        this.image_binary = image_binary;
        this.image_switch = image_switch;
        this.camera_picture = camera_picture;
        this.image_uri = image_uri;
        this.shop_id = shop_id;
    }
}
