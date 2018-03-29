package com.matriot.cbin.domain;

import com.google.gson.annotations.Expose;

/**
 * Created by NandhiniGovindasamy on 2/24/18.
 */

public class ChangePassword {
    @Expose
    private String oldPassword;
    @Expose
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
