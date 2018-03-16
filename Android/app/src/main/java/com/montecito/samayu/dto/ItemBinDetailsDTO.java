package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
  * Created by NandhiniGovindasamy on 2/20/18.
 */
@Entity(tableName = "item_bin_details")
public class ItemBinDetailsDTO {

        @Expose
        @PrimaryKey(autoGenerate = false)
        @SerializedName("_id")
        @NonNull
        private String id;


        @Expose
        @ColumnInfo(name = "rfId")

        private String rfId;

        @ColumnInfo(name = "oldId")
        @Expose
        private String oldId;

        @Expose
        @ColumnInfo(name = "confDevice")
        private String confDevice;

        @Expose
        @ColumnInfo(name = "confBy")
        private String confBy;

        @Expose
        @ColumnInfo(name = "uom")
        private String uom;

        @Expose
        @ColumnInfo(name = "status")
        private String status;

        @Expose
        @Ignore
        private Date updated;

        @ColumnInfo(name = "updated")
        private String updatedDate;


        @Ignore
        @Expose

        private Date created;


        @ColumnInfo(name = "created")
        private String createdDate;

        @Expose
        @ColumnInfo(name = "v")
        @SerializedName("__v")
        private int v;

        @Expose
        @ColumnInfo(name = "capacity")
        private int capacity;

        @Expose
        @Ignore
        private BinDTO crateBin;

        @Expose
        @Ignore
        private ItemDTO item;

        @Expose
        @Ignore
        private ThresoldDTO thresold;

        @Expose
        @Ignore
        private DeviceDTO currDevice;

        @Expose
        @Ignore
        private ReadingDTO lastReading;

        @Expose
        @Ignore
        private List<ReplenishmentsDTO> replenishments;

        @Expose
        @Ignore
        private ReplenishmentTaskDTO replenishTask;

        @Expose
        @ColumnInfo(name = "itemAlert")
        private boolean itemAlert;

        @Expose
        @ColumnInfo(name = "stockAlert")
        private boolean stockAlert;

        @Expose
        @ColumnInfo(name = "active")
        private boolean active;

        @Expose
        @Ignore
        private List<DeviceHistoryDTO> deviceHistory;






        public ReplenishmentTaskDTO getReplenishTask() {
            return replenishTask;
        }

        public void setReplenishTask(ReplenishmentTaskDTO replenishTask) {
            this.replenishTask = replenishTask;
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





