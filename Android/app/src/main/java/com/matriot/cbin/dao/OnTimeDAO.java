package com.matriot.cbin.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.matriot.cbin.dto.OnTimeDTO;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Dao
public interface OnTimeDAO {
    @Query("SELECT * FROM onTime")
    OnTimeDTO getAll();

    @Insert()
    void insertAll(OnTimeDTO onTimeDTO);

    @Query("DELETE FROM onTime")
    void deleteAll();
}
