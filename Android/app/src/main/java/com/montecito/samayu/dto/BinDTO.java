package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "bin")

public class BinDTO {

    @PrimaryKey(autoGenerate = true)
    private int sno;

    @ColumnInfo(name = "id")
    @SerializedName("_id")
    @NonNull
    @Expose
    private String id;

    @ColumnInfo(name = "itemBinId")
    private  String itemBinId;

    @ColumnInfo(name = "name")
    @Expose
    private String name;

    @ColumnInfo(name = "capacity")
    @Expose
    private String capacity;

    @ColumnInfo(name = "brand")
    @Expose
    private String brand;

    @Expose
    @Ignore
    private BinTypeDTO binType;

    @Ignore
    @Expose
    private BinDimensionDTO dimension;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BinTypeDTO getBinType() {
        return binType;
    }

    public void setBinType(BinTypeDTO binType) {
        this.binType = binType;
    }

    public BinDimensionDTO getDimension() {
        return dimension;
    }

    public void setDimension(BinDimensionDTO dimension) {
        this.dimension = dimension;
    }

    public  String getItemBinId() {
        return itemBinId;
    }

    public  void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

   /* @Override
    public int compareTo(@NonNull Object o) {
        BinDTO item = (BinDTO) o;
        return name.compareToIgnoreCase(item.getName());
    }*/
}
