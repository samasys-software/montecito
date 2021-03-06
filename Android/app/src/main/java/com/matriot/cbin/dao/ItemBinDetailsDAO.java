package com.matriot.cbin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.matriot.cbin.dto.BinDTO;
import com.matriot.cbin.dto.BinDimensionDTO;
import com.matriot.cbin.dto.BinTypeDTO;
import com.matriot.cbin.dto.DeviceDTO;
import com.matriot.cbin.dto.DeviceHistoryDTO;
import com.matriot.cbin.dto.ItemBinDetailsDTO;
import com.matriot.cbin.dto.ItemDTO;
import com.matriot.cbin.dto.ItemDimensionDTO;
import com.matriot.cbin.dto.LastDeviceDTO;
import com.matriot.cbin.dto.ReadingDTO;
import com.matriot.cbin.dto.ReadingValueDTO;
import com.matriot.cbin.dto.ReplenishmentTaskDTO;
import com.matriot.cbin.dto.ReplenishmentsDTO;
import com.matriot.cbin.dto.ThresoldDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/10/18.
 */
@Dao
public abstract class ItemBinDetailsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void inserItemBinDetails(ItemBinDetailsDTO itemBins);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertBin(BinDTO bins);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertBinType(BinTypeDTO binType);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertBinDimension(BinDimensionDTO binDimension);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertItem(ItemDTO items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertItemDimension(ItemDimensionDTO itemDimension);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDevice(DeviceDTO device);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertDeviceHistroy(List<DeviceHistoryDTO> deviceHistroy);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertLastDevice(LastDeviceDTO lastDevice);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReading(ReadingDTO reading);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReadingValue(ReadingValueDTO readingValue);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReplenishment(List<ReplenishmentsDTO> replenishment);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertReplenishmentTask(ReplenishmentTaskDTO replenishmentTask);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertThresold(ThresoldDTO thresold);



    @Query("SELECT * FROM item_bin_details WHERE id =:id")
    public abstract ItemBinDetailsDTO getAll(String id);

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

    @Query("SELECT * FROM replenishment_task WHERE itemBinId =:itemBinId")
    public abstract ReplenishmentTaskDTO getReplenishmentTaskForItem(String itemBinId);

    @Query("SELECT * FROM replenishments WHERE itemBinId =:itemBinId")
    public abstract List<ReplenishmentsDTO> getReplenishments(String itemBinId);

    @Query("SELECT * FROM replenishment_task WHERE replenishmentId =:replenishmentId")
    public abstract ReplenishmentTaskDTO getReplenishmentTask(String replenishmentId);


    @Query("SELECT * FROM device_histroy WHERE itemBinId =:itemBinId")
    public abstract List<DeviceHistoryDTO> getDeviceHistroy(String itemBinId);

    @Query("SELECT * FROM last_device WHERE deviceHistroyId =:deviceHistroyId")
    public abstract LastDeviceDTO getLastDevice(String deviceHistroyId);




    @Query("DELETE FROM item_bin_details WHERE id =:id")
    public  abstract void deleteAllIB(String id);





    public void insertAll(ItemBinDetailsDTO itemBin) {
        if(itemBin!=null) {

            String itemBinId = itemBin.getId();

            BinDTO bin = itemBin.getCrateBin();
            if (bin != null) {

                bin.setItemBinId(itemBin.getId());

                insertBin(bin);
                System.out.println("Bin Inserted Successfully");

                System.out.println("bin" + bin.getId());


                BinDimensionDTO binDimension = bin.getDimension();
                if (binDimension != null) {
                    binDimension.setBinId(bin.getId());
                    insertBinDimension(binDimension);
                    System.out.println("Dimension Inserted Successfully");
                }

                BinTypeDTO binType = bin.getBinType();
                if (binType != null) {

                    System.out.println("bin" + bin.getBinType().getId());


                    binType.setBinId(bin.getId());
                    insertBinType(binType);
                }
            }

            ItemDTO item = itemBin.getItem();
            if (item != null) {
                item.setItemBinId(itemBin.getId());
                insertItem(item);

                ItemDimensionDTO itemDimension = item.getDimension();
                if (itemDimension != null) {
                    itemDimension.setItemId(item.getId());
                    insertItemDimension(itemDimension);
                }
            }

            ThresoldDTO thresold = itemBin.getThresold();
            if (thresold != null) {
                thresold.setItemBinId(itemBin.getId());
                insertThresold(thresold);
            }

            DeviceDTO device = itemBin.getCurrDevice();
            if (device != null) {
                device.setItemBinId(itemBin.getId());
                insertDevice(device);
            }

            ReadingDTO reading = itemBin.getLastReading();
            if (reading != null) {
                reading.setItemBinId(itemBin.getId());
                insertReading(reading);

                ReadingValueDTO readingValue = reading.getReading();
                if (readingValue != null) {
                    readingValue.setReadingId(reading.getId());
                    insertReadingValue(readingValue);
                }
            }

            List<ReplenishmentsDTO> replenishmentsDTO = itemBin.getReplenishments();
            if (replenishmentsDTO != null) {
                for (ReplenishmentsDTO replenishmentsDTO1 : replenishmentsDTO) {
                    replenishmentsDTO1.setItemBinId(itemBin.getId());
                    ReplenishmentTaskDTO replenishmentTaskDTO = replenishmentsDTO1.getReplenishTask();
                    if (replenishmentTaskDTO != null) {
                        replenishmentTaskDTO.setReplenishmentId(replenishmentsDTO1.getId());
                        insertReplenishmentTask(replenishmentTaskDTO);
                    }
                }
                insertReplenishment(replenishmentsDTO);
            }

            ReplenishmentTaskDTO replenishmentTaskDTO = itemBin.getReplenishTask();
            if (replenishmentTaskDTO != null) {
                replenishmentTaskDTO.setItemBinId(itemBin.getId());
                insertReplenishmentTask(replenishmentTaskDTO);
            }

            List<DeviceHistoryDTO> deviceHistoryDTOs = itemBin.getDeviceHistory();
            if (deviceHistoryDTOs != null) {
                for (DeviceHistoryDTO deviceHistoryDTO : deviceHistoryDTOs) {

                    deviceHistoryDTO.setItemBinId(itemBin.getId());
                    LastDeviceDTO lastDeviceDTO = deviceHistoryDTO.getLastDevice();
                    if (lastDeviceDTO != null) {
                        lastDeviceDTO.setDeviceHistroyId(deviceHistoryDTO.getId());
                        insertLastDevice(lastDeviceDTO);
                    }
                }
                insertDeviceHistroy(deviceHistoryDTOs);
            }

            inserItemBinDetails(itemBin);
        }
    }

    public ItemBinDetailsDTO getAllItemBins(String itemBinId) {
        System.out.println("ItemBins=");
        ItemBinDetailsDTO itemBins = getAll(itemBinId);
        System.out.println("ItemBins="+itemBins);

        itemBins.setCrateBin(getAllBins(itemBins.getId()));
        itemBins.setItem(getAllItems(itemBins.getId()));
        itemBins.setThresold(getThresoldDetails(itemBins.getId()));
        itemBins.setCurrDevice(getDeviceDetails(itemBins.getId()));
        itemBins.setLastReading(getAllReadings(itemBins.getId()));
        itemBins.setReplenishTask(getReplenishmentTaskForItem(itemBins.getId()));
        itemBins.setReplenishments(getAllReplenishments(itemBins.getId()));
        itemBins.setDeviceHistory(getAllDeviceHistroy(itemBins.getId()));

        return itemBins;
    }

    public BinDTO getAllBins(String ItemBinId) {
        System.out.println("Bin Fetched entered");
        BinDTO bins = getBinDetails(ItemBinId);
        System.out.println(bins.getId());
        bins.setBinType(getBinTypeDetails(bins.getId()));
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

    public List<ReplenishmentsDTO> getAllReplenishments(String ItemBinId) {
       List<ReplenishmentsDTO> replenishmentsDTO = getReplenishments(ItemBinId);
       for(ReplenishmentsDTO replenishmentsDTO1:replenishmentsDTO){
           replenishmentsDTO1.setReplenishTask(getReplenishmentTask(replenishmentsDTO1.getId()));
       }
       return replenishmentsDTO;
    }

    public List<DeviceHistoryDTO> getAllDeviceHistroy(String ItemBinId) {
        List<DeviceHistoryDTO> deviceHistoryDTOS = getDeviceHistroy(ItemBinId);
        for(DeviceHistoryDTO deviceHistoryDTO:deviceHistoryDTOS){
            deviceHistoryDTO.setLastDevice(getLastDevice(deviceHistoryDTO.getId()));
        }
        return deviceHistoryDTOS;
    }

    public void deleteAll(String itemBinId) {


   //     deleteAllIB(itemBinId);


    }
}
