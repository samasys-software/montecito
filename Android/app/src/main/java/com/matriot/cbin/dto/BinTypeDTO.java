package com.matriot.cbin.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/23/18.
 */
@Entity(tableName = "bin_type")
public class BinTypeDTO {

    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String binId;

    @SerializedName("_id")
    @ColumnInfo(name = "id")
    @Expose
    @NonNull
    private String id;

    @Expose
    @ColumnInfo(name = "name")
    private String name;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getBinId() {
        return binId;
    }

    public void setBinId(String binId) {
        this.binId = binId;
    }
}
