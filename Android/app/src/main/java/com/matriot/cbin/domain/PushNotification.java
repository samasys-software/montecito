package com.matriot.cbin.domain;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 3/28/18.
 */

public class PushNotification {

    @Expose
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
