package com.montecito.samayu.service;

import android.util.Log;



import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kdsdh on 1/22/2018.
 */

public class SubscriptionManager implements Runnable {
    private WebSocketClient mWebSocketClient;
    private Map<String ,SubscriptionListner> map = new HashMap<>();


    private  static SubscriptionManager subscriptionManager= new SubscriptionManager();

    private SubscriptionManager(){
      Thread t = new Thread(this);
      t.start();
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
                Log.i("Websocket", "Opened");

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
                Log.i("Websocket", "Closed " + s);
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

    @Override
    public void run() {
        while(true) {
            try {


                connectWebSocket();

                break;
                //Connection Sucessful ;
            } catch (Exception e) {
                // Connection fail;
                e.printStackTrace();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

            }
        }


    }
    /*public HashMap unSubscribe(String type , SubscriptionListner listner){
        if(type.equals()){

        }

    }*/



}

