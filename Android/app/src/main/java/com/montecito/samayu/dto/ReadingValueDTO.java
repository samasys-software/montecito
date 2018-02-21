package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
public class ReadingValueDTO {
    private float weight;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}