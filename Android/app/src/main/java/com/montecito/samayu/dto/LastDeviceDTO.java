package com.montecito.samayu.dto;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/6/18.
 */
@Entity(tableName = "last_device")
public class LastDeviceDTO {
    @Expose
    private String _id;

    @Expose
    private String slno;

    @Expose
    private String location;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
