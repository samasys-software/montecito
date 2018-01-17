package com.prodcast.samayusoftcorp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.businessobjects.SessionInfo;
import com.dto.ConsumptionInfo;
import com.dto.ItemAvailabilityDTO;
import com.github.mikephil.charting.charts.BarChart;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.montecito.samayu.service.MontecitoClient;
import com.prodcast.samayu.samayusoftcorp.R;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by Preethiv on 1/13/2018.
 */

public class Home extends AppCompatActivity {
    ListView list;
    private WebSocketClient mWebSocketClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        connectWebSocket();

        String token = SessionInfo.instance().getToken();

        final Call<List<ItemAvailabilityDTO>> itemAvailablityDTOCall = new MontecitoClient().getClient().getItemAvailablityDTO(token);
        itemAvailablityDTOCall.enqueue(new Callback<List<ItemAvailabilityDTO>>() {
            @Override
            public void onResponse(Call<List<ItemAvailabilityDTO>> call, Response<List<ItemAvailabilityDTO>> response) {
                if(response.isSuccessful()){
                    ListView listView = findViewById(R.id.list);

                    List<ItemAvailabilityDTO>  itemAvailabilityDTOList = response.body();
                    listView.setAdapter(new TaskListAdapter(Home.this,itemAvailabilityDTOList));
                }
            }

            @Override
            public void onFailure(Call<List<ItemAvailabilityDTO>> call, Throwable t) {

            }
        });

        final Call <List<ConsumptionInfo>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfo(token);
        consumptionInfoCall.enqueue(new Callback<List<ConsumptionInfo>>() {
            @Override
            public void onResponse(Call<List<ConsumptionInfo>> call, Response<List<ConsumptionInfo>> response) {
                if(response.isSuccessful()){
                    BarChart barChart = findViewById(R.id.chart);

                    final List<ConsumptionInfo> consumptionInfo = response.body();
                    List<BarEntry> data = new ArrayList<>();
                    for(int i =0; i<consumptionInfo.size(); i++){
                        data.add( new BarEntry(i,(float) Double.parseDouble(consumptionInfo.get(i).getUsage())));
                                           }

                    BarDataSet dataSet = new BarDataSet(data,"Label");

                    final ArrayList<String> xAxisLabel = new ArrayList<>();
                    for( int j =0; j<consumptionInfo.size(); j++){
                    xAxisLabel.add(consumptionInfo.get(j).getItem());
                    }
                    XAxis xAxis=barChart.getXAxis();
                    xAxis.setValueFormatter(new IAxisValueFormatter() {
                        @Override
                        public String getFormattedValue(float value, AxisBase axis) {
                            if(xAxisLabel.size()> 0){
                            for(int i=0;i<xAxisLabel.size();i++){
                                return xAxisLabel.get(i);
                            }}

                                return "label";

                        }
                    });

                    dataSet.setColor(Color.BLUE);
                    dataSet.setValueTextColor(Color.BLACK);

                    BarData barData = new BarData(dataSet);
                    barChart.setData( barData );
                    barChart.invalidate();
                }
                else
                {
                    Toast.makeText(Home.this, "Error occured!!!!", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<ConsumptionInfo>> call, Throwable t) {

            }
        });


    }


    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://ec2-52-91-5-22.compute-1.amazonaws.com:8080/montecito/event");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }



        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");

            }

            @Override
            public void onMessage(String s) {
                final String message = s;
                Log.i("Websocket" , "Received "+s);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BarChart barChart = findViewById(R.id.chart);
                            JSONArray array = new JSONArray(message);
                            List<ConsumptionInfo> list = new ArrayList<ConsumptionInfo>();
                            for(int i=0;i<array.length();i++){
                                JSONObject obj = (JSONObject)array.get(i);

                                ConsumptionInfo info = new ConsumptionInfo();
                                info.setItem(obj.getString("item"));
                                info.setUsage(obj.getString("usage"));
                                list.add( info );
                            }

                            final List<ConsumptionInfo> consumptionInfo = list;
                            List<BarEntry> data = new ArrayList<>();
                            for (int i = 0; i < consumptionInfo.size(); i++) {
                                data.add(new BarEntry(i, (float) Double.parseDouble(consumptionInfo.get(i).getUsage())));
                            }

                            BarDataSet dataSet = new BarDataSet(data, "Label");

                            final ArrayList<String> xAxisLabel = new ArrayList<>();
                            for (int j = 0; j < consumptionInfo.size(); j++) {
                                xAxisLabel.add(consumptionInfo.get(j).getItem());
                            }
                            XAxis xAxis = barChart.getXAxis();
                            xAxis.setValueFormatter(new IAxisValueFormatter() {
                                @Override
                                public String getFormattedValue(float value, AxisBase axis) {
                                    if (xAxisLabel.size() > 0) {
                                        for (int i = 0; i < xAxisLabel.size(); i++) {
                                            return xAxisLabel.get(i);
                                        }
                                    }

                                    return "label";

                                }
                            });

                            dataSet.setColor(Color.BLUE);
                            dataSet.setValueTextColor(Color.BLACK);

                            BarData barData = new BarData(dataSet);
                            barChart.setData(barData);
                            barChart.invalidate();
                        }
                        catch(Exception er){
                            er.printStackTrace();
                        }
                    }
                });
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


}
