package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.montecito.samayu.db.AppDatabase;
import com.montecito.samayu.dto.ConsumptionCategoryDTO;

import com.montecito.samayu.dto.ConsumptionItemDTO;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by fgs on 1/18/2018.
 */

public class ChartFragment extends Fragment  {
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
      /*  SubscriptionManager.getInstance().subscribe("consumption", new UISubscriptionListener(getActivity()) {
            @Override
            public void doOnUI(JSONArray jsonArray) {
                try{
                    //This line are commented for the purpose of server data testing
//                    List<ConsumptionDTO> list = new ArrayList<ConsumptionDTO>();
//                    for(int i=0;i<jsonArray.length();i++) {
//                        JSONObject obj = (JSONObject) jsonArray.get(i);
//                        ConsumptionDTO info = new ConsumptionDTO();
//                        info.setId(obj.getString("_id"));
//                        info.setItem(obj.getString("item"));
//                        info.setUsage(obj.getString("usage"));
//                        list.add(info);
//                    }
//                    final List<ConsumptionDTO> consumptionInfo = list;
//                    Runnable runnable = new Runnable(){
//                        public void run(){
//                            addConsumption( db,consumptionInfo );
//
//                        }
//                    };
//                    Thread t = new Thread(runnable);t.start();
//                    updateChart(barChart, consumptionInfo);

                }catch (Exception er){
                    er.printStackTrace();
                    //mProgressDialog.dismiss();
                }

            }
        });
*/
        String token = SessionInfo.getInstance().getUserLogin().getToken();


        if(position==0) {
            if(isNetworkAvailable()) {

                final Call<List<ConsumptionItemDTO>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoItems(token);
                consumptionInfoCall.enqueue(new Callback<List<ConsumptionItemDTO>>() {
                    @Override
                    public void onResponse(Call<List<ConsumptionItemDTO>> call, Response<List<ConsumptionItemDTO>> response) {

                        if (response.isSuccessful()) {
                            BarChart barChart = view.findViewById(R.id.chart);

                            final List<ConsumptionItemDTO> consumptionInfo = response.body();
                            addConsumptionItem(db, consumptionInfo);
                            updateChart(barChart, consumptionInfo,null);
                        } else {
                            Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ConsumptionItemDTO>> call, Throwable t) {

                    }
                });

            }
            else{
                BarChart barChart = view.findViewById(R.id.chart);

                final List<ConsumptionItemDTO> consumptionInfo = getAllConsumptionItem(db);
                //addConsumption(db, consumptionInfo);
                updateChart(barChart, consumptionInfo,null);

            }
        }
        else if(position==1)
        {
            if(isNetworkAvailable()) {
                final Call<List<ConsumptionCategoryDTO>> consumptionInfoCall = new MontecitoClient().getClient().getConsumptionInfoCategory(token);
                consumptionInfoCall.enqueue(new Callback<List<ConsumptionCategoryDTO>>() {
                    @Override
                    public void onResponse(Call<List<ConsumptionCategoryDTO>> call, Response<List<ConsumptionCategoryDTO>> response) {

                        if (response.isSuccessful()) {
                            BarChart barChart = view.findViewById(R.id.chart);

                            final List<ConsumptionCategoryDTO> consumptionCategoryInfo = response.body();
                            //addConsumption( db,consumptionInfo );
                            addConsumptionCategory(db,consumptionCategoryInfo);

                            updateChart(barChart, null,consumptionCategoryInfo);
                        } else {
                            Toast.makeText(getActivity(), "Error occured!!!!", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<ConsumptionCategoryDTO>> call, Throwable t) {

                    }
                });
            }
            else{
                BarChart barChart = view.findViewById(R.id.chart);

                final List<ConsumptionCategoryDTO> consumptionCategoryInfo = getAllConsumptionCategory(db);
                //addConsumption( db,consumptionInfo );

                updateChart(barChart, null,consumptionCategoryInfo);

            }
        }
        else
        {


        }

    }


    public void updateChart(BarChart barChart, List<ConsumptionItemDTO> consumptionItemInfo,List<ConsumptionCategoryDTO> consumptionCategoryinfo){
        List<BarEntry> data = new ArrayList<>();
        final List<String> labels = new ArrayList<>();
        if(consumptionItemInfo!=null) {

            for (int i = 0; i < consumptionItemInfo.size(); i++) {
                data.add(new BarEntry((float) Double.parseDouble(consumptionItemInfo.get(i).getUsage()), i));
                labels.add(i, consumptionItemInfo.get(i).getItem());
            }
        }
        else
        {
            for (int i = 0; i < consumptionCategoryinfo.size(); i++) {
                data.add(new BarEntry((float) Double.parseDouble(consumptionCategoryinfo.get(i).getUsage()), i));
                labels.add(i, consumptionCategoryinfo.get(i).getCategory());
            }
        }
        BarDataSet dataSet = new BarDataSet(data,"Usage");

        // barChart.setDescription("");
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(true);
        barChart.getLegend().setEnabled(false);

        barChart.getXAxis().setDrawLabels(true);
        //barChart.setFitBars(true);

        //barChart.getAxisLeft().setAxisMinimum(0f);

        //dataSet.setColor(Color.BLUE);

        dataSet.setValueTextColor(Color.WHITE);barChart.getLegend().setTextColor(Color.WHITE);

        barChart.getAxisLeft().setTextColor(Color.WHITE);
        barChart.getXAxis().setTextColor(Color.WHITE);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        barChart.getXAxis().setXOffset(0);
        dataSet.setColors(getColorsForChart(data.size() , Color.RED , Color.GREEN));

        //Legend legend = barChart.getLegend();
        //legend.setForm(Legend.LegendForm.CIRCLE);

        BarData barData = new BarData(labels,dataSet);

        barChart.setData( barData );

        barChart.invalidate();

    }

    public static int[] getColorsForChart(int size, int end, int start){
        int[] gradient = new int[size];
        for(int i=0;i<size;i++){
            float ratio;
            if(size>1)
            {
                ratio= ((float)i)/(size-1);
            }
            else{
                ratio=((float)i)/size;
            }


            int red = (int)(Color.red(end)*ratio+Color.red(start)*(1-ratio));
            int green =(int) (Color.green(end)*ratio+Color.green(start)*(1-ratio));
            int blue =(int) (Color.blue(end)*ratio+Color.blue(start)*(1-ratio));


            gradient[i]=Color.rgb( red, green , blue );

        }

        return gradient;
    }
   private static void addConsumptionItem(final AppDatabase db,List<ConsumptionItemDTO> consumptionDetails) {
       db.consumptionItemDAO().deleteAll();
       db.consumptionItemDAO().insertAll(consumptionDetails);

    }
    private static List<ConsumptionItemDTO> getAllConsumptionItem(final AppDatabase db)
    {
        return db.consumptionItemDAO() .getAll();

    }

    private static void addConsumptionCategory(final AppDatabase db,List<ConsumptionCategoryDTO> consumptionDetails) {
        db.consumptionCategoryDAO().deleteAll();
        db.consumptionCategoryDAO().insertAll(consumptionDetails);

    }
    private static List<ConsumptionCategoryDTO> getAllConsumptionCategory(final AppDatabase db)
    {
        return db.consumptionCategoryDAO() .getAll();

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }




}
