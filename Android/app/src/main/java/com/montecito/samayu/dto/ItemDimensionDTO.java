package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 3/6/18.
 */
@Entity(tableName = "item_dimension")
public class ItemDimensionDTO {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "itemId")
    private String itemId;

    @ColumnInfo(name = "dia")
    private int dia;

    @ColumnInfo(name = "length")
    private int length;

    @ColumnInfo(name = "uom")
    private String uom;

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
