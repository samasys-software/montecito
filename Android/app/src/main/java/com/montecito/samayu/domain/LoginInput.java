package com.montecito.samayu.domain;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by NandhiniGovindasamy on 2/3/18.
 */

public class LoginInput implements Serializable{
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Expose
    private String email;
    @Expose
    private String password;

}
