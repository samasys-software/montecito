package com.montecito.samayu.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Preethiv on 1/15/2018.
 */
@Entity(tableName = "item_availablity_details")
public class ItemAvailabilityDTO {


    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    private String id;

    @ColumnInfo(name = "item")
    private String item;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "available")
    private String available;

    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @ColumnInfo(name = "availablePercent")
    private  String availablePercent;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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



    /* @Override
    public int compareTo(@NonNull Object o) {
        ItemAvailabilityDTO item = (ItemAvailabilityDTO) o;

        return  Integer.compare( Integer.parseInt(available.substring(0,available.length()-1)),Integer.parseInt(item.available.substring(0,item.available.length()-1)));
    }*/
}


