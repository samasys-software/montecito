package com.matriot.cbin.service;

import android.app.Activity;

import org.json.JSONArray;

/**
 * Created by kdsdh on 1/24/2018.
 */

public abstract class UISubscriptionListener implements SubscriptionListner {
    private Activity activity;
    public UISubscriptionListener(Activity activity){
        this.activity=activity;

    }

    @Override
    public void onMessage(final JSONArray jsonArray) {
       activity.runOnUiThread(new Runnable(){
           @Override
           public void run() {
               doOnUI(jsonArray);
           }


       });







    }



    public abstract void  doOnUI(JSONArray jsonArray);
}
