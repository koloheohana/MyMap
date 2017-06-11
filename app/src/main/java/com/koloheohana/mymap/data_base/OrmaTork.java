package com.koloheohana.mymap.data_base;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;



@Table
public class OrmaTork{
    @PrimaryKey(auto = false)
    public long id;
    @Column(indexed = true)
    public String tork_sentence;
    @Column(indexed = true)
    public String user_id;
    @Column(indexed = true)
    public String image_binary;
    @Column(indexed = true)
    public boolean image_switch;
    @Setter
    public OrmaTork(long id,String tork_sentence,String user_id,String image_binary,boolean image_switch){
        this.id = id;
        this.tork_sentence = tork_sentence;
        this.user_id = user_id;
        this.image_binary = image_binary;
        this.image_switch = image_switch;
    }
}
