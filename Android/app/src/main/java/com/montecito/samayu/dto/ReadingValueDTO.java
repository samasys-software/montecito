package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "reading_value")
public class ReadingValueDTO {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String readingId;

    @ColumnInfo(name = "weight")
    private float weight;

    @ColumnInfo(name = "status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @NonNull
    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(@NonNull String readingId) {
        this.readingId = readingId;
    }
}