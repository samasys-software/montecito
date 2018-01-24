package com.montecito.samayu.dto;

/**
 * Created by Preethiv on 1/15/2018.
 */

public class ConsumptionInfo {

    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    private String item;
    private String usage;

}
