package com.businessobjects;

import android.media.MediaCas;

import com.businessobjects.domain.UserLogin;

/**
 * Created by Preethiv on 1/15/2018.
 */

public class SessionInfo {

    public static SessionInfo instance = new SessionInfo();

    private SessionInfo(){

    }

    public static SessionInfo getInstance() {
        return instance;
    }



    public static void destroy(){
        instance = new SessionInfo();
    }

    private UserLogin userLogin;

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }
}
