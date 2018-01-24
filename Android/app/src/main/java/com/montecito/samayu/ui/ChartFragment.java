package com.montecito.samayu.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.montecito.samayu.service.SessionInfo;
import com.montecito.samayu.dto.ConsumptionInfo;
import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SubscriptionListner;
import com.montecito.samayu.service.SubscriptionManager;
import com.prodcast.samayu.samayusoftcorp.R;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fgs on 1/18/2018.
 */

public class ChartFragment extends Fragment implements SubscriptionListner {
    private WebSocketClient mWebSocketClient;


    int position;
    BarChart barChart;


    // newInstance constructor for creating fragment with arguments
    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        ChartFragment chartFragment = new ChartFragment();
        chartFragment.setArguments(bundle);
        return chartFragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position=getArguments().getInt("pos");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState){
        return inflator.inflate(R.layout.items_chart,container,false);

    }
    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.chart);
        SubscriptionManager.getInstance().subscribe("consumption",this);
        String token = SessionInfo.getInstance().getUserLogin().getToken();
        if(position==0) {


            final Call<List<ConsumptionInfo>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoItems(token);
            consumptionInfoCall.enqueue(new Callback<List<ConsumptionInfo>>() {
                @Override
                public void onResponse(Call<List<ConsumptionInfo>> call, Response<List<ConsumptionInfo>> response) {
                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<ConsumptionInfo> consumptionInfo = response.body();
                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ConsumptionInfo>> call, Throwable t) {

                }
            });

        }
        else if(position==1)
        {
            final Call<List<ConsumptionInfo>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoCategory(token);
            consumptionInfoCall.enqueue(new Callback<List<ConsumptionInfo>>() {
                @Override
                public void onResponse(Call<List<ConsumptionInfo>> call, Response<List<ConsumptionInfo>> response) {
                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<ConsumptionInfo> consumptionInfo = response.body();
                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ConsumptionInfo>> call, Throwable t) {

                }
            });
        }
        else
        {
            final Call<List<ConsumptionInfo>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoFloor(token);
            consumptionInfoCall.enqueue(new Callback<List<ConsumptionInfo>>() {
                @Override
                public void onResponse(Call<List<ConsumptionInfo>> call, Response<List<ConsumptionInfo>> response) {
                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<ConsumptionInfo> consumptionInfo = response.body();
                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<ConsumptionInfo>> call, Throwable t) {

                }
            });

        }

    }


    public void updateChart(BarChart barChart, List<ConsumptionInfo> consumptionInfo){
        List<BarEntry> data = new ArrayList<>();
        for(int i =0; i<consumptionInfo.size(); i++){
            data.add( new BarEntry(i,(float) Double.parseDouble(consumptionInfo.get(i).getUsage())));
        }

        BarDataSet dataSet = new BarDataSet(data,"Usage");
        // barChart.setDescription("");
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setDrawLabels(false);


        //dataSet.setColor(Color.BLUE);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setColors(new int[] {Color.GREEN,Color.YELLOW, Color.DKGRAY,Color.RED});

        BarData barData = new BarData(dataSet);
        barChart.setData( barData );
        barChart.invalidate();

    }


    @Override
    public void onMessage(JSONArray jsonArray) {
        try{
            List<ConsumptionInfo> list = new ArrayList<ConsumptionInfo>();
            for(int i=0;i<jsonArray.length();i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                ConsumptionInfo info = new ConsumptionInfo();
                info.setItem(obj.getString("item"));
                info.setUsage(obj.getString("usage"));
                list.add(info);
            }
            final List<ConsumptionInfo> consumptionInfo = list;
            updateChart(barChart , consumptionInfo );

        }catch (Exception er){
            er.printStackTrace();
        }

    }
}
