package com.matriot.cbin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.matriot.cbin.dto.AverageDTO;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Dao
public interface AverageDAO {
    @Query("SELECT * FROM average")
    AverageDTO getAll();

    @Insert()
    void insertAll(AverageDTO average);

    @Query("DELETE FROM average")
    void deleteAll();
}
