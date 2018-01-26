package com.montecito.samayu.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.domain.Consumption;


import java.util.List;

/**
 * Created by God on 1/25/2018.
 */

@Dao
public interface ConsumptionDAO {

    @Query("SELECT * FROM consumption_details")
    List<Consumption> getAll();

    @Insert
    void insertAll(List<Consumption> consumptions);

    @Delete
    void delete(Consumption consumption);
}