package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.domain.ItemAvailablity;

import java.util.List;

/**
 * Created by God on 1/26/2018.
 */

@Dao
public interface ItemAvailablityDAO {

    @Query("SELECT * FROM item_availablity_details")
    List<ItemAvailablity> getAll();

    @Insert
    void insertAll(List<ItemAvailablity> itemAvailablities);

    @Delete
    void delete(ItemAvailablity itemAvailablity);
}