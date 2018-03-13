package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 3/6/18.
 */
@Entity(tableName = "last_device")
public class LastDeviceDTO {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @Expose
    @NonNull
    private String id;

    @Expose
    @ColumnInfo(name = "slno")
    private String slno;

    @Expose
    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "deviceHistroyId")
    private String deviceHistroyId;


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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeviceHistroyId() {
        return deviceHistroyId;
    }

    public void setDeviceHistroyId(String deviceHistroyId) {
        this.deviceHistroyId = deviceHistroyId;
    }
}
