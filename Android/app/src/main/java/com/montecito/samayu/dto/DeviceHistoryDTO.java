package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "device_histroy")
public class DeviceHistoryDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @Expose
    @NonNull
    private String id;

    @Expose
    @ColumnInfo(name = "itemBin")
    private String itemBin;


    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @Expose
    @SerializedName("__v")
    @ColumnInfo(name = "v")
    private int v;

    @Expose
    @Ignore
    private Date created;

    @ColumnInfo(name = "created")
    private String createdDate;

    @Expose
    @Ignore
    private LastDeviceDTO lastDevice;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getItemBin() {
        return itemBin;
    }

    public void setItemBin(String itemBin) {
        this.itemBin = itemBin;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Date getCreated() {
        if(created==null){
            try {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                Date formattedDate = df.parse(createdDate);
                setCreated(formattedDate);
            }
            catch (ParseException e)
            {
                e.printStackTrace();

            }
        }
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;

    }

    public LastDeviceDTO getLastDevice() {
        return lastDevice;
    }

    public void setLastDevice(LastDeviceDTO lastDevice) {
        this.lastDevice = lastDevice;
    }

    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }

    public String getCreatedDate() {
        if(createdDate==null){
            SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            String formattedDate=df.format(created);
            setCreatedDate(formattedDate);
        }
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;

    }
}
