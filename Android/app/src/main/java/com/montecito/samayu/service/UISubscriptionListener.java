package com.montecito.samayu.service;

import android.app.Activity;
import android.os.Looper;
import android.support.annotation.UiThread;

import org.json.JSONArray;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

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
