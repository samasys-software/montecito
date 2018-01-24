package com.montecito.samayu.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.montecito.samayu.dto.ItemAvailabilityDTO;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.montecito.samayu.service.SubscriptionListner;
import com.montecito.samayu.service.SubscriptionManager;
import com.prodcast.samayu.samayusoftcorp.R;

import org.java_websocket.client.WebSocketClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Preethiv on 1/13/2018.
 */

public class Home extends AppCompatActivity implements SubscriptionListner {
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


        SubscriptionManager.getInstance().subscribe("availability",this);
        for(int i=0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 10, 0);
            tab.requestLayout();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }




    public void logout(MenuItem item){
        File dir =getFilesDir();
        File file = new File(dir, "MontecitoLogin.txt");

        boolean deleted = file.delete();
        SessionInfo.getInstance().destroy();

        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }



    @Override
    public void onMessage(final JSONArray jsonArray) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {

                    List<ItemAvailabilityDTO> itemAvailabilityDTOList= new ArrayList<>();
                    for(int i =0; i<jsonArray.length(); i++) {
                        JSONObject obj = (JSONObject) jsonArray.get(i);
                        ItemAvailabilityDTO item = new ItemAvailabilityDTO();
                        item.setAvailable(obj.getString("available"));
                        item.set_id(obj.getString("_id"));
                        item.setItem(obj.getString("item"));
                        item.setLocation(obj.getString("location"));
                        item.setStatus(obj.getString("status"));
                        itemAvailabilityDTOList.add(item);
                    }
                    listView.setAdapter(new TaskListAdapter(Home.this, itemAvailabilityDTOList));
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        });



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