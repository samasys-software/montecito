package com.montecito.samayu.dto;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "device_histroy")
public class DeviceHistoryDTO {

    @Expose
    private String _id;

    @Expose
    private String itemBin;

    @Expose
    private int __v;

    @Expose
    private Date created;

    @Expose
    private LastDeviceDTO lastDevice;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItemBin() {
        return itemBin;
    }

    public void setItemBin(String itemBin) {
        this.itemBin = itemBin;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public LastDeviceDTO getLastDevice() {
        return lastDevice;
    }

    public void setLastDevice(LastDeviceDTO lastDevice) {
        this.lastDevice = lastDevice;
    }
}
