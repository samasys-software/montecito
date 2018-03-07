package com.montecito.samayu.dto;

import android.arch.persistence.room.Entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "replenishment_task")
public class ReplenishmentTaskDTO {
    private String _id,itemBin,status;
    private int __v;
    private float trigger;
    private Date updated,created;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public void setTrigger(int trigger) {
        this.trigger = trigger;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
