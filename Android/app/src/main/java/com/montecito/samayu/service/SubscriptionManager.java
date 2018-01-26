package com.montecito.samayu.service;

import android.util.Log;

import com.montecito.samayu.dto.ConsumptionInfo;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.ui.Home;
import com.montecito.samayu.ui.TaskListAdapter;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kdsdh on 1/22/2018.
 */

public class SubscriptionManager {
    private WebSocketClient mWebSocketClient;
    private Map<String ,SubscriptionListner> map = new HashMap<>();

    public synchronized  boolean isClosed() {
        return isClosed;
    }
    public synchronized void setClosed(boolean closed) {
        isClosed = closed;
    }
    private boolean isClosed=true;
    private  static SubscriptionManager subscriptionManager= new SubscriptionManager();




    private class MonitorTask extends java.util.TimerTask{

        @Override
        public void run() {
            if( isClosed() ){
                try {

                    connectWebSocket();
                    setClosed(false);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private SubscriptionManager(){
        Timer  timer = new Timer();
        timer.scheduleAtFixedRate( new MonitorTask(),1, 60000);
    }
    public static SubscriptionManager getInstance(){
        return subscriptionManager;
    }

    private void connectWebSocket() throws Exception{
        URI uri;
        uri = new URI("ws://ec2-52-91-5-22.compute-1.amazonaws.com:8080/montecito/event");
        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                for(SubscriptionListner listner :map.values()){
                    listner.onConnect();
                }
                Log.i("Websocket", "Opened");
                //Call Subscription listener onConnect
            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                Log.i("Websocket" , "Received "+s);
                try {
                    JSONObject jsonObject= new JSONObject(message);
                    String type = jsonObject.getString("type");
                    JSONArray array= jsonObject.getJSONArray("payload");
                    if(map.containsKey(type))
                        map.get(type).onMessage(array);
                    }
                catch(Exception er){
                    er.printStackTrace();
                }

            }


            @Override
            public void onClose(int i, String s, boolean b) {
                setClosed(true);
               for(SubscriptionListner listner: map.values()){
                   listner.onDisconnect();
               }
                Log.i("Websocket", "Closed " + s);



                //Call SubscriptionListener.onDisconnect
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();

    }
    public void subscribe(String type, SubscriptionListner listner){
        map.put(type,listner);

    }







}

