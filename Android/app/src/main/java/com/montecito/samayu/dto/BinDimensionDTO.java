package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "bin_dimension")

public class BinDimensionDTO {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name="binId")
    private String binId;

    @Expose
    @ColumnInfo(name = "length")
    private int length;

    @Expose
    @ColumnInfo(name = "width")
    private int width;

    @Expose
    @ColumnInfo(name = "height")
    private int height;


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }
}