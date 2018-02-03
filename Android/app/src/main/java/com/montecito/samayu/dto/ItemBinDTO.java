package com.montecito.samayu.dto;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */

public class ItemBinDTO {

    private String _id;
    private BinDTO crateBin;
    private ItemDTO item;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public DeviceDTO getCurrDevice() {
        return currDevice;
    }

    public void setCurrDevice(DeviceDTO currDevice) {
        this.currDevice = currDevice;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public ReadingDTO getLastReading() {
        return lastReading;
    }

    public void setLastReading(ReadingDTO lastReading) {
        this.lastReading = lastReading;
    }

    private DeviceDTO currDevice;
    private String uom;
    private String capacity;

    public ThresholdDTO getThreshold() {
        return threshold;
    }

    public void setThreshold(ThresholdDTO threshold) {
        this.threshold = threshold;
    }

    private ReadingDTO lastReading;
    private ThresholdDTO threshold;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

}
