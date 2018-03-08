package com.montecito.samayu.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.montecito.samayu.dao.ConsumptionDAO;
import com.montecito.samayu.dao.ItemAvailablityDAO;
import com.montecito.samayu.dao.ItemBinDAO;
import com.montecito.samayu.dto.BinDTO;
import com.montecito.samayu.dto.BinDimensionDTO;
import com.montecito.samayu.dto.BinTypeDTO;
import com.montecito.samayu.dto.ConsumptionDTO;
import com.montecito.samayu.dto.DeviceDTO;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemDTO;
import com.montecito.samayu.dto.ItemDimensionDTO;
import com.montecito.samayu.dto.ReadingDTO;
import com.montecito.samayu.dto.ReadingValueDTO;
import com.montecito.samayu.dto.ThresoldDTO;

/**
 * Created by God on 1/25/2018.
 */
@Database(entities = {ItemAvailabilityDTO.class,ConsumptionDTO.class, ItemBinDTO.class, BinDTO.class, BinDimensionDTO.class, BinTypeDTO.class, ItemDTO.class,ItemDimensionDTO.class, ReadingDTO.class, ReadingValueDTO.class, ThresoldDTO.class, DeviceDTO.class}, version = 13)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ConsumptionDAO consumptionDAO();

    public abstract ItemAvailablityDAO itemAvailablityDAO();

    public abstract ItemBinDAO itemBinDAO();

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
