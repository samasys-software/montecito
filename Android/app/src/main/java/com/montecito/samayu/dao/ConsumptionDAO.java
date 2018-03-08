package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.ConsumptionDTO;

import java.util.List;

/**
 * Created by God on 1/25/2018.
 */

@Dao
public interface ConsumptionDAO {

    @Query("SELECT * FROM consumption_details")
    List<ConsumptionDTO> getAll();

    @Insert()
    void insertAll(List<ConsumptionDTO> consumptions);

    @Query("DELETE FROM consumption_details")
    void deleteAll();
}