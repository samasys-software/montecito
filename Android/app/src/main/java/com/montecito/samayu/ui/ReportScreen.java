package com.montecito.samayu.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
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
import com.github.mikephil.charting.utils.ColorTemplate;
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.ArrayList;
import java.util.List;

public class ReportScreen extends MontecitoBaseActivity {
    BarChart barChart;
    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_screen);
        DonutProgress donutProgress=(DonutProgress) findViewById(R.id.donut_progress);
        donutProgress.setProgress(3);
        barChart = (BarChart) findViewById(R.id.reportChart);
        pieChart = (PieChart) findViewById(R.id.pieChart);
        updateChart(barChart);
        updatePieDiagram(pieChart);




    }
    public void updateChart(BarChart barChart){
        List<BarEntry> data = new ArrayList<>();
        for(int i =0; i<5; i++){
            data.add( new BarEntry( i , 10));
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

    public void updatePieDiagram(PieChart pieChart){




    }

}
