package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Entity(tableName = "average")

public class AverageDTO {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int sno;

    @Expose
    @ColumnInfo(name="average")
    private String average;

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    @NonNull
    public int getSno() {
        return sno;
    }

    public void setSno(@NonNull int sno) {
        this.sno = sno;
    }
}
