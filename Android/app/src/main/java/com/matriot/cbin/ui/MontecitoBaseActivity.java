package com.matriot.cbin.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.matriot.cbin.R;
import com.matriot.cbin.domain.PushNotification;
import com.matriot.cbin.dto.RegisterPushNotificationDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;




import java.io.File;
import java.io.ObjectInputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MontecitoBaseActivity extends AppCompatActivity implements ActionMenuView.OnMenuItemClickListener {

    private ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutId){
        LinearLayout fullView = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_montecito_base, null);
        context=this;
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
        getMenuInflater().inflate(R.menu.menu,actionMenuView.getMenu());
        actionMenuView.setOnMenuItemClickListener(this);
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int id = item.getItemId();
        final Intent intent;
        final Bundle b;
      /*  if (id == R.id.nav_bar) {

        }
        else*/ if (id == R.id.nav_home) {
            intent = new Intent(this, Home.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_find) {
            intent =new Intent(this, BinMonitor.class);
            startActivity(intent);
        }else if (id == R.id.nav_chart) {
            intent=new Intent(this,ReportScreen.class);
            startActivity(intent);
        } else if (id == R.id.nav_user) {
            intent=new Intent(this,UserProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            if(isNetworkAvailable()) {
                PushNotification pushNotification=(PushNotification)loginRetrive("DeviceRegisterTokenFile.txt");
                Call<RegisterPushNotificationDTO> registerDeviceCall = new MontecitoClient().getClient().unRegisterDevice(SessionInfo.getInstance().getUserProfile().getId(), pushNotification, SessionInfo.getInstance().getUserLogin().getToken());
                registerDeviceCall.enqueue(new Callback<RegisterPushNotificationDTO>() {
                    @Override
                    public void onResponse(Call<RegisterPushNotificationDTO> call, Response<RegisterPushNotificationDTO> response) {
                        if (response.code() == 200) {
                            RegisterPushNotificationDTO registerPushNotificationDTO = response.body();
                            Toast.makeText(MontecitoBaseActivity.this,"Registration Status : "+registerPushNotificationDTO.getStatus(),Toast.LENGTH_LONG).show();
                        } else if (response.code() == 401 || response.code() == 403) {
                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterPushNotificationDTO> call, Throwable t) {

                    }
                });
            }
            File dir =getFilesDir();
            File file = new File(dir, "MontecitoLogin.txt");

            boolean deleted = file.delete();
            SessionInfo.getInstance().destroy();


            File file1 = new File(dir, "MontecitoLoginDetails.txt");

            boolean deleted1 = file1.delete();


            intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }/*else if (id == R.id.nav_remainder) {
        }*/
        }
        return true;
    }

    public ProgressDialog getProgressDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("One Moment Please");
        //p.show();
        return progressDialog;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
    private Object loginRetrive(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(openFileInput(fileName));
            Object r = (Object) ois.readObject();
            return r;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}