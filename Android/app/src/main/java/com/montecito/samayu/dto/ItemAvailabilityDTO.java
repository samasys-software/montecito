package com.montecito.samayu.dto;

import android.support.annotation.NonNull;

/**
 * Created by Preethiv on 1/15/2018.
 */

public class ItemAvailabilityDTO implements  Comparable{

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    private String _id;
    private String item;
    private String location;
    private String status;
    private String available,itemBinId,availablePercent;


    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }

    public String getAvailablePercent() {
        return availablePercent;
    }

    public void setAvailablePercent(String availablePercent) {
        this.availablePercent = availablePercent;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        ItemAvailabilityDTO item = (ItemAvailabilityDTO) o;

        return  Integer.compare( Integer.parseInt(available.substring(0,available.length()-1)),Integer.parseInt(item.available.substring(0,item.available.length()-1)));
    }
}
