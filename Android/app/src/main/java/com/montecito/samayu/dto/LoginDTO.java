package com.montecito.samayu.dto;

/**
 * Created by Preethiv on 1/13/2018.
 */

public class LoginDTO extends MontecitoDTO {
    private String token;
    private String firstName;
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
