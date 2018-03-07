package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "reading")
public class ReadingDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    private String id;

    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @Ignore
    private ReadingValueDTO reading;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }

    public ReadingValueDTO getReading() {
        return reading;
    }

    public void setReading(ReadingValueDTO reading) {
        this.reading = reading;
    }


}

