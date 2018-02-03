package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.montecito.samayu.db.AppDatabase;
import com.montecito.samayu.domain.Consumption;
import com.montecito.samayu.service.SessionInfo;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SubscriptionManager;
import com.montecito.samayu.service.UISubscriptionListener;
import com.prodcast.samayu.samayusoftcorp.R;

import org.java_websocket.client.WebSocketClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fgs on 1/18/2018.
 */

public class ChartFragment extends Fragment {
    private WebSocketClient mWebSocketClient;
    private AppDatabase db;
    Context context;
    ProgressDialog mProgressDialog;


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
        context=getActivity().getApplicationContext();
        position=getArguments().getInt("pos");
        db=AppDatabase.getAppDatabase(context);
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("One Moment Please");

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
        //mProgressDialoggetProgressDialog(context);
//        mProgressDialog.show();
        SubscriptionManager.getInstance().subscribe("consumption", new UISubscriptionListener(getActivity()) {
            @Override
            public void doOnUI(JSONArray jsonArray) {
                try{
                    List<Consumption> list = new ArrayList<Consumption>();
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        Consumption info = new Consumption();
                        info.setId(obj.getString("_id"));
                        info.setItem(obj.getString("item"));
                        info.setUsage(obj.getString("usage"));
                        list.add(info);
                    }
                    final List<Consumption> consumptionInfo = list;
                    Runnable runnable = new Runnable(){
                        public void run(){
                            addConsumption( db,consumptionInfo );

                        }
                    };
                    Thread t = new Thread(runnable);t.start();
                    updateChart(barChart, consumptionInfo);

                }catch (Exception er){
                    er.printStackTrace();
                    //mProgressDialog.dismiss();
                }

            }
        });

        String token = SessionInfo.getInstance().getUserLogin().getToken();


        if(position==0) {

            final Call<List<Consumption>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoItems(token);
            consumptionInfoCall.enqueue(new Callback<List<Consumption>>() {
                @Override
                public void onResponse(Call<List<Consumption>> call, Response<List<Consumption>> response) {

                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<Consumption> consumptionInfo = response.body();
                        addConsumption( db,consumptionInfo );
                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<Consumption>> call, Throwable t) {

                }
            });


        }
        else if(position==1)
        {

            final Call<List<Consumption>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoCategory(token);
            consumptionInfoCall.enqueue(new Callback<List<Consumption>>() {
                @Override
                public void onResponse(Call<List<Consumption>> call, Response<List<Consumption>> response) {

                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<Consumption> consumptionInfo = response.body();
                        addConsumption( db,consumptionInfo );

                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<Consumption>> call, Throwable t) {

                }
            });
        }
        else
        {

            final Call<List<Consumption>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoFloor(token);
            consumptionInfoCall.enqueue(new Callback<List<Consumption>>() {
                @Override
                public void onResponse(Call<List<Consumption>> call, Response<List<Consumption>> response) {

                    if (response.isSuccessful()) {
                        BarChart barChart = view.findViewById(R.id.chart);

                        final List<Consumption> consumptionInfo = response.body();
                        addConsumption( db,consumptionInfo );
                        updateChart(barChart, consumptionInfo);
                    } else {
                        Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<List<Consumption>> call, Throwable t) {

                }
            });

        }

    }


    public void updateChart(BarChart barChart, List<Consumption> consumptionInfo){
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
        barChart.setDrawGridBackground(false);
        barChart.setFitBars(true);


        //dataSet.setColor(Color.BLUE);

        dataSet.setValueTextColor(Color.WHITE);

        dataSet.setColors(getColorsForChart(data.size() , Color.RED , Color.GREEN));




        BarData barData = new BarData(dataSet);
        barChart.setData( barData );
        barChart.invalidate();

    }

    public static int[] getColorsForChart(int size, int end, int start){
        int[] gradient = new int[size];
        for(int i=0;i<size;i++){
            float ratio = ((float)i)/size;

            int red = (int)(Color.red(end)*ratio+Color.red(start)*(1-ratio));
            int green =(int) (Color.green(end)*ratio+Color.green(start)*(1-ratio));
            int blue =(int) (Color.blue(end)*ratio+Color.blue(start)*(1-ratio));


            gradient[i]=Color.rgb( red, green , blue );

        }

        return gradient;
    }

    private static void addConsumption(final AppDatabase db,List<Consumption> consumptionDetails) {
       db.consumptionDAO().deleteAll();
       db.consumptionDAO().insertAll(consumptionDetails);

    }
    private static List<Consumption> getAllConsumption(final AppDatabase db)
    {
        return db.consumptionDAO().getAll();

    }




}
