package com.montecito.samayu.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.montecito.samayu.dto.AverageDTO;
import com.montecito.samayu.dto.UserLoginDTO;
import com.montecito.samayu.dto.UserProfileDTO;

/**
 * Created by NandhiniGovindasamy on 3/13/18.
 */
@Dao
public interface UserProfileDAO {
    @Query("SELECT * FROM user_profile")
    UserProfileDTO getAll();

    @Insert()
    void insertAll(UserProfileDTO userProfileDTO);

    @Query("DELETE FROM user_profile")
    void deleteAll();
}
