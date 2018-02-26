package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

public class ThresoldDTO {

    private float min;
    private float normal;

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getNormal() {
        return normal;
    }

    public void setNormal(float normal) {
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