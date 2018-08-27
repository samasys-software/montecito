package com.matriot.cbin.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.matriot.cbin.dao.AverageDAO;
import com.matriot.cbin.dao.ConsumptionCategoryDAO;

import com.matriot.cbin.dao.ConsumptionItemDAO;
import com.matriot.cbin.dao.CountDAO;
import com.matriot.cbin.dao.ItemAvailablityDAO;
import com.matriot.cbin.dao.ItemBinDAO;
import com.matriot.cbin.dao.ItemBinDetailsDAO;
import com.matriot.cbin.dao.OnTimeDAO;
import com.matriot.cbin.dao.TopItemsDAO;
import com.matriot.cbin.dao.UserProfileDAO;
import com.matriot.cbin.dto.AverageDTO;
import com.matriot.cbin.dto.BinDTO;
import com.matriot.cbin.dto.BinDimensionDTO;
import com.matriot.cbin.dto.BinTypeDTO;
import com.matriot.cbin.dto.ConsumptionCategoryDTO;

import com.matriot.cbin.dto.ConsumptionItemDTO;
import com.matriot.cbin.dto.CountDTO;
import com.matriot.cbin.dto.DeviceDTO;
import com.matriot.cbin.dto.DeviceHistoryDTO;
import com.matriot.cbin.dto.ItemAvailabilityDTO;
import com.matriot.cbin.dto.ItemBinDTO;
import com.matriot.cbin.dto.ItemBinDetailsDTO;
import com.matriot.cbin.dto.ItemDTO;
import com.matriot.cbin.dto.ItemDimensionDTO;
import com.matriot.cbin.dto.LastDeviceDTO;
import com.matriot.cbin.dto.OnTimeDTO;
import com.matriot.cbin.dto.ReadingDTO;
import com.matriot.cbin.dto.ReadingValueDTO;
import com.matriot.cbin.dto.ReplenishmentTaskDTO;
import com.matriot.cbin.dto.ReplenishmentsDTO;
import com.matriot.cbin.dto.ThresoldDTO;
import com.matriot.cbin.dto.TopItemsDTO;
import com.matriot.cbin.dto.UserProfileDTO;

/**
 * Created by God on 1/25/2018.
 */
@Database(entities = {ItemAvailabilityDTO.class,ConsumptionItemDTO.class, ConsumptionCategoryDTO.class, ItemBinDTO.class, BinDTO.class, BinDimensionDTO.class, BinTypeDTO.class, ItemDTO.class,ItemDimensionDTO.class, ReadingDTO.class, ReadingValueDTO.class, ThresoldDTO.class, DeviceDTO.class, ItemBinDetailsDTO.class, LastDeviceDTO.class, DeviceHistoryDTO.class, ReplenishmentsDTO.class, ReplenishmentTaskDTO.class, AverageDTO.class, CountDTO.class, OnTimeDTO.class, TopItemsDTO.class, UserProfileDTO.class}, version = 27)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ConsumptionItemDAO consumptionItemDAO();

    public abstract ConsumptionCategoryDAO consumptionCategoryDAO();

    public abstract ItemAvailablityDAO itemAvailablityDAO();

    public abstract ItemBinDAO itemBinDAO();

    public abstract ItemBinDetailsDAO itemBinDetailsDAO();

    public abstract OnTimeDAO onTimeDAO();

    public abstract AverageDAO averageDAO();

    public abstract CountDAO countDAO();

    public abstract TopItemsDAO topItemsDAO();

    public abstract UserProfileDAO userProfileDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Montecitodb3Admin.db")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
