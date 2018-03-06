package com.montecito.samayu.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.montecito.samayu.dao.ConsumptionDAO;
import com.montecito.samayu.dao.ItemAvailablityDAO;
import com.montecito.samayu.domain.Consumption;
import com.montecito.samayu.domain.ItemAvailablity;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;

/**
 * Created by God on 1/25/2018.
 */
@Database(entities = {ItemAvailabilityDTO.class,Consumption.class}, version = 4)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ConsumptionDAO consumptionDAO();

    public abstract ItemAvailablityDAO itemAvailablityDAO();

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
