package com.montecito.samayu.service;

import com.montecito.samayu.dto.UserLoginDTO;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.UserProfileDTO;

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

    public UserProfileDTO getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        this.userProfile = userProfile;
    }

    private UserProfileDTO userProfile;

    private UserLoginDTO userLogin;

    public UserLoginDTO getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLoginDTO userLogin) {
        this.userLogin = userLogin;
    }

    private List<ItemBinDTO> itemBinDetails;


    public List<ItemBinDTO> getItemBinDetails() {
        return itemBinDetails;
    }

    public void setItemBinDetails(List<ItemBinDTO> itemBinDetails) {
        this.itemBinDetails = itemBinDetails;
    }

    public String getCurrentItemBinId() {
        return currentItemBinId;
    }

    public void setCurrentItemBinId(String currentItemBinId) {
        this.currentItemBinId = currentItemBinId;
    }

    private String currentItemBinId;

    private ItemBinDetailsDTO currenItemBinDetails;

    public ItemBinDetailsDTO getCurrenItemBinDetails() {
        return currenItemBinDetails;
    }

    public void setCurrenItemBinDetails(ItemBinDetailsDTO currenItemBinDetails) {
        this.currenItemBinDetails = currenItemBinDetails;
    }

    private List<ItemAvailabilityDTO> myReplenishmentTask;

    public List<ItemAvailabilityDTO> getMyReplenishmentTask() {
        return myReplenishmentTask;
    }

    public void setMyReplenishmentTask(List<ItemAvailabilityDTO > myReplenishmentTask) {
        this.myReplenishmentTask = myReplenishmentTask;
    }
}
