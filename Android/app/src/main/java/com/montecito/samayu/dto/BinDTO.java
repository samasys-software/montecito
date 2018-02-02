package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
public class BinDTO {

    private String _id;
    private String name;
    private String capacity;
    private String brand;

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
}
