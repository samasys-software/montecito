package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "thresold")
public class ThresoldDTO {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String itemBinId;


    @ColumnInfo(name = "min")
    @Expose
    private float min;

    @ColumnInfo(name = "normal")
    @Expose
    private float normal;

    @ColumnInfo(name = "max")
    @Expose
    private float max;


    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getNormal() {
        return normal;
    }

    public void setNormal(float normal) {
        this.normal = normal;
    }

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    @NonNull
    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(@NonNull String itemBinId) {
        this.itemBinId = itemBinId;
    }


}