package com.montecito.samayu.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */

public class TopItemsDTO {

    @Expose
    private  String item;

    @Expose
    private float quantity;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
