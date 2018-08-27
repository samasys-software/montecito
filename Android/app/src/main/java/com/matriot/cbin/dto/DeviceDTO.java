package com.matriot.cbin.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

@Entity(tableName = "device")
public class DeviceDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    @Expose
    private String id;

    @ColumnInfo(name = "itemBinId")
    @Expose
    private String itemBinId;

    @ColumnInfo(name = "slno")
    @Expose
    private String slno;

    @ColumnInfo(name = "name")
    @Expose
    private String name;

    @ColumnInfo(name = "location")
    @Expose
    private String location;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getSlno() {
        return slno;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public  String getItemBinId() {
        return itemBinId;
    }

    public  void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }


    /*@Override
    public int compareTo(@NonNull Object o) {
        DeviceDTO item = (DeviceDTO) o;
        return location.compareToIgnoreCase(item.getLocation());

    }
    */
}
