package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.AverageDTO;
import com.montecito.samayu.dto.CountDTO;

import java.util.List;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Dao
public interface CountDAO {
    @Query("SELECT * FROM count")
    CountDTO getAll();

    @Insert()
    void insertAll(CountDTO counts);

    @Query("DELETE FROM count")
    void deleteAll();


}
