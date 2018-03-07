package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by God on 1/25/2018.
 */
@Entity(tableName = "consumption_details")
public class ConsumptionDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    private String id;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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