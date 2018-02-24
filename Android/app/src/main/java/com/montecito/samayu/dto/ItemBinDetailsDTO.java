package com.montecito.samayu.dto;

import java.util.Date;
import java.util.List;

/**
 * Created by NandhiniGovindasamy on 2/20/18.
 */

public class ItemBinDetailsDTO {
    private String _id, rfId, oldId, confDevice, confBy, uom, status;
    private Date updated, created;
    private int __v, capacity;
    private BinDTO crateBin;
    private String item;
    private ThresoldDTO thresold;
    private String currDevice;
    private String lastReading;
    private List<ReplenishmentsDTO> replenishments;


    public ReplenishmentTaskDTO getReplishmentTask() {
        return replishmentTask;
    }

    public void setReplishmentTask(ReplenishmentTaskDTO replishmentTask) {
        this.replishmentTask = replishmentTask;
    }

    private ReplenishmentTaskDTO replishmentTask;
    private boolean itemAlert, stockAlert, active;
    private List<DeviceHistoryDTO> deviceHistory;



    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getRfId() {
        return rfId;
    }

    public void setRfId(String rfId) {
        this.rfId = rfId;
    }

    public String getOldId() {
        return oldId;
    }

    public void setOldId(String oldId) {
        this.oldId = oldId;
    }

    public String getConfDevice() {
        return confDevice;
    }

    public void setConfDevice(String confDevice) {
        this.confDevice = confDevice;
    }

    public String getConfBy() {
        return confBy;
    }

    public void setConfBy(String confBy) {
        this.confBy = confBy;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BinDTO getCrateBin() {
        return crateBin;
    }

    public void setCrateBin(BinDTO crateBin) {
        this.crateBin = crateBin;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ThresoldDTO getThresold() {
        return thresold;
    }

    public void setThresold(ThresoldDTO thresold) {
        this.thresold = thresold;
    }

    public String getCurrDevice() {
        return currDevice;
    }

    public void setCurrDevice(String currDevice) {
        this.currDevice = currDevice;
    }

    public String getLastReading() {
        return lastReading;
    }

    public void setLastReading(String lastReading) {
        this.lastReading = lastReading;
    }

    public List<ReplenishmentsDTO> getReplenishments() {
        return replenishments;
    }

    public void setReplenishments(List<ReplenishmentsDTO> replenishments) {
        this.replenishments = replenishments;
    }

    public boolean isItemAlert() {
        return itemAlert;
    }

    public void setItemAlert(boolean itemAlert) {
        this.itemAlert = itemAlert;
    }

    public boolean isStockAlert() {
        return stockAlert;
    }

    public void setStockAlert(boolean stockAlert) {
        this.stockAlert = stockAlert;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<DeviceHistoryDTO> getDeviceHistory() {
        return deviceHistory;
    }

    public void setDeviceHistory(List<DeviceHistoryDTO> deviceHistory) {
        this.deviceHistory = deviceHistory;
    }
}





