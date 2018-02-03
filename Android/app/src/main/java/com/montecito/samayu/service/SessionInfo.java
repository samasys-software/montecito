package com.montecito.samayu.service;

import com.montecito.samayu.domain.UserLogin;
import com.montecito.samayu.dto.ItemBinDTO;

import java.util.List;

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

    private List<ItemBinDTO> itemBinDetails;


    public List<ItemBinDTO> getItemBinDetails() {
        return itemBinDetails;
    }

    public void setItemBinDetails(List<ItemBinDTO> itemBinDetails) {
        this.itemBinDetails = itemBinDetails;
    }
}
