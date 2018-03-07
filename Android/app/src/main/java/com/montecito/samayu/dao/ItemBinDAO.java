package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.BinDTO;
import com.montecito.samayu.dto.BinDimensionDTO;
import com.montecito.samayu.dto.BinTypeDTO;
import com.montecito.samayu.dto.DeviceDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemDTO;
import com.montecito.samayu.dto.ItemDimensionDTO;
import com.montecito.samayu.dto.ReadingDTO;
import com.montecito.samayu.dto.ReadingValueDTO;
import com.montecito.samayu.dto.ThresoldDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/7/18.
 */
@Dao

public abstract class ItemBinDAO {
    @Insert
    public abstract void inserItemBin(List<ItemBinDTO> itemBins);

    @Insert
    public abstract void insertBin(BinDTO bins);

    @Insert
    public abstract void insertBinType(BinTypeDTO binType);


    @Insert
    public abstract void insertBinDimension(BinDimensionDTO binDimension);


    @Insert
    public abstract void insertItem(ItemDTO items);

    @Insert
    public abstract void insertItemDimension(ItemDimensionDTO itemDimension);

    @Insert
    public abstract void insertDevice(DeviceDTO device);

    @Insert
    public abstract void insertReading(ReadingDTO reading);

    @Insert
    public abstract void insertReadingValue(ReadingValueDTO readingValue);

    @Insert
    public abstract void insertThresold(ThresoldDTO thresold);

    @Query("SELECT * FROM item_bins")
    public abstract List<ItemBinDTO> getAll();

    @Query("SELECT * FROM bin WHERE itemBinId =:itemBinId")
    public abstract BinDTO getBinDetails(String itemBinId);

    @Query("SELECT * FROM bin_type WHERE binId =:binId")
    public abstract BinTypeDTO getBinTypeDetails(String binId);

    @Query("SELECT * FROM bin_dimension WHERE binId =:binId")
    public abstract BinDimensionDTO getBinDimensionDetails(String binId);

    @Query("SELECT * FROM item WHERE itemBinId =:itemBinId")
    public abstract ItemDTO getItemDetails(String itemBinId);

    @Query("SELECT * FROM item_dimension WHERE itemId =:itemId")
    public abstract ItemDimensionDTO getItemDimensionDetails(String itemId);

    @Query("SELECT * FROM device WHERE itemBinId =:itemBinId")
    public abstract DeviceDTO getDeviceDetails(String itemBinId);

    @Query("SELECT * FROM reading WHERE itemBinId =:itemBinId")
    public abstract ReadingDTO getReadingDetails(String itemBinId);

    @Query("SELECT * FROM reading_value WHERE readingId =:readingId")
    public abstract ReadingValueDTO getReadingValueDetails(String readingId);

    @Query("SELECT * FROM thresold WHERE itemBinId =:itemBinId")
    public abstract ThresoldDTO getThresoldDetails(String itemBinId);

    public void insertAll(List<ItemBinDTO> itemBins) {
        for (ItemBinDTO itemBin :itemBins) {

            BinDTO bin= itemBin.getCrateBin();
            bin.setItemBinId(itemBin.getId());
            insertBin(bin);

            BinDimensionDTO binDimension=bin.getDimension();
            binDimension.setBinId(bin.getId());
            insertBinDimension(binDimension);

            BinTypeDTO binType=bin.getBinType();
            binType.setBinId(bin.getId());
            insertBinType(binType);

            ItemDTO item=itemBin.getItem();
            item.setItemBinId(itemBin.getId());
            insertItem(item);

            ItemDimensionDTO itemDimension=item.getDimension();
            itemDimension.setItemId(item.getId());
            insertItemDimension(itemDimension);

            DeviceDTO device=itemBin.getCurrDevice();
            device.setItemBinId(item.getId());
            insertDevice(device);

            ReadingDTO reading=itemBin.getLastReading();
            reading.setItemBinId(item.getId());
            insertReading(reading);

            ReadingValueDTO readingValue=reading.getReading();
            readingValue.setReadingId(reading.getId());
            insertReadingValue(readingValue);

            ThresoldDTO thresold=itemBin.getThresold();
            thresold.setItemBinId(item.getId());
            insertThresold(thresold);

        }

        inserItemBin(itemBins);
    }

    public List<ItemBinDTO> getAllItemBins() {
        List<ItemBinDTO> itemBins = getAll();
        for(ItemBinDTO itemBin:itemBins)
        {
            itemBin.setCrateBin(getAllBins(itemBin.getId()));
            itemBin.setItem(getAllItems(itemBin.getId()));
            itemBin.setCurrDevice(getDeviceDetails(itemBin.getId()));
            itemBin.setLastReading(getAllReadings(itemBin.getId()));
            itemBin.setThresold(getThresoldDetails(itemBin.getId()));
        }

        return itemBins;
    }

    public BinDTO getAllBins(String ItemBinId) {
        BinDTO bins = getBinDetails(ItemBinId);
        bins.setBinType(getBinTypeDetails(bins.getId()));
        bins.setDimension(getBinDimensionDetails(bins.getId()));

        return bins;
    }

    public ItemDTO getAllItems(String ItemBinId) {
        ItemDTO items = getItemDetails(ItemBinId);
        items.setDimension(getItemDimensionDetails(items.getId()));
        return items;
    }

    public ReadingDTO getAllReadings(String ItemBinId) {
        ReadingDTO readings = getReadingDetails(ItemBinId);
        readings.setReading(getReadingValueDetails(readings.getId()));
        return readings;
    }

}
