package com.montecito.samayu.dto;

import com.google.gson.annotations.Expose;

/**
 * Created by Preethiv on 1/13/2018.
 */

public class LoginDTO extends MontecitoDTO {
    @Expose
    private String token;
    @Expose
    private String firstName;
    @Expose
    private String lastName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
