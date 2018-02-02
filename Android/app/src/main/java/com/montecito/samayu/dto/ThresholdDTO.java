package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

public class ThresholdDTO {

    private String min;
    private String normal;

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getNormal() {
        return normal;
    }

    public void setNormal(String normal) {
        this.normal = normal;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    private String max;
}