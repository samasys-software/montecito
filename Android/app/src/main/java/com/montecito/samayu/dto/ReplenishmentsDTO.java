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
@Entity(tableName = "replenishments")
public class ReplenishmentsDTO {
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    @Expose
    private String id;

    @Expose
    @ColumnInfo(name = "status")
    private String status;

    @Expose
    @ColumnInfo(name = "itemBin")
    private String itemBin;


    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @Expose
    @ColumnInfo(name = "quantity")
    private float quantity;

    @Expose
    @ColumnInfo(name = "v")
    @SerializedName("__v")
    private int v;

    @Expose
    @Ignore
    private ReplenishmentTaskDTO replenishTask;

    @Expose
    @Ignore
    private Date created;

    @ColumnInfo(name = "created")
    private String createdDate;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItemBin() {
        return itemBin;
    }

    public void setItemBin(String itemBin) {
        this.itemBin = itemBin;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }


    public ReplenishmentTaskDTO getReplenishTask() {
        return replenishTask;
    }

    public void setReplenishTask(ReplenishmentTaskDTO replenishTask) {
        this.replenishTask = replenishTask;
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

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
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
