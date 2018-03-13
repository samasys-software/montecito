package com.montecito.samayu.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */

public class CountDTO {
    @Expose
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
