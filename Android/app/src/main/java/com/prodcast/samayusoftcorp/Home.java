package com.prodcast.samayusoftcorp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private WebSocketClient mWebSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mViewPager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        String token = SessionInfo.instance().getToken();

        final Call<List<ItemAvailabilityDTO>> itemAvailablityDTOCall = new MontecitoClient().getClient().getItemAvailablityDTO(token);
        itemAvailablityDTOCall.enqueue(new Callback<List<ItemAvailabilityDTO>>() {
            @Override
            public void onResponse(Call<List<ItemAvailabilityDTO>> call, Response<List<ItemAvailabilityDTO>> response) {
                if (response.isSuccessful()) {
                    ListView listView = findViewById(R.id.list);

                    List<ItemAvailabilityDTO> itemAvailabilityDTOList = response.body();
                    listView.setAdapter(new TaskListAdapter(Home.this, itemAvailabilityDTOList));
                }
            }

            @Override
            public void onFailure(Call<List<ItemAvailabilityDTO>> call, Throwable t) {

            }
        });


    }
}
