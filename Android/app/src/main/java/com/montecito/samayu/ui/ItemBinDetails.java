package com.montecito.samayu.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.montecito.samayu.dto.ItemBinDTO;
import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;


public class ItemBinDetails extends MontecitoBaseActivity {
    ExpandableRelativeLayout expandableLayout1,itemDetailsLayout,alertSettingsLayout;
    ImageButton binButton,itemButton,alertButton;
    ItemBinDTO binItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bin_details);
        binButton=findViewById(R.id.binButton);
        itemButton=findViewById(R.id.itemButton);
        alertButton=findViewById(R.id.alertButton);
        binItems= SessionInfo.getInstance().getCurrentItem();
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


    }
    public void binDetails (){
        binDetails(true);
    }
    public void binDetails(boolean toggle) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.BinDetailsLayout);
        if(toggle) expandableLayout1.toggle(); // toggle expand and collapse
        //RelativeLayout relativeLayout=(RelativeLayout) expandableLayout1.findViewById(R.id.bin);
        TextView binName=(TextView) findViewById(R.id.binName);
        TextView binLocation=(TextView) findViewById(R.id.binLocation);
        TextView binType=(TextView) findViewById(R.id.binType);
        TextView binDimension=(TextView) findViewById(R.id.binDimension);
        TextView cBinIdentity=(TextView) findViewById(R.id.cBinIdentity);
        TextView rfid=(TextView) findViewById(R.id.rfid);

        binName.setText(binItems.getCrateBin().getName());
        binLocation.setText(binItems.getCurrDevice().getLocation());
        binType.setText("-----");
        binDimension.setText("-----");
        rfid.setText("-----");
        cBinIdentity.setText(binItems.getCurrDevice().getName());



       if(expandableLayout1.isExpanded())
       {
           binButton.setImageResource(R.drawable.downarrow);
       }
       else
       {
           binButton.setImageResource(R.drawable.uparrow);
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

        itemName.setText(binItems.getItem().getName());
        material.setText(binItems.getItem().getMaterial());
        itemDimension.setText("-----");
        itemVolume.setText("------");
        units.setText(binItems.getItem().getUom());
        surface.setText("------");
        availability.setText("-----");

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
        alertSettingsLayout.toggle(); // toggle expand and collapse

        if(alertSettingsLayout.isExpanded())
        {
            alertButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            alertButton.setImageResource(R.drawable.uparrow);
        }
    }



}
