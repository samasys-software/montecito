package com.montecito.samayu.service;

import org.json.JSONArray;

import java.util.List;

/**
 * Created by kdsdh on 1/22/2018.
 */

public interface SubscriptionListner {
    public void onMessage(JSONArray jsonArray);
    public void onDisconnect();
    public void onConnect();
}
