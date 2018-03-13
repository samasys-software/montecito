package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Entity(tableName = "count")


public class CountDTO {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int sno;

    @Expose
    @ColumnInfo(name="count")
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @NonNull
    public int getSno() {
        return sno;
    }

    public void setSno(@NonNull int sno) {
        this.sno = sno;
    }
}
