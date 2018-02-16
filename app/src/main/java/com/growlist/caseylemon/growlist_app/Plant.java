package com.growlist.caseylemon.growlist_app;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by caseylemon on 2/16/18.
 */

@Entity(tableName = "plants")
public class Plant {
    @PrimaryKey
    private int p_id;

    @ColumnInfo(name = "name")
    private String plantName;

    @ColumnInfo(name = "genus")
    private String plantGenus;

    public int getP_id(){
        return p_id;
    }

    public void setP_id(int p_id){
        this.p_id = p_id;
    }

    public String getPlantName(){
        return plantName;
    }

    public void setPlantName(String plantName){
        this.plantName = plantName;
    }

    public String getPlantGenus(){
        return plantGenus;
    }

    public void setPlantGenus(String plantGenus){
        this.plantGenus = plantGenus;
    }
}
