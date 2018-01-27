package com.montecito.samayu.domain;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.montecito.samayu.dto.ItemAvailabilityDTO;

/**
 * Created by God on 1/26/2018.
 */

//@Entity(tableName = "item_availablity_details")
public class ItemAvailablity implements Comparable
{
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
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

    //@PrimaryKey(autoGenerate = true)
    //@NonNull
    private long _id;

    //@ColumnInfo(name = "item")
    private String item;

    //@ColumnInfo(name = "location")
    private String location;

  //  @ColumnInfo(name = "status")
    private String status;

   // @ColumnInfo(name = "available")
    private String available;

    @Override
    public int compareTo(@NonNull Object o) {
        ItemAvailabilityDTO item = (ItemAvailabilityDTO) o;

        return  Integer.compare( Integer.parseInt(available.substring(0,available.length()-1)),Integer.parseInt(item.getAvailable().substring(0,item.getAvailable().length()-1)));
    }
}
