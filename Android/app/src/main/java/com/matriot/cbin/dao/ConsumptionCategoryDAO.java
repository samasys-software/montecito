package com.matriot.cbin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.matriot.cbin.dto.ConsumptionCategoryDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/9/18.
 */

@Dao

public interface ConsumptionCategoryDAO {

    @Query("SELECT * FROM consumption_category_details")
    List<ConsumptionCategoryDTO> getAll();

    @Insert()
    void insertAll(List<ConsumptionCategoryDTO> consumptions);

    @Query("DELETE FROM consumption_category_details")
    void deleteAll();

}
