package com.matriot.cbin;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.matriot.cbin.domain.PushNotification;
import com.matriot.cbin.dto.LoginDTO;
import com.matriot.cbin.dto.RegisterPushNotificationDTO;
import com.matriot.cbin.dto.UserLoginDTO;
import com.matriot.cbin.service.MontecitoClient;
import com.matriot.cbin.service.SessionInfo;
import com.matriot.cbin.ui.Home;
import com.matriot.cbin.ui.LoginScreen;
import com.matriot.cbin.ui.MontecitoBaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by NandhiniGovindasamy on 3/28/18.
 */

public class FireBaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
            Log.v("FirebaseIDService", "Refreshed token: " + refreshedToken);

            sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String token) {
        PushNotification pushNotification=new PushNotification();
        pushNotification.setToken(token);
        System.out.println("Token"+token);
        System.out.println("Token"+SessionInfo.getInstance().getUserLogin().getToken());
        if(isNetworkAvailable()) {
            Call<RegisterPushNotificationDTO> registerDeviceCall = new MontecitoClient().getClient().registerDevice("5a92959989e0a52ee4541ebc", pushNotification, SessionInfo.getInstance().getUserLogin().getToken());
            registerDeviceCall.enqueue(new Callback<RegisterPushNotificationDTO>() {
                @Override
                public void onResponse(Call<RegisterPushNotificationDTO> call, Response<RegisterPushNotificationDTO> response) {
                    if (response.code() == 200) {
                        RegisterPushNotificationDTO registerPushNotificationDTO = response.body();
                        Log.d("Registration Status :",registerPushNotificationDTO.getStatus());

                    } else if (response.code() == 401 || response.code() == 403) {



                    } else {

                    }

                }

                @Override
                public void onFailure(Call<RegisterPushNotificationDTO> call, Throwable t) {

                }
            });
        }

    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}