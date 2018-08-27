package com.matriot.cbin.service;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kdsdh on 1/22/2018.
 */

public interface SubscriptionListner {
    public void onMessage(JSONObject jsonArray);
}
