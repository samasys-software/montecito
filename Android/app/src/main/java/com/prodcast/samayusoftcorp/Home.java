package com.prodcast.samayusoftcorp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
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

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }
    ListView listView;
    private TabLayout tabLayout;
    private ViewPager mViewPager;
    private WebSocketClient mWebSocketClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = findViewById(R.id.list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        getSupportActionBar().setDisplayShowTitleEnabled(false);


        mViewPager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        String token = SessionInfo.getInstance().getUserLogin().getToken();

        final Call<List<ItemAvailabilityDTO>> itemAvailablityDTOCall = new MontecitoClient().getClient().getItemAvailablityDTO(token);
        itemAvailablityDTOCall.enqueue(new Callback<List<ItemAvailabilityDTO>>() {
            @Override
            public void onResponse(Call<List<ItemAvailabilityDTO>> call, Response<List<ItemAvailabilityDTO>> response) {
                if (response.isSuccessful()) {



                    List<ItemAvailabilityDTO> itemAvailabilityDTOList = response.body();
                  //  Collections.sort(itemAvailabilityDTOList,new StatusComp());
                    listView.setAdapter(new TaskListAdapter(Home.this, itemAvailabilityDTOList));
                }
            }

            @Override
            public void onFailure(Call<List<ItemAvailabilityDTO>> call, Throwable t) {

            }
        });


    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }




    public void logout(MenuItem item){
        Intent intent = new Intent(Home.this, LoginScreen.class);
        startActivity(intent);
    }



   /* public void logout(){
        SessionInfo.destroy();
        File dir = getFilesDir();

       finish();
    }*/




  /*  class StatusComp implements Comparator<ItemAvailabilityDTO> {

        @Override
        public int compare(ItemAvailabilityDTO task1, ItemAvailabilityDTO task2) {
            if(task1.getStatus()>task2.getStatus()){
                return 1;
            }
            else if(task1.getStatus()<task2.getStatus()) {
                return -1;
            }
            else{
                return 0;
            }
        }
    }*/

}
