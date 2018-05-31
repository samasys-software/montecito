package com.matriot.cbin.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

//import com.github.mikephil.charting.data.PieEntry;
import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.dto.AverageDTO;
import com.matriot.cbin.dto.CountDTO;
import com.matriot.cbin.dto.OnTimeDTO;
import com.matriot.cbin.dto.TopItemsDTO;
import com.matriot.cbin.service.CircleProgressDesign;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportScreen extends MontecitoBaseActivity {
    BarChart barChart;
    PieChart pieChart;
    TextView activeCount;


    DonutProgress donutProgress;
    Context context;
    CircleProgressDesign circleProgress;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        context=this;
        db=AppDatabase.getAppDatabase(context);
        activeCount=(TextView) findViewById(R.id.count);
        donutProgress=(DonutProgress) findViewById(R.id.donut_progress);
        circleProgress=(CircleProgressDesign) findViewById(R.id.circle_progress);
        barChart = (BarChart) findViewById(R.id.reportChart);


        if(isNetworkAvailable()) {
            final Call<CountDTO> activeCountDTO = new MontecitoClient().getClient().getDevicesActiveCount(SessionInfo.getInstance().getUserLogin().getToken());
            activeCountDTO.enqueue(new Callback<CountDTO>() {
                @Override
                public void onResponse(Call<CountDTO> call, Response<CountDTO> response) {
                    if (response.code() == 200) {
                        try {
                            CountDTO count = response.body();
                            addLocalDevicesActiveCount(db,count);
                            activeCount.setText(String.valueOf(count.getCount()));
                        } catch (Exception e) {

                        }
                    }
                    else if (response.code() == 401 || response.code() == 403) {
                            Intent intent = new Intent(ReportScreen.this, LoginScreen.class);
                            startActivity(intent);
                    }
                    else{

                    }
                }

                @Override
                public void onFailure(Call<CountDTO> call, Throwable t) {

                }
            });
        }
        else{
            try {
                CountDTO count = getLocalDevicesActiveCount(db);
                activeCount.setText(String.valueOf(count.getCount()));
            }
            catch(NullPointerException e){
                Intent intent=new Intent(ReportScreen.this,NetworkProblem.class);
                startActivity(intent);
                return;
            }

        }
        if(isNetworkAvailable()) {
            final Call<OnTimeDTO> onTimeDTO = new MontecitoClient().getClient().getOnTime(SessionInfo.getInstance().getUserLogin().getToken());
            onTimeDTO.enqueue(new Callback<OnTimeDTO>() {
                @Override
                public void onResponse(Call<OnTimeDTO> call, Response<OnTimeDTO> response) {
                    if (response.code() == 200) {
                        try {

                            OnTimeDTO onTime = response.body();
                            addLocalOnTime(db, onTime);
                            donutProgress.setProgress(Float.valueOf(onTime.getPercent()));

                        } catch (Exception e) {

                        }

                    }
                    else if (response.code() == 401 || response.code() == 403) {
                            Intent intent = new Intent(ReportScreen.this, LoginScreen.class);
                            startActivity(intent);
                    }
                    else{
                    }
                }

                @Override
                public void onFailure(Call<OnTimeDTO> call, Throwable t) {

                }
            });
        }
        else{
            try {
                OnTimeDTO onTime = getLocalOnTime(db);
                //getLocalOnTime(db, onTime);
                donutProgress.setProgress(Float.valueOf(onTime.getPercent()));
            }
            catch(NullPointerException e){
                Intent intent=new Intent(ReportScreen.this,NetworkProblem.class);
                startActivity(intent);
                return;
            }
        }
        if(isNetworkAvailable()) {
            final Call<AverageDTO> averageDTO = new MontecitoClient().getClient().getAverage(SessionInfo.getInstance().getUserLogin().getToken());
            averageDTO.enqueue(new Callback<AverageDTO>() {
                @Override
                public void onResponse(Call<AverageDTO> call, Response<AverageDTO> response) {
                    if (response.code() == 200) {
                        try {


                            AverageDTO averageDTO = response.body();
                            addLocalAverage(db,averageDTO);
                            circleProgress.setProgress(Float.valueOf(averageDTO.getAverage()));

                        } catch (Exception e) {

                        }

                    }
                    else if (response.code() == 401 || response.code() == 403) {
                            Intent intent = new Intent(ReportScreen.this, LoginScreen.class);
                            startActivity(intent);
                    }
                    else{
                    }

                }

                @Override
                public void onFailure(Call<AverageDTO> call, Throwable t) {

                }
            });
        }
        else{
            try {
                AverageDTO averageDTO = getLocalAverage(db);
                circleProgress.setProgress(Float.valueOf(averageDTO.getAverage()));
            }
            catch(NullPointerException e){
                Intent intent=new Intent(ReportScreen.this,NetworkProblem.class);
                startActivity(intent);
                return;

            }
        }

        if(isNetworkAvailable()) {
            final Call<List<TopItemsDTO>> topItemsDTO = new MontecitoClient().getClient().getTopItems(SessionInfo.getInstance().getUserLogin().getToken());
            topItemsDTO.enqueue(new Callback<List<TopItemsDTO>>() {
                @Override
                public void onResponse(Call<List<TopItemsDTO>> call, Response<List<TopItemsDTO>> response) {
                    if (response.code() == 200) {
                        try {
                            List<TopItemsDTO> topItems = response.body();
                            addLocalTopItems(db,topItems);
                            updateChart(barChart, topItems);
                        } catch (Exception e) {

                        }
                    }
                    else if (response.code() == 401 || response.code() == 403) {
                            Intent intent = new Intent(ReportScreen.this, LoginScreen.class);
                            startActivity(intent);
                    }
                    else{

                    }
                }

                @Override
                public void onFailure(Call<List<TopItemsDTO>> call, Throwable t) {

                }
            });
        }
        else{
            try {
                List<TopItemsDTO> topItems = getLocalTopItems(db);
                updateChart(barChart, topItems);
            }
            catch(NullPointerException e){
                Intent intent=new Intent(ReportScreen.this,NetworkProblem.class);
                startActivity(intent);
                return;
            }

        }

      //  pieChart = (PieChart) findViewById(R.id.pieChart);

        //updatePieDiagram(pieChart);
    }

    public void updateChart(BarChart barChart, List<TopItemsDTO> topItems){
        try {
            List<BarEntry> data = new ArrayList<>();
            final List<String> labels = new ArrayList<>();
            if (topItems != null) {

                for (int i = 0; i < topItems.size(); i++) {
                    data.add(new BarEntry(topItems.get(i).getQuantity(), i));
                    labels.add(i, topItems.get(i).getItem());
                }
            }
            BarDataSet dataSet = new BarDataSet(data, "Top Items");

            // barChart.setDescription("");
            barChart.getAxisRight().setDrawLabels(false);
            barChart.getAxisLeft().setDrawLabels(true);
            barChart.getLegend().setEnabled(false);

            barChart.getXAxis().setDrawLabels(true);
            //barChart.setFitBars(true);

            //barChart.getAxisLeft().setAxisMinimum(0f);

            //dataSet.setColor(Color.BLUE);

            dataSet.setValueTextColor(Color.WHITE);
            barChart.getLegend().setTextColor(Color.WHITE);

            barChart.getAxisLeft().setTextColor(Color.WHITE);
            barChart.getXAxis().setTextColor(Color.WHITE);
            barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
            barChart.getXAxis().setXOffset(0);
            barChart.getXAxis().setTextSize(16);
            dataSet.setColors(getColorsForChart(data.size(), Color.RED, Color.GREEN));

            //Legend legend = barChart.getLegend();
            //legend.setForm(Legend.LegendForm.CIRCLE);

            BarData barData = new BarData(labels, dataSet);

            barChart.setData(barData);
            barChart.animateY(1000);
            barChart.invalidate();
            barData.setDrawValues(false);
        }
        catch(IndexOutOfBoundsException e){
            Intent intent=new Intent(ReportScreen.this,NetworkProblem.class);
            startActivity(intent);
            return;
        }

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


    private static void addLocalDevicesActiveCount(final AppDatabase db, CountDTO counts) {
        db.countDAO().deleteAll();
        db.countDAO().insertAll(counts);

    }
    private static CountDTO  getLocalDevicesActiveCount(final AppDatabase db)
    {
        return db.countDAO().getAll();

    }

    private static void addLocalOnTime(final AppDatabase db, OnTimeDTO onTimeDTO) {
        db.onTimeDAO().deleteAll();
        db.onTimeDAO().insertAll(onTimeDTO);

    }
    private static OnTimeDTO  getLocalOnTime(final AppDatabase db)
    {
        return db.onTimeDAO().getAll();

    }

    private static void addLocalAverage(final AppDatabase db, AverageDTO averageDTO) {
        db.averageDAO().deleteAll();
        db.averageDAO().insertAll(averageDTO);

    }
    private static AverageDTO  getLocalAverage(final AppDatabase db)
    {
        return db.averageDAO().getAll();

    }

    private static void addLocalTopItems(final AppDatabase db, List<TopItemsDTO> topItemsDTOList) {
        db.topItemsDAO().deleteAll();
        db.topItemsDAO().insertAll(topItemsDTOList);

    }
    private static List<TopItemsDTO>  getLocalTopItems(final AppDatabase db)
    {
        return db.topItemsDAO().getAll();

    }
}
