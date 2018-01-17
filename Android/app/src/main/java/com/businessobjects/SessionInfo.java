package com.businessobjects;

import android.media.MediaCas;

/**
 * Created by Preethiv on 1/15/2018.
 */

public class SessionInfo {

    public static SessionInfo instance = new SessionInfo();

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

    private String token;
    private String firstName;
    private String lastName;

    private SessionInfo(){

    }
    public static SessionInfo instance(){
        return instance;
    }

    public static void destroy(){
        instance = new SessionInfo();
    }
}
