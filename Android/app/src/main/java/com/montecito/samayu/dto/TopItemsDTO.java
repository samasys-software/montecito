package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Entity(tableName = "topItems")

public class TopItemsDTO {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int sno;

    @Expose
    @ColumnInfo(name="item")
    private  String item;

    @Expose
    @ColumnInfo(name="quantity")
    private float quantity;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    @NonNull
    public int getSno() {
        return sno;
    }

    public void setSno(@NonNull int sno) {
        this.sno = sno;
    }
}
