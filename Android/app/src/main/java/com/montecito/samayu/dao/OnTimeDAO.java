package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.AverageDTO;
import com.montecito.samayu.dto.OnTimeDTO;

import java.util.List;

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
