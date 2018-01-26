package com.montecito.samayu.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.montecito.samayu.dao.ConsumptionDAO;
import com.montecito.samayu.dao.ItemAvailablityDAO;
import com.montecito.samayu.domain.Consumption;
import com.montecito.samayu.domain.ItemAvailablity;

/**
 * Created by God on 1/25/2018.
 */
@Database(entities = {Consumption.class, ItemAvailablity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ConsumptionDAO consumptionDAO();

    public abstract ItemAvailablityDAO itemAvailablityDAO();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "Montecitodb1Admin.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
