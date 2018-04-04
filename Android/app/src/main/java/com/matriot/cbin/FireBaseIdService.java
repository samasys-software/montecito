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

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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
            PushNotification pushNotificationDTO=new PushNotification();
            pushNotificationDTO.setToken(refreshedToken);
        loginToFile(pushNotificationDTO,"DeviceRegisterTokenFile.txt");


           // sendRegistrationToServer(refreshedToken);

    }

    public void loginToFile(Object details,String fileName) {

        File file = new File(getFilesDir(), fileName);
        file.delete();

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName, FireBaseIdService.this.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject(details);
            outputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}