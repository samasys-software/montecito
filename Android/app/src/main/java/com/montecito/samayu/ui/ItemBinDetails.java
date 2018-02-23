package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.dto.ItemBinDetailsDTO;
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


    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bin_details);
        binButton=findViewById(R.id.binButton);
        itemButton=findViewById(R.id.itemButton);
        alertButton=findViewById(R.id.alertButton);
        replenishmentDetailsButton=findViewById(R.id.ReplenishmentDetailsButton);
        replenishmentHistoryButton=findViewById(R.id.ReplenishmentHistoryButton);
        cbinMovementButton=findViewById(R.id.cbinMovementButton);

        String itemBinId=SessionInfo.getInstance().getCurrentItemBinId();

      final Call<ItemBinDetailsDTO> itemBinDetails=new MontecitoClient().getClient().getItemBinDetails(itemBinId,SessionInfo.getInstance().getUserLogin().getToken());
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
        binDetails(false);
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
        TextView binName=(TextView) findViewById(R.id.binName);
        TextView binLocation=(TextView) findViewById(R.id.binLocation);
        TextView binType=(TextView) findViewById(R.id.binType);
        TextView binDimension=(TextView) findViewById(R.id.binDimension);
        TextView cBinIdentity=(TextView) findViewById(R.id.cBinIdentity);
        TextView rfid=(TextView) findViewById(R.id.rfid);
        if(binItems!=null) {

            binName.setText(binItems.getCrateBin().getName());
            binLocation.setText("null");
            binType.setText("null");
            binDimension.setText("null");
            rfid.setText(binItems.getRfId());
            cBinIdentity.setText("null");
        }



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
        if(binItems!=null) {


            itemName.setText(binItems.getItem().getName());
            material.setText(binItems.getItem().getMaterial());
            units.setText(binItems.getItem().getUom());
            itemDimension.setText("null");
            itemVolume.setText("null");
            surface.setText("null");
            availability.setText("null");
        }
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
        if(binItems.isItemAlert()==true){
            changeAlert.setChecked(true);
        }
        else{
            changeAlert.setChecked(false);
        }
       if(binItems.isStockAlert()==true){
           alertStatus.setChecked(true);
       }
       else{
           alertStatus.setChecked(false);
       }
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
