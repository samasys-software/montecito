package com.montecito.samayu.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */

public class OnTimeDTO {

    @Expose
    private int total;

    @Expose
    private int ontime;

    @Expose
    private String percent;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOntime() {
        return ontime;
    }

    public void setOntime(int ontime) {
        this.ontime = ontime;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
