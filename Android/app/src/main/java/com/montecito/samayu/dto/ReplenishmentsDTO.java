package com.montecito.samayu.dto;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "replenishments")
public class ReplenishmentsDTO {
    @Expose
    private String _id;

    @Expose
    private String status;

    @Expose
    private String itemBin;

    @Expose
    private float quantity;

    @Expose
    private int __v;

    @Expose
    private ReplenishmentTaskDTO replenishTask;

    @Expose
    private Date created;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
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
    }
}
