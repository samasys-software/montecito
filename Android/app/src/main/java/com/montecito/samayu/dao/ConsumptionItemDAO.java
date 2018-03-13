package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.montecito.samayu.dto.ConsumptionItemDTO;

import java.util.List;

/**
 * Created by God on 1/25/2018.
 */

@Dao
public interface ConsumptionItemDAO {

    @Query("SELECT * FROM consumption_item_details")
    List<ConsumptionItemDTO> getAll();

    @Insert()
    void insertAll(List<ConsumptionItemDTO> consumptions);

    @Query("DELETE FROM consumption_item_details")
    void deleteAll();
}