package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

public class ThresoldDTO {

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

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    private float max;
}