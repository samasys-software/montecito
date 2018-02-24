package com.montecito.samayu.dto;

import android.support.annotation.NonNull;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
public class DeviceDTO implements Comparable{

    private String _id;
    private String slno;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    @Override
    public int compareTo(@NonNull Object o) {
        DeviceDTO item = (DeviceDTO) o;
        return location.compareToIgnoreCase(item.getLocation());

    }
}
