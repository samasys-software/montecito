package com.matriot.cbin.service;

import android.util.Log;



import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kdsdh on 1/22/2018.
 */

public class SubscriptionManager implements Runnable {
    private WebSocketClient mWebSocketClient;
    private Map<String ,SubscriptionListner> map = new HashMap<>();
    private boolean doNotConnect=false;

    public boolean isDoNotConnect() {
        return doNotConnect;
    }

    public void setDoNotConnect(boolean doNotConnect) {
        this.doNotConnect = doNotConnect;
        try {
            mWebSocketClient.close();
        }
        catch(Exception e){

        }
    }

    private  static SubscriptionManager subscriptionManager= new SubscriptionManager();

    private SubscriptionManager(){
        startConnection();

    }
    public static SubscriptionManager getInstance(){

       return subscriptionManager;
    }

    private void connectWebSocket() throws Exception{
        if(doNotConnect){
            return;
        }
        URI uri;


            uri = new URI("ws://cbindev.matriotsolutions.com/primus?_primuscb=MDJGszT");



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

                    //This line are commented for the purpose of server data testing
                     JSONObject jsonObject= new JSONObject(message);
                     JSONArray emit = jsonObject.getJSONArray("emit");
                     String type=emit.getString(0).split(":")[0];
                     Log.i("Websocket Type",type);
                     JSONObject array= emit.getJSONObject(1);
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
               startConnection();

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

    private void startConnection(){
        Thread t = new Thread(this);
        t.start();
    }

    /*public HashMap unSubscribe(String type , SubscriptionListner listner){
        if(type.equals()){

        }

    }*/



}

