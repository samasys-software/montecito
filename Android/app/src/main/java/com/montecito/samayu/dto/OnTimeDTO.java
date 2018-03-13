package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Entity(tableName = "onTime")

public class OnTimeDTO {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int sno;

    @Expose
    @ColumnInfo(name="total")
    private int total;

    @Expose
    @ColumnInfo(name="ontime")
    private int ontime;

    @Expose
    @ColumnInfo(name="percent")
    private String percent;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOntime() {
        return ontime;
    }

    public void setOntime(int ontime) {
        this.ontime = ontime;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    @NonNull
    public int getSno() {
        return sno;
    }

    public void setSno(@NonNull int sno) {
        this.sno = sno;
    }
}
