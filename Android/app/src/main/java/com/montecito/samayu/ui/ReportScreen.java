package com.montecito.samayu.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
//import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportScreen extends MontecitoBaseActivity {
    BarChart barChart;
    PieChart pieChart;
    TextView activeCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        activeCount=(TextView) findViewById(R.id.count);

        final Call<ResponseBody> activeCountDTO=new MontecitoClient().getClient().getDevicesActiveCount( SessionInfo.getInstance().getUserLogin().getToken());
        activeCountDTO.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code()==200){
                    try {


                        String count = response.body().string();
                        activeCount.setText(count);
                    }
                    catch (Exception e){

                    }




                }
                else if(response.code()==401 || response.code() ==403){

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


        DonutProgress donutProgress=(DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setProgress(3);
        barChart = (BarChart) findViewById(R.id.reportChart);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        updateChart(barChart);
        updatePieDiagram(pieChart);
    }

    public void updateChart(BarChart barChart){
        List<BarEntry> data = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        for(int i =0; i<5; i++){
            data.add( new BarEntry( i , 10));

                labels.add("abc");
        }

        BarDataSet dataSet = new BarDataSet(data,"Usage");
        // barChart.setDescription("");
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.setDrawGridBackground(false);
        //barChart.setFitBars(true);


        //dataSet.setColor(Color.BLUE);

        dataSet.setValueTextColor(Color.WHITE);

        dataSet.setColors(getColorsForChart(data.size() , Color.RED , Color.GREEN));




        BarData barData = new BarData(labels,dataSet);
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

    public void updatePieDiagram(PieChart pieChart)
    {
        pieChart.setUsePercentValues(true);
        //ArrayList<PieEntry> yvalues = new ArrayList<PieEntry>();
        //yvalues.add(new PieEntry(1, 0));
//        yvalues.add(new PieEntry(1, 1));
//
//        PieDataSet dataSet = new PieDataSet(yvalues,"");
//
//        ArrayList<String> xVals = new ArrayList<String>();
//
//        xVals.add("January");
//        xVals.add("February");
//
//        PieData data = new PieData( dataSet);
//        // In Percentage term
//        data.setValueFormatter(new PercentFormatter());
//        // Default value
//        //data.setValueFormatter(new DefaultValueFormatter(0));
//        pieChart.setData(data);
//        pieChart.setDrawHoleEnabled(false);
//        pieChart.setDrawEntryLabels(false);
//        pieChart.setTransparentCircleRadius(25f);
//
//        dataSet.setDrawValues(false);
//        data.setDrawValues(false);
//
//        dataSet.setColors(ColorTemplate.PASTEL_COLORS);
//

       // pieChart.animateXY(1400, 1400);
    }

}
