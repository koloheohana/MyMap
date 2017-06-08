package com.koloheohana.mymap.data;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by User on 2017/06/08.
 */

public class DbTork extends RealmObject {
    private int ID;
    private String tork;
    private String CLOCK;
    private String SHOP_DATA;
    private String IMAGE_BINARY;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTork() {
        return tork;
    }

    public void setTork(String tork) {
        this.tork = tork;
    }

    public String getCLOCK() {
        return CLOCK;
    }

    public void setCLOCK(String CLOCK) {
        this.CLOCK = CLOCK;
    }

    public String getSHOP_DATA() {
        return SHOP_DATA;
    }

    public void setSHOP_DATA(String SHOP_DATA) {
        this.SHOP_DATA = SHOP_DATA;
    }

    public String getIMAGE_BINARY() {
        return IMAGE_BINARY;
    }

    public void setIMAGE_BINARY(String IMAGE_BINARY) {
        this.IMAGE_BINARY = IMAGE_BINARY;
    }
}
