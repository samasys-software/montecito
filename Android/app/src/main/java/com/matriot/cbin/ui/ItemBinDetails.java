package com.matriot.cbin.ui;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.matriot.cbin.R;
import com.matriot.cbin.db.AppDatabase;
import com.matriot.cbin.dto.ItemBinDetailsDTO;
import com.matriot.cbin.domain.Status;
import com.matriot.cbin.service.FormatNumber;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemBinDetails extends MontecitoBaseActivity
{
    ExpandableRelativeLayout expandableLayout1,itemDetailsLayout,alertSettingsLayout,cbinMovementLayout,ReplenishmentHistoryLayout,ReplenishmentDetailsLayout;
    ImageButton binButton,itemButton,alertButton,replenishmentDetailsButton,replenishmentHistoryButton,cbinMovementButton;
    ItemBinDetailsDTO binItems;
    TextView binItemPercentage;
    String itemBinId=SessionInfo.getInstance().getCurrentItemBinId();
    Context context;
    NumberFormat numberFormat = FormatNumber.getNumberFormat();
    private AppDatabase db;
    ListView cBinListView;

    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bin_details);
        context=this;
        db=AppDatabase.getAppDatabase(context);
        binItemPercentage=(TextView)findViewById(R.id.BinItem);
        binButton=(ImageButton)findViewById(R.id.binButton);
        itemButton=(ImageButton)findViewById(R.id.itemButton);
        alertButton=(ImageButton)findViewById(R.id.alertButton);
        replenishmentDetailsButton=(ImageButton)findViewById(R.id.ReplenishmentDetailsButton);
        replenishmentHistoryButton=(ImageButton)findViewById(R.id.ReplenishmentHistoryButton);
        cbinMovementButton=(ImageButton)findViewById(R.id.cbinMovementButton);
        cBinListView=(ListView)findViewById(R.id.cbinMovement);
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("One Moment Please..");



      if(isNetworkAvailable())
      {
          mProgressDialog.show();
          final Call<ItemBinDetailsDTO> itemBinDetails = new MontecitoClient().getClient().getItemBinDetails(itemBinId, SessionInfo.getInstance().getUserLogin().getToken());
          System.out.println("New Item Bin Api Call");
          itemBinDetails.enqueue(new Callback<ItemBinDetailsDTO>()
          {
              @Override
              public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response)
              {
                  if (response.code()==200)
                  {
                      binItems = response.body();
                      addItemBinDetails(db,binItems);

                      binItemPercentage.setText(numberFormat.format(binItems.getLastReading().getReading().getWeight() / binItems.getThresold().getMax() * 100) + "%");
                      binDetails(false);
                      mProgressDialog.cancel();

                  }
                  else {
                      if (response.code() == 401 || response.code() == 403) {
                          mProgressDialog.cancel();
                          Intent intent = new Intent(ItemBinDetails.this, LoginScreen.class);
                          startActivity(intent);

                      }
                  }
              }

              @Override
              public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t)
              {
                  mProgressDialog.cancel();
              }
          });
      }
      else
      {
          binItems = getAllItemBinDetails(db,itemBinId);
          binItemPercentage.setText(numberFormat.format(binItems.getLastReading().getReading().getWeight() / binItems.getThresold().getMax() * 100) + "%");
          binDetails(false);
      }

        binButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                binDetails();
            }
        });
        itemButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                ItemDetails();
            }
        });
        alertButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
              AlertDetails();
            }
        });
        replenishmentDetailsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                replenishmentDetails();
            }
        });
        replenishmentHistoryButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                replenishmentHistoryDetails();
            }
        });
        cbinMovementButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                cbinMovementDetails();
            }
        });

    }

    public void binDetails ()
    {
        binDetails(true);
    }

    public void binDetails(boolean toggle)
    {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.BinDetailsLayout);
        if(toggle)
        {
            expandableLayout1.toggle(); // toggle expand and collapse
        }

        if (expandableLayout1.isExpanded())
        {
            binButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            binButton.setImageResource(R.drawable.ic_up);
        }

        //RelativeLayout relativeLayout=(RelativeLayout) expandableLayout1.findViewById(R.id.bin);

        TextView binName=(TextView) findViewById(R.id.binName);
        TextView binLocation=(TextView) findViewById(R.id.binLocation);
        TextView binType=(TextView) findViewById(R.id.binType);
        TextView binDimension=(TextView) findViewById(R.id.binDimension);
        TextView cBinIdentity=(TextView) findViewById(R.id.cBinIdentity);
        TextView rfid=(TextView) findViewById(R.id.rfid);

            binName.setText(binItems.getCrateBin().getBrand() + ":" +binItems.getCrateBin().getName());
            binLocation.setText( binItems.getCurrDevice().getLocation());
            binType.setText(binItems.getCrateBin().getBinType().getName());
            binDimension.setText( binItems.getCrateBin().getDimension().getLength() + "X" + binItems.getCrateBin().getDimension().getWidth()+"X"+binItems.getCrateBin().getDimension().getHeight());
            rfid.setText(binItems.getRfId());
            cBinIdentity.setText(binItems.getCurrDevice().getName()+ "#" + binItems.getCurrDevice().getSlno());
    }

    public void ItemDetails()
    {
        itemDetailsLayout = (ExpandableRelativeLayout) findViewById(R.id.ItemDetailsLayout);
        itemDetailsLayout.toggle(); // toggle expand and collapse
        TextView itemName=(TextView) findViewById(R.id.itemName);
        LinearLayout layout=(LinearLayout) findViewById(R.id.itemLayout2) ;
        TextView itemDimension=(TextView) findViewById(R.id.itemDimension);
       // TextView itemVolume=(TextView) findViewById(R.id.itemVolume);
        TextView material=(TextView) findViewById(R.id.material);
        TextView units=(TextView) findViewById(R.id.units);
      //  TextView surface=(TextView) findViewById(R.id.surface);
        //TextView availability=(TextView) findViewById(R.id.availability);

        layout.setVisibility(View.GONE);
            itemName.setText(binItems.getItem().getName());
            material.setText(binItems.getItem().getMaterial());
            units.setText(binItems.getItem().getUom());
            itemDimension.setText(binItems.getItem().getDimension().getLength() + "X" + binItems.getItem().getDimension().getDia() + "(" + binItems.getItem().getDimension().getUom()+")");
           // itemVolume.setText("");
            //surface.setText("");
            //availability.setText("");

        if(itemDetailsLayout.isExpanded())
        {
            itemButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            itemButton.setImageResource(R.drawable.ic_up);
        }
    }

    public void AlertDetails()
    {
        alertSettingsLayout = (ExpandableRelativeLayout) findViewById(R.id.AlertSettingsLayout);
        SwitchCompat alertStatus=(SwitchCompat) findViewById(R.id.alertEnable);
        SwitchCompat changeAlert=(SwitchCompat) findViewById(R.id.itemChangeAlertEnabled);
        TextView notificationAlert=(TextView)findViewById(R.id.notificationAlert);
        //TextView calibrationFactor=(TextView)findViewById(R.id.calibrationFactor);
        alertSettingsLayout.toggle(); // toggle expand and collapse

        if(alertSettingsLayout.isExpanded())
        {
            alertButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            alertButton.setImageResource(R.drawable.ic_up);
        }

        if(binItems!=null)
        {
            changeAlert.setChecked(binItems.isItemAlert());
            alertStatus.setChecked(binItems.isStockAlert());
            notificationAlert.setText(((binItems.getThresold().getMin()/binItems.getThresold().getMax())*100)+"");
            // calibrationFactor.setText("");x
        }

     changeAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
     {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b)
         {
             Status status=new Status();
             status.setEnable(b);
             if(isNetworkAvailable())
             {
                 Call<ItemBinDetailsDTO> itemBinDetailsChange = new MontecitoClient().getClient().itemAlert(itemBinId, status, SessionInfo.getInstance().getUserLogin().getToken());
                 itemBinDetailsChange.enqueue(new Callback<ItemBinDetailsDTO>()
                 {
                     @Override
                     public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response)
                     {
                         if (response.code() == 200)
                         {
                             Toast.makeText(context, "Your Item Alert is Changed Successfully", Toast.LENGTH_LONG).show();
                         }
                         else if (response.code() == 401 || response.code() == 403) {
                             Intent intent = new Intent(ItemBinDetails.this, LoginScreen.class);
                             startActivity(intent);
                         }
                         else {

                         }

                     }

                     @Override
                     public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t)
                     {

                     }
                 });
             }
         }
     });

     alertStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
     {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b)
         {
             Status status=new Status();
             status.setEnable(b);
             if(isNetworkAvailable())
             {
                 Call<ItemBinDetailsDTO> itemBinDetailsChange = new MontecitoClient().getClient().stockAlert(itemBinId, status, SessionInfo.getInstance().getUserLogin().getToken());
                 itemBinDetailsChange.enqueue(new Callback<ItemBinDetailsDTO>()
                 {
                     @Override
                     public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response)
                     {
                         if (response.code() == 200)
                         {
                             ItemBinDetailsDTO binItems = response.body();
                             Toast.makeText(context, "Your Stock Alert Is Changed Successfully", Toast.LENGTH_LONG).show();
                         }
                         else
                         {
                             Intent intent = new Intent(ItemBinDetails.this, LoginScreen.class);
                             startActivity(intent);
                         }
                     }

                     @Override
                     public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t)
                     {

                     }
                 });
             }
         }
     });
    }

    public void replenishmentDetails()
    {
        ReplenishmentDetailsLayout = (ExpandableRelativeLayout) findViewById(R.id.ReplenishmentDetailsLayout);
        ReplenishmentDetailsLayout.toggle(); // toggle expand and collapse
        TextView triggerOn=(TextView)findViewById(R.id.triggeredOn);
        TextView quantity=(TextView)findViewById(R.id.quantity);
        TextView replenishmentStatus=(TextView)findViewById(R.id.percentage);

        if(binItems.getReplenishTask()!=null)
        {
            try
            {
                SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy HH:mm");
                String formattedDate=df.format(binItems.getReplenishTask().getCreated());
                triggerOn.setText(formattedDate);
                quantity.setText(String.valueOf(binItems.getReplenishTask().getTrigger()));
                replenishmentStatus.setText((binItems.getReplenishTask().getTrigger() / binItems.getThresold().getMax() * 100) + "%");
            }
            catch(Exception e)
            {

            }
        }

        if(ReplenishmentDetailsLayout.isExpanded())
        {
            replenishmentDetailsButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            replenishmentDetailsButton.setImageResource(R.drawable.ic_up);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    public void replenishmentHistoryDetails()
    {
        ReplenishmentHistoryLayout = (ExpandableRelativeLayout) findViewById(R.id.ReplenishmentHistoryLayout);

        ReplenishmentHistoryLayout.toggle(); // toggle expand and collapse
        ListView listView;
        listView=(ListView) findViewById(R.id.replenishmentHistroy);

        listView.setOnTouchListener(new ListView.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }

        });
        if(ReplenishmentHistoryLayout.isExpanded())
        {
            replenishmentHistoryButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            replenishmentHistoryButton.setImageResource(R.drawable.ic_up);
        }

        listView.setAdapter(new ReplenishmentHistroyAdapter(ItemBinDetails.this, binItems));

    }

    public void cbinMovementDetails()
    {
        cbinMovementLayout = (ExpandableRelativeLayout) findViewById(R.id.cbinMovementLayout);
        cbinMovementLayout.toggle(); // toggle expand and collapse

        if(cbinMovementLayout.isExpanded())
        {
            cbinMovementButton.setImageResource(R.drawable.ic_down);
        }
        else
        {
            cbinMovementButton.setImageResource(R.drawable.ic_up);
        }

        if(binItems!=null)
        {
            cBinListView.setAdapter(new CBinMovementAdapter(ItemBinDetails.this, binItems));
        }
    }

    private static void addItemBinDetails(final AppDatabase db, ItemBinDetailsDTO itemBins)
    {
        db.itemBinDetailsDAO().deleteAll(itemBins.getId());
        db.itemBinDetailsDAO().insertAll(itemBins);
    }
    private static ItemBinDetailsDTO getAllItemBinDetails(final AppDatabase db,String itemBinDetailsId)
    {
        return db.itemBinDetailsDAO().getAllItemBins(itemBinDetailsId);
    }

}
