package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "replenishment_task")
public class ReplenishmentTaskDTO {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    @Expose
    private String id;

    @Expose
    @ColumnInfo(name = "itemBin")
    private String itemBin;


    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @Expose
    @ColumnInfo(name = "replenishmentId")
    private String replenishmentId;

    @Expose
    @ColumnInfo(name = "status")
    private String status;

    @Expose
    @ColumnInfo(name = "v")
    @SerializedName("__v")
    private int v;

    @Expose
    @ColumnInfo(name = "trigger")
    private float trigger;

    @Expose
    @Ignore
    private Date updated;

    @ColumnInfo(name = "updated")
    private String updatedDate;

    @Expose
    @Ignore
    private Date created;

    @ColumnInfo(name = "created")
    private String createdDate;

    public String getItemBin() {
        return itemBin;
    }

    public void setItemBin(String itemBin) {
        this.itemBin = itemBin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTrigger() {
        return trigger;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setTrigger(float trigger) {
        this.trigger = trigger;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDate=df.format(updated);
        setUpdatedDate(formattedDate);
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String formattedDate=df.format(created);
        setCreatedDate(formattedDate);
    }

    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }

    public String getReplenishmentId() {
        return replenishmentId;
    }

    public void setReplenishmentId(String replenishmentId) {
        this.replenishmentId = replenishmentId;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;

        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            Date formattedDate = df.parse(updatedDate);
            setUpdated(formattedDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();

        }
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;

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
}
