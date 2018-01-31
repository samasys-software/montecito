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

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.prodcast.samayu.samayusoftcorp.R;


public class ItemBinDetails extends MontecitoBaseActivity {
    ExpandableRelativeLayout expandableLayout1,itemDetailsLayout,alertSettingsLayout;
    ImageButton binButton,itemButton,alertButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_bin_details);
        binButton=findViewById(R.id.binButton);
        itemButton=findViewById(R.id.itemButton);
        alertButton=findViewById(R.id.alertButton);
        binButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BinDetails(view);

            }
        });
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemDetails(view);

            }
        });
        alertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AlertDetails(view);

            }
        });

    }
    public void BinDetails(View view) {
        expandableLayout1 = (ExpandableRelativeLayout) findViewById(R.id.BinDetailsLayout);
        expandableLayout1.toggle(); // toggle expand and collapse
       if(expandableLayout1.isExpanded())
       {
           binButton.setImageResource(R.drawable.downarrow);
       }
       else
       {
           binButton.setImageResource(R.drawable.uparrow);
       }
    }

    public void ItemDetails(View view) {
        itemDetailsLayout = (ExpandableRelativeLayout) findViewById(R.id.ItemDetailsLayout);
        itemDetailsLayout.toggle(); // toggle expand and collapse
        if(itemDetailsLayout.isExpanded())
        {
            itemButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            itemButton.setImageResource(R.drawable.uparrow);
        }
    }

    public void AlertDetails(View view) {
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
