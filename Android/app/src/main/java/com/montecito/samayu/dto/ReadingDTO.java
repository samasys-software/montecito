package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

public class ReadingDTO {

    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ReadingValueDTO getReading() {
        return reading;
    }

    public void setReading(ReadingValueDTO reading) {
        this.reading = reading;
    }

    private ReadingValueDTO reading;
}

