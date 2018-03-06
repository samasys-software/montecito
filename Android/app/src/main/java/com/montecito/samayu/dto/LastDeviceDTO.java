package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 3/6/18.
 */

public class LastDeviceDTO {
    private String _id;
    private String slno;
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
