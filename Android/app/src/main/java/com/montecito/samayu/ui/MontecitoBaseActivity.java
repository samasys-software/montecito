package com.montecito.samayu.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.montecito.samayu.service.SessionInfo;
import com.prodcast.samayu.samayusoftcorp.R;



import java.io.File;

public abstract class MontecitoBaseActivity extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




    }

    @Override
    public void setContentView(int layoutId){

        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_montecito_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutId, activityContainer, true);

        initializeDrawer(fullView);


             super.setContentView(fullView);
    }

    protected void initializeDrawer(LinearLayout linearLayout){

        Toolbar toolbar = (Toolbar) linearLayout.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionMenuView actionMenuView = (ActionMenuView)linearLayout.findViewById(R.id.actionMenuView);
        actionMenuView.setOnMenuItemClickListener(this);



    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        final Intent intent;
        final Bundle b;
        if (id == R.id.nav_bar) {
            intent =new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.nav_chart) {
            intent =new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.nav_user) {
            intent =new Intent(this, Home.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            File dir =getFilesDir();
            File file = new File(dir, "MontecitoLogin.txt");

            boolean deleted = file.delete();
            SessionInfo.getInstance().destroy();

            intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }else if (id == R.id.nav_remainder) {
            intent = new Intent(this, Home.class);

            startActivity(intent);
        }
        return true;
    }
}