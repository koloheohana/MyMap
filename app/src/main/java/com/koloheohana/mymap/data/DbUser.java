package com.koloheohana.mymap.data;

import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.koloheohana.mymap.menutab.Tork;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by User on 2017/06/08.
 */

public class DbUser extends RealmObject{
    @PrimaryKey
    private int ID;
    private String NAME;
    private String COMMENT;
    private String ICON_BINARY;
    private RealmList<DbTork> TORK;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
    }

    public String getICON_BINARY() {
        return ICON_BINARY;
    }

    public void setICON_BINARY(String ICON_BINARY) {
        this.ICON_BINARY = ICON_BINARY;
    }

    public RealmList<DbTork> getTORK() {
        return TORK;
    }

    public void setTORK(RealmList<DbTork> TORK) {
        this.TORK = TORK;
    }
}
