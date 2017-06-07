package com.koloheohana.mymap.data;

import android.support.annotation.Nullable;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Created by User on 2017/06/07.
 */
@Table
public class TodoM {
    @PrimaryKey(autoincrement = true)
    public final long id;

    @Column(indexed = true)
    public final String title;

    @Column
    @Nullable
    public final String content;

    @Column(indexed = true)
    public final boolean done;

    @Setter
    public TodoM(long id, String title, String content, boolean done) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.done = done;

    }

}
