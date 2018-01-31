package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.ListView;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

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

    private int currentViewMode;
    final int VIEW_MODE_LISTVIEW=1;
    final int VIEW_MODE_CARDVIEW=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_monitor);
        context=this;
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
        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        mProgressDialog=getProgressDialog(context);



        //get currentmode view in shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode=sharedPreferences.getInt("currentViewMode",VIEW_MODE_LISTVIEW);
        setBinData();
        cardViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.show();
                currentViewMode=1;

               setBinData();
                cardViewImage.setVisibility(View.GONE);
                listViewImage.setVisibility(View.VISIBLE);
            }
        });


       listViewImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.show();
                currentViewMode=0;
                setBinData();
                listViewImage.setVisibility(View.GONE);
                cardViewImage.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setBinData() {


        String token = SessionInfo.getInstance().getUserLogin().getToken();
        final Call<List<ItemAvailabilityDTO>> itemAvailability = new MontecitoClient().getClient().getItemAvailablityDTO(token);
        itemAvailability.enqueue(new Callback<List<ItemAvailabilityDTO>>() {
            @Override
            public void onResponse(Call<List<ItemAvailabilityDTO>> call, Response<List<ItemAvailabilityDTO>> response) {
                if(response.isSuccessful()){
                    List<ItemAvailabilityDTO> binItem = response.body();
                                if (VIEW_MODE_CARDVIEW == currentViewMode) {
                                    stubListView.setVisibility(View.VISIBLE);
                                    stubCardview.setVisibility(View.GONE);
                                    recyclerView.setAdapter(new BinMonitorecyclerViewAdapter(BinMonitor.this, binItem));
                                    mProgressDialog.dismiss();

                                } else {
                                    stubCardview.setVisibility(View.VISIBLE);
                                    stubListView.setVisibility(View.GONE);
                                    listView.setAdapter(new BinMonitorListViewAdapter(BinMonitor.this, binItem));
                                    mProgressDialog.dismiss();
                                }
                  }
               }


            @Override
            public void onFailure(Call<List<ItemAvailabilityDTO>> call, Throwable t) {
                mProgressDialog.dismiss();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }

}
