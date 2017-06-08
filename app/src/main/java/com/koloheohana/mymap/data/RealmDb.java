package com.koloheohana.mymap.data;

import io.realm.RealmObject;

/**
 * Created by User on 2017/06/08.
 */

public class RealmDb extends RealmObject{
    private int id;
    private String name;
    private String comment;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
