package com.matriot.cbin.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/28/18.
 */

public class RegisterPushNotificationDTO {
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
