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
            SessionInfo.getInstance().setRegisterDeviceToken(refreshedToken);

           // sendRegistrationToServer(refreshedToken);

    }


}