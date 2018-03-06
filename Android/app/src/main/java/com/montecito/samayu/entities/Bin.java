package com.montecito.samayu.entities;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "bin_details")

public class Bin{

    private String _id;
    private String name;
    private String capacity;
    private String brand;
    private BinTypeDTO binType;
    private DimensionDTO dimension;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public DimensionDTO getDimension() {
        return dimension;
    }

    public void setDimension(DimensionDTO dimension) {
        this.dimension = dimension;
    }

    /*@Override
    public int compareTo(@NonNull Object o) {
        BinDTO item = (BinDTO) o;
        return name.compareToIgnoreCase(item.getName());
    }*/
}
