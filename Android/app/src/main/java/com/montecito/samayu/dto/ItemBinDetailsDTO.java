package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/20/18.
 */

public class ItemBinDetailsDTO {
    private String _id, rfId, oldId, confDevice, confBy, uom, status, updated, created;
    private int __v, capacity;
    private BinDTO crateBin;
    private ItemDTO item;
    private ThresoldDTO thresold;
    private ReadingDTO lastReading;
    private boolean itemAlert, stockAlert, active;

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

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
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

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public ThresoldDTO getThresold() {
        return thresold;
    }

    public void setThresold(ThresoldDTO thresold) {
        this.thresold = thresold;
    }

    public ReadingDTO getLastReading() {
        return lastReading;
    }

    public void setLastReading(ReadingDTO lastReading) {
        this.lastReading = lastReading;
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
}