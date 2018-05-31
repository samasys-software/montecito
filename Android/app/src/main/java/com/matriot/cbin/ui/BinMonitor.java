package com.matriot.cbin.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.dto.ItemBinDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;
import com.matriot.cbin.service.SubscriptionManager;
import com.matriot.cbin.service.UISubscriptionListener;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BinMonitor extends MontecitoBaseActivity {

    private GridLayoutManager gridLayoutManager;
    View diaView;
    RecyclerView recyclerView;
    ListView listView;
    ImageView cardViewImage,listViewImage,downArrow,upArrow;
    ViewStub stubCardview,stubListView;
    ProgressDialog mProgressDialog;
    Context context;
    LinearLayout  locationLayout, floorLayout;
    Spinner sortBy;
    RadioGroup rg;
    RadioButton ascButton,descButton,selectedSortButton;
    private AppDatabase db;
    //private int currentSortingMode =0;
    //final int SORTING_MODE_ASC=0;

    private int currentViewMode=0;
    final int VIEW_MODE_LISTVIEW=1;
    final int VIEW_MODE_CARDVIEW=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_monitor);
        context=this;
        db=AppDatabase.getAppDatabase(context);
        locationLayout=(LinearLayout) findViewById(R.id.locationLayout);
        floorLayout=(LinearLayout) findViewById(R.id.FloorLayout);
        locationLayout.setVisibility(View.GONE);
        floorLayout.setVisibility(View.GONE);
        cardViewImage = (ImageView) findViewById(R.id.cardviewImage);
        listViewImage=(ImageView) findViewById(R.id.listViewImage);
        downArrow=(ImageView) findViewById(R.id.down);
        upArrow=(ImageView) findViewById(R.id.up);
        gridLayoutManager = new GridLayoutManager(BinMonitor.this, 2);
        stubListView=(ViewStub)findViewById(R.id.stub_list);
        stubCardview=(ViewStub)findViewById(R.id.stub_card);
            // inflate the viewstub before get view
        stubListView.inflate();
        stubCardview.inflate();

        listView=(ListView)findViewById(R.id.listview);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
       // sortBy=(Spinner)findViewById(R.id.sortby);

        SubscriptionManager.getInstance().subscribe("itembin", new UISubscriptionListener(this) {


            @Override
            public void doOnUI(final JSONObject jsonArray) {
                try {
                    String itemBinId = jsonArray.getString("itemBin");
                    if(isNetworkAvailable()) {
                        final Call<ItemBinDTO> itemBinChanges = new MontecitoClient().getClient().getItemBinChangesDTO(itemBinId,SessionInfo.getInstance().getUserLogin().getToken());
                        itemBinChanges.enqueue(new Callback<ItemBinDTO>() {
                            @Override
                            public void onResponse(Call<ItemBinDTO> call, Response<ItemBinDTO> response) {
                                if (response.code()==200) {
                                    ItemBinDTO binItem = response.body();
                                    List<ItemBinDTO> binItems=SessionInfo.getInstance().getItemBinDetails();
                                    for(int i=0;i<binItems.size();i++){
                                        if(binItems.get(i).getId().equals(binItem.getId())){
                                            SessionInfo.getInstance().getItemBinDetails().get(i).getLastReading().getReading().setWeight(binItem.getLastReading().getReading().getWeight());
                                            break;
                                        }
                                    }

                                    addItemBins(db,binItems);
                                    setBinData();
                                }
                                else if (response.code() == 401 || response.code() == 403) {
                                    Intent intent = new Intent(BinMonitor.this, LoginScreen.class);
                                    startActivity(intent);
                                }
                                else {

                                }
                            }

                            @Override
                            public void onFailure(Call<ItemBinDTO> call, Throwable t) {
                                //mProgressDialog.dismiss();

                            }
                        });
                    }
                    else{


                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                // updateTasks(jsonArray);     // This line is commented for the purpose of server data testing

            }
        });



        if(isNetworkAvailable()) {
            final Call<List<ItemBinDTO>> itemBinAvailability = new MontecitoClient().getClient().getItemBinDTO(SessionInfo.getInstance().getUserLogin().getToken());
            itemBinAvailability.enqueue(new Callback<List<ItemBinDTO>>() {
                @Override
                public void onResponse(Call<List<ItemBinDTO>> call, Response<List<ItemBinDTO>> response) {
                    if (response.code()==200) {
                        List<ItemBinDTO> binItem = response.body();
                        SessionInfo.getInstance().setItemBinDetails(binItem);
                        addItemBins(db,binItem);
                        setBinData();
                    }
                    else if (response.code() == 401 || response.code() == 403) {
                        Intent intent = new Intent(BinMonitor.this, LoginScreen.class);
                        startActivity(intent);
                    }
                    else {

                    }
                }

                @Override
                public void onFailure(Call<List<ItemBinDTO>> call, Throwable t) {
                    //mProgressDialog.dismiss();

                }
            });
        }
        else{
            List<ItemBinDTO> binItem = getAllItemBins(db);
            SessionInfo.getInstance().setItemBinDetails(binItem);
            setBinData();

        }


        assert recyclerView != null;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(gridLayoutManager);
        //mProgressDialog=getProgressDialog(context);
        //get currentmode view in shared preference
        SharedPreferences sharedPreferences=getSharedPreferences("ViewMode",MODE_PRIVATE);
        currentViewMode=sharedPreferences.getInt("currentViewMode",VIEW_MODE_CARDVIEW);//default viewmode

      /*  */

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
              SessionInfo.getInstance().setCurrentItemBinId(SessionInfo.getInstance().getItemBinDetails().get(i).getId());
              Intent intent = new Intent(BinMonitor.this, ItemBinDetails.class);
               startActivity(intent);

           }
       });

       downArrow.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {


                                            //  final TextView total=(TextView) findViewById(R.id.total);
                                            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(BinMonitor.this, R.style.AlertTheme);
                                            alertDialog.setTitle(" Sorting Details");
                                            alertDialog.setCancelable(true);
                                            LayoutInflater inflater = BinMonitor.this.getLayoutInflater();
                                             diaView = inflater.inflate(R.layout.sort_by_details, null);
                                            alertDialog.setView(diaView);
                                            sortBy = (Spinner) diaView.findViewById(R.id.sortby);
                                            getSortValues();
                                             rg = (RadioGroup) diaView.findViewById(R.id.rg);
                                             ascButton=(RadioButton) diaView.findViewById(R.id.asc);
                                             descButton=(RadioButton) diaView.findViewById(R.id.desc);

                                             rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                                            {
                                                public void onCheckedChanged(RadioGroup group, int checkedId) {
                                                    switch(checkedId){
                                                        case R.id.asc:
                                                            // do operations specific to this selection
                                                            descButton.setChecked(false);



                                                            break;
                                                        case R.id.desc:
                                                            // do operations specific to this selection
                                                            ascButton.setChecked(false);

                                                            // find the radiobutton by returned id

                                                            break;

                                                    }

                                                }


                                            });



                                            alertDialog.setNegativeButton("CANCEL",
                                                    new DialogInterface.OnClickListener() {
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // Write your code here to execute after dialog
                                                            dialog.cancel();

                                                        }
                                                    });

                                            alertDialog.setPositiveButton("Ok",
                                                    new DialogInterface.OnClickListener() {

                                                        public void onClick(DialogInterface dialog, int which) {
                                                            // Write your code here to execute after dialog
                                                            String selectedValue=(String)sortBy.getSelectedItem();
                                                            String value=getSortByValue(selectedValue);
                                                            int selectedId=rg.getCheckedRadioButtonId();
                                                            selectedSortButton=(RadioButton) diaView.findViewById(selectedId);
                                                            String sortingValue=selectedSortButton.getText().toString();
                                                            Toast.makeText(context,sortingValue+"HAi",Toast.LENGTH_LONG).show();
                                                            sortingAPI(value,sortingValue);
                                                        }

                                                    });
                                            //alertDialog.show();
                                            final AlertDialog checkout = alertDialog.create();
                                            checkout.show();


                                            //setBinData();

              /* downArrow.setVisibility(View.GONE);
               upArrow.setVisibility(View.VISIBLE);
               String selectedValue=(String)sortBy.getSelectedItem();
               String value=getSortByValue(selectedValue);
               System.out.println(value);
               sortingAPI(value,"asc");*/


                                            /*sortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                List<ItemBinDTO> binItem = SessionInfo.getInstance().getItemBinDetails();

                                                @Override
                                                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                    String selectedValue = adapterView.getSelectedItem().toString();

                                                    String value = getSortByValue(selectedValue);

                                                    System.out.println(value);
                                                    sortingAPI(value, "asc");

                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> adapterView) {

                                                }
                                            });*/
                                        }
       });

   /*     upArrow.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
               // setBinData();
                upArrow.setVisibility(View.GONE);
                downArrow.setVisibility(View.VISIBLE);
                String selectedValue=(String)sortBy.getSelectedItem();
                String value=getSortByValue(selectedValue);
                System.out.println(value);
                sortingAPI(value,"desc");
            }
        });*/

    }
    @Override
    public void onResume(){
        super.onResume();
//        Toast.makeText(context,"Sorry! Your Item Bin Is Empty",Toast.LENGTH_LONG).show();
    }


    public void setBinData() {
        List<ItemBinDTO> binItem=SessionInfo.getInstance().getItemBinDetails();
        if (VIEW_MODE_CARDVIEW == currentViewMode) {
            stubCardview.setVisibility(View.VISIBLE);
            stubListView.setVisibility(View.GONE);
            recyclerView.setAdapter(new BinMonitorecyclerViewAdapter(BinMonitor.this, binItem));
           // mProgressDialog.dismiss();
        }
        else {
            stubListView.setVisibility(View.VISIBLE);
            stubCardview.setVisibility(View.GONE);
            listView.setAdapter(new BinMonitorListViewAdapter(BinMonitor.this, binItem));
           // mProgressDialog.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,((ActionMenuView)findViewById(R.id.actionMenuView)).getMenu());
        return true;
    }

    private static void addItemBins(final AppDatabase db, List<ItemBinDTO> itemBins) {

        db.itemBinDAO().deleteAll();
        db.itemBinDAO().insertAll(itemBins);

    }
    private static List<ItemBinDTO> getAllItemBins(final AppDatabase db)
    {
        return db.itemBinDAO().getAllItemBins();

    }

    private String getSortByValue(String selectedValue)
    {
        String values;
        if(selectedValue.equalsIgnoreCase("By Item")){
            //Collections.sort(binItem);
            values = "item";
        }
        else if (selectedValue.equalsIgnoreCase("By Location"))
        {
            values = "location";
        }
        else if (selectedValue.equalsIgnoreCase("By Stock"))
        {
            values = "stock";
        }
        else
        {
            values = "please selected";
        }
        return  values;

    }
    private void sortingAPI(String value,String order){
        if(isNetworkAvailable()) {
            final Call<List<ItemBinDTO>> itemBinAvailability = new MontecitoClient().getClient().getSortByDetails(value,order,SessionInfo.getInstance().getUserLogin().getToken());
            itemBinAvailability.enqueue(new Callback<List<ItemBinDTO>>() {
                @Override
                public void onResponse(Call<List<ItemBinDTO>> call, Response<List<ItemBinDTO>> response) {
                    if (response.code()==200) {
                        List<ItemBinDTO> binItem = response.body();
                        SessionInfo.getInstance().setItemBinDetails(binItem);
                        setBinData();
                    }
                    else if (response.code() == 401 || response.code() == 403) {
                        Intent intent = new Intent(BinMonitor.this, LoginScreen.class);
                        startActivity(intent);
                    }
                    else {

                    }
                }

                @Override
                public void onFailure(Call<List<ItemBinDTO>> call, Throwable t) {
                    //mProgressDialog.dismiss();

                }
            });
        }
        else{
            List<ItemBinDTO> binItem = getAllItemBins(db);
            SessionInfo.getInstance().setItemBinDetails(binItem);
            setBinData();

        }


    }

    private  void getSortValues(){
        List<String> sortByValues=new ArrayList<>();
        sortByValues.add("Please Select");
        sortByValues.add("By Item");
        sortByValues.add("By Location");
        sortByValues.add("By Stock");


        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sortByValues);
        sortBy.setAdapter(dataAdapter);
    }

}
