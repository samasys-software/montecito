package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BinMonitor extends MontecitoBaseActivity {

    private GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    ListView listView;
    ImageView cardViewImage,listViewImage;
    ViewStub stubCardview,stubListView;
    ProgressDialog mProgressDialog;
    Context context;
    LinearLayout  locationLayout, floorLayout;
    Spinner sortBy;

    private int currentViewMode=0;
    final int VIEW_MODE_LISTVIEW=1;
    final int VIEW_MODE_CARDVIEW=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_monitor);
        context=this;
        locationLayout=(LinearLayout) findViewById(R.id.locationLayout);
        floorLayout=(LinearLayout) findViewById(R.id.FloorLayout);
        locationLayout.setVisibility(View.GONE);
        floorLayout.setVisibility(View.GONE);
        cardViewImage = (ImageView) findViewById(R.id.cardviewImage);
        listViewImage=(ImageView) findViewById(R.id.listViewImage);
        gridLayoutManager = new GridLayoutManager(BinMonitor.this, 2);
        stubListView=(ViewStub)findViewById(R.id.stub_list);
        stubCardview=(ViewStub)findViewById(R.id.stub_card);
            // inflate the viewstub before get view
        stubListView.inflate();
        stubCardview.inflate();

        listView=(ListView)findViewById(R.id.listview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        sortBy=(Spinner)findViewById(R.id.sortby);

        List<String> sortByValues=new ArrayList<>();
        sortByValues.add("Please Select");
        sortByValues.add("By Name");
        sortByValues.add("By Location");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sortByValues);
        sortBy.setAdapter(dataAdapter);


        final Call<List<ItemBinDTO>> itemBinAvailability = new MontecitoClient().getClient().getItemBinDTO();
        itemBinAvailability.enqueue(new Callback<List<ItemBinDTO>>() {
            @Override
            public void onResponse(Call<List<ItemBinDTO>> call, Response<List<ItemBinDTO>> response) {
                if (response.isSuccessful()) {
                    List<ItemBinDTO> binItem = response.body();
                    SessionInfo.getInstance().setItemBinDetails(binItem);
                    setBinData();
                }
            }

                @Override
                public void onFailure(Call<List<ItemBinDTO>> call, Throwable t) {
                    mProgressDialog.dismiss();

                }
            });

        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        mProgressDialog=getProgressDialog(context);
        //get currentmode view in shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode=sharedPreferences.getInt("currentViewMode",VIEW_MODE_CARDVIEW);//default viewmode

        sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            List<ItemBinDTO> binItem=SessionInfo.getInstance().getItemBinDetails();

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedValue=adapterView.getSelectedItem().toString();
                if(selectedValue.equalsIgnoreCase("By Name")){
                    //Collections.sort(binItem);
                }
                else{
                    System.out.println(selectedValue);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
       cardViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               currentViewMode=0;
                setBinData();
                cardViewImage.setVisibility(View.GONE);
                listViewImage.setVisibility(View.VISIBLE);
            }
        });

       listViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentViewMode=1;
                setBinData();
                listViewImage.setVisibility(View.GONE);
                cardViewImage.setVisibility(View.VISIBLE);
            }
        });
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              SessionInfo.getInstance().setCurrentItem(SessionInfo.getInstance().getItemBinDetails().get(i));
               Intent intent = new Intent(BinMonitor.this, ItemBinDetails.class);
               startActivity(intent);

           }
       });

    }

    public void setBinData() {
        List<ItemBinDTO> binItem=SessionInfo.getInstance().getItemBinDetails();
        if (VIEW_MODE_CARDVIEW == currentViewMode) {
            stubCardview.setVisibility(View.VISIBLE);
            stubListView.setVisibility(View.GONE);
            recyclerView.setAdapter(new BinMonitorecyclerViewAdapter(BinMonitor.this, binItem));
            mProgressDialog.dismiss();
        }
        else {
            stubListView.setVisibility(View.VISIBLE);
            stubCardview.setVisibility(View.GONE);
            listView.setAdapter(new BinMonitorListViewAdapter(BinMonitor.this, binItem));
            mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }


}
