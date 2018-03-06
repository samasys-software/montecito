package com.montecito.samayu.dto;

import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */

public class DeviceHistoryDTO {
    private String _id,itemBin;
    private int __v;
    private Date created;
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
