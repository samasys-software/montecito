package com.montecito.samayu.dto;

import android.arch.persistence.room.Entity;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

/**
  * Created by NandhiniGovindasamy on 2/20/18.
 */
@Entity(tableName = "item_bin_details")
public class ItemBinDetailsDTO {
    @Expose
    private String _id;

    @Expose
    private String rfId;

    @Expose
    private String oldId;

    @Expose
    private String confDevice;

    @Expose
    private String confBy;

    @Expose
    private String uom;

    @Expose
    private String status;

    @Expose
    private Date updated;

    @Expose
    private Date created;

    @Expose
    private int __v;

    @Expose
    private int capacity;

    @Expose
    private BinDTO crateBin;

    @Expose
    private ItemDTO item;

    @Expose
    private ThresoldDTO thresold;

    @Expose
    private DeviceDTO currDevice;

    @Expose
    private ReadingDTO lastReading;

    @Expose
    private List<ReplenishmentsDTO> replenishments;

    @Expose
    private ReplenishmentTaskDTO replenishTask;

    @Expose
    private boolean itemAlert;

    @Expose
    private boolean stockAlert;

    @Expose
    private boolean active;

    @Expose
    private List<DeviceHistoryDTO> deviceHistory;






    public ReplenishmentTaskDTO getReplenishTask() {
        return replenishTask;
    }

    public void setReplenishTask(ReplenishmentTaskDTO replenishTask) {
        this.replenishTask = replenishTask;
    }


    

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

    public ThresoldDTO getThresold() {
        return thresold;
    }

    public void setThresold(ThresoldDTO thresold) {
        this.thresold = thresold;
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

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public DeviceDTO getCurrDevice() {
        return currDevice;
    }

    public void setCurrDevice(DeviceDTO currDevice) {
        this.currDevice = currDevice;
    }

    public ReadingDTO getLastReading() {
        return lastReading;
    }

    public void setLastReading(ReadingDTO lastReading) {
        this.lastReading = lastReading;
    }
}





