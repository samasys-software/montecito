package com.montecito.samayu.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.montecito.samayu.dto.ConsumptionDTO;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NandhiniGovindasamy on 2/6/18.
 */

public class ChartFragment1 extends Fragment{

    Context context;

    int position;
    BarChart barChart;



    // newInstance constructor for creating fragment with arguments
    public static Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        ChartFragment1 chartFragment = new ChartFragment1();
        chartFragment.setArguments(bundle);
        return chartFragment;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=getActivity().getApplicationContext();
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

        String token = SessionInfo.getInstance().getUserLogin().getToken();


        if(position==0) {
            List<ConsumptionDTO> list = new ArrayList<ConsumptionDTO>();
            for(int i=0;i<1;i++) {

                ConsumptionDTO info = new ConsumptionDTO();
                info.setId("_id"+i);
                info.setItem("item");
                info.setUsage(""+10);
                list.add(info);
            }
            final List<ConsumptionDTO> consumptionInfo = list;
            updateChart(barChart,consumptionInfo);


        }
        else if(position==1)
        {
            List<ConsumptionDTO> list = new ArrayList<ConsumptionDTO>();
            for(int i=0;i<4;i++) {

                ConsumptionDTO info = new ConsumptionDTO();
                info.setId("_id"+i);
                info.setItem("item");
                info.setUsage(""+20);
                list.add(info);
            }
            final List<ConsumptionDTO> consumptionInfo = list;
            updateChart(barChart,consumptionInfo);

        }
        else
        {
            List<ConsumptionDTO> list = new ArrayList<ConsumptionDTO>();
                    for(int i=0;i<7;i++) {

                            ConsumptionDTO info = new ConsumptionDTO();
                            info.setId("_id"+i);
                            info.setItem("item");
                            info.setUsage(""+15);
                            list.add(info);
                       }
                       final List<ConsumptionDTO> consumptionInfo = list;
                    updateChart(barChart,consumptionInfo);
        }


    }


    public void updateChart(BarChart barChart, List<ConsumptionDTO> consumptionInfo){
        List<BarEntry> data = new ArrayList<>();
        for(int i =0; i<consumptionInfo.size(); i++){
            data.add( new BarEntry( i,(float) Double.parseDouble(consumptionInfo.get(i).getUsage())));
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
            float ratio = ((float)i)/(size-1);

            int red = (int)(Color.red(end)*ratio+Color.red(start)*(1-ratio));
            int green =(int) (Color.green(end)*ratio+Color.green(start)*(1-ratio));
            int blue =(int) (Color.blue(end)*ratio+Color.blue(start)*(1-ratio));


            gradient[i]=Color.rgb( red, green , blue );

        }

        return gradient;
    }

  /*  private static void addConsumption(final AppDatabase db,List<ConsumptionDTO> consumptionDetails) {
        db.consumptionDAO().deleteAll();
        db.consumptionDAO().insertAll(consumptionDetails);

    }
    private static List<ConsumptionDTO> getAllConsumption(final AppDatabase db)
    {
        return db.consumptionDAO().getAll();

    }*/




}
