package com.montecito.samayu.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by NandhiniGovindasamy on 2/2/18.
 */
@Entity(tableName = "item")
public class ItemDTO {

    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    @Expose
    @NonNull
    private String id;

    @ColumnInfo(name = "itemBinId")
    private String itemBinId;

    @ColumnInfo(name = "name")
    @Expose
    private String name;

    @ColumnInfo(name = "category")
    @Expose
    private String category;

    @ColumnInfo(name = "uom")
    @Expose
    private String uom;

    @Ignore
    @Expose
    private ItemDimensionDTO dimension;

    @ColumnInfo(name = "material")
    @Expose
    private String material;


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public ItemDimensionDTO getDimension() {
        return dimension;
    }

    public void setDimension(ItemDimensionDTO dimension) {
        this.dimension = dimension;
    }

    public String getItemBinId() {
        return itemBinId;
    }

    public void setItemBinId(String itemBinId) {
        this.itemBinId = itemBinId;
    }


}
