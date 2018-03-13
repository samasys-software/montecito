package com.montecito.samayu.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */

public class AverageDTO {

    @Expose
    private String average;

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }
}
