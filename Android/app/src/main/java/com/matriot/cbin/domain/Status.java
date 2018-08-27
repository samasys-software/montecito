package com.matriot.cbin.domain;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 2/24/18.
 */

public class Status {
    @Expose
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
