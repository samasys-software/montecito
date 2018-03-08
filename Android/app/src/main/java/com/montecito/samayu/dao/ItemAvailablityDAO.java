package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.ItemAvailabilityDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/5/18.
 */

@Dao
public interface ItemAvailablityDAO {
    @Query("SELECT * FROM item_availablity_details")
    List<ItemAvailabilityDTO> getAll();

    @Insert()
    void insertAll(List<ItemAvailabilityDTO> itemAvailabilityDTOList);

    @Query("DELETE FROM item_availablity_details")
    void deleteAll();
}
