package com.montecito.samayu.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.prodcast.samayu.samayusoftcorp.R;

public class UserProfile extends MontecitoBaseActivity {
    ExpandableRelativeLayout changePasswordLayout;
    ImageButton changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        changeButton=findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });
    }
    public void changePassword() {
        changePasswordLayout = (ExpandableRelativeLayout) findViewById(R.id.ChangePasswordDetailsLayout);
        changePasswordLayout.toggle(); // toggle expand and collapse

        if(changePasswordLayout.isExpanded())
        {
            changeButton.setImageResource(R.drawable.downarrow);
        }
        else
        {
            changeButton.setImageResource(R.drawable.uparrow);
        }
    }
}
