package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.montecito.samayu.domain.UserLogin;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
import com.montecito.samayu.dto.LoginDTO;
import com.montecito.samayu.dto.Status;
import com.montecito.samayu.service.MontecitoClient;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ItemBinDetails extends MontecitoBaseActivity {
    ExpandableRelativeLayout expandableLayout1,itemDetailsLayout,alertSettingsLayout,cbinMovementLayout,ReplenishmentHistoryLayout,ReplenishmentDetailsLayout;
    ImageButton binButton,itemButton,alertButton,replenishmentDetailsButton,replenishmentHistoryButton,cbinMovementButton;
    ItemBinDetailsDTO binItems;
    TextView binItemPercentage;
    String itemBinId=SessionInfo.getInstance().getCurrentItemBinId();
    Context context;



    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bin_details);
        context=this;
        binItemPercentage=findViewById(R.id.BinItem);
        binButton=(ImageButton)findViewById(R.id.binButton);
        itemButton=(ImageButton)findViewById(R.id.itemButton);
        alertButton=(ImageButton)findViewById(R.id.alertButton);
        replenishmentDetailsButton=(ImageButton)findViewById(R.id.ReplenishmentDetailsButton);
        replenishmentHistoryButton=(ImageButton)findViewById(R.id.ReplenishmentHistoryButton);
        cbinMovementButton=(ImageButton)findViewById(R.id.cbinMovementButton);




      final Call<ItemBinDetailsDTO> itemBinDetails=new MontecitoClient().getClient().getItemBinDetails(itemBinId,SessionInfo.getInstance().getUserLogin().getToken());
        System.out.println("New Item Bin Api Call");
        itemBinDetails.enqueue(new Callback<ItemBinDetailsDTO>() {
            @Override
            public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response) {
                if(response.isSuccessful()){
                    binItems=response.body();
                }
            }

            @Override
            public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t) {

            }
        });


        //nDetails(false);
        binButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binDetails();

            }
        });
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDetails();

            }
        });
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AlertDetails();

            }
        });
        replenishmentDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replenishmentDetails();

            }
        });
        replenishmentHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replenishmentHistoryDetails();

            }
        });
        cbinMovementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbinMovementDetails();

            }
        });



    }
    public void binDetails (){

        binDetails(true);
    }
    public void binDetails(boolean toggle) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.BinDetailsLayout);
        if(toggle) {
            expandableLayout1.toggle(); // toggle expand and collapse

        }
        if (expandableLayout1.isExpanded()) {
            binButton.setImageResource(R.drawable.downarrow);
        } else {
            binButton.setImageResource(R.drawable.uparrow);
        }

        //RelativeLayout relativeLayout=(RelativeLayout) expandableLayout1.findViewById(R.id.bin);
        binItemPercentage.setText((binItems.getLastReading().getReading().getWeight()/binItems.getThresold(). getMax() * 100)+"%");
        TextView binName=(TextView) findViewById(R.id.binName);
        TextView binLocation=(TextView) findViewById(R.id.binLocation);
        TextView binType=(TextView) findViewById(R.id.binType);
        TextView binDimension=(TextView) findViewById(R.id.binDimension);
        TextView cBinIdentity=(TextView) findViewById(R.id.cBinIdentity);
        TextView rfid=(TextView) findViewById(R.id.rfid);

            binName.setText(binItems.getCrateBin().getBrand() + ":" +binItems.getCrateBin().getName());
            binLocation.setText( binItems.getCurrDevice().getName());
            binType.setText("null"); //binItems.getCrateBin().getBinType().getName());
            binDimension.setText( "null");  //binItems.getCrateBin().getDimension().getLength() + "X" + binItems.getCrateBin().getDimension().getWidth() + "X"+ binItems.getCrateBin().getDimension().getHeight());
            rfid.setText(binItems.getRfId());
            cBinIdentity.setText(binItems.getCurrDevice() + "#" + binItems.getCurrDevice());
    }

    public void ItemDetails() {
        itemDetailsLayout = (ExpandableRelativeLayout) findViewById(R.id.ItemDetailsLayout);
        itemDetailsLayout.toggle(); // toggle expand and collapse
        TextView itemName=(TextView) findViewById(R.id.itemName);
        TextView itemDimension=(TextView) findViewById(R.id.itemDimension);
        TextView itemVolume=(TextView) findViewById(R.id.itemVolume);
        TextView material=(TextView) findViewById(R.id.material);
        TextView units=(TextView) findViewById(R.id.units);
        TextView surface=(TextView) findViewById(R.id.surface);
        TextView availability=(TextView) findViewById(R.id.availability);


            itemName.setText(binItems.getItem().getName());
            material.setText(binItems.getItem().getMaterial());
            units.setText(binItems.getItem().getUom());
            itemDimension.setText("null");  //binItems.getItem().getDimension());
            itemVolume.setText("null");
            surface.setText("null");
            availability.setText("null");

        if(itemDetailsLayout.isExpanded())
        {
            itemButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            itemButton.setImageResource(R.drawable.uparrow);
        }
    }

    public void AlertDetails() {
        alertSettingsLayout = (ExpandableRelativeLayout) findViewById(R.id.AlertSettingsLayout);
        SwitchCompat alertStatus=(SwitchCompat) findViewById(R.id.alertEnable);
        SwitchCompat changeAlert=(SwitchCompat) findViewById(R.id.itemChangeAlertEnabled);

        TextView notificationAlert=(TextView)findViewById(R.id.notificationAlert);
        TextView calibrationFactor=(TextView)findViewById(R.id.calibrationFactor);
        alertSettingsLayout.toggle(); // toggle expand and collapse

        if(alertSettingsLayout.isExpanded())
        {
            alertButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            alertButton.setImageResource(R.drawable.uparrow);
        }

        changeAlert.setChecked(binItems.isItemAlert());

        alertStatus.setChecked(binItems.isStockAlert());
     changeAlert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

             Status status=new Status();
             status.setEnable(b);
             Call<ItemBinDetailsDTO> itemBinDetailsChange = new MontecitoClient().getClient().itemAlert( itemBinId,status,SessionInfo.getInstance().getUserLogin().getToken() );
             itemBinDetailsChange.enqueue(new Callback<ItemBinDetailsDTO>() {
                 @Override
                 public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response) {
                     if( response.isSuccessful() ) {
                         ItemBinDetailsDTO binItems = response.body();

                         Toast.makeText(context,"Your Item Alert is Changed Successfully",Toast.LENGTH_LONG).show();


                     }
                     else{

                     }
                 }

                 @Override
                 public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t) {

                 }
             });


         }
     });
     alertStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             Status status=new Status();
             status.setEnable(b);
             Call<ItemBinDetailsDTO> itemBinDetailsChange = new MontecitoClient().getClient().stockAlert( itemBinId,status,SessionInfo.getInstance().getUserLogin().getToken() );
             itemBinDetailsChange.enqueue(new Callback<ItemBinDetailsDTO>() {
                 @Override
                 public void onResponse(Call<ItemBinDetailsDTO> call, Response<ItemBinDetailsDTO> response) {
                     if( response.isSuccessful() ) {
                         ItemBinDetailsDTO binItems = response.body();

                         Toast.makeText(context,"Your Stock Alert Is Changed Successfully",Toast.LENGTH_LONG).show();

                     }
                     else{

                     }
                 }

                 @Override
                 public void onFailure(Call<ItemBinDetailsDTO> call, Throwable t) {

                 }
             });




         }
     });
        notificationAlert.setText(binItems.getThresold().getMin());
        calibrationFactor.setText("null");

    }

    public void replenishmentDetails(){
        ReplenishmentDetailsLayout = (ExpandableRelativeLayout) findViewById(R.id.ReplenishmentDetailsLayout);
        ReplenishmentDetailsLayout.toggle(); // toggle expand and collapse

        if(ReplenishmentDetailsLayout.isExpanded())
        {
            replenishmentDetailsButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            replenishmentDetailsButton.setImageResource(R.drawable.uparrow);
        }
    }

    public void replenishmentHistoryDetails(){
        ReplenishmentHistoryLayout = (ExpandableRelativeLayout) findViewById(R.id.ReplenishmentHistoryLayout);
        ReplenishmentHistoryLayout.toggle(); // toggle expand and collapse

        if(ReplenishmentHistoryLayout.isExpanded())
        {
            replenishmentHistoryButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            replenishmentHistoryButton.setImageResource(R.drawable.uparrow);
        }

    }
    public void cbinMovementDetails(){
        cbinMovementLayout = (ExpandableRelativeLayout) findViewById(R.id.cbinMovementLayout);
        cbinMovementLayout.toggle(); // toggle expand and collapse

        if(cbinMovementLayout.isExpanded())
        {
            cbinMovementButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            cbinMovementButton.setImageResource(R.drawable.uparrow);
        }

    }



}
