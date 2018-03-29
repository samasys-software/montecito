package com.matriot.cbin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.matriot.cbin.dto.TopItemsDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Dao
public interface TopItemsDAO {
    @Query("SELECT * FROM topItems")
    List<TopItemsDTO> getAll();

    @Insert()
    void insertAll(List<TopItemsDTO> topItemsDTOS);

    @Query("DELETE FROM topItems")
    void deleteAll();

}
