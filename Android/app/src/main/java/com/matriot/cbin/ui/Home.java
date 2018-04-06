package com.matriot.cbin.ui;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.dto.ItemAvailabilityDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;


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

public class Home extends MontecitoBaseActivity{

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
    ProgressDialog mProgressDialog;
    Context context;
    private AppDatabase db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context=this;
        db=AppDatabase.getAppDatabase(context);
        listView = findViewById(R.id.list);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
//       getSupportActionBar().setDisplayShowTitleEnabled(false);

        mViewPager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mProgressDialog=getProgressDialog(context);
        mProgressDialog.show();
        String token = SessionInfo.getInstance().getUserLogin().getToken();

        if(isNetworkAvailable())
        {
            Log.d("message","You are Online");

            final Call<List<ItemAvailabilityDTO>> itemAvailablityDTOCall = new MontecitoClient().getClient().getItemAvailablityDTO(token);
            itemAvailablityDTOCall.enqueue(new Callback<List<ItemAvailabilityDTO>>() {

                @Override
                public void onResponse(Call<List<ItemAvailabilityDTO>> call, Response<List<ItemAvailabilityDTO>> response) {

                    if(response.code()==200) {
                        List<ItemAvailabilityDTO> itemAvailabilityDTOList = response.body();
                        SessionInfo.getInstance().setMyReplenishmentTask(itemAvailabilityDTOList);
                        addItemAvailablity(db,itemAvailabilityDTOList);
                        //  Collections.sort(itemAvailabilityDTOList,new StatusComp());
                        //This line is commented for the purpose of server data testing
                        listView.setAdapter(new TaskListAdapter(Home.this, itemAvailabilityDTOList));
                        mProgressDialog.dismiss();
                    }
                    else if(response.code()==401 || response.code() ==403)
                    {
                        File dir =getFilesDir();
                        File file = new File(dir, "MontecitoLogin.txt");

                        boolean deleted = file.delete();
                        SessionInfo.getInstance().destroy();

                        Intent intent = new Intent(Home.this, LoginScreen.class);
                        startActivity(intent);
                    }
                    else {

                    }



                }

                @Override
                public void onFailure(Call<List<ItemAvailabilityDTO>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    Toast.makeText(context,"Network Error",Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(context,"Your data is in Offline",Toast.LENGTH_LONG).show();
            Log.d("message","You are Offline");
            List<ItemAvailabilityDTO> itemAvailabilityDTOList = getAllItemAvailablities(db);
            SessionInfo.getInstance().setMyReplenishmentTask(itemAvailabilityDTOList);
            listView.setAdapter(new TaskListAdapter(Home.this, itemAvailabilityDTOList));
            mProgressDialog.dismiss();

        }


        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            p.setMargins(0, 0, 10, 0);
            tab.requestLayout();
        }

      /*  SubscriptionManager.getInstance().subscribe("availability", new UISubscriptionListener(this) {


            @Override
            public void doOnUI(final JSONArray jsonArray) {
                //updateTasks(jsonArray);      This line is commented for the purpose of server data testing

            }
        });
*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SessionInfo.getInstance().setCurrentItemBinId(SessionInfo.getInstance().getMyReplenishmentTask().get(i).getItemBinId());
                Intent intent = new Intent(Home.this, ItemBinDetails.class);
                startActivity(intent);
            }
        });
    }


    public void updateTasks(JSONArray jsonArray) {
        try {

            List<ItemAvailabilityDTO> itemAvailabilityDTOList= new ArrayList<>();
            for(int i =0; i<jsonArray.length(); i++) {
                JSONObject obj = (JSONObject) jsonArray.get(i);
                ItemAvailabilityDTO item = new ItemAvailabilityDTO();
                item.setAvailable(obj.getString("available"));
                item.setId(obj.getString("id"));
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

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }




    public void logout(MenuItem item){
        //TODO: Add code to remove the login authentication code file and call SessionInfo.destroy

        Intent intent = new Intent(Home.this, LoginScreen.class);
        startActivity(intent);
    }






    private static void addItemAvailablity(final AppDatabase db, List<ItemAvailabilityDTO> itemAvailabilityDTOList) {
        db.itemAvailablityDAO().deleteAll();
        db.itemAvailablityDAO().insertAll(itemAvailabilityDTOList);

    }
    private static List<ItemAvailabilityDTO> getAllItemAvailablities(final AppDatabase db)
    {
        return db.itemAvailablityDAO().getAll();

    }


}
