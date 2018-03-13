package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fgs on 2/12/2018.
 */
@Entity(tableName = "user_profile")

public class UserProfileDTO {


    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @NonNull
    @Expose
    private String id;

    @Expose
    @ColumnInfo(name="role")
    private String role;

    @Expose
    @ColumnInfo(name="name")
   private String name;

    @Expose
    @ColumnInfo(name="email")
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
