package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertBin(BinDTO bins);

  /*  @Insert
    public abstract void insertBinType(BinTypeDTO binType);
*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertBinDimension(BinDimensionDTO binDimension);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertItem(ItemDTO items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertItemDimension(ItemDimensionDTO itemDimension);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDevice(DeviceDTO device);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReading(ReadingDTO reading);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReadingValue(ReadingValueDTO readingValue);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertThresold(ThresoldDTO thresold);

    @Query("SELECT * FROM item_bins")
    public abstract List<ItemBinDTO> getAll();

    @Query("SELECT * FROM bin WHERE itemBinId =:itemBinId")
    public abstract BinDTO getBinDetails(String itemBinId);

    /*@Query("SELECT * FROM bin_type WHERE binId =:binId")
    public abstract BinTypeDTO getBinTypeDetails(String binId);*/

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

    @Query("DELETE FROM item_bins")
    public  abstract void deleteAllIB();

    @Query("DELETE FROM bin")
    public  abstract void deleteAllBin();

//    @Query("DELETE FROM bin_dimension")
//    public  abstract void deleteAllBD();

    @Query("DELETE FROM item")
    public  abstract void deleteAllItem();

//    @Query("DELETE FROM item_dimension")
//    public  abstract void deleteAllID();

    @Query("DELETE FROM device")
    public  abstract void deleteAllDevice();

    @Query("DELETE FROM reading")
    public  abstract void deleteAllReading();

    @Query("DELETE FROM reading_value")
    public  abstract void deleteAllReadValue();

    @Query("DELETE FROM thresold")
    public  abstract void deleteAllThresold();



    public void insertAll(List<ItemBinDTO> itemBins) {
        for (ItemBinDTO itemBin :itemBins) {
            String itemBinId=itemBin.getId();

            BinDTO bin= itemBin.getCrateBin();

            bin.setItemBinId(itemBin.getId());

            insertBin(bin);
            System.out.println("Bin Inserted Successfully");

            System.out.println("bin"+bin.getId());

            BinDimensionDTO binDimension=bin.getDimension();
            binDimension.setBinId(bin.getId());
            insertBinDimension(binDimension);
            System.out.println("Dimension Inserted Successfully");

           // System.out.println("bin"+bin.getBinType().getId());

           /* BinTypeDTO binType=bin.getBinType();
            binType.setBinId(bin.getId());
            insertBinType(binType);*/

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
            reading.setItemBinId(itemBin.getId());
            insertReading(reading);

            ReadingValueDTO readingValue=reading.getReading();
            readingValue.setReadingId(reading.getId());
            insertReadingValue(readingValue);

            ThresoldDTO thresold=itemBin.getThresold();
            thresold.setItemBinId(itemBin.getId());
            insertThresold(thresold);

        }

        inserItemBin(itemBins);
    }

    public List<ItemBinDTO> getAllItemBins() {
        List<ItemBinDTO> itemBins = getAll();
        System.out.println("ItemBin Fetched Successfully");
        System.out.println("ItemBin size"+itemBins.size());


        for(ItemBinDTO itemBin:itemBins)
        {
            System.out.println("ItemBin Id="+itemBin.getId());

            itemBin.setCrateBin(getAllBins(itemBin.getId()));
            System.out.println("Item Fetched entered");
            itemBin.setItem(getAllItems(itemBin.getId()));
            System.out.println("Device Fetched entered");
            itemBin.setCurrDevice(getDeviceDetails(itemBin.getId()));
            System.out.println("Readings Fetched entered");
            itemBin.setLastReading(getAllReadings(itemBin.getId()));
            System.out.println("thresold Fetched entered");
            itemBin.setThresold(getThresoldDetails(itemBin.getId()));
        }

        return itemBins;
    }

    public BinDTO getAllBins(String ItemBinId) {
        System.out.println("Bin Fetched entered");
        BinDTO bins = getBinDetails(ItemBinId);
        System.out.println(bins.getId());
       // bins.setBinType(getBinTypeDetails(bins.getId()));
        bins.setDimension(getBinDimensionDetails(bins.getId()));
        System.out.println("Bin Dimension Fetched entered");

        return bins;
    }

    public ItemDTO getAllItems(String ItemBinId) {
        ItemDTO items = getItemDetails(ItemBinId);
        items.setDimension(getItemDimensionDetails(items.getId()));
        return items;
    }

    public ReadingDTO getAllReadings(String ItemBinId) {
        ReadingDTO readings = getReadingDetails(ItemBinId);
        System.out.println("Reading Id="+readings.getId());
        readings.setReading(getReadingValueDetails(readings.getId()));
        return readings;
    }


    public void deleteAll() {


        deleteAllIB();


    }
}
