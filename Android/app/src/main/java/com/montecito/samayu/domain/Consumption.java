package com.montecito.samayu.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by God on 1/25/2018.
 */
@Entity(tableName = "consumption_details")
public class Consumption {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long _id;


    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name = "usage")
    private String usage;

}