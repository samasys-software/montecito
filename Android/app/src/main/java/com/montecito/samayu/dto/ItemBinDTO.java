package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "item_bins")

public class ItemBinDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    private String id;

    @Ignore
    private BinDTO crateBin;

    @Ignore
    private ItemDTO item;

    @Ignore
    private DeviceDTO currDevice;

    @ColumnInfo(name = "uom")
    private String uom;

    @ColumnInfo(name = "capacity")
    private float capacity;

    @Ignore
    private ReadingDTO lastReading;

    @Ignore
    private ThresoldDTO thresold;

    @ColumnInfo(name = "status")
    private String status;

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
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


    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public ReadingDTO getLastReading() {
        return lastReading;
    }

    public void setLastReading(ReadingDTO lastReading) {
        this.lastReading = lastReading;
    }



    public ThresoldDTO getThresold() {
        return thresold;
    }

    public void setThresold(ThresoldDTO thresold) {
        this.thresold = thresold;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


   /* @Override
    public int compareTo(@NonNull Object o) {
        ItemBinDTO item = (ItemBinDTO) o;
        return crateBin.getName().compareToIgnoreCase(item.crateBin.getName());

    }
*/


}
