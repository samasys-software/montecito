package com.montecito.samayu.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.montecito.samayu.dao.AverageDAO;
import com.montecito.samayu.dao.ConsumptionCategoryDAO;

import com.montecito.samayu.dao.ConsumptionItemDAO;
import com.montecito.samayu.dao.CountDAO;
import com.montecito.samayu.dao.ItemAvailablityDAO;
import com.montecito.samayu.dao.ItemBinDAO;
import com.montecito.samayu.dao.ItemBinDetailsDAO;
import com.montecito.samayu.dao.OnTimeDAO;
import com.montecito.samayu.dao.TopItemsDAO;
import com.montecito.samayu.dao.UserProfileDAO;
import com.montecito.samayu.dto.AverageDTO;
import com.montecito.samayu.dto.BinDTO;
import com.montecito.samayu.dto.BinDimensionDTO;
import com.montecito.samayu.dto.BinTypeDTO;
import com.montecito.samayu.dto.ConsumptionCategoryDTO;

import com.montecito.samayu.dto.ConsumptionItemDTO;
import com.montecito.samayu.dto.CountDTO;
import com.montecito.samayu.dto.DeviceDTO;
import com.montecito.samayu.dto.DeviceHistoryDTO;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.ItemDTO;
import com.montecito.samayu.dto.ItemDimensionDTO;
import com.montecito.samayu.dto.LastDeviceDTO;
import com.montecito.samayu.dto.OnTimeDTO;
import com.montecito.samayu.dto.ReadingDTO;
import com.montecito.samayu.dto.ReadingValueDTO;
import com.montecito.samayu.dto.ReplenishmentTaskDTO;
import com.montecito.samayu.dto.ReplenishmentsDTO;
import com.montecito.samayu.dto.ThresoldDTO;
import com.montecito.samayu.dto.TopItemsDTO;
import com.montecito.samayu.dto.UserProfileDTO;
import com.montecito.samayu.ui.UserProfile;

/**
 * Created by God on 1/25/2018.
 */
@Database(entities = {ItemAvailabilityDTO.class,ConsumptionItemDTO.class, ConsumptionCategoryDTO.class, ItemBinDTO.class, BinDTO.class, BinDimensionDTO.class, BinTypeDTO.class, ItemDTO.class,ItemDimensionDTO.class, ReadingDTO.class, ReadingValueDTO.class, ThresoldDTO.class, DeviceDTO.class, ItemBinDetailsDTO.class, LastDeviceDTO.class, DeviceHistoryDTO.class, ReplenishmentsDTO.class, ReplenishmentTaskDTO.class, AverageDTO.class, CountDTO.class, OnTimeDTO.class, TopItemsDTO.class, UserProfileDTO.class}, version = 26)
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
